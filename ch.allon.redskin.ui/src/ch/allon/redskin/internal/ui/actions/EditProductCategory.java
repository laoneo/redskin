package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.internal.ui.custom.EObjectDialog;

public class EditProductCategory extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.isEmpty())
			return;

		EObjectDialog dialog = new EObjectDialog(getShell(),
				"Produkt bearbeiten");
		dialog.setNewObject(selectedObjects.get(0));
		dialog.open();
	}

}
