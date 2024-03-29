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

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.teneo.hibernate.resource.HibernateResource;

import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.core.RedskinCoreActivator;

/**
 * @author Allon Moritz
 * 
 */
public class DBFactory {

	private static Resource PRICE_CATEGORIES_RESOURCE;
	private static Resource CUSTOMERS_RESOURCE;
	private static Resource PRODUCTS_RESOURCE;
	private static HibernateResource ORDERS_RESOURCE;

	private static ResourceSetImpl resourceSet = new ResourceSetImpl();

	static {
		createResources();
	}

	public static Resource getPriceCategoryResource() {
		return PRICE_CATEGORIES_RESOURCE;
	}

	public static Resource getProductsResource() {
		return PRODUCTS_RESOURCE;
	}

	public static Resource getCustomerResource() {
		return CUSTOMERS_RESOURCE;
	}

	public static Resource getOrdersResource() {
		return ORDERS_RESOURCE;
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
			CUSTOMERS_RESOURCE = resourceSet.createResource(URI
					.createURI("hibernate://?"
							+ HibernateResource.SESSION_CONTROLLER_PARAM
							+ "=ShopDB&query1=FROM Customer"));
			CUSTOMERS_RESOURCE.load(Collections.EMPTY_MAP);
			ORDERS_RESOURCE = (HibernateResource) resourceSet
					.createResource(URI.createURI("hibernate://?"
							+ HibernateResource.SESSION_CONTROLLER_PARAM
							+ "=ShopDB&query1=FROM Order where number < '0'"));
			ORDERS_RESOURCE.load(Collections.EMPTY_MAP);
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.commitTransaction();
		} catch (Exception e) {
			RedskinCore.handleException(e);
		}
	}

	public static Order findOrder(String number) {
		for (EObject obj : ORDERS_RESOURCE.getContents()) {
			if (obj.eClass().getClassifierID() == ShopPackage.ORDER
					&& ((Order) obj).getNumber().equals(number))
				return (Order) obj;
		}
		Object[] objects = ORDERS_RESOURCE.getObjectsByQuery(
				"FROM Order where number = '" + number + "'", false);
		if (objects.length < 1)
			return null;
		return (Order) objects[0];
	}

	public static Object[] computeOrders(Date from, Date to, String text,
			boolean nonPaid, boolean showAll) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("select distinct o from Order o ");
		if (!showAll) {
			hqlQuery.append("join o.transactions t where ");
			hqlQuery.append("t.endDate between timestamp('"
					+ format.format(from) + " 00:00:00') and timestamp('"
					+ format.format(to) + " 23:59:59') ");
			if (nonPaid)
				hqlQuery.append("and t.paidDate is null ");
			if (text != null && text.length() > 0) {
				hqlQuery.append("and ( o.customer.surname like '%" + text
						+ "%' or ");
				hqlQuery.append("o.customer.familyName like '%" + text
						+ "%' or ");
				hqlQuery.append("o.customer.address like '%" + text + "%' or ");
				hqlQuery.append("o.customer.hotel like '%" + text + "%')");
			}
		}
		return ((HibernateResource) getOrdersResource()).getObjectsByQuery(
				hqlQuery.toString(), false);
	}

	public static synchronized void saveResources() {
		try {
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.beginTransaction();
			PRICE_CATEGORIES_RESOURCE.save(Collections.EMPTY_MAP);
			PRODUCTS_RESOURCE.save(Collections.EMPTY_MAP);
			CUSTOMERS_RESOURCE.save(Collections.EMPTY_MAP);
			ORDERS_RESOURCE.save(Collections.EMPTY_MAP);
			RedskinCoreActivator.getSessionController().getSessionWrapper()
					.commitTransaction();
		} catch (Exception e) {
			RedskinCore.handleException(e);
		}
	}

}
