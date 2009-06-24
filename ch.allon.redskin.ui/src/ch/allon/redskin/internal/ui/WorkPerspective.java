package ch.allon.redskin.internal.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import ch.allon.redskin.internal.ui.RedskinUIActivator;

public class WorkPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(false);

		layout
				.addPerspectiveShortcut(RedskinUIActivator.ID_ORDE_LIST_PERSPECTIVE);

		layout.addView(RedskinUIActivator.ID_WORK_VIEW, IPageLayout.RIGHT,
				0.7f, editorArea);
		layout.addStandaloneView(RedskinUIActivator.ID_PRODUCT_VIEW, false,
				IPageLayout.LEFT, 0.3f, RedskinUIActivator.ID_WORK_VIEW);
		layout.addStandaloneView(RedskinUIActivator.ID_PRICE_CATEGORY_VIEW,
				false, IPageLayout.BOTTOM, 0.7f,
				RedskinUIActivator.ID_PRODUCT_VIEW);
	}

}
