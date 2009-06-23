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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Allon Moritz
 * 
 */
public class EObjectCombo {

	private Map<Integer, EObject> references;
	private Combo combo;

	public EObjectCombo(Composite parent, List<EObject> childs) {
		createCombo(parent);
		loadCombo(childs);
	}

	/**
	 * @param object
	 * @param reference
	 */
	private void loadCombo(List<EObject> childs) {
		references = new HashMap<Integer, EObject>();

		int i = 0;
		for (EObject eObject : childs) {
			references.put(i, eObject);
			combo.add(eObject.toString(), i);
			i++;
		}
	}

	/**
	 * @param parent
	 * @param object
	 * @param reference
	 */
	private void createCombo(Composite parent) {
		combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}

	public EObject getSelectedChild() {
		return references.get(combo.getSelectionIndex());
	}

	public void select(EObject object) {
		for (EObject obj : references.values()) {
			if (obj.equals(object))
				combo.setText(obj.toString());
		}
	}
}
