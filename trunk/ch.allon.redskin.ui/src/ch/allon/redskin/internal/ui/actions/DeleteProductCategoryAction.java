package ch.allon.redskin.internal.ui.actions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.UIUtil;

public class DeleteProductCategoryAction extends EObjectAction {

	@Override
	protected void run(final List<EObject> selectedObjects) {
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				for (EObject obj : selectedObjects) {
					if (obj instanceof ProductCategory) {
						ProductCategory p = (ProductCategory) obj;
						if (p.getParent() == null)
							DBFactory.deleteFromResource(Arrays.asList(obj));
						else {
							p.getParent().getSubCategorys().remove(p);
						}
					}
				}
				return Status.OK_STATUS;
			}
		});
	}

}
