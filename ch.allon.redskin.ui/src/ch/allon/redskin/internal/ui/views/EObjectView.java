package ch.allon.redskin.internal.ui.views;

import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import ch.allon.redskin.core.IJobRunnable;
import ch.allon.redskin.core.model.shop.provider.ShopItemProviderAdapterFactory;
import ch.allon.redskin.internal.ui.RedskinUIActivator;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.actions.EObjectAction;

public abstract class EObjectView extends ViewPart implements
		IEditingDomainProvider {

	private AdapterFactoryEditingDomain ed;
	private Viewer viewer;
	private IMemento memento;

	@Override
	final public void createPartControl(Composite parent) {
		Composite container = UIUtil.createStandardComposite(parent, 1);
		createToolBar(container);
		viewer = createViewer(container);
		createBottomBar(container);

		if (viewer instanceof StructuredViewer) {
			StructuredViewer structuredViewer = (StructuredViewer) viewer;
			structuredViewer
					.setContentProvider(new AdapterFactoryContentProvider(
							getEditingDomain().getAdapterFactory()));
			structuredViewer.setLabelProvider(new AdapterFactoryLabelProvider(
					getEditingDomain().getAdapterFactory()));
			structuredViewer.setUseHashlookup(true);
		}
		viewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		getEditingDomain().getCommandStack().addCommandStackListener(
				new CommandStackListener() {
					public void commandStackChanged(final EventObject event) {
						UIUtil.getDisplay().asyncExec(new Runnable() {
							public void run() {

								// Try to select the affected objects.
								//
								Command mostRecentCommand = ((CommandStack) event
										.getSource()).getMostRecentCommand();
								if (mostRecentCommand != null) {
									Collection<?> affectedObjects = mostRecentCommand
											.getAffectedObjects();
									if (affectedObjects != null
											&& !affectedObjects.isEmpty())
										viewer.setSelection(
												new StructuredSelection(
														affectedObjects
																.toArray()),
												true);
								}
							}
						});
					}
				});

		UIUtil.runUIJob(new IJobRunnable() {
			@Override
			public IStatus run(IProgressMonitor monitor) {
				final Object input = createInput(memento);
				UIUtil.getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						viewer.setInput(input);
					}
				});

				return Status.OK_STATUS;
			}
		});
		createContextMenu();

		initialize(memento);
	}

	protected void createBottomBar(Composite container) {
	}

	protected abstract Object createInput(IMemento memento);

	protected abstract Viewer createViewer(Composite parent);

	protected void createToolBar(Composite parent) {
		EObjectAction[] actions = createToolBarActions();
		if (actions == null || actions.length < 1)
			return;

		Composite c = UIUtil.createStandardComposite(parent, 1);

		for (final EObjectAction action : actions) {
			Button b = new Button(c, SWT.PUSH);
			b.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
					false));
			b.setToolTipText(action.getText());
			b
					.setImage(RedskinUIActivator.getImage(action
							.getActionImagePath()));
			b.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					action.setActivePart(null, EObjectView.this);
					action.run();
				}
			});
		}

		Label separator = new Label(c, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}

	protected EObjectAction[] createToolBarActions() {
		return null;
	}

	private void createContextMenu() {
		// Create menu manager.
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				mgr
						.add(new GroupMarker(
								IWorkbenchActionConstants.MB_ADDITIONS));
				EObjectAction[] actions = createContextMenuActions(viewer
						.getSelection());
				if (actions != null) {
					for (EObjectAction action : actions) {
						action.setActivePart(null, EObjectView.this);
						mgr.add(action);
					}
				}
			}
		});

		// Create menu.
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);

		// Register menu for extension.
		getSite().registerContextMenu(menuMgr, viewer);
	}

	protected EObjectAction[] createContextMenuActions(ISelection selection) {
		return null;
	}

	@Override
	final public void init(IViewSite site, IMemento memento)
			throws PartInitException {
		this.memento = memento;
		super.init(site, memento);
	}

	/**
	 * This method is used instead of init(..., IMemento memento) because it
	 * gets called when the viewer is initialized. Please be aware that the
	 * memento can be null.
	 * 
	 * @param memento
	 */
	protected void initialize(IMemento memento) {
		memento = null;
	}

	@Override
	public void setFocus() {
		if (viewer != null)
			viewer.getControl().setFocus();
	}

	@Override
	public AdapterFactoryEditingDomain getEditingDomain() {
		if (ed == null) {
			ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
			adapterFactory
					.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			adapterFactory
					.addAdapterFactory(getShopItemProviderAdapterFactory());
			adapterFactory
					.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

			BasicCommandStack commandStack = new BasicCommandStack();
			ed = new AdapterFactoryEditingDomain(adapterFactory, commandStack,
					new HashMap<Resource, Boolean>());
		}
		return ed;
	}

	protected ShopItemProviderAdapterFactory getShopItemProviderAdapterFactory() {
		return new ShopItemProviderAdapterFactory();
	}

	public Viewer getViewer() {
		return viewer;
	}
}
