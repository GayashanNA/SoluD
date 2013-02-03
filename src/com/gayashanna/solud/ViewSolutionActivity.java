package com.gayashanna.solud;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.gayashanna.solud.exceptions.ConnectionUnavailableException;
import com.gayashanna.solud.exceptions.ImageURLNotFoundException;
import com.gayashanna.solud.logic.DialogBoxHandler;
import com.gayashanna.solud.logic.ImageHandler;
import com.gayashanna.solud.logic.Solution;
import com.gayashanna.solud.logic.SolutionHandler;
import com.gayashanna.solud.logic.SolutionLoader;

/***
 * This class is used to display the solution of the equation. Solution is
 * acquired from the Wolfram | Alpha OMCE.
 * 
 * @author GayashanNA
 * 
 */
public class ViewSolutionActivity extends Activity implements OnClickListener {

	private TextView solutionText, solutionStatus, inputInterpretation,
			classification;
	private ImageView ivSolution, ivGraph;
	private ImageHandler imageHandler;
	private SolutionHandler solutionHandler;
	private Bitmap solution, graph;
	private String equation;
	private Solution solutionContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_solution);
		// initialize the UI
		initialize();
	}

	/***
	 * initializing the local variables.
	 */
	private void initialize() {
		// create a new ImageHandler
		imageHandler = new ImageHandler();
		solutionText = (TextView) findViewById(R.id.solutionText);
		solutionStatus = (TextView) findViewById(R.id.solutionStatus);
		inputInterpretation = (TextView) findViewById(R.id.inputInterpret);
		classification = (TextView) findViewById(R.id.classification);
		ivSolution = (ImageView) findViewById(R.id.ivSolution);
		ivGraph = (ImageView) findViewById(R.id.ivGraph);
		// initialize bitmaps with available resources
		solution = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		graph = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		// initialize click-able items
		ivSolution.setOnClickListener(this);
		ivGraph.setOnClickListener(this);
		// acquire the equation from the TextInputActivity
		equation = getIntent().getExtras().getString("Equation");
		// acquire the solution and display loading screen in two threads
		SolutionLoader loader = new SolutionLoader(this, this);
		loader.execute(this);
	}

	/***
	 * Set the elements in the view with the content from the acquired solution.
	 * 
	 * @param <code>Solution</code> solutionContainer
	 */
	public void setSolution(Solution solutionContainer) {
		solutionStatus.setText(solutionContainer.getSolutionStatus());
		inputInterpretation.setText(solutionContainer.getInputInterpretation());
		classification.setText(solutionContainer.getClassification());
		solutionText.setText(solutionContainer.getSolution());
		// acquire the images from ImageHandler class
		try {
			solution = imageHandler.getBitmap(solutionContainer
					.getSolutionImgURL());
			graph = imageHandler.getBitmap(solutionContainer
					.getSolutionGraphURL());
		} catch (NullPointerException e) {
			DialogBoxHandler.getInstance().displayErrorDialogBox("ERROR",
					"Did not receive an image.", this);
		} catch (ImageURLNotFoundException e) {
			DialogBoxHandler.getInstance().displayErrorDialogBox("ERROR",
					"Unable to retrieve the image(s).", this);
		} catch (ClientProtocolException e) {
			DialogBoxHandler
					.getInstance()
					.displayErrorDialogBox(
							"ERROR",
							"Unable to connect. Please check your Internet connection.",
							this);
		} catch (URISyntaxException e) {
			DialogBoxHandler.getInstance().displayErrorDialogBox("ERROR",
					"Unable to form the URL. Please re-try", this);
		} catch (IOException e) {
			DialogBoxHandler.getInstance().displayErrorDialogBox("ERROR",
					"Please re-try.", this);
		} finally {
			// set the image views with the acquired image
			ivSolution.setImageBitmap(solution);
			ivGraph.setImageBitmap(graph);
		}
	}

	/***
	 * Handle the click events.
	 */
	public void onClick(View view) {
		switch (view.getId()) {
		// if solution graph is clicked
		case R.id.ivSolution:
			DialogBoxHandler.getInstance().openImageDialogBox(
					ViewSolutionActivity.this, solution,"Solution:"); // display
																		// the
																		// solution
																		// image
																		// in a
																		// separate
																		// dialog
																		// box
			break;
		// if graph is clicked
		case R.id.ivGraph:
			DialogBoxHandler.getInstance().openImageDialogBox(
					ViewSolutionActivity.this, graph,
					"Graph(s) of the Solution:");
			break;
		}

	}

	/**
	 * Acquire the solution bundle
	 * 
	 * @return
	 */
	public Solution solveEquation() {
		// initiate the SolutionHandler
		solutionHandler = new SolutionHandler();
		try {
			// set the solution
			solutionContainer = solutionHandler.getSolution(equation);
		} catch (ConnectionUnavailableException e) {
			DialogBoxHandler
					.getInstance()
					.displayErrorDialogBox(
							"ERROR",
							"Connection is not available. Please connect the device to an Internet connection",
							this); // if the connection was not available
		}
		return solutionContainer;
	}

	/***
	 * Set the solution
	 * 
	 * @param solution
	 */
	public void setSolutioContainer(Solution solution) {
		this.solutionContainer = solution;
	}

	/***
	 * Get the solution
	 * 
	 * @return <code>Solution</code>
	 */
	public Solution getSolutionContainer() {
		return solutionContainer;
	}
}
