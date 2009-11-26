package ch.allon.redskin.internal.ui.views;

import java.text.SimpleDateFormat;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.Transaction;
import ch.allon.redskin.core.model.shop.provider.OrderItemProvider;
import ch.allon.redskin.core.model.shop.provider.ShopItemProviderAdapterFactory;
import ch.allon.redskin.core.model.shop.provider.TransactionItemProvider;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.actions.NewOrderAction;

public class OrderListView extends EObjectView {

	@Override
	protected Object createInput(IMemento memento) {
		DBFactory.getOrdersResource().eAdapters().add(new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				if (notification.getNotifier() instanceof Transaction)
					((TreeViewer) getViewer())
							.refresh(((Transaction) notification.getNotifier())
									.getOrder());
				super.notifyChanged(notification);
			}
		});
		return DBFactory.getOrdersResource().getContents().toArray();
	}

	@Override
	protected StructuredViewer createViewer(Composite parent) {
		FilteredTree filteredTree = new FilteredTree(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER
						| SWT.FULL_SELECTION, new PatternFilter() {
					@Override
					protected boolean isParentMatch(Viewer viewer,
							Object element) {
						if (element instanceof Order) {
							Order o = (Order) element;
							String word = "";
							if (o.getCustomer() == null)
								word = Messages.OrderListView_No_Customer_Label;
							else
								word = o.getCustomer().getSurname() + " " //$NON-NLS-1$ //$NON-NLS-2$
										+ o.getCustomer().getFamilyName();
							return wordMatches(word);
						}
						return super.isParentMatch(viewer, element);
					}

					@Override
					protected boolean isLeafMatch(Viewer viewer, Object element) {
						if (element instanceof Transaction) {
							Transaction t = (Transaction) element;
							String word = "";
							word = t.getProduct().getNumber() + " "
									+ t.getProduct().getName() + " "
									+ t.getProduct().getDescription();
							return wordMatches(word)
									|| isParentMatch(viewer, t.getOrder());
						}
						return super.isLeafMatch(viewer, element);
					}
				}, true);
		final TreeViewer viewer = filteredTree.getViewer();
		viewer.setContentProvider(new AdapterFactoryContentProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		viewer.setColumnProperties(new String[] { Messages.WorkView_Number_Col,
				Messages.WorkView_Description_Col, Messages.WorkView_Days_Col,
				Messages.WorkView_Return_Col, Messages.WorkView_Price_Col,
				Messages.OrderListView_Paid_Col });

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				NewOrderAction action = new NewOrderAction();
				action.setActivePart(null, OrderListView.this);
				Object obj = ((IStructuredSelection) event.getSelection())
						.getFirstElement();
				if (obj instanceof Transaction)
					obj = ((Transaction) obj).getOrder();
				action.selectionChanged(null, new StructuredSelection(obj));
				action.run();
			}
		});

		Tree tree = viewer.getTree();
		TableLayout layout = new TableLayout();
		tree.setLayout(layout);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		TreeColumn c = new TreeColumn(tree, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Number_Col);
		c.setResizable(true);

		c = new TreeColumn(tree, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Description_Col);
		c.setResizable(true);

		c = new TreeColumn(tree, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Days_Col);
		c.setResizable(true);

		c = new TreeColumn(tree, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Return_Col);
		c.setResizable(true);

		c = new TreeColumn(tree, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Price_Col);
		c.setResizable(true);

		c = new TreeColumn(tree, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.OrderListView_Paid_Col);
		c.setResizable(true);

		TextCellEditor textEditor = new TextCellEditor(tree);
		((Text) textEditor.getControl()).addVerifyListener(

		new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = "0123456789.".indexOf(e.text) >= 0; //$NON-NLS-1$
			}
		});

		viewer
				.setCellEditors(new CellEditor[] { new TextCellEditor(tree),
						new TextCellEditor(tree), new TextCellEditor(tree),
						new TextCellEditor(tree), textEditor,
						new TextCellEditor(tree) });
		viewer.setCellModifier(new ICellModifier() {

			@Override
			public void modify(Object element, String property, Object value) {
				final IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				final Transaction row = (Transaction) selection
						.getFirstElement();
				if (row == null)
					return;
				String v = (String) value;
				if (v.length() < 1)
					return;
				row.setPrice(Double.parseDouble(v));
			}

			@Override
			public Object getValue(Object element, String property) {
				ITableLabelProvider labelProvider = (ITableLabelProvider) viewer
						.getLabelProvider();
				int column = 0;
				if (property.equals(Messages.WorkView_Number_Col))
					column = 0;
				else if (property.equals(Messages.WorkView_Description_Col))
					column = 1;
				else if (property.equals(Messages.WorkView_Days_Col))
					column = 2;
				else if (property.equals(Messages.WorkView_Return_Col))
					column = 3;
				else if (property.equals(Messages.WorkView_Price_Col))
					column = 4;
				else if (property.equals(Messages.OrderListView_Paid_Col))
					column = 5;
				return labelProvider.getColumnText(element, column);
			}

			@Override
			public boolean canModify(Object element, String property) {
				return property.equals(Messages.WorkView_Price_Col);
			}
		});

		return viewer;
	}

	@Override
	protected AdapterFactoryContentProvider createContentProvider() {
		return new AdapterFactoryContentProvider(getEditingDomain()
				.getAdapterFactory()) {
			@Override
			public Object[] getElements(Object object) {
				if (object instanceof Object[])
					return (Object[]) object;
				return super.getElements(object);
			}
		};
	}

	@Override
	protected ShopItemProviderAdapterFactory getShopItemProviderAdapterFactory() {
		return new OrderViewShopItemProviderAdapterFactory();
	}

	private class OrderViewShopItemProviderAdapterFactory extends
			ShopItemProviderAdapterFactory {
		private OrderViewTransactionItemProvider orderViewTransactionItemProvider;
		private OrderViewOrderItemProvider orderViewOrderItemProvider;

		@Override
		public Adapter createTransactionAdapter() {
			if (orderViewTransactionItemProvider == null)
				orderViewTransactionItemProvider = new OrderViewTransactionItemProvider(
						this);
			return orderViewTransactionItemProvider;
		}

		@Override
		public Adapter createOrderAdapter() {
			if (orderViewOrderItemProvider == null)
				orderViewOrderItemProvider = new OrderViewOrderItemProvider(
						this);
			return orderViewOrderItemProvider;
		}

		private class OrderViewTransactionItemProvider extends
				TransactionItemProvider {

			private final SimpleDateFormat FORMAT = new SimpleDateFormat(
					"dd.MM.yyyy"); //$NON-NLS-1$

			private final SimpleDateFormat PAID_FORMAT = new SimpleDateFormat(
					"kk:mm dd.MM.yyyy"); //$NON-NLS-1$

			public OrderViewTransactionItemProvider(
					AdapterFactory adapterFactory) {
				super(adapterFactory);
			}

			@Override
			public String getColumnText(Object object, int columnIndex) {
				if (!(object instanceof Transaction))
					return super.getColumnText(object, columnIndex);
				Transaction tr = (Transaction) object;
				switch (columnIndex) {
				case 0:
					return "" + tr.getProduct().getNumber(); //$NON-NLS-1$
				case 1:
					return tr.getProduct().getDescription();
				case 2:
					long diff = tr.getEndDate().getTime()
							- tr.getStartDate().getTime();
					return "" + (diff / 86400000); //$NON-NLS-1$
				case 3:
					return FORMAT.format(tr.getEndDate());
				case 4:
					return "" + tr.getPrice(); //$NON-NLS-1$
				case 5:
					if (tr.getPaidDate() == null)
						return "Noch nicht bezahlt";
					return PAID_FORMAT.format(tr.getPaidDate());
				}
				return super.getColumnText(object, columnIndex);
			}
		}

		private class OrderViewOrderItemProvider extends OrderItemProvider {

			public OrderViewOrderItemProvider(AdapterFactory adapterFactory) {
				super(adapterFactory);
			}

			@Override
			public String getColumnText(Object object, int columnIndex) {
				if (!(object instanceof Order))
					return super.getColumnText(object, columnIndex);
				Order o = (Order) object;
				switch (columnIndex) {
				case 0:
					if (o.getCustomer() == null)
						return Messages.OrderListView_No_Customer_Label;
					return "" + o.getCustomer().getSurname() + " " //$NON-NLS-1$ //$NON-NLS-2$
							+ o.getCustomer().getFamilyName();
				case 5:
					boolean paid = true;
					for (EObject tr : o.getTransactions()) {
						if (((Transaction) tr).getPaidDate() == null) {
							paid = false;
							break;
						}
					}
					return paid ? "Ja" : "Nein";
				}
				return super.getColumnText(object, columnIndex);
			}
		}
	}

}
