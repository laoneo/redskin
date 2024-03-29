package ch.allon.redskin.internal.core.model;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class RedskinModelActivator extends Plugin {
	// The plug-in ID
	public static final String PLUGIN_ID = "ch.allon.redskin.core.model";

	// The shared instance
	private static RedskinModelActivator plugin;

	/**
	 * The constructor
	 */
	public RedskinModelActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static RedskinModelActivator getDefault() {
		return plugin;
	}
}
