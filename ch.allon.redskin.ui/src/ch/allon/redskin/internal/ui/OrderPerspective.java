package ch.allon.redskin.internal.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import ch.allon.redskin.internal.ui.RedskinUIActivator;

public class OrderPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		layout.addPerspectiveShortcut(RedskinUIActivator.ID_WORK_PERSPECTIVE);

		layout.addView(RedskinUIActivator.ID_ORDER_LIST_VIEW,
				IPageLayout.RIGHT, 1.0f, editorArea);
	}

}
