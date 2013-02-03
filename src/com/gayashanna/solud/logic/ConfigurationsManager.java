package com.gayashanna.solud.logic;

import com.gayashanna.solud.R;

import android.app.Activity;

/***
 * This class is responsible for acquiring the configurations from config.xml
 * file
 * 
 * @author GayashanNA
 * 
 */
public class ConfigurationsManager {
	private Activity activity;

	public ConfigurationsManager(Activity activity) {
		this.activity = activity;
	}

	/***
	 * Acquire the property by key value
	 * 
	 * @param property
	 * @return
	 */
	public String getProperty(int property) {
		return activity.getString(R.string.input);
	}
}
