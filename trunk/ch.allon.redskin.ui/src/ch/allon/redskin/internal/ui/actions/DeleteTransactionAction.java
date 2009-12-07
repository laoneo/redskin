/**
 * 
 */
package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.core.model.shop.Transaction;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.UIUtil;

public class DeleteTransactionAction extends EObjectAction {

	@Override
	protected void run(final List<EObject> selectedObjects) {
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				for (EObject obj : selectedObjects) {
					if (obj instanceof Transaction) {
						Transaction t = (Transaction) obj;
						t.getOrder().getTransactions().remove(t);
					}
				}
				return Status.OK_STATUS;
			}
		});
	}

}
