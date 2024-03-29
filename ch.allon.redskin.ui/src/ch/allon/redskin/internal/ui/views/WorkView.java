package ch.allon.redskin.internal.ui.views;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;

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
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
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
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.ProductIndexer;
import ch.allon.redskin.core.RedskinCore;
import ch.allon.redskin.core.model.shop.Customer;
import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.Product;
import ch.allon.redskin.core.model.shop.ShopFactory;
import ch.allon.redskin.core.model.shop.Transaction;
import ch.allon.redskin.core.model.shop.provider.OrderItemProvider;
import ch.allon.redskin.core.model.shop.provider.ShopItemProviderAdapterFactory;
import ch.allon.redskin.core.model.shop.provider.TransactionItemProvider;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.actions.MarkOrderPaid;
import ch.allon.redskin.internal.ui.actions.ShowOrderReportAction;
import ch.allon.redskin.internal.ui.custom.CustomDialog;
import ch.allon.redskin.internal.ui.custom.CustomerDialog;
import ch.allon.redskin.internal.ui.custom.ProductProposalProvider;

public class WorkView extends EObjectView {

	private Text numberField;
	private Text rentField;
	private Button tomorrowButton;

	@Override
	protected Object createInput(IMemento memento) {
		if (memento != null) {
			String number = memento.getString("ordernumber"); //$NON-NLS-1$
			if (number != null) {
				final Order order = DBFactory.findOrder(number);
				if (order != null) {
					if (order.getCustomer() != null) {
						UIUtil.getDisplay().asyncExec(new Runnable() {

							@Override
							public void run() {
								setPartName(order.getCustomer().toString());
							}
						});
					}
					return order;
				}
			}
		}
		return ShopFactory.eINSTANCE.createOrder();
	}

