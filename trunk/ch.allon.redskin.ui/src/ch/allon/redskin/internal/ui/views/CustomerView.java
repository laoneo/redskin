package ch.allon.redskin.internal.ui.views;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.Customer;
import ch.allon.redskin.core.model.shop.provider.OrderItemProvider;
import ch.allon.redskin.core.model.shop.provider.ShopItemProviderAdapterFactory;
import ch.allon.redskin.internal.ui.actions.EObjectAction;
import ch.allon.redskin.internal.ui.actions.EditCustomerAction;
import ch.allon.redskin.internal.ui.actions.NewCustomerAction;

public class CustomerView extends EObjectView {

	@Override
	protected Object createInput() {
		return DBFactory.getCustomerResource();
	}

	@Override
	protected EObjectAction[] createToolBarActions() {
		return new EObjectAction[] { new NewCustomerAction() };
	}

	@Override
	protected Viewer createViewer(Composite parent) {
		TableViewer viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
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
				EditCustomerAction action = new EditCustomerAction();
				action.setActivePart(null, CustomerView.this);
				action.selectionChanged(null, event.getSelection());
				action.run();
			}
		});

		Table table = viewer.getTable();
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText("Vorname");
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText("Nachname");
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText("Telefon");
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText("Hotel");
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText("Adresse");
		c.setResizable(true);

		return viewer;
	}

	@Override
	protected ShopItemProviderAdapterFactory getShopItemProviderAdapterFactory() {
		return new CustomerViewShopItemProviderAdapterFactory();
	}

	private class CustomerViewShopItemProviderAdapterFactory extends
			ShopItemProviderAdapterFactory {
		private CustomerViewCustomerItemProvider customerViewCustomerItemProvider;

		@Override
		public Adapter createCustomerAdapter() {
			if (customerViewCustomerItemProvider == null)
				customerViewCustomerItemProvider = new CustomerViewCustomerItemProvider(
						this);
			return customerViewCustomerItemProvider;
		}

		private class CustomerViewCustomerItemProvider extends
				OrderItemProvider {

			public CustomerViewCustomerItemProvider(
					AdapterFactory adapterFactory) {
				super(adapterFactory);
			}

			@Override
			public String getColumnText(Object object, int columnIndex) {
				if (!(object instanceof Customer))
					return super.getColumnText(object, columnIndex);
				Customer c = (Customer) object;
				switch (columnIndex) {
				case 0:
					return c.getSurname();
				case 1:
					return c.getFamilyName();
				case 2:
					return c.getTelephoneNr();
				case 3:
					return c.getHotel();
				case 4:
					return c.getAddress();
				default:
					break;
				}
				return super.getColumnText(object, columnIndex);
			}
		}
	}

}
