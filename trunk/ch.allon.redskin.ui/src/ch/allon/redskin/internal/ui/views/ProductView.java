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
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.IJobRunnable;
import ch.allon.redskin.core.model.shop.ProductCategory;
import ch.allon.redskin.core.model.shop.provider.ShopItemProviderAdapterFactory;
import ch.allon.redskin.internal.ui.Messages;
import ch.allon.redskin.internal.ui.RedskinUIActivator;
import ch.allon.redskin.internal.ui.UIUtil;
import ch.allon.redskin.internal.ui.actions.NewProductCategoryAction;

public class ProductView extends ViewPart implements IEditingDomainProvider {

	private TreeViewer viewer;
	private AdapterFactoryEditingDomain ed;

	public ProductView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite container = UIUtil.createStandardComposite(parent, 1);
		createToolBar(container);
		FilteredTree tree = new FilteredTree(container, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER,
				new PatternFilter(), true);
		viewer = tree.getViewer();
		viewer.setContentProvider(new AdapterFactoryContentProvider(
				getEditingDomain().getAdapterFactory()));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getEditingDomain().getAdapterFactory()));
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
				final ProductCategory rootNode = DBFactory
						.getProductsRootNode();
				UIUtil.getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						viewer.setInput(rootNode);
						viewer.expandAll();
					}
				});

				return Status.OK_STATUS;
			}
		});
		createContextMenu();
	}

	private void createToolBar(Composite parent) {
		Composite c = UIUtil.createStandardComposite(parent, 1);

		Button addCategory = new Button(c, SWT.PUSH);
		addCategory.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER,
				false, false));
		addCategory.setToolTipText(Messages.NewCategoryAction_Titel);
		addCategory.setImage(RedskinUIActivator
				.getImage("actions/new_product_category.gif")); //$NON-NLS-1$
		addCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewProductCategoryAction action = new NewProductCategoryAction();
				action.setActivePart(null, ProductView.this);
				action.run();
			}
		});
		Label separator = new Label(c, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
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
				if (viewer.getSelection().isEmpty()) {
					NewProductCategoryAction action = new NewProductCategoryAction();
					action.setActivePart(null, ProductView.this);
					mgr.add(action);
				}
			}
		});

		// Create menu.
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);

		// Register menu for extension.
		getSite().registerContextMenu(menuMgr, viewer);
	}

	@Override
	public void setFocus() {
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
					.addAdapterFactory(new ShopItemProviderAdapterFactory());
			adapterFactory
					.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

			BasicCommandStack commandStack = new BasicCommandStack();
			ed = new AdapterFactoryEditingDomain(adapterFactory, commandStack,
					new HashMap<Resource, Boolean>());
		}
		return ed;
	}
}