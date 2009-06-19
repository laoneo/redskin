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
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "ch.allon.redskin.internal.ui.messages"; //$NON-NLS-1$
	public static String Application_Title;
	public static String NewCategoryAction_Titel;
	public static String NewOrderAction_Title;
	public static String NewPriceCategoryAction_Column_Day;
	public static String NewPriceCategoryAction_Column_Price;
	public static String NewPriceCategoryAction_New_Day_Action;
	public static String NewPriceCategoryAction_Price_For_More_Days;
	public static String WorkView_Correct_Number_1;
	public static String WorkView_Correct_Number_2;
	public static String WorkView_Days_Col;
	public static String WorkView_Days_To_Rent;
	public static String WorkView_Description_Col;
	public static String WorkView_Error_Title;
	public static String WorkView_From_Tomorrow;
	public static String WorkView_New_Order;
	public static String WorkView_Number;
	public static String WorkView_Number_Col;
	public static String WorkView_Price_Col;
	public static String WorkView_Register;
	public static String WorkView_Return_Col;
	public static String WorkView_Save;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
