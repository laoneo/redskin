package ch.allon.redskin.test;

import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;

public class PerformanceTestCase extends HibernateBaseTestCase {

	public void testPerformance() throws Exception {
		ProductCategory root = ShopFactory.eINSTANCE.createProductCategory();
		root.setName("root");
		getProductsResource().getContents().add(root);
		saveResources();
		for (int i = 0; i < 100; i++) {
			ProductCategory tmp = ShopFactory.eINSTANCE.createProductCategory();
			tmp.setName("test cat " + i);
			for (int j = 0; j < 100; j++) {
				Product product = ShopFactory.eINSTANCE.createProduct();
				product.setName("test prod " + j);
				product.setNumber(j);
				tmp.getProducts().add(product);
				saveResources();
			}
			root.getSubCategorys().add(tmp);
			saveResources();
		}
	}
}
