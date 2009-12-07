package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Point;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.custom.EObjectDialog;

public class EditOrderAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.isEmpty())
			return;

		EObjectDialog dialog = new EObjectDialog(getShell(),
				"Auftrag bearbeiten") {

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
		dialog.setNewObject(selectedObjects.get(0));
		if (dialog.open() == Dialog.CANCEL)
			return;
		final EObject object = dialog.getNewObject();
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				DBFactory.save(object);
				return Status.OK_STATUS;
			}
		});
	}

}
