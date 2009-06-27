package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.core.DBFactory;

public class DeleteCustomerAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.size() < 1)
			return;
		DBFactory.getCustomerResource().getContents().removeAll(selectedObjects);
	}

}
