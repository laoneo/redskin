/**
 * The Shop is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Shop is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Shop.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Allon Moritz
 * @copyright 2007-2009 Allon Moritz
 * @version 0.1.0
 */
package ch.allon.redskin.internal.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.progress.IWorkbenchSiteProgressService;

import ch.allon.redskin.core.IJobRunnable;

/**
 * @author Allon Moritz
 * 
 */
public class UIUtil {

	public static Display getDisplay() {
		Display d = Display.getCurrent();
		if (d == null)
			d = Display.getDefault();
		return d;
	}

	public static void runUIJob(final IJobRunnable runnable) {
		Job job = new Job("UI Job") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				return runnable.run(monitor);
			}
		};
		if (RedskinUIActivator.getWindow().getActivePage() != null
				&& RedskinUIActivator.getWindow().getActivePage()
						.getActivePart() != null) {
			IWorkbenchPart activePart = RedskinUIActivator.getWindow()
					.getActivePage().getActivePart();
			IWorkbenchSiteProgressService siteService = (IWorkbenchSiteProgressService) activePart
					.getSite().getAdapter(IWorkbenchSiteProgressService.class);
			siteService.schedule(job, 0, true);
		} else
			job.schedule();
	}

	public static Composite createStandardComposite(Composite parent, int cols) {
		Composite container = new Composite(parent, SWT.NULL);
		if (parent.getLayout() instanceof GridLayout)
			container
					.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		GridLayout layout = new GridLayout(cols, false);
		layout.horizontalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		container.setLayout(layout);
		return container;
	}

	public static void handleException(Exception e) {
		ErrorDialog.openError(getDisplay().getActiveShell(), "Error", e
				.getLocalizedMessage(), new Status(IStatus.ERROR,
				"ch.allon.redskin.ui", e.getLocalizedMessage()));
	}

}
