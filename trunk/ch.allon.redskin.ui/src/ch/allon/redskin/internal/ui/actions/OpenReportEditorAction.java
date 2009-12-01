package ch.allon.redskin.internal.ui.actions;

import java.io.File;

import org.eclipse.birt.report.designer.internal.ui.editors.ReportEditorInput;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ch.allon.redskin.internal.ui.RedskinUIActivator;

public class OpenReportEditorAction extends Action {

	public OpenReportEditorAction() {
		setEnabled(true);
		setText("Report öffnen");
	}

	/*
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}

	/*
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		IEditorInput input = new ReportEditorInput(
				new File(Platform.getInstallLocation().getURL().toString().substring(5)+"\\redskin.rptdesign"));
		IEditorDescriptor editorDesc = getEditorDescriptor(input, OpenStrategy
				.activateOnOpen());
		try {
			RedskinUIActivator.getWindow().getActivePage().openEditor(input,
					editorDesc.getId());
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private IEditorDescriptor getEditorDescriptor(IEditorInput input,
			boolean determineContentType) {
		if (input == null) {
			throw new IllegalArgumentException();
		}
		IContentType contentType = Platform.getContentTypeManager()
				.findContentTypeFor(input.getName());
		IEditorRegistry editorReg = PlatformUI.getWorkbench()
				.getEditorRegistry();
		return editorReg.getDefaultEditor(input.getName(), contentType);
	}

}
