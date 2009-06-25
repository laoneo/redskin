package ch.allon.redskin.internal.ui.views;

import java.text.SimpleDateFormat;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

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
	protected Object createInput() {
		return DBFactory.getOrdersResource();
	}

	@Override
	protected Viewer createViewer(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new AdapterFactoryContentProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				NewOrderAction action = new NewOrderAction();
				action.setActivePart(null, OrderListView.this);
				action.selectionChanged(null, event.getSelection());
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

		return viewer;
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
					"dd.MM.yyyyy");

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
					return "" + tr.getProduct().getNumber();
				case 1:
					return tr.getProduct().getDescription();
				case 2:
					long diff = tr.getEndDate().getTime()
							- tr.getStartDate().getTime();
					return "" + (diff / 86400000);
				case 3:
					return FORMAT.format(tr.getEndDate());
				case 4:
					return "" + tr.getPrice();
				default:
					break;
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
						return "Kein Kunde";
					return "" + o.getCustomer().getSurname() + " "
							+ o.getCustomer().getFamilyName();
				}
				return super.getColumnText(object, columnIndex);
			}
		}
	}

}
