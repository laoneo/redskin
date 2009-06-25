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
package ch.allon.redskin.internal.ui;

import org.eclipse.osgi.util.NLS;

/**
 * @author Allon Moritz
 *
 */
public class FieldMessages extends NLS {
	private static final String BUNDLE_NAME = "ch.allon.redskin.internal.ui.fieldmessages"; //$NON-NLS-1$
	public static String PRODUCT__NUMBER;
	public static String PRODUCT__NAME;
	public static String PRODUCT__DESCRIPTION;
	public static String PRODUCT__RENTED_DAYS;
	public static String PRODUCT__PRICE_CATEGORY;
	
	public static String CUSTOMER__SURNAME;
	public static String CUSTOMER__FAMILY_NAME;
	public static String CUSTOMER__TELEPHONE_NR;
	public static String CUSTOMER__ADDRESS;
	public static String CUSTOMER__HOTEL;
	public static String CUSTOMER__COMMENTS;
	
	public static String PRODUCT_CATEGORY__NAME;
	

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, FieldMessages.class);
	}

	private FieldMessages() {
	}
}
