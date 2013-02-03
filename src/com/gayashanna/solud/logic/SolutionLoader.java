package com.gayashanna.solud.logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.gayashanna.solud.ViewSolutionActivity;

/***
 * AsyncTask class to handle the acquisition of solutions. The solutions are
 * acquired in a separate thread to the UI of the application. While the
 * solutions are being acquired and loaded this class shows a spinner progress
 * dialog to the UI.
 * 
 * @author GayashanNA
 * 
 */
public class SolutionLoader extends AsyncTask<ViewSolutionActivity, Void, Void> {
	// progress dialog
	private ProgressDialog solutionLoadProgress;
	private ViewSolutionActivity vsActivity;
	private Solution solutionContainer;
	private Context context;

	/***
	 * Constructor needs to have the current activity and the context to be
	 * passed when creating the class.
	 * 
	 * @param currVSActivity
	 * @param currContext
	 */
	public SolutionLoader(ViewSolutionActivity currVSActivity,
			Context currContext) {
		this.vsActivity = currVSActivity;
		this.context = currContext;
	}

	/***
	 * Before executing the thread this method is called.
	 */
	@Override
	protected void onPreExecute() {
		// initiate the progress dialog
		solutionLoadProgress = new ProgressDialog(context);
		solutionLoadProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// do not allow the user to back away from the dialog box
		solutionLoadProgress.setCancelable(false);
		solutionLoadProgress.setMessage("Loading solution...");
		solutionLoadProgress.setIndeterminate(true);
		solutionLoadProgress.show();
	}

	/***
	 * After executing the thread and the result is retrieved this method is
	 * called.
	 */
	@Override
	protected void onPostExecute(Void result) {
		// load the solution to the UI
		if (solutionContainer != null) {
			vsActivity.setSolution(solutionContainer);
		} else {
			// If the connection was not available
			DialogBoxHandler
					.getInstance()
					.displayErrorDialogBox(
							"ERROR",
							"No Internet connection. Please connect your device to the Internet and retry.",
							vsActivity);
		}
		// discard the ProgressDialog
		solutionLoadProgress.dismiss();
	}

	/***
	 * This method is responsible for running the solution loading process in
	 * the background.
	 */
	@Override
	protected Void doInBackground(ViewSolutionActivity... params) {
		// acquire an instance of the ConnnectionChecker
		ConnectionChecker connectionChecker = ConnectionChecker.getInstance();
		// set its context
		connectionChecker.setContext(vsActivity);
		if (connectionChecker.isNetworkAvailable()) { // if the Internet
														// connection is
														// available
			solutionContainer = vsActivity.solveEquation(); // acquire solution
		} else {
			solutionContainer = null;
		}
		return null; // required by the method
	}

}
