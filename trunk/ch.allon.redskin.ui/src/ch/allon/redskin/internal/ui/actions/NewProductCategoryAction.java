package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.custom.EObjectDialog;

public class NewProductCategoryAction extends EObjectAction {

	@Override
	public String getActionText() {
		return Messages.NewCategoryAction_Titel;
	}

	@Override
	public String getActionImagePath() {
		return "icons/actions/new_product_category.gif"; //$NON-NLS-1$
	}

	@Override
	protected void run(final List<EObject> selectedObjects) {
		EObjectDialog dialog = new EObjectDialog(getShell(), getActionText());
		dialog.setNewObject(ShopFactory.eINSTANCE.createProductCategory());
		dialog.open();
		if (dialog.getReturnCode() == Dialog.CANCEL)
			return;
		final ProductCategory object = (ProductCategory) dialog.getNewObject();
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				if (!selectedObjects.isEmpty()) {
					ProductCategory parent = (ProductCategory) selectedObjects.get(0);
					parent.getSubCategorys().add(object);
				} else {
					DBFactory.getProductsResource().getContents().add(object);
				}
				return Status.OK_STATUS;
			}
		});
	}
}