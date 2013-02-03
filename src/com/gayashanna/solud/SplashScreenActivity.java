package com.gayashanna.solud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/***
 * This is the splash screen.
 * 
 * @author GayashanNA
 * 
 */
public class SplashScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(5000); // display time for the splash screen
				} catch (InterruptedException e) {
					// in case of an error exit from the application
					finish();
				} finally {
					Intent main = new Intent(
							"com.gayashanna.solud.HOMESCREENACTIVITY");
					startActivity(main);
				}
			}
		};
		timer.start();
	}

	/***
	 * If the application was paused by the OS call this method.
	 */
	@Override
	protected void onPause() {
		super.onPause();
		// exit from the application
		finish();
	}

}
