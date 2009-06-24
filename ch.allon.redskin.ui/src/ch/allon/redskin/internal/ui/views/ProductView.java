package ch.allon.redskin.internal.ui.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.internal.ui.actions.EObjectAction;
import ch.allon.redskin.internal.ui.actions.NewProductCategoryAction;

public class ProductView extends EObjectView {

	@Override
	protected Object createInput() {
		return DBFactory.getProductsRootNode();
	}

	@Override
	protected Viewer createViewer(Composite parent) {
		FilteredTree tree = new FilteredTree(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER, new PatternFilter(), true);
		return tree.getViewer();
	}

	@Override
	protected EObjectAction[] createToolBarActions() {
		return new EObjectAction[] { new NewProductCategoryAction() };
	}

	@Override
	protected EObjectAction[] createContextMenuActions(ISelection selection) {
		if (selection.isEmpty()) {
			return new EObjectAction[] { new NewProductCategoryAction() };
		}
		return super.createContextMenuActions(selection);
	}

}