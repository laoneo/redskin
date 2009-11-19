package ch.allon.redskin.internal.ui.views;

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.internal.ui.actions.EObjectAction;
import ch.allon.redskin.internal.ui.actions.NewProductCategoryAction;

public class ProductView extends EObjectView {

	@Override
	protected Object createInput(IMemento memento) {
		return DBFactory.getProductsResource();
	}

	@Override
	protected StructuredViewer createViewer(Composite parent) {
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

	@Override
	protected AdapterFactoryContentProvider createContentProvider() {
		return new AdapterFactoryContentProvider(getEditingDomain()
				.getAdapterFactory()) {
			@SuppressWarnings("unchecked")
			Comparator comparator = new Comparator() {
				public int compare(Object o1, Object o2) {
					if (o1 instanceof Product && o2 instanceof Product) {
						return ((Product) o1).getName().compareTo(
								((Product) o2).getName());
					} else if (o1 instanceof ProductCategory
							&& o2 instanceof ProductCategory)
						return ((ProductCategory) o1).getName().compareTo(
								((ProductCategory) o2).getName());
					return 0;
				}
			};

			@SuppressWarnings("unchecked")
			@Override
			public Object[] getElements(Object object) {
				Object[] children = super.getElements(object);
				Arrays.sort(children, comparator);
				return children;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object[] getChildren(Object object) {
				Object[] children = super.getChildren(object);
				Arrays.sort(children, comparator);
				return children;
			}
		};
	}

}