package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.dialogs.Dialog;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.custom.EObjectDialog;

public class NewProductAction extends EObjectAction {

	@Override
	protected void run(final List<EObject> selectedObjects) {
		if (selectedObjects.isEmpty())
			return;
		EObjectDialog dialog = new EObjectDialog(getShell(), Messages.NewProductAction_Title) {

			@Override
			protected List<EObject> getChilds(EObject object,
					EReference reference) {
				if (object.eClass().getClassifierID() == ShopPackage.PRODUCT
						&& reference.getFeatureID() == ShopPackage.PRODUCT__PRICE_CATEGORY) {
					return  DBFactory.getPriceCategoryResource().getContents();
				}
				return null;
			}
		};
		dialog.setNewObject(ShopFactory.eINSTANCE.createProduct());
		if (dialog.open() == Dialog.CANCEL)
			return;
		final Product object = (Product) dialog.getNewObject();
		UIUtil.runModelModificationJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				ProductCategory category = (ProductCategory) selectedObjects.get(0);
				category.getProducts().add(object);
				return Status.OK_STATUS;
			}
		});
	}
}