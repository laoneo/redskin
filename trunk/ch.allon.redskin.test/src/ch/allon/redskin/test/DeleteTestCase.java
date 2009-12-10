package ch.allon.redskin.test;

import ch.allon.redskin.core.model.shop.PriceCategory;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;

public class DeleteTestCase extends HibernateBaseTestCase {

	public void testDelete() {
		PriceCategory category = ShopFactory.eINSTANCE.createPriceCategory();
		category.setName("test");
		category.getPrices().add(1.0);
		getPriceCategoryResource().getContents().add(category);

		Product product = ShopFactory.eINSTANCE.createProduct();
		product.setName("testProduct");
		product.setNumber(1);
		product.setPriceCategory(category);
		getProductsResource().getContents().add(product);

		ProductCategory root = ShopFactory.eINSTANCE.createProductCategory();
		root.setName("root");
		getProductsResource().getContents().add(root);

		ProductCategory node1 = ShopFactory.eINSTANCE.createProductCategory();
		node1.setName("node1");
		root.getSubCategorys().add(node1);

		root.getSubCategorys().remove(node1);

		ProductCategory node2 = ShopFactory.eINSTANCE.createProductCategory();
		node2.setName("node2");
		root.getSubCategorys().add(node2);

	}
}
