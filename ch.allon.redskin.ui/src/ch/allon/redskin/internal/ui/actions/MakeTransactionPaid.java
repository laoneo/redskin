package ch.allon.redskin.internal.ui.actions;

import java.util.Date;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.core.model.shop.Transaction;


public class MakeTransactionPaid extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.isEmpty())
			return;
		for (EObject obj : selectedObjects) {
			if (obj.eClass().getClassifierID() == ShopPackage.TRANSACTION) {
				((Transaction) obj).setPaidDate(new Date());
			}
		}
	}

}
