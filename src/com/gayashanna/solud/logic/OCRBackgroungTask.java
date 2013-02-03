package com.gayashanna.solud.logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.gayashanna.solud.ViewCapturedImageActivity;

/***
 * This class is responsible for running a the OCR decoding process on a
 * background thread while displaying a progress dialog on the foreground.
 * 
 * @author GayashanNA
 * 
 */
public class OCRBackgroungTask extends AsyncTask<Bitmap, Void, Void> {
	private ViewCapturedImageActivity vcActivity;
	private Context context;
	private String decodedText;
	private ProgressDialog ocrProgress;

	/***
	 * Constructor needs to have the current activity and the context to be
	 * passed when creating the class.
	 * 
	 * @param vcActivity
	 * @param currContext
	 */
	public OCRBackgroungTask(ViewCapturedImageActivity vcActivity,
			Context currContext) {
		this.vcActivity = vcActivity;
		this.context = currContext;
	}

	/***
	 * After executing the thread and the result is retrieved this method is
	 * called.
	 */
	@Override
	protected void onPostExecute(Void result) {
		if (decodedText != null) { // if the decoded text is retrieved
			// set text on the activity
			vcActivity.setDecodedText(decodedText);
			// discard the progress dialog box
			ocrProgress.dismiss();
		} else {
			// If the OCR was not successful
			DialogBoxHandler.getInstance().displayErrorDialogBox("ERROR",
					"OCR task was unsuccessful. Please re-try.", vcActivity);
		}
	}

	/***
	 * Before executing the thread this method is called.
	 */
	@Override
	protected void onPreExecute() {
		// initiate the progress dialog
		ocrProgress = new ProgressDialog(context);
		ocrProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// do not allow the user to back away from the dialog box
		ocrProgress.setCancelable(false);
		ocrProgress.setMessage("Decoding equation...");
		ocrProgress.setIndeterminate(true);
		// display the dialog box
		ocrProgress.show();
	}

	/***
	 * This method is responsible for running the OCR decoding process in the
	 * background.
	 */
	@Override
	protected Void doInBackground(Bitmap... params) {
		// Instantiate an OCRHandler
		OCRHandler ocrHandler = new OCRHandler();
		if (params[0] == null) { // if the image was null return null
			decodedText = null;
		} else {
			// initiate OCR process
			decodedText = ocrHandler.getDecodedText(params[0]);
		}
		return null; // required by the method
	}

}
