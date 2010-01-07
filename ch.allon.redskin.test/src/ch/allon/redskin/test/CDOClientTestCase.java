package ch.allon.redskin.test;

import junit.framework.TestCase;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.net4j.CDOSessionConfiguration;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.util.container.IPluginContainer;

import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;

public class CDOClientTestCase extends TestCase {

	public void testSession() {
		CDOSession session = openSession();
		CDOTransaction transaction = session.openTransaction();
		CDOResource resource = transaction.getOrCreateResource("/res1");

		Product product = ShopFactory.eINSTANCE.createProduct();
		product.setName("testProduct");
		product.setNumber(1);

		ProductCategory root = ShopFactory.eINSTANCE.createProductCategory();
		root.setName("root");
		resource.getContents().add(root);

		ProductCategory node1 = ShopFactory.eINSTANCE.createProductCategory();
		node1.setName("node1");
		root.getSubCategorys().add(node1);
		node1.getProducts().add(product);

		ProductCategory node2 = ShopFactory.eINSTANCE.createProductCategory();
		node2.setName("node2");
		root.getSubCategorys().add(node2);

		// Modify that object
		// Commit atomically and release all locks
		transaction.commit();
		session.close();
	}

	public CDOSession openSession() {
		// Create TCP connector through wiring container
		IConnector connector = (IConnector) IPluginContainer.INSTANCE
				.getElement("org.eclipse.net4j.connectors", "tcp",
						"localhost:2036");
		// Create CDO session configuration
		CDOSessionConfiguration config = CDONet4jUtil
				.createSessionConfiguration();
		config.setConnector(connector);
		config.setRepositoryName("repo1");
		// config.setLazyPackageRegistry();
		// Open CDO session
		return config.openSession();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
