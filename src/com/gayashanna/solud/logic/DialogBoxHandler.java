package com.gayashanna.solud.logic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.gayashanna.solud.R;

/***
 * This class is responsible for handling the dialog boxes that appear in the
 * application. This is a singleton class where only one instance of the class
 * is existing.
 * 
 * @author GayashanNA
 * 
 */
public class DialogBoxHandler {
	private static DialogBoxHandler dialogHandler = null;

	/***
	 * Private constructor
	 */
	private DialogBoxHandler() {
	}

	/***
	 * Provides singleton access to the class.
	 * 
	 * @return DialogBoxHandler
	 */
	public static synchronized DialogBoxHandler getInstance() {
		if (dialogHandler == null) {
			dialogHandler = new DialogBoxHandler();
		}
		return dialogHandler;
	}

	/***
	 * This method is used to open a custom dialog box with a specific image.
	 * 
	 * @param context
	 *            the context where the dialog box is opened.
	 * @param image
	 *            The bitmap image to be set at the dialog box
	 * @param title
	 *            The title of the dialog box
	 */
	public void openImageDialogBox(Context context, Bitmap image, String title) {
		final Dialog imageDalogBox = new Dialog(context);
		imageDalogBox.setContentView(R.layout.image_dialog);
		// get the image view in the dialog box layout
		ImageView ivOpenedImage = (ImageView) imageDalogBox
				.findViewById(R.id.ivOpenedImage);
		// get the button in the dialog box layout
		Button bOK = (Button) imageDalogBox.findViewById(R.id.b_ImageDialog_OK);
		// set image from bitmap
		ivOpenedImage.setImageBitmap(image);
		imageDalogBox.setTitle(title);
		// set the OK button
		bOK.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				imageDalogBox.dismiss();
			}
		});
		imageDalogBox.show();
	}

	/**
	 * Implements the alert box (dialog box) to confirm the exit operation from
	 * the user.
	 * 
	 * @param activity
	 *            This is preferably the activity of the application.
	 */
	public void showExitAlertBox(final Activity activity) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(R.string.exitDialogMessage);
		builder.setCancelable(false); // so the user can not escape the alert by
										// pressing the back button
		// yes button
		builder.setPositiveButton(R.string.yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						activity.finish();
					}
				});
		// no button
		builder.setNegativeButton(R.string.no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog exitAlert = builder.create();
		// show the dialog box
		exitAlert.show();
	}

	/***
	 * This method is used to display an Error dialog box
	 * 
	 * @param title
	 *            Title of the dialog
	 * @param message
	 *            Message to be displayed
	 * @param activity
	 *            Current activity
	 */
	public void displayErrorDialogBox(String title, String message,
			final Activity activity) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(message);
		builder.setTitle(title);
		builder.setCancelable(false); // so the user can not escape the alert by
										// pressing the back button
		// OK button
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog exitAlert = builder.create();
		exitAlert.show();
	}

	/***
	 * /*** This method is used to display an Error dialog box
	 * 
	 * @param title
	 *            Title of the dialog
	 * @param message
	 *            Message to be displayed
	 * @param context
	 *            Current context
	 */
	public void displayErrorDialogBox(String title, String message,
			final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setTitle(title);
		builder.setCancelable(false); // so the user can not escape the alert by
										// pressing the back button
		// OK button
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog exitAlert = builder.create();
		exitAlert.show();
	}
}
