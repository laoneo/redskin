/**
 * 
 */
package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.UIUtil;

public class DeleteTransactionAction extends EObjectAction {

	@Override
	protected void run(final List<EObject> selectedObjects) {
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				for (EObject object : selectedObjects) {
					EcoreUtil.delete(object);
				}
				return Status.OK_STATUS;
			}
		});
	}

}
