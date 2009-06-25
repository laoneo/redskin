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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.teneo.hibernate.resource.HibernateResource;

import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.internal.core.RedskinCoreActivator;

/**
 * @author Allon Moritz
 * 
 */
public class DBFactory {

	private static ProductCategory ROOT;
	private static Resource PRICE_CATEGORIES_RESOURCE;
	private static Resource ORDERS_RESOURCE;
	private static Resource CUSTOMERS_RESOURCE;

	public static synchronized Resource getPriceCategoryResource() {
		if (PRICE_CATEGORIES_RESOURCE == null) {
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.beginTransaction();
			String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM
					+ "=ShopDB&query1=FROM PriceCategory";
			URI uri = URI.createURI(uriStr);
			PRICE_CATEGORIES_RESOURCE = new ResourceSetImpl().getResource(uri,
					true);
			// res.load(Collections.EMPTY_MAP);
			PRICE_CATEGORIES_RESOURCE.eAdapters().add(new EContentAdapter() {
				private boolean innerCall = false;

				@Override
				public void notifyChanged(Notification notification) {
					if (innerCall)
						return;
					innerCall = true;
					try {
						PRICE_CATEGORIES_RESOURCE.save(Collections.EMPTY_MAP);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						innerCall = false;
					}
				}
			});
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.commitTransaction();
		}
		return PRICE_CATEGORIES_RESOURCE;
	}

	public static synchronized ProductCategory getProductsRootNode() {
		if (ROOT == null) {
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.beginTransaction();
			String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM
					+ "=ShopDB&query1=FROM ProductCategory";
			URI uri = URI.createURI(uriStr);
			final Resource res = new ResourceSetImpl().getResource(uri, true);
			// res.load(Collections.EMPTY_MAP);

			EList<EObject> contents = res.getContents();
			ProductCategory loadedRoot;
			// first time
			if (contents.isEmpty()) {
				loadedRoot = ShopFactory.eINSTANCE.createProductCategory();
				loadedRoot.setName("root");
				res.getContents().add(loadedRoot);
				try {
					RedskinCoreActivator.getSessionController()
							.getSessionWrapper().beginTransaction();
					res.save(Collections.EMPTY_MAP);
					RedskinCoreActivator.getSessionController()
							.getSessionWrapper().commitTransaction();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else
				loadedRoot = (ProductCategory) contents.get(0);
			res.eAdapters().add(new EContentAdapter() {
				private boolean innerCall = false;

				@Override
				public void notifyChanged(Notification notification) {
					if (innerCall)
						return;
					innerCall = true;
					try {
						RedskinCoreActivator.getSessionController()
								.getSessionWrapper().beginTransaction();
						res.save(Collections.EMPTY_MAP);
						RedskinCoreActivator.getSessionController()
								.getSessionWrapper().commitTransaction();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						innerCall = false;
					}
					super.notifyChanged(notification);
				}
			});
			ROOT = loadedRoot;
		}
		return ROOT;
	}

	public static Resource getOrdersResource() {
		if (ORDERS_RESOURCE == null) {
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.beginTransaction();
			String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM
					+ "=ShopDB&query1=FROM Order";
			URI uri = URI.createURI(uriStr);
			ORDERS_RESOURCE = new ResourceSetImpl().getResource(uri, true);
			// res.load(Collections.EMPTY_MAP);
			ORDERS_RESOURCE.eAdapters().add(new EContentAdapter() {
				private boolean innerCall = false;

				@Override
				public void notifyChanged(Notification notification) {
					if (innerCall)
						return;
					innerCall = true;
					try {
						ORDERS_RESOURCE.save(Collections.EMPTY_MAP);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						innerCall = false;
					}
				}
			});
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.commitTransaction();
		}
		return ORDERS_RESOURCE;
	}

	public synchronized static Resource getCustomerResource() {
		if (CUSTOMERS_RESOURCE == null) {
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.beginTransaction();
			String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM
					+ "=ShopDB&query1=FROM Customer";
			URI uri = URI.createURI(uriStr);
			CUSTOMERS_RESOURCE = new ResourceSetImpl().getResource(uri, true);
			// res.load(Collections.EMPTY_MAP);
			CUSTOMERS_RESOURCE.eAdapters().add(new EContentAdapter() {
				private boolean innerCall = false;

				@Override
				public void notifyChanged(Notification notification) {
					if (innerCall)
						return;
					innerCall = true;
					try {
						CUSTOMERS_RESOURCE.save(Collections.EMPTY_MAP);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						innerCall = false;
					}
				}
			});
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.commitTransaction();
		}
		return CUSTOMERS_RESOURCE;
	}
}
