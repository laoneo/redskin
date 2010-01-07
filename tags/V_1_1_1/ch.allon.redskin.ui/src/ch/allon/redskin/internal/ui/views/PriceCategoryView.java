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
package ch.allon.redskin.internal.ui.views;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.internal.ui.actions.EObjectAction;
import ch.allon.redskin.internal.ui.actions.EditPriceCategoryAction;
import ch.allon.redskin.internal.ui.actions.NewPriceCategoryAction;

/**
 * @author Allon Moritz
 * 
 */
public class PriceCategoryView extends EObjectView {

	@Override
	protected EObjectAction[] createToolBarActions() {
		return new EObjectAction[] { new NewPriceCategoryAction() };
	}

	@Override
	protected EObjectAction[] createContextMenuActions(ISelection selection) {
		if (selection.isEmpty()) {
			return new EObjectAction[] { new NewPriceCategoryAction() };
		}
		return super.createContextMenuActions(selection);
	}

	@Override
	protected Object createInput(IMemento memento) {
		return DBFactory.getPriceCategoryResource();
	}

	@Override
	protected StructuredViewer createViewer(Composite parent) {
		FilteredTree tree = new FilteredTree(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER, new PatternFilter(), true);
		tree.getViewer().addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				EditPriceCategoryAction action = new EditPriceCategoryAction();
				action.selectionChanged(null, event.getSelection());
				action.setActivePart(null, PriceCategoryView.this);
				action.run();
			}
		});
		return tree.getViewer();
	}

}
