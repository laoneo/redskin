package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IViewReference;

import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.RedskinUIActivator;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.views.WorkView;

public class DeleteOrderAction extends EObjectAction {

	@Override
	protected void run(final List<EObject> selectedObjects) {
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				for (EObject object : selectedObjects) {
					EcoreUtil.delete(object);
				}
				UIUtil.getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						for (EObject eObject : selectedObjects) {
							IViewReference[] references = RedskinUIActivator
									.getWindow().getActivePage()
									.getViewReferences();
							for (IViewReference reference : references) {
								if (reference.getId().equals(
										RedskinUIActivator.ID_WORK_VIEW)) {
									WorkView view = (WorkView) reference
											.getPart(false);
									if (view != null
											&& view.getOrder() == eObject)
										RedskinUIActivator.getWindow()
												.getActivePage().hideView(view);
								}
							}
						}
					}
				});
				return Status.OK_STATUS;
			}
		});
	}

}
