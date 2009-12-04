package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.IJobRunnable;
import ch.allon.redskin.internal.ui.UIUtil;

public class DeleteOrderAction extends EObjectAction {

	@Override
	protected void run(final List<EObject> selectedObjects) {
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				DBFactory.deleteFromResource(selectedObjects);
				return Status.OK_STATUS;
			}
		});
	}

}