	@Override
	protected StructuredViewer createViewer(Composite parent) {
		final TableViewer viewer = new TableViewer(parent, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		viewer.setContentProvider(new AdapterFactoryContentProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		viewer.setColumnProperties(new String[] { Messages.WorkView_Number_Col,
				Messages.WorkView_Name_Col, Messages.WorkView_Description_Col,
				Messages.WorkView_Days_Col, Messages.WorkView_Return_Col,
				Messages.WorkView_Price_Col, Messages.WorkView_Paid_Col });

		Table table = viewer.getTable();
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 50, true));
		c.setText(Messages.WorkView_Number_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Name_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.WorkView_Description_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 10, true));
		c.setText(Messages.WorkView_Days_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 80, true));
		c.setText(Messages.WorkView_Return_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 70, true));
		c.setText(Messages.WorkView_Price_Col);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 80, true));
		c.setText(Messages.WorkView_Paid_Col);
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
				new TextCellEditor(table), new TextCellEditor(table),
				textEditor, new TextCellEditor(table) });
		viewer.setCellModifier(new ICellModifier() {

			@Override
			public void modify(Object element, String property, Object value) {
				final IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				final Transaction row = (Transaction) selection
						.getFirstElement();
				if (row == null)
					return;
				final String v = (String) value;
				if (v.length() < 1)
					return;
				UIUtil.runModelModificationJob(new IJobRunnable() {

					@Override
					public IStatus run(IProgressMonitor monitor) {
						row.setPrice(Double.parseDouble(v));
						UIUtil.getDisplay().asyncExec(new Runnable() {

							@Override
							public void run() {
								viewer.refresh();
							}
						});
						return Status.OK_STATUS;
					}
				});
			}

			@Override
			public Object getValue(Object element, String property) {
				ITableLabelProvider labelProvider = (ITableLabelProvider) viewer
						.getLabelProvider();
				int column = 0;
				if (property.equals(Messages.WorkView_Number_Col))
					column = 0;
				else if (property.equals(Messages.WorkView_Name_Col))
					column = 1;
				else if (property.equals(Messages.WorkView_Description_Col))
					column = 2;
				else if (property.equals(Messages.WorkView_Days_Col))
					column = 3;
				else if (property.equals(Messages.WorkView_Return_Col))
					column = 4;
				else if (property.equals(Messages.WorkView_Price_Col))
					column = 5;
				else if (property.equals(Messages.WorkView_Paid_Col))
					column = 6;
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
		buttonBar.setLayout(new GridLayout(5, false));

		Button customerButton = new Button(buttonBar, SWT.PUSH);
		customerButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		customerButton.setText(Messages.WorkView_Search_Customer_Action);
		customerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleAddCustomer();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button commentButton = new Button(buttonBar, SWT.PUSH);
		commentButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		commentButton.setText(Messages.WorkView_Enter_Comment);
		commentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog dialog = new InputDialog(getSite().getShell(),
						Messages.WorkView_Comment_Dialog_Title,
						Messages.WorkView_Comment_Dialog_Text, "", //$NON-NLS-3$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
						null);
				if (dialog.open() == Dialog.CANCEL)
					return;
				final String input = dialog.getValue();
				UIUtil.runModelModificationJob(new IJobRunnable() {

					@Override
					public IStatus run(IProgressMonitor monitor) {
						getOrder().getComments().add(input);
						return Status.OK_STATUS;
					}
				});
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button printButton = new Button(buttonBar, SWT.PUSH);
		printButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		printButton.setText(Messages.WorkView_Show_Order_Action);
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
			}
		});

		Button markAsPaidButton = new Button(buttonBar, SWT.PUSH);
		markAsPaidButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		markAsPaidButton.setText(Messages.WorkView_Mark_Order_Paid_Action);
		markAsPaidButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MarkOrderPaid action = new MarkOrderPaid();
				action.selectionChanged(null, new StructuredSelection(
						getOrder()));
				action.setActivePart(null, WorkView.this);
				action.run();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
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
		final Customer customer = dialog.getCustomer();
		UIUtil.runModelModificationJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				if (!DBFactory.getOrdersResource().getContents().contains(
						getOrder())) {
					DBFactory.getOrdersResource().getContents().add(getOrder());
				}
				getOrder().setCustomer(customer);
				return Status.OK_STATUS;
			}
		});
		setPartName(customer.toString());
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
		final Product product = ProductIndexer.getProduct(number);
		if (product == null) {
			MessageDialog.open(MessageDialog.ERROR, getViewSite().getShell(),
					Messages.WorkView_Error_Title,
					Messages.WorkView_Correct_Number_1 + numberField.getText()
							+ Messages.WorkView_Correct_Number_2, SWT.NONE);
			numberField.setText(""); //$NON-NLS-1$
			numberField.setFocus();
			return;
		}
		if (product.getPriceCategory() == null) {
			MessageDialog.open(MessageDialog.ERROR, getViewSite().getShell(),
					Messages.WorkView_Error_Title,
					Messages.WorkView_Product_No_Price_Error, SWT.NONE);
			numberField.setFocus();
			return;
		}
		for (Transaction t : getOrder().getTransactions()) {
			if (t.getProduct().equals(product)) {
				MessageDialog.open(MessageDialog.ERROR, getViewSite()
						.getShell(), Messages.WorkView_Error_Title,
						Messages.WorkView_Number_Registered_Error_1 + number
								+ Messages.WorkView_Number_Registered_Error_2,
						SWT.NONE);
				numberField.setText(""); //$NON-NLS-1$
				numberField.setFocus();
				return;
			}
		}
		final Transaction transaction = ShopFactory.eINSTANCE
				.createTransaction();
		try {
			final GregorianCalendar startDate = new GregorianCalendar();
			final GregorianCalendar endDate = new GregorianCalendar();
			if (tomorrowButton.getSelection()) {
				endDate.add(GregorianCalendar.DAY_OF_MONTH, 1);
			}
			int days = Integer.parseInt(rentField.getText());
			endDate.add(GregorianCalendar.DAY_OF_MONTH, days);
			EList<Double> prices = product.getPriceCategory().getPrices();
			double price = prices.get(Math.min(prices.size() - 2, days - 1));
			if (prices.size() <= days) {
				price += (days - prices.size() + 1)
						* prices.get(prices.size() - 1);
			}
			final double tmpPrice = price;
			UIUtil.runModelModificationJob(new IJobRunnable() {

				@Override
				public IStatus run(IProgressMonitor monitor) {
					transaction.setStartDate(startDate.getTime());
					transaction.setEndDate(endDate.getTime());
					transaction.setPrice(tmpPrice);
					if (!DBFactory.getOrdersResource().getContents().contains(
							getOrder())) {
						DBFactory.getOrdersResource().getContents().add(
								getOrder());
					}
					transaction.setProduct(product);
					getOrder().getTransactions().add(transaction);
					return Status.OK_STATUS;
				}
			});
			numberField.setText(""); //$NON-NLS-1$
			numberField.setFocus();
		} catch (Exception e) {
			RedskinCore.handleException(e);
			numberField.setFocus();
		}
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
		if (getOrder() != null)
			memento.putString("ordernumber", getOrder().getNumber()); //$NON-NLS-1$
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
			super(parentShell, Messages.WorkView_Customer_List_Dialog_Title);
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

			UIUtil.runModelModificationJob(new IJobRunnable() {

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
			createButton(parent, IDialogConstants.CLIENT_ID,
					Messages.WorkView_New_Customer_Action, true);
			super.createButtonsForButtonBar(parent);
		}

		@Override
		protected void buttonPressed(int buttonId) {
			if (buttonId == IDialogConstants.CLIENT_ID) {
				final CustomerDialog dialog = new CustomerDialog(getShell(),
						Messages.WorkView_New_Customer_Dialog_Title);
				dialog.setNewObject(ShopFactory.eINSTANCE.createCustomer());
				if (dialog.open() == Dialog.CANCEL)
					return;
				final EObject customer = dialog.getNewObject();
				UIUtil.runModelModificationJob(new IJobRunnable() {

					@Override
					public IStatus run(IProgressMonitor monitor) {
						DBFactory.getCustomerResource().getContents().add(
								customer);
						return Status.OK_STATUS;
					}
				});
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
				TOTAL_ROW.setNumber("-1"); //$NON-NLS-1$
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
					"dd.MM.yyyy"); //$NON-NLS-1$

			private final SimpleDateFormat PAID_FORMAT = new SimpleDateFormat(
					"kk:mm dd.MM.yyyy"); //$NON-NLS-1$

			public WorkViewTransactionItemProvider(AdapterFactory adapterFactory) {
				super(adapterFactory);
			}

			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				UIUtil.getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						((TableViewer) getViewer()).refresh();
					}
				});
			}

			@Override
			public String getColumnText(Object object, int columnIndex) {
				if (!(object instanceof Transaction))
					return super.getColumnText(object, columnIndex);
				Transaction tr = (Transaction) object;

				if (tr.getNumber().equals("-1")) { //$NON-NLS-1$
					switch (columnIndex) {
					case 4:
						return Messages.WorkView_Total_Value;
					case 5:
						EList<Transaction> transactions = getOrder()
								.getTransactions();
						double price = 0;
						for (Transaction t : transactions) {
							price += t.getPrice();
						}
						return new Double(price).toString();
					default:
						return ""; //$NON-NLS-1$
					}
				}

				switch (columnIndex) {
				case 0:
					return "" + tr.getProduct().getNumber(); //$NON-NLS-1$
				case 1:
					return tr.getProduct().getName();
				case 2:
					return tr.getProduct().getDescription();
				case 3:
					long diff = tr.getEndDate().getTime()
							- tr.getStartDate().getTime();
					return "" + (diff / 86400000); //$NON-NLS-1$
				case 4:
					return FORMAT.format(tr.getEndDate());
				case 5:
					return "" + tr.getPrice(); //$NON-NLS-1$
				case 6:
					if (tr.getPaidDate() == null)
						return Messages.WorkView_Not_Paid_Label;
					return PAID_FORMAT.format(tr.getPaidDate());
				default:
					break;
				}
				return super.getColumnText(object, columnIndex);
			}
		}
	}
}