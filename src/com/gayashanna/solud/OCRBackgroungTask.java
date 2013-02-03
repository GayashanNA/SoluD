package com.gayashanna.solud;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.gayashanna.solud.logic.DialogBoxHandler;
import com.gayashanna.solud.logic.OCRHandler;

public class OCRBackgroungTask extends AsyncTask<Bitmap, Void, Void> {
	private ViewCapturedImageActivity vcActivity;
	private Context context;
	private String decodedText;
	private ProgressDialog ocrProgress;

	public OCRBackgroungTask(ViewCapturedImageActivity vcActivity,
			Context currContext) {
		this.vcActivity = vcActivity;
		this.context = currContext;
	}

	@Override
	protected void onPostExecute(Void result) {
		if (decodedText != null) {
			vcActivity.setDecodedText(decodedText);
			ocrProgress.dismiss();
		} else {
			// If the OCR was not successful
			DialogBoxHandler
					.getInstance()
					.displayErrorDialogBox(
							"ERROR",
							"OCR task was unsuccessful. Please re-try.",
							vcActivity);
		}
	}

	@Override
	protected void onPreExecute() {
		// initiate the progress dialog
		ocrProgress = new ProgressDialog(context);
		ocrProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// do not allow the user to back away from the dialog box
		ocrProgress.setCancelable(false);
		ocrProgress.setMessage("Decoding equation...");
		ocrProgress.setIndeterminate(true);
		ocrProgress.show();
	}

	@Override
	protected Void doInBackground(Bitmap... params) {
		OCRHandler ocrHandler = new OCRHandler();
		if (params[0] == null) {
			decodedText = null;
		} else {
			decodedText = ocrHandler.getDecodedText(params[0]);
		}
		return null;
	}

}
