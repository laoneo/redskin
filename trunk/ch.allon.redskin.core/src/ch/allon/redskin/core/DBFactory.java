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

	private static EContentAdapter resourceSaver = new EContentAdapter() {
		private boolean innerCall = false;

		@Override
		public void notifyChanged(Notification notification) {
			if (innerCall)
				return;
			innerCall = true;
			try {
				RedskinCoreActivator.getSessionController().getSessionWrapper()
						.beginTransaction();
				PRICE_CATEGORIES_RESOURCE.save(Collections.EMPTY_MAP);
				PRODUCTS_RESOURCE.save(Collections.EMPTY_MAP);
				ORDERS_RESOURCE.save(Collections.EMPTY_MAP);
				CUSTOMERS_RESOURCE.save(Collections.EMPTY_MAP);
				RedskinCoreActivator.getSessionController().getSessionWrapper()
						.commitTransaction();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				innerCall = false;
			}
			super.notifyChanged(notification);
		}
	};

	private static Resource PRICE_CATEGORIES_RESOURCE;
	private static Resource ORDERS_RESOURCE;
	private static Resource CUSTOMERS_RESOURCE;
	private static Resource PRODUCTS_RESOURCE;

	private static ResourceSetImpl resourceSet = new ResourceSetImpl();

	static {
		createResources();
	}

	public synchronized static Resource getPriceCategoryResource() {
		return PRICE_CATEGORIES_RESOURCE;
	}

	public synchronized static Resource getProductsResource() {
		return PRODUCTS_RESOURCE;
	}

	public synchronized static Resource getOrdersResource() {
		return ORDERS_RESOURCE;
	}

	public synchronized static Resource getCustomerResource() {
		return CUSTOMERS_RESOURCE;
	}

	private static void createResources() {
		try {
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.beginTransaction();
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
			ORDERS_RESOURCE = resourceSet.createResource(URI
					.createURI("hibernate://?"
							+ HibernateResource.SESSION_CONTROLLER_PARAM
							+ "=ShopDB&query1=FROM Order"));
			ORDERS_RESOURCE.load(Collections.EMPTY_MAP);
			CUSTOMERS_RESOURCE = resourceSet.createResource(URI
					.createURI("hibernate://?"
							+ HibernateResource.SESSION_CONTROLLER_PARAM
							+ "=ShopDB&query1=FROM Customer"));
			CUSTOMERS_RESOURCE.load(Collections.EMPTY_MAP);
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.commitTransaction();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PRICE_CATEGORIES_RESOURCE.eAdapters().add(resourceSaver);
		PRODUCTS_RESOURCE.eAdapters().add(resourceSaver);
		ORDERS_RESOURCE.eAdapters().add(resourceSaver);
		CUSTOMERS_RESOURCE.eAdapters().add(resourceSaver);
	}
}
