package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.custom.CustomerDialog;

public class NewCustomerAction extends EObjectAction {

	@Override
	public String getActionText() {
		return Messages.NewCustomerAction_Text;
	}

	@Override
	public String getActionImagePath() {
		return "icons/actions/new_customer.gif"; //$NON-NLS-1$
	}

	@Override
	protected void run(List<EObject> selectedObjects) {
		CustomerDialog dialog = new CustomerDialog(getShell(), Messages.NewCustomerAction_Dialog_Title);
		dialog.setNewObject(ShopFactory.eINSTANCE.createCustomer());
		if (dialog.open() == Dialog.CANCEL)
			return;
		final EObject object = dialog.getNewObject();
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				DBFactory.getCustomerResource().getContents().add(object);
				return Status.OK_STATUS;
			}
		});
	}
}
