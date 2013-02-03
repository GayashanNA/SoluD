package com.gayashanna.solud.logic;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/***
 * This class is used to check whether an Internet connection is available to
 * the device. This is a singleton class.
 * 
 * @author GayashanNA
 * 
 */
public class ConnectionChecker {

	private static ConnectionChecker connChecker = null;
	private Context context;

	/**
	 * private constructor
	 */
	private ConnectionChecker() {
	}

	/***
	 * This method is used to access the singleton instance of the
	 * ConnectionChecker class.
	 * 
	 * This method is synchronized.
	 * 
	 * @return <code>ConnectionChecker</code> Singleton instance of the
	 *         ConnectionChecker class
	 */
	public static synchronized ConnectionChecker getInstance() {
		if (connChecker == null) {
			connChecker = new ConnectionChecker();
		}
		return connChecker;
	}

	/***
	 * Acquire the network state from the device and check whether the Internet
	 * connection is available.
	 * 
	 * @return <code>boolean</code> <code>true</code> if a connection is
	 *         available
	 */
	public boolean isNetworkAvailable() {
		ConnectivityManager connectionManager = (ConnectivityManager) getContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
		// if no network is available networkInfo will be null
		// otherwise check if we are connected
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

	/***
	 * Get current context of the application
	 * 
	 * @return Context
	 */
	private Context getContext() {
		return context;
	}

	/***
	 * Set the current context of the application. This context is used to check
	 * the network state of the device.
	 * 
	 * @param context
	 */
	public void setContext(Context context) {
		this.context = context;
	}
}
