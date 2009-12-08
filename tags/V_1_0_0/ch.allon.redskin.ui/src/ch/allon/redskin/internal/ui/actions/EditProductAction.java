package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.ui.custom.EObjectDialog;

public class EditProductAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.isEmpty())
			return;

		final EObjectDialog dialog = new EObjectDialog(getShell(),
				"Produkt bearbeiten") {

			@Override
			protected List<EObject> getChilds(EObject object,
					EReference reference) {
				if (object.eClass().getClassifierID() == ShopPackage.PRODUCT
						&& reference.getFeatureID() == ShopPackage.PRODUCT__PRICE_CATEGORY) {
					return DBFactory.getPriceCategoryResource().getContents();
				}
				return null;
			}
		};
		dialog.setNewObject(selectedObjects.get(0));
		dialog.open();
	}

}
