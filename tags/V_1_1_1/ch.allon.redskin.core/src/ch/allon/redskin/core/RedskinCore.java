package ch.allon.redskin.core;

import org.hibernate.exception.ConstraintViolationException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import ch.allon.redskin.internal.core.RedskinCoreActivator;

public class RedskinCore {

	public static void handleException(Exception exception) {
		BundleContext cx = RedskinCoreActivator.getDefault().getBundle()
				.getBundleContext();
		ServiceReference reference = cx.getServiceReference(IErrorService.class
				.getName());
		if (exception instanceof ConstraintViolationException)
			exception = new Exception(
					"Element kann nicht gelöscht werden da es noch von einem anderen Element gebraucht wird.",
					exception);
		((IErrorService) cx.getService(reference)).handleException(exception);
	}
}
