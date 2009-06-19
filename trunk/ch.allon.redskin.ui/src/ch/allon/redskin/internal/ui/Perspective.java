package ch.allon.redskin.internal.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		layout.addStandaloneView(RedskinUIActivator.ID_PRODUCT_VIEW, false,
				IPageLayout.LEFT, 0.3f, editorArea);
		layout.addStandaloneView(RedskinUIActivator.ID_PRICE_CATEGORY_VIEW, false,
				IPageLayout.BOTTOM, 0.7f, RedskinUIActivator.ID_PRODUCT_VIEW);
		layout.addStandaloneView(RedskinUIActivator.ID_WORK_VIEW, false,
				IPageLayout.LEFT, 0.7f, editorArea);
	}

}
