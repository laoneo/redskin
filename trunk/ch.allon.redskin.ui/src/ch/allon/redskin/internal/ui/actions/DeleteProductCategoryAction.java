package ch.allon.redskin.internal.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ProductCategory;

public class DeleteProductCategoryAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.size() < 1)
			return;
		for (EObject obj : selectedObjects) {
			if (obj instanceof ProductCategory) {
				ProductCategory p = (ProductCategory) obj;
				if (p.getParent() == null)
					DBFactory.getProductsResource().getContents().remove(p);
				else
					p.getParent().getSubCategorys().remove(p);
			}
		}
	}

}
