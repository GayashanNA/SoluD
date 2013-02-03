package com.gayashanna.solud;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.gayashanna.solud.logic.ConnectionChecker;
import com.gayashanna.solud.logic.DialogBoxHandler;
import com.gayashanna.solud.logic.Solution;

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
	private ProgressDialog solutionLoadProgress;
	private ViewSolutionActivity vsActivity;
	private Solution solutionContainer;
	private Context context;

	// Constructor sets the ViewSolutionActivity class and its current context
	public SolutionLoader(ViewSolutionActivity currVSActivity,
			Context currContext) {
		this.vsActivity = currVSActivity;
		this.context = currContext;
	}

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

	@Override
	protected Void doInBackground(ViewSolutionActivity... params) {
		ConnectionChecker connectionChecker = ConnectionChecker.getInstance();
		connectionChecker.setContext(vsActivity);
		if (connectionChecker.isNetworkAvailable()) {
			solutionContainer = vsActivity.solveEquation(); // acquire solution
		} else {
			solutionContainer = null;
		}
		return null; // required by the method
	}

}
