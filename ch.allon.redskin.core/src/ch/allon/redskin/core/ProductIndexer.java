/**
 * The Redskin Shop Application is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Redskin Shop Application is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Redskin Shop Application.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Allon Moritz
 * @copyright 2007-2009 Allon Moritz
 * @version 0.1.0
 */
package ch.allon.redskin.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;

import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopPackage;

/**
 * @author Allon Moritz
 * 
 */
public class ProductIndexer {

	private static Map<Integer, Product> productIndex;

	static {
		DBFactory.getProductsRootNode().eAdapters().add(new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				productIndex = null;
				super.notifyChanged(notification);
			}
		});
	}

	public static Product getProduct(Integer number) {
		return getIndex().get(number);
	}

	public static void visit(IProductVisitor visitor) {
		Collection<Product> products = getIndex().values();

		for (Iterator<Product> iterator = products.iterator(); iterator
				.hasNext();) {
			Product p = iterator.next();
			if (!visitor.visit(p))
				return;
		}
	}

	/**
	 * @return
	 */
	private static Map<Integer, Product> getIndex() {
		if (productIndex == null) {
			productIndex = new HashMap<Integer, Product>();
			ProductCategory root = DBFactory.getProductsRootNode();
			root.eAdapters().add(new EContentAdapter() {
				@Override
				public void notifyChanged(Notification notification) {
					productIndex = null;
					super.notifyChanged(notification);
				}
			});
			for (Iterator<EObject> iterator = root.eAllContents(); iterator
					.hasNext();) {
				EObject eObject = iterator.next();
				if (eObject.eClass().getClassifierID() == ShopPackage.PRODUCT) {
					final Product p = (Product) eObject;
					p.eAdapters().add(new EContentAdapter() {
						@Override
						public void notifyChanged(Notification notification) {
							if (notification.getFeature() != null
									&& ((EStructuralFeature) notification
											.getFeature()).getFeatureID() == ShopPackage.PRODUCT__NUMBER) {
								productIndex.remove(notification.getOldValue());
								productIndex.put((Integer) notification
										.getNewValue(), p);
							}
							super.notifyChanged(notification);
						}
					});
					productIndex.put(p.getNumber(), p);
				}
			}
		}
		return productIndex;
	}
}
