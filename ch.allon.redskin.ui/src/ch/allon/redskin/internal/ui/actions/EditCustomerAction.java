package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.internal.ui.custom.CustomerDialog;

public class EditCustomerAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.isEmpty())
			return;

		CustomerDialog dialog = new CustomerDialog(getShell(),
				"Kunde bearbeiten");
		dialog.setNewObject(selectedObjects.get(0));
		dialog.open();
	}

}
