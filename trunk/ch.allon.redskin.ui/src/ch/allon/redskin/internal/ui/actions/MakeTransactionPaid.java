package ch.allon.redskin.internal.ui.actions;

import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.core.model.shop.Transaction;

public class MakeTransactionPaid extends EObjectAction {

	@Override
	protected IStatus runInModelThread(List<EObject> selectedObjects) {
		for (EObject obj : selectedObjects) {
			if (obj.eClass().getClassifierID() == ShopPackage.TRANSACTION) {
				((Transaction) obj).setPaidDate(new Date());
			}
		}
		return Status.OK_STATUS;
	}
}
