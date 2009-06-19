package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.jface.dialogs.Dialog;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.RedskinUIActivator;
import ch.allon.redskin.internal.ui.custom.EObjectDialog;

public class NewProductCategoryAction extends EObjectAction {

	public NewProductCategoryAction() {
		super();
		setText(Messages.NewCategoryAction_Titel);
		setImageDescriptor(RedskinUIActivator
				.getImageDescriptor("icons/actions/new_product_category.gif")); //$NON-NLS-1$
	}

	@Override
	protected void run(List<EObject> selectedObjects) {
		ProductCategory parent = DBFactory.getProductsRootNode();
		if (!selectedObjects.isEmpty())
			parent = (ProductCategory) selectedObjects.get(0);

		EObjectDialog dialog = new EObjectDialog(getShell(), null);
		dialog.setNewObject(ShopFactory.eINSTANCE.createProductCategory());
		dialog.open();
		if (dialog.getReturnCode() == Dialog.CANCEL)
			return;
		Command command = CreateChildCommand.create(getEditingDomain(), parent,
				new CommandParameter(parent,
						ShopPackage.Literals.PRODUCT_CATEGORY__SUB_CATEGORYS,
						dialog.getNewObject()), selectedObjects);
		getEditingDomain().getCommandStack().execute(command);
	}
}