package ch.allon.redskin.test;

import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;

public class SetParentTestCase extends HibernateBaseTestCase {

	public void testDragAndDrop() throws Exception {
		Product product = ShopFactory.eINSTANCE.createProduct();
		product.setName("testProduct");
		product.setNumber(1);

		ProductCategory root = ShopFactory.eINSTANCE.createProductCategory();
		root.setName("root");
		getProductsResource().getContents().add(root);
		saveResources();

		ProductCategory node1 = ShopFactory.eINSTANCE.createProductCategory();
		node1.setName("node1");
		root.getSubCategorys().add(node1);
		node1.getProducts().add(product);
		saveResources();

		ProductCategory node2 = ShopFactory.eINSTANCE.createProductCategory();
		node2.setName("node2");
		root.getSubCategorys().add(node2);
		saveResources();
		product.setProductCategory(node2);
		saveResources();
		assert (node2.getProducts().get(0) == product);
	}
}
