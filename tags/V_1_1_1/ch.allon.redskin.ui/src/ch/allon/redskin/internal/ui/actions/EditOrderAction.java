package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Point;

import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.custom.EObjectDialog;

public class EditOrderAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.isEmpty())
			return;

		EObjectDialog dialog = new EObjectDialog(getShell(),
				Messages.EditOrderAction_Title) {

			@Override
			protected boolean isFieldEditable(EAttribute attribute) {
				if (attribute.getFeatureID() == ShopPackage.ORDER__NUMBER)
					return false;
				return super.isFieldEditable(attribute);
			}

			@Override
			protected Point getInitialSize() {
				return new Point(400, 200);
			}
		};
		Order order = (Order) selectedObjects.get(0);
		order.getComments();
		dialog.setNewObject(order);
		dialog.open();
	}

}
