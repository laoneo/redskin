package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.core.model.shop.Product;

public class DeleteProductAction extends EObjectAction {

	@Override
	protected IStatus runInModelThread(List<EObject> selectedObjects) {
		for (EObject obj : selectedObjects) {
			if (obj instanceof Product) {
				Product p = (Product) obj;
				p.getProductCategory().getProducts().remove(p);
			}
		}
		return Status.OK_STATUS;
	}

}
