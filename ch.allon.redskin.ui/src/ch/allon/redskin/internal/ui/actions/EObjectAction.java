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
package ch.allon.redskin.internal.ui.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Allon Moritz
 * 
 */
@SuppressWarnings("unchecked")
public abstract class EObjectAction extends Action implements
		IObjectActionDelegate, IEditingDomainProvider {

	private List<EObject> selectedObjects = Collections.EMPTY_LIST;
	private IViewPart view;

	/**
	 * @param selectedObject
	 */
	protected abstract void run(List<EObject> selectedObjects);

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		if (targetPart instanceof IViewPart)
			view = (IViewPart) targetPart;
	}

	@Override
	public void run(IAction action) {
		run(selectedObjects);
	}

	@Override
	public void run() {
		run(selectedObjects);
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
			selectedObjects = new ArrayList<EObject>(
					((IStructuredSelection) selection).toList());
		} else
			selectedObjects = Collections.EMPTY_LIST;
	}

	public Shell getShell() {
		return view.getSite().getShell();
	}

	@Override
	public EditingDomain getEditingDomain() {
		if (view instanceof IEditingDomainProvider)
			return ((IEditingDomainProvider) view).getEditingDomain();
		return null;
	}

}