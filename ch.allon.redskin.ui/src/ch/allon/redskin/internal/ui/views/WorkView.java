package ch.allon.redskin.internal.ui.views;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.eclipse.ui.part.ViewPart;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.IJobRunnable;
import ch.allon.redskin.core.ProductIndexer;
import ch.allon.redskin.core.model.shop.Customer;
import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.core.model.shop.Transaction;
import ch.allon.redskin.core.model.shop.provider.ShopItemProviderAdapterFactory;
import ch.allon.redskin.core.model.shop.provider.TransactionItemProvider;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.custom.CustomDialog;
import ch.allon.redskin.internal.ui.custom.EObjectDialog;

public class WorkView extends ViewPart implements IEditingDomainProvider,
		ISaveablePart2 {

	private TableViewer viewer;
	private Order order;
	private Text numberField;
	private Text rentField;
	private Button tomorrowButton;
	private AdapterFactoryEditingDomain ed;
	private boolean dirty;
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public void createPartControl(Composite parent) {
		Composite container = UIUtil.createStandardComposite(parent, 1);

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
				doSave(null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				doSave(null);
			}
		});
	}

	/**
	 * @param container
	 * @return
	 */
	private void createRegisterBar(Composite container) {
		Composite buttonBar = new Composite(container, SWT.NONE);
		buttonBar.setLayout(new GridLayout(7, false));

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

		Button customerButton = new Button(buttonBar, SWT.PUSH);
		customerButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		customerButton.setText("Kunde suchen");
		customerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleAddCustomer();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleAddCustomer();
			}
		});

		buttonBar.setTabList(new Control[] { numberField, rentField,
				tomorrowButton, registerButton });
	}

	private void handleAddCustomer() {
		CustomerListDialog dialog = new CustomerListDialog(getViewSite()
				.getShell());
		if (dialog.open() == Dialog.CANCEL || dialog.getCustomer() == null)
			return;
		order.setCustomer(dialog.getCustomer());
		setPartName(dialog.getCustomer().toString());
		dirty = true;
		firePropertyChange(PROP_DIRTY);
	}

	private void handleCreateTransaction() {
		int number = -1;
		try {
			number = Integer.parseInt(numberField.getText());
		} catch (NumberFormatException e1) {
			MessageDialog.open(MessageDialog.ERROR, viewer.getControl()
					.getShell(), Messages.WorkView_Error_Title,
					Messages.WorkView_Correct_Number_1 + numberField.getText()
							+ Messages.WorkView_Correct_Number_2, SWT.NONE);
			numberField.setFocus();
			return;
		}
		Product product = ProductIndexer.getProduct(number);
		if (product == null) {
			MessageDialog.open(MessageDialog.ERROR, viewer.getControl()
					.getShell(), Messages.WorkView_Error_Title,
					Messages.WorkView_Correct_Number_1 + numberField.getText()
							+ Messages.WorkView_Correct_Number_2, SWT.NONE);
			numberField.setFocus();
			return;
		}
		Transaction transaction = ShopFactory.eINSTANCE.createTransaction();
		try {
			GregorianCalendar c = new GregorianCalendar();
			transaction.setTransactionNr(FORMAT.format(c.getTime()));
			if (tomorrowButton.getSelection()) {
				c.add(GregorianCalendar.DAY_OF_MONTH, 1);
			}
			transaction.setStartDate(c.getTime());
			int days = Integer.parseInt(rentField.getText());
			c.add(GregorianCalendar.DAY_OF_MONTH, days);
			transaction.setEndDate(c.getTime());
			EList<Double> prices = product.getPriceCategory().getPrices();
			double price = 0.0;
			if (prices.size() > days)
				price = prices.get(days);
			transaction.setPrice(price);
		} catch (Exception e) {
			MessageDialog.open(MessageDialog.ERROR, viewer.getControl()
					.getShell(), Messages.WorkView_Error_Title,
					"Ein Fehler ist aufgetreten:\n" + e.getLocalizedMessage(),
					SWT.NONE);
			numberField.setFocus();
			return;
		}

		transaction.setProduct(product);
		transaction.setOrder(order);
		numberField.setText("");
		numberField.setFocus();
		dirty = true;
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (order.getOrderNr() == null || order.getOrderNr().length() == 0)
			order.setOrderNr(FORMAT.format(new Date()));
		EList<EObject> contents = DBFactory.getOrdersResource().getContents();
		if (contents.contains(order)) {
			try {
				DBFactory.getOrdersResource().save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			contents.add(order);
		dirty = false;
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		return true;
	}

	@Override
	public int promptToSaveOnClose() {
		boolean save = MessageDialog
				.openQuestion(
						getViewSite().getShell(),
						"Speichern",
						"Der Auftrag wurde noch nicht gespeichert, soll gespeichert werden vor dem schliessen?");
		return save ? YES : NO;
	}

	@Override
	public void setFocus() {
		numberField.setFocus();
	}

	@Override
	public AdapterFactoryEditingDomain getEditingDomain() {
		if (ed == null) {
			ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
			adapterFactory
					.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			adapterFactory
					.addAdapterFactory(new WorkViewShopItemProviderAdapterFactory());
			adapterFactory
					.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

			BasicCommandStack commandStack = new BasicCommandStack();
			ed = new AdapterFactoryEditingDomain(adapterFactory, commandStack,
					new HashMap<Resource, Boolean>());
		}
		return ed;
	}

	private class CustomerListDialog extends CustomDialog {

		private Customer customer;
		private TreeViewer treeViewer;

		protected CustomerListDialog(Shell parentShell) {
			super(parentShell, "Kunden Liste");
		}

		public Customer getCustomer() {
			return customer;
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite c = (Composite) super.createDialogArea(parent);
			FilteredTree tree = new FilteredTree(c, SWT.SINGLE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER, new PatternFilter(), true);
			tree.setLayoutData(new GridData(GridData.FILL_BOTH));
			treeViewer = tree.getViewer();

			treeViewer.addDoubleClickListener(new IDoubleClickListener() {

				@Override
				public void doubleClick(DoubleClickEvent event) {
					buttonPressed(IDialogConstants.OK_ID);
				}
			});

			treeViewer.setContentProvider(new AdapterFactoryContentProvider(
					getEditingDomain().getAdapterFactory()));
			treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(
					getEditingDomain().getAdapterFactory()));

			UIUtil.runUIJob(new IJobRunnable() {

				@Override
				public IStatus run(IProgressMonitor monitor) {
					final Resource resource = DBFactory.getCustomerResource();
					UIUtil.getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							treeViewer.setInput(resource);
						}
					});
					return Status.OK_STATUS;
				}
			});
			return c;
		}

		@Override
		protected void createButtonsForButtonBar(Composite parent) {
			createButton(parent, IDialogConstants.CLIENT_ID, "Neuer Kunde",
					true);
			super.createButtonsForButtonBar(parent);
		}

		@Override
		protected void buttonPressed(int buttonId) {
			if (buttonId == IDialogConstants.CLIENT_ID) {
				EObjectDialog dialog = new EObjectDialog(getShell(),
						"Neuer Kunde") {

					@Override
					protected List<EObject> getChilds(EObject object,
							EReference reference) {
						return null;
					}

					@Override
					protected Point getInitialSize() {
						return new Point(400, 230);
					}
				};
				dialog.setNewObject(ShopFactory.eINSTANCE.createCustomer());
				if (dialog.open() == Dialog.CANCEL)
					return;
				DBFactory.getCustomerResource().getContents().add(
						dialog.getNewObject());
			} else if (buttonId == IDialogConstants.OK_ID) {
				ISelection selection = treeViewer.getSelection();
				if (!selection.isEmpty()
						&& selection instanceof IStructuredSelection)
					customer = (Customer) ((IStructuredSelection) selection)
							.getFirstElement();
			}
			super.buttonPressed(buttonId);
		}

		@Override
		protected Point getInitialSize() {
			return new Point(400, 300);
		}
	}

	private class WorkViewShopItemProviderAdapterFactory extends
			ShopItemProviderAdapterFactory {
		private WorkViewTransactionItemProvider workViewTransactionItemProvider;

		@Override
		public Adapter createTransactionAdapter() {
			if (workViewTransactionItemProvider == null)
				workViewTransactionItemProvider = new WorkViewTransactionItemProvider(
						this);
			return workViewTransactionItemProvider;
		}

		private class WorkViewTransactionItemProvider extends
				TransactionItemProvider {

			private final SimpleDateFormat FORMAT = new SimpleDateFormat(
					"dd.MM.yyyyy");

			public WorkViewTransactionItemProvider(AdapterFactory adapterFactory) {
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
	}
}