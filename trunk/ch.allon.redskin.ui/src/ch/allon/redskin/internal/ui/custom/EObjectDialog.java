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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ch.allon.redskin.core.RedskinCore;
import ch.allon.redskin.core.model.shop.ShopPackage;
import ch.allon.redskin.internal.ui.FieldMessages;
import ch.allon.redskin.internal.ui.IJobRunnable;
import ch.allon.redskin.internal.ui.UIUtil;

/**
 * @author Allon Moritz
 * 
 */
public class EObjectDialog extends CustomDialog {

	private EObject newObject;
	private Map<EStructuralFeature, Object> fields = new HashMap<EStructuralFeature, Object>();

	/**
	 * @param parentShell
	 * @param title
	 */
	public EObjectDialog(Shell parentShell, String title) {
		super(parentShell, title);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite c = (Composite) super.createDialogArea(parent);
		((GridLayout) c.getLayout()).numColumns = 2;
		EList<EAttribute> attributes = newObject.eClass().getEAttributes();
		for (EAttribute attribute : attributes) {
			if (!include(attribute))
				continue;
			if (!attribute.isMany())
				createTextField(c, getFieldText(attribute) + ":", attribute); //$NON-NLS-1$
			else
				createList(c, getFieldText(attribute) + ":", attribute); //$NON-NLS-1$
		}

		EList<EReference> references = newObject.eClass().getEReferences();
		for (EReference reference : references) {
			List<EObject> childs = getChilds(newObject, reference);
			if (childs != null) {
				Label label = new Label(c, SWT.NONE);
				label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
						false, false));
				label.setText(getFieldText(reference) + ":"); //$NON-NLS-1$
				EObjectCombo combo = new EObjectCombo(c, childs);
				combo.select((EObject) newObject.eGet(reference));
				fields.put(reference, combo);
			}
		}
		return c;
	}

	protected boolean include(EAttribute attribute) {
		return true;
	}

	@SuppressWarnings("unchecked")
	private void createList(Composite c, String text, EAttribute attribute) {
		Label label = new Label(c, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		label.setText(text);

		org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(c,
				SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		EList<Object> data = (EList<Object>) newObject.eGet(attribute);
		for (Object obj : data) {
			list.add(obj.toString());
		}
	}

	private String getFieldText(EStructuralFeature feature) {
		Field[] fieldMessages = FieldMessages.class.getFields();
		for (int i = 0; i < fieldMessages.length; i++) {
			Field field = fieldMessages[i];
			try {
				Field literal = ShopPackage.Literals.class.getField(field
						.getName());
				Object literalAttribute = literal.get(null);
				if (literalAttribute != null) {
					EStructuralFeature f = (EStructuralFeature) literalAttribute;
					if (f.getFeatureID() == feature.getFeatureID()
							&& f.getContainerClass().equals(
									feature.getContainerClass()))
						return (String) field.get(null);
				}
			} catch (SecurityException e) {
				RedskinCore.handleException(e);
			} catch (NoSuchFieldException e) {
				RedskinCore.handleException(e);
			} catch (IllegalArgumentException e) {
				RedskinCore.handleException(e);
			} catch (IllegalAccessException e) {
				RedskinCore.handleException(e);
			}
		}
		return null;
	}

	private void createTextField(Composite parent, String text,
			EAttribute attribute) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		label.setText(text);
		Text textField = new Text(parent, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		textField
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		Object contents = newObject.eGet(attribute);
		if (contents != null)
			textField.setText(contents.toString());
		if (attribute.getEType().getClassifierID() == EcorePackage.EINTEGER_OBJECT) {
			textField.addVerifyListener(new VerifyListener() {
				@Override
				public void verifyText(VerifyEvent e) {
					try {
						Integer.parseInt(e.text);
					} catch (NumberFormatException e1) {
						e.doit = false;
					}
				}
			});
		}

		textField.setEnabled(isFieldEditable(attribute));
		fields.put(attribute, textField);
	}

	protected boolean isFieldEditable(EAttribute attribute) {
		return true;
	}

	protected List<EObject> getChilds(EObject object, EReference reference) {
		return null;
	}

	@Override
	protected void okPressed() {
		final Map<EStructuralFeature, Object> data = collectDataInUIThread();
		UIUtil.runUIJob(new IJobRunnable() {

			@Override
			public IStatus run(IProgressMonitor monitor) {
				setDataInModelThread(data);
				return Status.OK_STATUS;
			}
		});
		super.okPressed();
	}

	protected void setDataInModelThread(
			final Map<EStructuralFeature, Object> data) {
		for (EStructuralFeature feature : data.keySet())
			newObject.eSet(feature, data.get(feature));
	}

	protected Map<EStructuralFeature, Object> collectDataInUIThread() {
		final Map<EStructuralFeature, Object> data = new HashMap<EStructuralFeature, Object>();
		EList<EAttribute> attributes = newObject.eClass().getEAllAttributes();
		for (EAttribute attribute : attributes) {
			Object control = fields.get(attribute);
			Class<?> instanceClass = attribute.getEType().getInstanceClass();
			if (control instanceof Text) {
				Object value = null;
				if (instanceClass.equals(String.class))
					value = ((Text) control).getText();
				if (instanceClass.equals(Integer.class))
					try {
						value = new Integer(((Text) control).getText());
					} catch (NumberFormatException e) {
						value = 0;
					}
				if (value != null)
					data.put(attribute, value);
				else
					System.err.println("Missing value"); //$NON-NLS-1$
			}
		}

		EList<EReference> references = newObject.eClass().getEReferences();
		for (EReference reference : references) {
			Object control = fields.get(reference);
			if (control instanceof EObjectCombo) {
				data
						.put(reference, ((EObjectCombo) control)
								.getSelectedChild());
			}
		}
		return data;
	}

	/**
	 * @param newObject
	 *            the newObject to set
	 */
	public void setNewObject(EObject newObject) {
		this.newObject = newObject;
	}

	/**
	 * @return the newObject
	 */
	public EObject getNewObject() {
		return newObject;
	}
}
