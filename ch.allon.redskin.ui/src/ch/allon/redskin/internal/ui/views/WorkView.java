package ch.allon.redskin.internal.ui.views;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
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
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.IJobRunnable;
import ch.allon.redskin.core.ProductIndexer;
import ch.allon.redskin.core.model.shop.Customer;
import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.core.model.shop.Transaction;
import ch.allon.redskin.core.model.shop.provider.OrderItemProvider;
import ch.allon.redskin.core.model.shop.provider.ShopItemProviderAdapterFactory;
import ch.allon.redskin.core.model.shop.provider.TransactionItemProvider;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.actions.ShowOrderReportAction;
import ch.allon.redskin.internal.ui.custom.CustomDialog;
import ch.allon.redskin.internal.ui.custom.CustomerDialog;
import ch.allon.redskin.internal.ui.custom.ProductProposalProvider;

public class WorkView extends EObjectView implements ISaveablePart2 {

	private Text numberField;
	private Text rentField;
	private Button tomorrowButton;
	private boolean dirty;

	@Override
	protected Object createInput(IMemento memento) {
		if (memento != null) {
			String number = memento.getString("ordernumber");
			if (number != null) {
				for (Iterator<EObject> j = DBFactory.getOrdersResource()
						.getAllContents(); j.hasNext();) {
					EObject obj = j.next();
					if (obj instanceof Order) {
						final Order order = (Order) obj;
						if (order.getNumber().equals(number)) {
							if (order.getCustomer() != null) {
								UIUtil.getDisplay().asyncExec(new Runnable() {

									@Override
									public void run() {
										setPartName(order.getCustomer()
												.toString());
									}
								});
							}
							return order;
						}
					}
				}
			}
		}
		return ShopFactory.eINSTANCE.createOrder();
	}

