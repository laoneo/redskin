/**
 * The Redskin Shop Application is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Redskin Shop Application is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Redskin Shop Application.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Allon Moritz
 * @copyright 2007-2009 Allon Moritz
 * @version 0.1.0
 */
package ch.allon.redskin.internal.ui.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import ch.allon.redskin.core.model.shop.PriceCategory;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.ui.Messages;

public class PriceCategoryDialog extends EObjectDialog {

	private TableViewer viewer;

	/**
	 * @param parentShell
	 * @param config
	 */
	public PriceCategoryDialog(Shell shell) {
		super(shell, Messages.PriceCategoryDialog_Title);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

		viewer = new TableViewer(container, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		PricesTableProvider provider = new PricesTableProvider();
		viewer.setContentProvider(provider);
		viewer.setLabelProvider(provider);
		viewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		viewer.setColumnProperties(new String[] {
				Messages.NewPriceCategoryAction_Column_Day,
				Messages.NewPriceCategoryAction_Column_Price });

		Table table = viewer.getTable();
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.NewPriceCategoryAction_Column_Day);
		c.setResizable(true);

		c = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(3, 100, true));
		c.setText(Messages.NewPriceCategoryAction_Column_Price);
		c.setResizable(true);

		// Column 4 : Percent complete (Text with digits only)
		TextCellEditor textEditor = new TextCellEditor(table);
		((Text) textEditor.getControl()).addVerifyListener(

		new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = "0123456789.".indexOf(e.text) >= 0; //$NON-NLS-1$
			}
		});
		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(table),
				textEditor });
		viewer.setCellModifier(new ICellModifier() {

			@Override
			public void modify(Object element, String property, Object value) {
				final IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				final Row row = (Row) selection.getFirstElement();
				if (row == null || value == null
						|| value.toString().length() == 0)
					return;
				row.price = Double.parseDouble((String) value);
				viewer.refresh(row);
			}

			@Override
			public Object getValue(Object element, String property) {
				if (property.equals(Messages.NewPriceCategoryAction_Column_Day))
					return "" + ((Row) element).pos; //$NON-NLS-1$
				else if (property
						.equals(Messages.NewPriceCategoryAction_Column_Price))
					return "" + ((Row) element).price; //$NON-NLS-1$
				return null;
			}

			@Override
			public boolean canModify(Object element, String property) {
				return property
						.equals(Messages.NewPriceCategoryAction_Column_Price);
			}
		});

		viewer.setInput(getNewObject());

		return container;
	}

	@Override
	protected void setDataInModelThread(Map<EStructuralFeature, Object> data) {
		PricesTableProvider provider = (PricesTableProvider) viewer
				.getContentProvider();
		List<Row> rows = provider.getData();
		PriceCategory cat = (PriceCategory) getNewObject();
		cat.getPrices().clear();
		for (Row row : rows) {
			cat.getPrices().add(row.price);
		}
		super.setDataInModelThread(data);
	}

	@Override
	protected boolean include(EAttribute attribute) {
		if (attribute.getFeatureID() == ShopPackage.PRICE_CATEGORY__PRICES)
			return false;
		return super.include(attribute);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button newPriceButton = createButton(parent,
				IDialogConstants.CLIENT_ID,
				Messages.NewPriceCategoryAction_New_Day_Action, true);
		newPriceButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				handleAddRow();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleAddRow();
			}
		});
		super.createButtonsForButtonBar(parent);
	}

	protected void handleAddRow() {
		PricesTableProvider provider = (PricesTableProvider) viewer
				.getContentProvider();
		List<Row> data = provider.getData();
		data.add(new Row(data.size(), 0.0));
		viewer.refresh();
		getShell().pack();
	}

	private class PricesTableProvider extends LabelProvider implements
			IStructuredContentProvider, ITableLabelProvider {

		private List<Row> data;

		@Override
		public Object[] getElements(Object inputElement) {
			if (data == null) {
				PriceCategory cat = (PriceCategory) inputElement;
				EList<Double> existingPrices = cat.getPrices();

				int size = existingPrices.size() < 6 ? 6 : existingPrices
						.size();
				data = new ArrayList<Row>(size);
				for (int i = 0; i < size; i++) {
					if (i < existingPrices.size()) {
						data.add(new Row(i, existingPrices.get(i)));
					} else
						data.add(new Row(i, 0.0));
				}
			}
			return data.toArray();
		}

		/**
		 * @return the data
		 */
		public List<Row> getData() {
			return data;
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Row row = (Row) element;
			if (columnIndex == 0) {
				if (row.pos == data.size() - 1)
					return Messages.NewPriceCategoryAction_Price_For_More_Days;
				return "" + (row.pos + 1); //$NON-NLS-1$
			} else
				return "" + row.price; //$NON-NLS-1$
		}
	}

	private class Row {
		int pos;
		double price;

		public Row(int pos, double price) {
			super();
			this.pos = pos;
			this.price = price;
		}
	}

}