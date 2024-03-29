package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import ch.allon.redskin.core.RedskinCore;
import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.RedskinUIActivator;
import ch.allon.redskin.internal.ui.views.WorkView;

public class NewOrderAction extends EObjectAction {

	@Override
	public String getActionText() {
		return Messages.NewOrderAction_Title;
	}

	@Override
	public String getActionImagePath() {
		return "icons/actions/new_order.gif"; //$NON-NLS-1$
	}

	@Override
	protected void run(List<EObject> selectedObjects) {
		try {
			IPerspectiveDescriptor workPerspective = RedskinUIActivator
					.getWindow().getWorkbench().getPerspectiveRegistry()
					.findPerspectiveWithId(
							RedskinUIActivator.ID_WORK_PERSPECTIVE); //$NON-NLS-1$
			if (workPerspective != null) {
				RedskinUIActivator.getWindow().getActivePage().setPerspective(
						workPerspective);
			}
			IViewReference[] references = RedskinUIActivator.getWindow()
					.getActivePage().getViewReferences();
			String secondId = null;
			Order order = null;
			if (selectedObjects.size() > 0)
				order = (Order) selectedObjects.get(0);
			for (IViewReference reference : references) {
				if (reference.getId().equals(RedskinUIActivator.ID_WORK_VIEW)) {
					WorkView view = (WorkView) reference.getPart(true);
					if (view != null && view.getOrder() != null
							&& order != null && view.getOrder().equals(order))
						return;
					secondId = RedskinUIActivator.ID_WORK_VIEW
							+ System.currentTimeMillis();
				}
			}
			IViewPart part = RedskinUIActivator.getWindow().getActivePage()
					.showView(RedskinUIActivator.ID_WORK_VIEW, secondId,
							IWorkbenchPage.VIEW_ACTIVATE);
			if (part instanceof WorkView && selectedObjects.size() > 0)
				((WorkView) part).setOrder(order);
		} catch (PartInitException e) {
			RedskinCore.handleException(e);
		}
	}
}
