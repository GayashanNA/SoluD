package com.gayashanna.solud;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gayashanna.solud.logic.DialogBoxHandler;
import com.gayashanna.solud.logic.OCRBackgroungTask;

/***
 * This class is used to view the captured image when the user requests to
 * capture an image of the differential equation.
 * 
 * @author GayashanNA
 * 
 */
public class ViewCapturedImageActivity extends Activity implements
		OnClickListener {
	private ImageView ivCapturedImage;
	private TextView tvDecodedText;
	private Button bRecaptureImage, bEditEquation, bSolve;
	private Bitmap capturedImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.view_captured_image);
		initialize(savedInstanceState);
	}

	/***
	 * Initializes the elements in the activity. After initialization this
	 * method calls the <code>captureImage()</code> function to view the
	 * captured image in the Image view.
	 * 
	 * @param <code>Bundle</code> savedInstanceState
	 */
	private void initialize(Bundle savedInstanceState) {
		ivCapturedImage = (ImageView) findViewById(R.id.ivCapturedImage);
		tvDecodedText = (TextView) findViewById(R.id.tvDecodedText);
		bRecaptureImage = (Button) findViewById(R.id.bRecaptureImage);
		bEditEquation = (Button) findViewById(R.id.bEditDecodedText);
		bSolve = (Button) findViewById(R.id.bSolveThisEquation);
		// set on click listeners for the buttons
		bRecaptureImage.setOnClickListener(this);
		bEditEquation.setOnClickListener(this);
		bSolve.setOnClickListener(this);
		// capture the image
		captureImage();
	}

	/**
	 * Implements the actions that are run after each button press.
	 */
	public void onClick(View v) {
		Bundle equation = new Bundle();
		// put the decoded text into the bundle which will be sent to other
		// intents
		equation.putString("Equation", tvDecodedText.getText().toString());
		switch (v.getId()) {
		// when image re-capture button is pressed re-open the camera
		case R.id.bRecaptureImage:
			captureImage();
			break;
		// when solve equation button is pressed open the ViewSolutionActivity
		case R.id.bSolveThisEquation:
			if (checkInput()) {
				Intent intentToOpenSolutionView = new Intent(this,
						ViewSolutionActivity.class);
				intentToOpenSolutionView.putExtras(equation);
				startActivity(intentToOpenSolutionView);
			} else {
				Toast.makeText(
						this,
						"Differential equation is not valid. Please edit the equation to a valid differential equation.",
						10).show();
			}
			break;
		// when edit equation is pressed open the TextInputActivity to allow
		// editing the text
		case R.id.bEditDecodedText:
			Intent intentOpenInputView = new Intent(this,
					TextInputActivity.class);
			intentOpenInputView.putExtras(equation);
			startActivity(intentOpenInputView);
			break;
		}

	}

	/**
	 * This method is used to open the in-built camera application of the
	 * android device and returns that image to the intent so it can be used in
	 * imageDecode function. This method is also used to set the image of the
	 * Image view located in this activity.
	 */
	private void captureImage() {
		Intent intentCaptureImage = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intentCaptureImage, 0);
	}

	/***
	 * When the camera application returns the captured image, this method will
	 * display it in the ImageView
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			capturedImage = (Bitmap) data.getExtras().get("data");
			if (capturedImage == null) {
				displayImageCaptureError();
			} else {
				ivCapturedImage.setImageBitmap(capturedImage);
				OCRBackgroungTask backgroungTask = new OCRBackgroungTask(this,
						this);
				backgroungTask.execute(capturedImage);
			}
		}
	}

	/***
	 * Set the TextView element to the decoded text
	 * 
	 * @param text
	 */
	public void setDecodedText(String text) {
		tvDecodedText.setText(text);
	}

	/***
	 * Displays an error if image capture task failed.
	 */
	private void displayImageCaptureError() {
		DialogBoxHandler.getInstance().displayErrorDialogBox("ERROR",
				"Please re-capture the image.", this);
	}

	/***
	 * This method Validates the input to be a differential equation.
	 * 
	 * @return <code>boolean</code>
	 */
	private boolean checkInput() {
		if ("".contentEquals(tvDecodedText.getText().toString())) {
			return false;
		} else if (!(tvDecodedText.getText().toString().matches(".+=.+"))) {
			return false;
		}
		return true;
	}
}
