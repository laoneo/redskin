package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.RedskinUIActivator;

public class NewOrderAction extends EObjectAction {

	public NewOrderAction() {
		super();
		setText(Messages.NewOrderAction_Title);
		setImageDescriptor(RedskinUIActivator
				.getImageDescriptor("icons/actions/new_order.gif")); //$NON-NLS-1$
	}

	@Override
	protected void run(List<EObject> selectedObjects) {
		try {
			IViewReference[] references = RedskinUIActivator.getWindow()
					.getActivePage().getViewReferences();
			String secondId = null;
			for (IViewReference reference : references) {
				if (reference.getId().equals(RedskinUIActivator.ID_WORK_VIEW))
					secondId = RedskinUIActivator.ID_WORK_VIEW
							+ System.currentTimeMillis();
			}
			RedskinUIActivator.getWindow().getActivePage().showView(
					RedskinUIActivator.ID_WORK_VIEW, secondId,
					IWorkbenchPage.VIEW_ACTIVATE);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
