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
package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ShopFactory;

/**
 * @author Allon Moritz
 * 
 */
public class NewPriceCategoryAction extends EObjectAction {

	@Override
	public String getActionText() {
		return "Neue Preis Kategorie";
	}

	@Override
	public String getActionImagePath() {
		return "icons/actions/new_price_category.gif";
	}

	@Override
	protected void run(List<EObject> selectedObjects) {
		PriceCategoryDialog dialog = new PriceCategoryDialog(getShell());
		dialog.setNewObject(ShopFactory.eINSTANCE.createPriceCategory());
		if (dialog.open() == Dialog.CANCEL)
			return;
		DBFactory.getPriceCategoryResource().getContents().add(
				dialog.getNewObject());
	}

}
