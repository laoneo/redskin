package ch.allon.redskin.internal.ui.views;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.actions.EObjectAction;
import ch.allon.redskin.internal.ui.actions.EditProductAction;
import ch.allon.redskin.internal.ui.actions.EditProductCategoryAction;
import ch.allon.redskin.internal.ui.actions.NewProductCategoryAction;

public class ProductView extends EObjectView {

	@Override
	protected Object createInput(IMemento memento) {
		return DBFactory.getProductsResource();
	}

	@Override
	protected StructuredViewer createViewer(Composite parent) {
		final FilteredTree tree = new FilteredTree(parent, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER,
				new PatternFilter(), true);
		tree.getViewer().setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
		tree.getViewer().addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				EObject obj = (EObject) ((StructuredSelection) event
						.getSelection()).getFirstElement();
				EObjectAction a = null;
				if (obj.eClass().getClassifierID() == ShopPackage.PRODUCT)
					a = new EditProductAction();
				else if (obj.eClass().getClassifierID() == ShopPackage.PRODUCT_CATEGORY)
					a = new EditProductCategoryAction();
				if (a != null) {
					a.selectionChanged(null, event.getSelection());
					a.setActivePart(null, ProductView.this);
					a.run();
				}

			}
		});

		int ops = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { TextTransfer.getInstance() };
		tree.getViewer().addDragSupport(ops, transfers,
				new ViewerDragAdapter(tree.getViewer()) {
					@Override
					public void dragSetData(DragSourceEvent event) {
						event.data = String.class.getName();
					}
				});

		tree.getViewer().addDropSupport(ops, transfers,
				new ViewerDropAdapter(tree.getViewer()) {

					@Override
					public boolean validateDrop(Object target, int operation,
							TransferData transferType) {
						return target instanceof ProductCategory;
					}

					@SuppressWarnings("unchecked")
					@Override
					public boolean performDrop(final Object data) {
						IStructuredSelection selection = (IStructuredSelection) tree
								.getViewer().getSelection();
						final List selectedObjects = selection.toList();
						UIUtil.runModelModificationJob(new IJobRunnable() {

							@Override
							public IStatus run(IProgressMonitor monitor) {

								for (Object obj : selectedObjects) {
									if (obj instanceof ProductCategory)
										((ProductCategory) obj)
												.setParent((ProductCategory) getCurrentTarget());
									if (obj instanceof Product)
										((Product) obj)
												.setProductCategory((ProductCategory) getCurrentTarget());
								}
								return Status.OK_STATUS;
							}
						});
						return true;
					}
				});

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