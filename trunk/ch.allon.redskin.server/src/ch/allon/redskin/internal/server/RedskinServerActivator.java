package ch.allon.redskin.internal.server;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RedskinServerActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "ch.allon.redskin.server";

	// The shared instance
	private static RedskinServerActivator plugin;
	
	/**
	 * The constructor
	 */
	public RedskinServerActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
	public static RedskinServerActivator getDefault() {
		return plugin;
	}

}
