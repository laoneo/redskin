package ch.allon.redskin.internal.ui.views;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IMemento;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.core.model.shop.Transaction;
import ch.allon.redskin.core.model.shop.provider.OrderItemProvider;
import ch.allon.redskin.core.model.shop.provider.ShopItemProviderAdapterFactory;
import ch.allon.redskin.core.model.shop.provider.TransactionItemProvider;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.actions.NewOrderAction;

public class OrderListView extends EObjectView {

	private DateTime fromDateControl;
	private DateTime toDateControl;
	private Button todayBackButton;
	private Button tomorrowBackButton;
	private Text nameText;

	private int scheduledTextJobsCounter;
	private Button nonPaidButton;
	private Button showAllButton;

	@Override
	protected Object createInput(IMemento memento) {
		DBFactory.getOrdersResource().eAdapters().add(new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				UIUtil.getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						getViewer().refresh();
					}
				});
				super.notifyChanged(notification);
			}
		});
		UIUtil.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				updateContent();
			}
		});
		return Collections.EMPTY_LIST.toArray();
	}

	@Override
	protected StructuredViewer createViewer(Composite parent) {
		final TreeViewer viewer = new TreeViewer(parent, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
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
	protected void createToolBar(Composite parent) {
		Composite container = UIUtil.createStandardComposite(parent, 8);
		GridLayout layout = (GridLayout) container.getLayout();
		layout.horizontalSpacing = 4;
		layout.marginTop = 4;
		layout.marginWidth = 4;
		layout.verticalSpacing = 4;

		todayBackButton = new Button(container, SWT.CHECK);
		todayBackButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		todayBackButton.setText("Heute zurück");
		todayBackButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (todayBackButton.getSelection()
						|| tomorrowBackButton.getSelection()) {
					toDateControl.setEnabled(false);
					fromDateControl.setEnabled(false);
				} else {
					toDateControl.setEnabled(true);
					fromDateControl.setEnabled(true);
				}
				updateContent();
			}
		});

		tomorrowBackButton = new Button(container, SWT.CHECK);
		tomorrowBackButton.setLayoutData(new GridData(SWT.BEGINNING,
				SWT.CENTER, false, false));
		tomorrowBackButton.setText("Morgen zurück");
		tomorrowBackButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (todayBackButton.getSelection()
						|| tomorrowBackButton.getSelection()) {
					toDateControl.setEnabled(false);
					fromDateControl.setEnabled(false);
				} else {
					toDateControl.setEnabled(true);
					fromDateControl.setEnabled(true);
				}
				updateContent();
			}
		});

		Label label = new Label(container, SWT.NONE);
		GridData gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.horizontalIndent = 30;
		label.setLayoutData(gd);
		label.setText("Von:");
		fromDateControl = new DateTime(container, SWT.DATE | SWT.DROP_DOWN
				| SWT.MEDIUM);
		fromDateControl.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		fromDateControl.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				updateContent();
			}
		});

		label = new Label(container, SWT.NONE);
		gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gd.horizontalIndent = 30;
		label.setLayoutData(gd);
		label.setText("Bis:");
		toDateControl = new DateTime(container, SWT.DATE | SWT.DROP_DOWN
				| SWT.MEDIUM);
		toDateControl.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		toDateControl.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				updateContent();
			}
		});

		nonPaidButton = new Button(container, SWT.CHECK);
		nonPaidButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		nonPaidButton.setText("Nicht bezahlt");
		nonPaidButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				updateContent();
			}
		});

		showAllButton = new Button(container, SWT.CHECK);
		showAllButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		showAllButton.setText("Alle");
		showAllButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				updateContent();
			}
		});

		container = UIUtil.createStandardComposite(parent, 2);
		layout = (GridLayout) container.getLayout();
		layout.horizontalSpacing = 4;
		layout.marginBottom = 4;
		layout.marginWidth = 4;
		layout.verticalSpacing = 4;
		label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		label.setText("Person:");
		nameText = new Text(container, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		nameText.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				Thread t = new Thread(new Runnable() {
					public void run() {
						try {
							scheduledTextJobsCounter++;
							Thread.sleep(1000);
							if (scheduledTextJobsCounter > 1)
								return;
							UIUtil.getDisplay().asyncExec(new Runnable() {

								@Override
								public void run() {
									updateContent();
								}
							});
						} catch (InterruptedException e) {
						} finally {
							scheduledTextJobsCounter--;
						}

					}
				});
				t.start();
			}
		});
	}

	private void updateContent() {
		GregorianCalendar from = null;
		GregorianCalendar to = null;
		from = new GregorianCalendar(fromDateControl.getYear(), fromDateControl
				.getMonth(), fromDateControl.getDay());
		to = new GregorianCalendar(toDateControl.getYear(), toDateControl
				.getMonth(), toDateControl.getDay());

		if (todayBackButton.getSelection() && tomorrowBackButton.getSelection()) {
			from = new GregorianCalendar();
			to = new GregorianCalendar();
			to.add(GregorianCalendar.DATE, 1);
		} else if (todayBackButton.getSelection()) {
			from = new GregorianCalendar();
			to = new GregorianCalendar();
		} else if (tomorrowBackButton.getSelection()) {
			from = new GregorianCalendar();
			from.add(GregorianCalendar.DATE, 1);
			to = new GregorianCalendar();
			to.add(GregorianCalendar.DATE, 1);
		}
		final Date tmpFrom = from.getTime();
		final Date tmpTo = to.getTime();
		final String tmpPerson = nameText.getText();
		final boolean nonPaid = nonPaidButton.getSelection();
		final boolean showAll = showAllButton.getSelection();
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				final Object[] orders = DBFactory.computeOrders(tmpFrom, tmpTo,
						tmpPerson, nonPaid, showAll);
				UIUtil.getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						getViewer().setInput(orders);
					}
				});
				return Status.OK_STATUS;
			}
		});
	}

	@Override
	protected void initialize(IMemento memento) {
		if (memento != null) {
			todayBackButton.setSelection(Boolean.getBoolean(memento
					.getString("todayBack")));
			tomorrowBackButton.setSelection(Boolean.getBoolean(memento
					.getString("tomorrowBack")));
			if (todayBackButton.getSelection()
					|| tomorrowBackButton.getSelection()) {
				toDateControl.setEnabled(false);
				fromDateControl.setEnabled(false);
			} else {
				toDateControl.setEnabled(true);
				fromDateControl.setEnabled(true);
			}
			nonPaidButton.setSelection(Boolean.getBoolean(memento
					.getString("nonPaid")));

			fromDateControl.setYear(Integer.parseInt(memento
					.getString("fromDateYear")));
			fromDateControl.setMonth(Integer.parseInt(memento
					.getString("fromDateMonth")));
			fromDateControl.setDay(Integer.parseInt(memento
					.getString("fromDateDay")));
			toDateControl.setYear(Integer.parseInt(memento
					.getString("toDateYear")));
			toDateControl.setMonth(Integer.parseInt(memento
					.getString("toDateMonth")));
			toDateControl.setDay(Integer.parseInt(memento
					.getString("toDateDay")));
			nameText.setText(memento.getString("personText"));
		} else {
			nonPaidButton.setSelection(true);
		}
		super.initialize(memento);
	}

	@Override
	public void saveState(IMemento memento) {
		memento.putString("todayBack", "" + todayBackButton.getSelection());
		memento.putString("tomorrowBack", ""
				+ tomorrowBackButton.getSelection());
		memento.putString("nonPaid", "" + nonPaidButton.getSelection());
		memento.putString("fromDateYear", "" + fromDateControl.getYear());
		memento.putString("fromDateMonth", "" + fromDateControl.getMonth());
		memento.putString("fromDateDay", "" + fromDateControl.getDay());
		memento.putString("toDateYear", "" + toDateControl.getYear());
		memento.putString("toDateMonth", "" + toDateControl.getMonth());
		memento.putString("toDateDay", "" + toDateControl.getDay());
		memento.putString("personText", nameText.getText());
		super.saveState(memento);
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
