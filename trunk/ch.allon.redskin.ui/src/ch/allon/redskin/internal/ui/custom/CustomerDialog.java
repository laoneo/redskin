package ch.allon.redskin.internal.ui.custom;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

import ch.allon.redskin.core.model.shop.ShopPackage;

public class CustomerDialog extends EObjectDialog {

	public CustomerDialog(Shell parentShell, String title) {
		super(parentShell, title);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(400, 230);
	}

	@Override
	protected boolean include(EAttribute attribute) {
		return attribute.getFeatureID() != ShopPackage.CUSTOMER__COMMENTS;
	}

}
