package ch.allon.redskin.internal.core;

import java.util.Properties;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.eclipse.emf.teneo.hibernate.resource.SessionController;
import org.hibernate.cfg.Environment;
import org.osgi.framework.BundleContext;

import ch.allon.redskin.core.model.shop.ShopPackage;

/**
 * The activator class controls the plug-in life cycle
 */
public class RedskinCoreActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "ch.allon.shop.core";

	// The shared instance
	private static RedskinCoreActivator plugin;

	private static SessionController sessionController;

	/**
	 * The constructor
	 */
	public RedskinCoreActivator() {
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
		plugin = null;
		if (sessionController != null)
			sessionController.getSessionWrapper().close();
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static RedskinCoreActivator getDefault() {
		return plugin;
	}

	/**
	 * @return the sessionController
	 */
	public static SessionController getSessionController() {
		if (sessionController == null) {
			Properties props = new Properties();
			props.setProperty(Environment.DRIVER,
					"org.apache.derby.jdbc.EmbeddedDriver");
			props.setProperty(Environment.URL, "jdbc:derby:ShopDB;create=true");
			props.setProperty(Environment.DIALECT,
					org.hibernate.dialect.DerbyDialect.class.getName());
			props.setProperty(Environment.SHOW_SQL, "true");
			HbDataStore hbds = (HbDataStore) HbHelper.INSTANCE
					.createRegisterDataStore("ShopDB");
			hbds.setProperties(props);
			hbds.setEPackages(new EPackage[] { ShopPackage.eINSTANCE });
			hbds.initialize();

			sessionController = new SessionController();
			sessionController.setHbDataStore(hbds);
			SessionController.registerSessionController("ShopDB",
					sessionController);
		}
		return sessionController;
	}

}
