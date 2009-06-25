package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.jface.dialogs.Dialog;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.ui.custom.EObjectDialog;
import ch.allon.redskin.internal.ui.custom.IDialogConfig;

public class NewProductAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		ProductCategory parent = DBFactory.getProductsRootNode();
		if (!selectedObjects.isEmpty())
			parent = (ProductCategory) selectedObjects.get(0);
		EObjectDialog dialog = new EObjectDialog(getShell(),
				new IDialogConfig() {

					@Override
					public List<EObject> getChilds(EObject object,
							EReference reference) {
						if (object.eClass().getClassifierID() == ShopPackage.PRODUCT
								&& reference.getFeatureID() == ShopPackage.PRODUCT__PRICE_CATEGORY) {
							return DBFactory.getPriceCategoryResource()
									.getContents();
						}
						return null;
					}
				});
		dialog.setNewObject(ShopFactory.eINSTANCE.createProduct());
		dialog.open();
		if (dialog.getReturnCode() == Dialog.CANCEL)
			return;
		Command command = CreateChildCommand.create(getEditingDomain(), parent,
				new CommandParameter(parent,
						ShopPackage.Literals.PRODUCT_CATEGORY__PRODUCTS, dialog
								.getNewObject()), selectedObjects);
		getEditingDomain().getCommandStack().execute(command);
	}
}