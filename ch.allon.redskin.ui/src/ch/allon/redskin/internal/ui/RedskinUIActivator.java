package ch.allon.redskin.internal.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import ch.allon.redskin.core.DBFactory;
import ch.allon.redskin.core.IErrorService;

/**
 * The activator class controls the plug-in life cycle
 */
public class RedskinUIActivator extends AbstractUIPlugin {

	// plugin wide id's
	public static final String PLUGIN_ID = "ch.allon.redskin.ui"; //$NON-NLS-1$
	public static final String ID_WORK_VIEW = "ch.allon.redskin.ui.views.WorkView"; //$NON-NLS-1$
	public static final String ID_PRODUCT_VIEW = "ch.allon.redskin.ui.views.ProductView"; //$NON-NLS-1$
	public static final String ID_PRICE_CATEGORY_VIEW = "ch.allon.redskin.ui.views.PriceCategoryView"; //$NON-NLS-1$
	public static final String ID_CUSTOMER_VIEW = "ch.allon.redskin.ui.views.CustomerView"; //$NON-NLS-1$
	public static final String ID_ORDER_LIST_VIEW = "ch.allon.redskin.ui.views.OrderListView"; //$NON-NLS-1$
	public static final String ID_WORK_PERSPECTIVE = "ch.allon.redskin.ui.WorkPerspective"; //$NON-NLS-1$
	public static final String ID_ORDER_PERSPECTIVE = "ch.allon.redskin.ui.OrderPerspective"; //$NON-NLS-1$
	public static final String ID_CUSTOMER_PERSPECTIVE = "ch.allon.redskin.ui.CustomerPerspective"; //$NON-NLS-1$

	// The shared instance
	private static RedskinUIActivator plugin;
	private static ImageRegistry imageRegistry;

	/**
	 * The constructor
	 */
	public RedskinUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		context.registerService(IErrorService.class.getName(),
				new IErrorService() {
					public void handleException(final Exception exception) {
						UIUtil.getDisplay().asyncExec(new Runnable() {

							@Override
							public void run() {
								String message = exception.getMessage();
								if (exception.getLocalizedMessage() != null
										&& exception.getLocalizedMessage()
												.length() > 0)
									message = exception.getLocalizedMessage();
								ErrorDialog
										.openError(
												UIUtil.getDisplay()
														.getActiveShell(),
												Messages.RedskinUIActivator_Error_Dialog_Title,
												Messages.RedskinUIActivator_Error_Message,
												new Status(IStatus.ERROR,
														"ch.allon.redskin.ui", //$NON-NLS-1$
														message));
							}
						});
					}
				}, null);
		// init the resources that they will be present operating on the model
		DBFactory.getOrdersResource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static RedskinUIActivator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static Image getImage(String path) {
		if (imageRegistry == null)
			imageRegistry = getDefault().createImageRegistry();
		Image image = imageRegistry.get(path);
		if (image == null) {
			ImageDescriptor desc = getImageDescriptor(path.startsWith("icons/") ? path : "icons/" + path); //$NON-NLS-1$ //$NON-NLS-2$
			image = desc.createImage();
			imageRegistry.put(path, image);
		}
		return image;
	}

	public static IWorkbenchWindow getWindow() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow();
	}
}
