package ch.allon.redskin.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.derby.drda.NetworkServerControl;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.eclipse.emf.teneo.hibernate.resource.HibernateResource;
import org.eclipse.emf.teneo.hibernate.resource.SessionController;
import org.hibernate.cfg.Environment;

import ch.allon.redskin.core.model.shop.ShopPackage;

public class HibernateBaseTestCase extends TestCase {

	private SessionController sessionController;
	private Resource PRICE_CATEGORIES_RESOURCE;
	private Resource ORDERS_RESOURCE;
	private Resource CUSTOMERS_RESOURCE;
	private Resource PRODUCTS_RESOURCE;

	private ResourceSetImpl resourceSet = new ResourceSetImpl();

	public Resource getPriceCategoryResource() {
		return PRICE_CATEGORIES_RESOURCE;
	}

	public Resource getProductsResource() {
		return PRODUCTS_RESOURCE;
	}

	public Resource getOrdersResource() {
		return ORDERS_RESOURCE;
	}

	public Resource getCustomerResource() {
		return CUSTOMERS_RESOURCE;
	}

	public SessionController getSessionController() {
		return sessionController;
	}

	public synchronized void saveResources() throws IOException {
		getSessionController().getSessionWrapper().beginTransaction();
		PRICE_CATEGORIES_RESOURCE.save(Collections.EMPTY_MAP);
		PRODUCTS_RESOURCE.save(Collections.EMPTY_MAP);
		CUSTOMERS_RESOURCE.save(Collections.EMPTY_MAP);
		ORDERS_RESOURCE.save(Collections.EMPTY_MAP);
		getSessionController().getSessionWrapper().commitTransaction();
	}

	protected void createResources() throws IOException {
		getSessionController().getSessionWrapper().beginTransaction();
		PRICE_CATEGORIES_RESOURCE = resourceSet.createResource(URI
				.createURI("hibernate://?"
						+ HibernateResource.SESSION_CONTROLLER_PARAM
						+ "=ShopDB&query1=FROM PriceCategory"));
		PRICE_CATEGORIES_RESOURCE.load(Collections.EMPTY_MAP);
		PRODUCTS_RESOURCE = resourceSet
				.createResource(URI
						.createURI("hibernate://?"
								+ HibernateResource.SESSION_CONTROLLER_PARAM
								+ "=ShopDB&query1=FROM ProductCategory where parent=null"));
		PRODUCTS_RESOURCE.load(Collections.EMPTY_MAP);
		CUSTOMERS_RESOURCE = resourceSet.createResource(URI
				.createURI("hibernate://?"
						+ HibernateResource.SESSION_CONTROLLER_PARAM
						+ "=ShopDB&query1=FROM Customer"));
		CUSTOMERS_RESOURCE.load(Collections.EMPTY_MAP);
		ORDERS_RESOURCE = (HibernateResource) resourceSet.createResource(URI
				.createURI("hibernate://?"
						+ HibernateResource.SESSION_CONTROLLER_PARAM
						+ "=ShopDB&query1=FROM Order where number < '0'"));
		ORDERS_RESOURCE.load(Collections.EMPTY_MAP);
		getSessionController().getSessionWrapper().commitTransaction();
	}

	protected void setUp() throws Exception {
		URL entry = Platform.getBundle("ch.allon.redskin.test").getEntry(
				"ShopDB");
		if (entry != null)
			deleteDirectory(new File(FileLocator.toFileURL(entry).getFile()));

		NetworkServerControl server = new NetworkServerControl();
		server.start(null);

		Properties props = new Properties();
		props.setProperty(Environment.DRIVER,
				"org.apache.derby.jdbc.ClientDriver");
		props.setProperty(Environment.URL,
				"jdbc:derby://localhost:1527/ShopDB;create=true");
		props.setProperty(Environment.DIALECT,
				org.hibernate.dialect.DerbyDialect.class.getName());
		props.setProperty(Environment.SHOW_SQL, "true");
		props.setProperty(PersistenceOptions.CASCADE_POLICY_ON_CONTAINMENT,
				"REMOVE,REFRESH,PERSIST,MERGE");
		HbDataStore hbds = (HbDataStore) HbHelper.INSTANCE
				.createRegisterDataStore("ShopDB");
		hbds.setProperties(props);
		hbds.setEPackages(new EPackage[] { ShopPackage.eINSTANCE });
		hbds.initialize();

		sessionController = new SessionController();
		sessionController.setHbDataStore(hbds);
		SessionController
				.registerSessionController("ShopDB", sessionController);

		createResources();
	}

	protected void tearDown() throws Exception {
		if (sessionController != null)
			sessionController.getSessionWrapper().close();
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
