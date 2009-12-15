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
	public static String CustomerView_Label_Addr;
	public static String CustomerView_Label_Hotel;
	public static String CustomerView_Label_Name;
	public static String CustomerView_Label_Pre_Name;
	public static String CustomerView_Label_Tel;
	public static String EditCustomerAction_Title;
	public static String EditOrderAction_Title;
	public static String EditProductAction_Dialog_Title;
	public static String EditProductCategoryAction_Dialog_Title;
	public static String NewCategoryAction_Titel;
	public static String NewCustomerAction_Dialog_Title;
	public static String NewCustomerAction_Text;
	public static String NewOrderAction_Title;
	public static String NewPriceCategoryAction_Column_Day;
	public static String NewPriceCategoryAction_Column_Price;
	public static String NewPriceCategoryAction_New_Day_Action;
	public static String NewPriceCategoryAction_Price_For_More_Days;
	public static String NewPriceCategoryAction_Text;
	public static String NewProductAction_Title;
	public static String OrderListView_All;
	public static String OrderListView_From;
	public static String OrderListView_No_Customer_Label;
	public static String OrderListView_No;
	public static String OrderListView_Not_Paid;
	public static String OrderListView_Not_Paid_1;
	public static String OrderListView_Paid_Col;
	public static String OrderListView_Person;
	public static String OrderListView_To;
	public static String OrderListView_Today_Back;
	public static String OrderListView_Tomorrow_Back;
	public static String OrderListView_Yes;
	public static String PriceCategoryDialog_Title;
	public static String RedskinUIActivator_Error_Dialog_Title;
	public static String ShowOrderReportAction_Dialog_Title;
	public static String ShowOrderReportAction_Print_Dialog_Title;
	public static String WorkView_Comment_Dialog_Text;
	public static String WorkView_Comment_Dialog_Title;
	public static String WorkView_Correct_Number_1;
	public static String WorkView_Correct_Number_2;
	public static String WorkView_Customer_List_Dialog_Title;
	public static String WorkView_Days_Col;
	public static String WorkView_Days_To_Rent;
	public static String WorkView_Description_Col;
	public static String WorkView_Enter_Comment;
	public static String WorkView_Error_Title;
	public static String WorkView_From_Tomorrow;
	public static String WorkView_Mark_Order_Paid_Action;
	public static String WorkView_Name_Col;
	public static String WorkView_New_Customer_Action;
	public static String WorkView_New_Customer_Dialog_Title;
	public static String WorkView_Not_Paid_Label;
	public static String WorkView_Number;
	public static String WorkView_Number_Col;
	public static String WorkView_Number_Registered_Error_1;
	public static String WorkView_Number_Registered_Error_2;
	public static String WorkView_Paid_Col;
	public static String WorkView_Price_Col;
	public static String WorkView_Product_No_Price_Error;
	public static String WorkView_Register;
	public static String WorkView_Return_Col;
	public static String WorkView_Save;
	public static String WorkView_Search_Customer_Action;
	public static String WorkView_Show_Order_Action;
	public static String WorkView_Total_Value;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
