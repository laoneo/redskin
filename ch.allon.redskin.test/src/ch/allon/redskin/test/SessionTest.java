package ch.allon.redskin.test;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.derby.drda.NetworkServerControl;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.classic.Session;

import ch.allon.redskin.core.model.shop.PriceCategory;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.core.model.shop.ShopPackage;

public class SessionTest extends TestCase {

	private NetworkServerControl server;
	private Session SESSION;

	public void testDelete() {
		SESSION.beginTransaction();
		ProductCategory category = ShopFactory.eINSTANCE
				.createProductCategory();
		category.setName("Test cat");
		SESSION.save(category);

		PriceCategory pcat = ShopFactory.eINSTANCE.createPriceCategory();
		pcat.setName("11");
		pcat.getPrices().add(1.00);
		pcat.getPrices().add(2.00);
		pcat.getPrices().add(3.00);
		SESSION.save(pcat);

		Product product = ShopFactory.eINSTANCE.createProduct();
		product.setName("1");
		product.setNumber(1);
		product.setPriceCategory(pcat);
		product.setProductCategory(category);

		SESSION.save(product);
		SESSION.getTransaction().commit();
		
		SESSION.beginTransaction();
		Query query = SESSION.createQuery("FROM ProductCategory");
		ProductCategory cat = (ProductCategory) query.list().get(0);
		SESSION.delete(cat);
		SESSION.getTransaction().commit();
		
		SESSION.beginTransaction();
		query = SESSION.createQuery("FROM ProductCategory");
		assertTrue(query.list().isEmpty());
		
		query = SESSION.createQuery("FROM Product");
		assertTrue(query.list().isEmpty());
		
		query = SESSION.createQuery("FROM PriceCategory");
		assertTrue(!query.list().isEmpty());
		SESSION.getTransaction().commit();
	}

	public void testAdd() throws Exception {
		SESSION.beginTransaction();

		ProductCategory category = ShopFactory.eINSTANCE
				.createProductCategory();
		category.setName("Test cat");
		SESSION.save(category);

		PriceCategory pcat = ShopFactory.eINSTANCE.createPriceCategory();
		pcat.setName("1");
		pcat.getPrices().add(1.00);
		pcat.getPrices().add(2.00);
		pcat.getPrices().add(3.00);
		SESSION.save(pcat);

		Product product = ShopFactory.eINSTANCE.createProduct();
		product.setName("1");
		product.setNumber(1);
		product.setPriceCategory(pcat);
		product.setProductCategory(category);

		SESSION.save(product);
		SESSION.getTransaction().commit();

		SESSION.beginTransaction();
		
		Query query = SESSION.createQuery("FROM ProductCategory");
		ProductCategory cat = (ProductCategory) query.list().get(0);
		assert (cat == category);
		Product p = cat.getProducts().get(0);
		assert (p == product);
		
		SESSION.getTransaction().commit();
	}

	@Override
	protected void setUp() throws Exception {
		URL entry = Platform.getBundle("ch.allon.redskin.test").getEntry(
				"ShopDB");
		if (entry != null)
			deleteDirectory(new File(FileLocator.toFileURL(entry).getFile()));

		server = new NetworkServerControl();
		server.start(null);

		Properties props = new Properties();
		props.setProperty(Environment.DRIVER,
				"org.apache.derby.jdbc.ClientDriver");
		props.setProperty(Environment.URL,
				"jdbc:derby://localhost:1527/ShopDB;create=true");
		props.setProperty(Environment.DIALECT,
				org.hibernate.dialect.DerbyDialect.class.getName());
		props.setProperty(Environment.SHOW_SQL, "true");
		HbDataStore hbds = (HbDataStore) HbHelper.INSTANCE
				.createRegisterDataStore("ShopDB");
		hbds.setProperties(props);
		hbds.setEPackages(new EPackage[] { ShopPackage.eINSTANCE });
		hbds.initialize();

		final SessionFactory sessionFactory = hbds.getSessionFactory();

		// Open a new Session and start Transaction.
		SESSION = sessionFactory.openSession();
	}

	protected void tearDown() throws Exception {
		SESSION.close();
		server.shutdown();
	}

	static public boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}
}