	@Override
	protected Viewer createViewer(Composite parent) {
		final TableViewer viewer = new TableViewer(parent, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		viewer.setContentProvider(new AdapterFactoryContentProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		viewer.setColumnProperties(new String[] { Messages.WorkView_Number_Col,
				Messages.WorkView_Description_Col, Messages.WorkView_Days_Col,
				Messages.WorkView_Return_Col, Messages.WorkView_Price_Col });

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

		TextCellEditor textEditor = new TextCellEditor(table);
		((Text) textEditor.getControl()).addVerifyListener(

		new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = "0123456789.".indexOf(e.text) >= 0; //$NON-NLS-1$
			}
		});
		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(table),
				new TextCellEditor(table), new TextCellEditor(table),
				new TextCellEditor(table), textEditor });
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
				viewer.refresh();
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
	protected void createBottomBar(Composite container) {
		Composite buttonBar = new Composite(container, SWT.NONE);
		buttonBar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		buttonBar.setLayout(new GridLayout(4, false));

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

		Button commentButton = new Button(buttonBar, SWT.PUSH);
		commentButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		commentButton.setText("Kommentar eingeben");
		commentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog dialog = new InputDialog(getSite().getShell(),
						"Kommentar", "Bitte einen Kommentar eingeben.", "",
						null);
				if (dialog.open() == Dialog.CANCEL)
					return;
				getOrder().getComments().add(dialog.getValue());
				dirty = true;
				firePropertyChange(PROP_DIRTY);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleAddCustomer();
			}
		});

		Button printButton = new Button(buttonBar, SWT.PUSH);
		printButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		printButton.setText("Auftrag zeigen");
		printButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ShowOrderReportAction action = new ShowOrderReportAction();
				action.selectionChanged(null, new StructuredSelection(
						getOrder()));
				action.setActivePart(null, WorkView.this);
				action.run();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleAddCustomer();
			}
		});
	}

	@Override
	protected void createToolBar(Composite container) {
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
				new ProductProposalProvider(), null, new char[] {}, true);

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

	private void handleAddCustomer() {
		CustomerListDialog dialog = new CustomerListDialog(getViewSite()
				.getShell());
		if (dialog.open() == Dialog.CANCEL || dialog.getCustomer() == null)
			return;
		getOrder().setCustomer(dialog.getCustomer());
		setPartName(dialog.getCustomer().toString());
		dirty = getOrder().eResource() == null ? true : getOrder().eResource()
				.isModified();
		firePropertyChange(PROP_DIRTY);
	}

	private void handleCreateTransaction() {
		int number = -1;
		try {
			number = Integer.parseInt(numberField.getText());
		} catch (NumberFormatException e1) {
			MessageDialog.open(MessageDialog.ERROR, getViewSite().getShell(),
					Messages.WorkView_Error_Title,
					Messages.WorkView_Correct_Number_1 + numberField.getText()
							+ Messages.WorkView_Correct_Number_2, SWT.NONE);
			numberField.setFocus();
			return;
		}
		Product product = ProductIndexer.getProduct(number);
		if (product == null) {
			MessageDialog.open(MessageDialog.ERROR, getViewSite().getShell(),
					Messages.WorkView_Error_Title,
					Messages.WorkView_Correct_Number_1 + numberField.getText()
							+ Messages.WorkView_Correct_Number_2, SWT.NONE);
			numberField.setText("");
			numberField.setFocus();
			return;
		}
		for (Transaction t : getOrder().getTransactions()) {
			if (t.getProduct().equals(product)) {
				MessageDialog.open(MessageDialog.ERROR, getViewSite()
						.getShell(), Messages.WorkView_Error_Title, "Nummer "
						+ number + " ist schon registriert", SWT.NONE);
				numberField.setText("");
				numberField.setFocus();
				return;
			}
		}
		Transaction transaction = ShopFactory.eINSTANCE.createTransaction();
		try {
			GregorianCalendar c = new GregorianCalendar();
			if (tomorrowButton.getSelection()) {
				c.add(GregorianCalendar.DAY_OF_MONTH, 1);
			}
			transaction.setStartDate(c.getTime());
			int days = Integer.parseInt(rentField.getText());
			c.add(GregorianCalendar.DAY_OF_MONTH, days);
			transaction.setEndDate(c.getTime());
			EList<Double> prices = product.getPriceCategory().getPrices();
			double price = prices.get(Math.min(prices.size() - 2, days - 1));
			if (prices.size() <= days) {
				price += (days - prices.size())
						* prices.get(prices.size() - 1);
			}
			transaction.setPrice(price);
		} catch (Exception e) {
			MessageDialog.open(MessageDialog.ERROR, getViewSite().getShell(),
					Messages.WorkView_Error_Title,
					"Ein Fehler ist aufgetreten:\n" + e.getLocalizedMessage(),
					SWT.NONE);
			numberField.setFocus();
			return;
		}

		transaction.setProduct(product);
		getOrder().getTransactions().add(transaction);
		numberField.setText("");
		numberField.setFocus();
		dirty = true;
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		EList<EObject> contents = DBFactory.getOrdersResource().getContents();
		if (contents.contains(getOrder())) {
			try {
				DBFactory.getOrdersResource().save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			contents.add(getOrder());
		dirty = DBFactory.getOrdersResource().isModified();
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

	public void setOrder(Order order) {
		getViewer().setInput(order);
		if (order.getCustomer() != null)
			setPartName(order.getCustomer().toString());
	}

	public Order getOrder() {
		return (Order) getViewer().getInput();
	}

	@Override
	public void saveState(IMemento memento) {
		memento.putString("ordernumber", getOrder().getNumber());
		super.saveState(memento);
	}

	@Override
	protected ShopItemProviderAdapterFactory getShopItemProviderAdapterFactory() {
		return new WorkViewShopItemProviderAdapterFactory();
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
				CustomerDialog dialog = new CustomerDialog(getShell(),
						"Neuer Kunde");
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
		private WorkViewOrderItemProvider workViewOrderItemProvider;

		@Override
		public Adapter createTransactionAdapter() {
			if (workViewTransactionItemProvider == null)
				workViewTransactionItemProvider = new WorkViewTransactionItemProvider(
						this);
			return workViewTransactionItemProvider;
		}

		@Override
		public Adapter createOrderAdapter() {
			if (workViewOrderItemProvider == null)
				workViewOrderItemProvider = new WorkViewOrderItemProvider(this);
			return workViewOrderItemProvider;
		}

		private class WorkViewOrderItemProvider extends OrderItemProvider {

			private Transaction TOTAL_ROW;

			public WorkViewOrderItemProvider(AdapterFactory adapterFactory) {
				super(adapterFactory);
				TOTAL_ROW = ShopFactory.eINSTANCE.createTransaction();
				TOTAL_ROW.setNumber("-1");
			}

			@SuppressWarnings("unchecked")
			@Override
			public Collection<?> getChildren(Object object) {
				Collection<Object> children = (Collection<Object>) super
						.getChildren(object);
				if (!children.contains(TOTAL_ROW))
					children.add(TOTAL_ROW);
				return children;
			}
		}

		private class WorkViewTransactionItemProvider extends
				TransactionItemProvider {

			private final SimpleDateFormat FORMAT = new SimpleDateFormat(
					"dd.MM.yyyyy");

			public WorkViewTransactionItemProvider(AdapterFactory adapterFactory) {
				super(adapterFactory);
			}

			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				((TableViewer) getViewer()).refresh();
			}

			@Override
			public String getColumnText(Object object, int columnIndex) {
				if (!(object instanceof Transaction))
					return super.getColumnText(object, columnIndex);
				Transaction tr = (Transaction) object;

				if (tr.getNumber().equals("-1")) {
					switch (columnIndex) {
					case 3:
						return "Total:";
					case 4:
						EList<Transaction> transactions = getOrder()
								.getTransactions();
						double price = 0;
						for (Transaction t : transactions) {
							price += t.getPrice();
						}
						return new Double(price).toString();
					default:
						return "";
					}
				}

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