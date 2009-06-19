package ch.allon.redskin.internal.ui.views;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.eclipse.ui.part.ViewPart;

import ch.allon.redskin.core.ProductIndexer;
import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.core.model.shop.Transaction;
import ch.allon.redskin.core.model.shop.provider.ShopItemProviderAdapterFactory;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.RedskinUIActivator;
import ch.allon.redskin.internal.ui.UIUtil;

public class WorkView extends ViewPart implements IEditingDomainProvider {

	private TableViewer viewer;
	private Order order;
	private Text numberField;
	private Text rentField;
	private Button tomorrowButton;
	private AdapterFactoryEditingDomain ed;

	public void createPartControl(Composite parent) {
		Composite container = UIUtil.createStandardComposite(parent, 1);

		createToolBar(container);
		createRegisterBar(container);

		viewer = new TableViewer(container, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new AdapterFactoryContentProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = viewer.getTable();
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Number_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Description_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Days_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Return_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Price_Col);
		c.setResizable(true);

		order = ShopFactory.eINSTANCE.createOrder();
		viewer.setInput(order);

		createBottomBar(container);
	}

	private void createToolBar(Composite parent) {
		Composite c = UIUtil.createStandardComposite(parent, 1);

		Button newOrderButton = new Button(c, SWT.PUSH);
		newOrderButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		newOrderButton.setToolTipText(Messages.NewOrderAction_Title);
		newOrderButton.setImage(RedskinUIActivator
				.getImage("actions/new_order.gif")); //$NON-NLS-1$
		newOrderButton.setText(Messages.WorkView_New_Order);
		newOrderButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				order = ShopFactory.eINSTANCE.createOrder();
				viewer.setInput(order);
			}
		});

		Label separator = new Label(c, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}

	private void createBottomBar(Composite container) {
		Composite buttonBar = new Composite(container, SWT.NONE);
		buttonBar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		buttonBar.setLayout(new GridLayout(1, false));
		Button button = new Button(buttonBar, SWT.PUSH);
		button
				.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
						false));
		button.setText(Messages.WorkView_Save);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSaveOrder();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleSaveOrder();
			}
		});
	}

	/**
	 * @param container
	 */
	private void createRegisterBar(Composite container) {
		Composite buttonBar = new Composite(container, SWT.NONE);
		buttonBar.setLayout(new GridLayout(6, false));

		Label label = new Label(buttonBar, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		label.setText(Messages.WorkView_Number);

		numberField = new Text(buttonBar, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		numberField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		new ContentAssistCommandAdapter(numberField, new TextContentAdapter(),
				new ProductProposalProvider(), null, new char[] { '1', '2',
						'3', '4', '5', '6', '7', '8', '9' }, true);

		label = new Label(buttonBar, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		label.setText(Messages.WorkView_Days_To_Rent);

		rentField = new Text(buttonBar, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		rentField
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		tomorrowButton = new Button(buttonBar, SWT.CHECK);
		tomorrowButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		tomorrowButton.setText(Messages.WorkView_From_Tomorrow);

		Button registerButton = new Button(buttonBar, SWT.PUSH);
		registerButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		registerButton.setText(Messages.WorkView_Register);
		registerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleCreateTransaction();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleCreateTransaction();
			}
		});
	}

	private void handleSaveOrder() {
		// EditingDomain ed = DBFactory.getProductEditingDomain();
		// Command command = CreateChildCommand.create(ed, order,
		// new CommandParameter(order,
		// ShopPackage.Literals.ORDER__TRANSACTIONS,
		// transaction), Collections.EMPTY_LIST);
		// ed.getCommandStack().execute(command);
		// command = CreateChildCommand.create(ed, product,
		// new CommandParameter(product,
		// ShopPackage.Literals.PRODUCT__TRANSACTIONS,
		// transaction), Collections.EMPTY_LIST);
		// ed.getCommandStack().execute(command);
	}

	private void handleCreateTransaction() {
		Product product = ProductIndexer.getProduct(Integer
				.parseInt(numberField.getText()));
		if (product == null) {
			MessageDialog.open(MessageDialog.ERROR, viewer.getControl()
					.getShell(), Messages.WorkView_Error_Title,
					Messages.WorkView_Correct_Number_1
							+ numberField.getText()
							+ Messages.WorkView_Correct_Number_2,
					SWT.NONE);
			numberField.setFocus();
			return;
		}
		Transaction transaction = ShopFactory.eINSTANCE.createTransaction();
		GregorianCalendar c = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); //$NON-NLS-1$
		transaction.setTransactionNr(format.format(c.getTime()));
		if (tomorrowButton.getSelection()) {
			c.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		transaction.setStartDate(c.getTime());
		c.add(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(rentField
				.getText()));
		transaction.setEndDate(c.getTime());
		transaction.getProduct().getPriceCategory().getPrices().get(
				Integer.parseInt(rentField.getText()));
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public AdapterFactoryEditingDomain getEditingDomain() {
		if (ed == null) {
			ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
			adapterFactory
					.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			adapterFactory
					.addAdapterFactory(new ShopItemProviderAdapterFactory());
			adapterFactory
					.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

			BasicCommandStack commandStack = new BasicCommandStack();
			ed = new AdapterFactoryEditingDomain(adapterFactory, commandStack,
					new HashMap<Resource, Boolean>());
		}
		return ed;
	}
}