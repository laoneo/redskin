package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.internal.ui.custom.CustomerDialog;

public class NewCustomerAction extends EObjectAction {

	@Override
	public String getActionText() {
		return "Neuer Kunde";
	}

	@Override
	public String getActionImagePath() {
		return "icons/actions/new_customer.gif";
	}

	@Override
	protected void run(List<EObject> selectedObjects) {
		CustomerDialog dialog = new CustomerDialog(getShell(), "Neuer Kunde");
		dialog.setNewObject(ShopFactory.eINSTANCE.createCustomer());
		if (dialog.open() == Dialog.CANCEL)
			return;
		DBFactory.getCustomerResource().getContents()
				.add(dialog.getNewObject());
	}
}
