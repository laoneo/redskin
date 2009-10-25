/**
 * 
 */
package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.core.model.shop.Transaction;

public class DeleteTransactionAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.size() < 1)
			return;
		for (EObject obj : selectedObjects) {
			if (obj instanceof Transaction) {
				Transaction t = (Transaction) obj;
				t.getOrder().getTransactions().remove(t);
			}
		}
	}

}
