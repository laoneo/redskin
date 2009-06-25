package ch.allon.redskin.internal.ui.custom;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

public class CustomDialog extends Dialog {

	private final String title;

	protected CustomDialog(Shell shell, String title) {
		super(shell);
		this.title = title;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(title);
	}
}
