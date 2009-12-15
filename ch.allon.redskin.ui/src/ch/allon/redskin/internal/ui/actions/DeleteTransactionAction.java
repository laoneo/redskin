/**
 * 
 */
package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class DeleteTransactionAction extends EObjectAction {

	@Override
	protected IStatus runInModelThread(List<EObject> selectedObjects) {
		for (EObject object : selectedObjects) {
			EcoreUtil.delete(object);
		}
		return Status.OK_STATUS;
	}
}
