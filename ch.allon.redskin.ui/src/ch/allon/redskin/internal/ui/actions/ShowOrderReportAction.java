package ch.allon.redskin.internal.ui.actions;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import ch.allon.redskin.core.RedskinCore;
import ch.allon.redskin.core.model.shop.Customer;
import ch.allon.redskin.core.model.shop.Order;
import ch.allon.redskin.internal.ui.RedskinUIActivator;
import ch.allon.redskin.internal.ui.custom.CustomDialog;

public class ShowOrderReportAction extends EObjectAction {

	@Override
	protected void run(List<EObject> selectedObjects) {
		if (selectedObjects.isEmpty()
				|| !(selectedObjects.get(0) instanceof Order))
			return;
		BrowserDialog dialog = new BrowserDialog(getShell(), "Report");
		dialog.setText(createReport((Order) selectedObjects.get(0)));
		dialog.open();
	}

	private String createReport(Order order) {
		EngineConfig config = new EngineConfig();

		// Create the report engine
		IReportEngineFactory factory = (IReportEngineFactory) Platform
				.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
		IReportEngine engine = factory.createReportEngine(config);

		IReportRunnable design = null;
		try {
			// Open a report design - use design to modify design, retrieve
			// embedded images etc.
			String report = FileLocator.toFileURL(
					FileLocator.find(RedskinUIActivator.getDefault()
							.getBundle(), new Path("report/redskin.rptdesign"),
							null)).getPath();
			FileInputStream fs = new FileInputStream(report);
			design = engine.openReportDesign(fs);
			IRunAndRenderTask task = engine.createRunAndRenderTask(design);
			Customer c = order.getCustomer();
			task.setParameterValue("surname", c == null ? "" : c.getSurname());
			task.setParameterValue("familyname", c == null ? "" : c
					.getFamilyName());
			task.setParameterValue("telephone", c == null ? "" : c
					.getTelephoneNr());
			task.setParameterValue("address", c == null ? "" : c.getAddress());
			task.setParameterValue("hotel", c == null ? "" : c.getHotel());
			task.setParameterValue("ordernr", order.getNumber());

			// Set rendering options - such as file or stream output,
			// output format, whether it is embeddable, etc
			IRenderOption options = new HTMLRenderOption();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			options.setOutputStream(bos);
			options.setOutputFormat("html");

			task.setRenderOption(options);

			// run the report and destroy the engine
			task.run();
			task.close();

			// set Browser text accordingly
			return bos.toString("utf-8");
		} catch (Exception e) {
			RedskinCore.handleException(e);
		} finally {
			engine.destroy();
		}
		return "";
	}

	private class BrowserDialog extends CustomDialog {

		private String text;
		private Browser browser;

		protected BrowserDialog(Shell shell, String title) {
			super(shell, title);
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite container = (Composite) super.createDialogArea(parent);
			browser = new Browser(container, SWT.NONE);
			browser.setLayoutData(new GridData(GridData.FILL_BOTH));
			browser.setText(text);

			return container;
		}

		public void setText(String text) {
			this.text = text;
		}

		@Override
		protected Point getInitialSize() {
			return new Point(840, 500);
		}

		@Override
		protected void createButtonsForButtonBar(Composite parent) {
			Button printButton = createButton(parent,
					IDialogConstants.CLIENT_ID, "Drucken", false);
			printButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					browser.execute("window.print();");
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			super.createButtonsForButtonBar(parent);
		}

	}

}
