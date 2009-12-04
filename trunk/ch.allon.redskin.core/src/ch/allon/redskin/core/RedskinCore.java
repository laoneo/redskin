package ch.allon.redskin.core;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import ch.allon.redskin.internal.core.RedskinCoreActivator;

public class RedskinCore {

	public static void handleException(Exception exception) {
		BundleContext cx = RedskinCoreActivator.getDefault().getBundle()
				.getBundleContext();
		ServiceReference reference = cx.getServiceReference(IErrorService.class
				.getName());
		((IErrorService) cx.getService(reference)).handleException(exception);
	}
}
