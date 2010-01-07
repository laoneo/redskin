package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.custom.EObjectDialog;

public class EditProductCategoryAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.isEmpty())
			return;

		EObjectDialog dialog = new EObjectDialog(getShell(),
				Messages.EditProductCategoryAction_Dialog_Title);
		dialog.setNewObject(selectedObjects.get(0));
		dialog.open();
	}

}
