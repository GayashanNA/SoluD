package com.gayashanna.solud;

import com.gayashanna.solud.logic.DialogBoxHandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

/***
 * 
 * @author GayashanNA
 * 
 */
public class HomeScreenActivity extends Activity implements OnClickListener {
	private ImageButton bTypeIn, bCapture, bHelp, bAbout, bExit;
	private ImageView soludLogo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		initialize();
		
	}

	private void initialize() {
		bExit = (ImageButton) findViewById(R.id.bHomeExit);
		bTypeIn = (ImageButton) findViewById(R.id.bHomeTypein);
		bCapture = (ImageButton) findViewById(R.id.bHomeCapture);
		bHelp = (ImageButton) findViewById(R.id.bHomeHelp);
		bAbout = (ImageButton) findViewById(R.id.bHomeAbout);
		soludLogo = (ImageView) findViewById(R.id.soludLogo);
		bExit.setOnClickListener(this);
		bTypeIn.setOnClickListener(this);
		bCapture.setOnClickListener(this);
		bHelp.setOnClickListener(this);
		bAbout.setOnClickListener(this);
		soludLogo.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intentToBeOpened = null;
		switch (v.getId()) {
		case R.id.bHomeExit:
			DialogBoxHandler.getInstance().showExitAlertBox(
					HomeScreenActivity.this);
			break;
		case R.id.bHomeTypein:
			intentToBeOpened = new Intent(HomeScreenActivity.this,
					TextInputActivity.class);
			startActivity(intentToBeOpened);
			break;
		case R.id.bHomeCapture:
			intentToBeOpened = new Intent(HomeScreenActivity.this,
					ViewCapturedImageActivity.class);
			startActivity(intentToBeOpened);
			break;
		case R.id.bHomeHelp:
			intentToBeOpened = new Intent(HomeScreenActivity.this,
					ViewHelpActivity.class);
			startActivity(intentToBeOpened);
			break;

		case R.id.bHomeAbout:
		default:
			intentToBeOpened = new Intent(HomeScreenActivity.this,
					ViewAboutActivity.class);
			startActivity(intentToBeOpened);
			break;
		}
	}

}
