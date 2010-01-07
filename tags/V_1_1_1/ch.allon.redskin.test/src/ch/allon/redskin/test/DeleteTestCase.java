package ch.allon.redskin.test;

import ch.allon.redskin.core.model.shop.PriceCategory;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;

public class DeleteTestCase extends HibernateBaseTestCase {

	public void testDelete() throws Exception {
		PriceCategory category = ShopFactory.eINSTANCE.createPriceCategory();
		category.setName("test");
		category.getPrices().add(1.0);
		getPriceCategoryResource().getContents().add(category);
		saveResources();

		Product product = ShopFactory.eINSTANCE.createProduct();
		product.setName("testProduct");
		product.setNumber(1);
		product.setPriceCategory(category);

		ProductCategory root = ShopFactory.eINSTANCE.createProductCategory();
		root.setName("root");
		getProductsResource().getContents().add(root);
		saveResources();

		ProductCategory node1 = ShopFactory.eINSTANCE.createProductCategory();
		node1.setName("node1");
		node1.getProducts().add(product);
		root.getSubCategorys().add(node1);
		saveResources();

		root.getSubCategorys().remove(node1);
		saveResources();

		ProductCategory node2 = ShopFactory.eINSTANCE.createProductCategory();
		node2.setName("node2");
		root.getSubCategorys().add(node2);
		saveResources();

		root.getSubCategorys().remove(node1);
		root.getSubCategorys().remove(node2);
		saveResources();

		Object[] roots = getProductsResource().getObjectsByQuery(
				"FROM ProductCategory where parent=null", false);
		assert (roots.length > 0);
		assert (roots[0] == root);
		assert (((ProductCategory) roots[0]).getSubCategorys().size() == 0);
		Object[] products = getProductsResource().getObjectsByQuery(
				"FROM Product", false);
		assert (products.length == 0);
	}
}
