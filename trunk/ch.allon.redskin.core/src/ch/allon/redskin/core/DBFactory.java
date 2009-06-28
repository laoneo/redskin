/**
 * The Shop is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Shop is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Shop.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Allon Moritz
 * @copyright 2007-2009 Allon Moritz
 * @version 0.1.0
 */
package ch.allon.redskin.core;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.teneo.hibernate.resource.HibernateResource;

import ch.allon.redskin.internal.core.RedskinCoreActivator;

/**
 * @author Allon Moritz
 * 
 */
public class DBFactory {

	private static Resource PRICE_CATEGORIES_RESOURCE;
	private static Resource ORDERS_RESOURCE;
	private static Resource CUSTOMERS_RESOURCE;
	private static Resource PRODUCTS_RESOURCE;

	public synchronized static Resource getPriceCategoryResource() {
		if (PRICE_CATEGORIES_RESOURCE == null) {
			PRICE_CATEGORIES_RESOURCE = createResource("PriceCategory");
		}
		return PRICE_CATEGORIES_RESOURCE;
	}

	public synchronized static Resource getProductsResource() {
		if (PRODUCTS_RESOURCE == null) {
			PRODUCTS_RESOURCE = createResource("ProductCategory where parent=null");
		}
		return PRODUCTS_RESOURCE;
	}

	public synchronized static Resource getOrdersResource() {
		if (ORDERS_RESOURCE == null) {
			ORDERS_RESOURCE = createResource("Order");
		}
		return ORDERS_RESOURCE;
	}

	public synchronized static Resource getCustomerResource() {
		if (CUSTOMERS_RESOURCE == null) {
			CUSTOMERS_RESOURCE = createResource("Customer");
		}
		return CUSTOMERS_RESOURCE;
	}

	private static Resource createResource(String type) {
		RedskinCoreActivator.getSessionController().getSessionWrapper()
				.beginTransaction();
		String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM
				+ "=ShopDB&query1=FROM " + type;
		URI uri = URI.createURI(uriStr);
		final Resource res = new ResourceSetImpl().getResource(uri, true);
		// res.load(Collections.EMPTY_MAP);
		res.eAdapters().add(new EContentAdapter() {
			private boolean innerCall = false;

			@Override
			public void notifyChanged(Notification notification) {
				if (innerCall)
					return;
				innerCall = true;
				try {
					res.save(Collections.EMPTY_MAP);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					innerCall = false;
				}
				super.notifyChanged(notification);
			}
		});
		RedskinCoreActivator.getSessionController().getSessionWrapper()
				.commitTransaction();
		return res;
	}
}
