package com.gayashanna.solud.logic;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;

import android.util.Log;

import com.gayashanna.solud.exceptions.ConnectionUnavailableException;
import com.gayashanna.solud.exceptions.ImageURLNotFoundException;

/***
 * This class handles the solutions. It acquire the data from the instance of
 * Solution and put them in a bundle so the data can be passed between
 * activities.
 * 
 * @author GayashanNA
 * 
 */
public class SolutionHandler {
	private WACommunicator waCommunicator;
	private static final String OUTPUT_FORMAT = "image,plaintext";

	public SolutionHandler() {
		waCommunicator = new WACommunicator();
		waCommunicator.setOutputFormat(OUTPUT_FORMAT);
	}

	/***
	 * Returns the formatted solution as a Solution
	 * 
	 * 
	 * @param equation
	 * @return <code>Solution</code> The solution of the query
	 * @throws ConnectionUnavailableException
	 */
	public Solution getSolution(String equation)
			throws ConnectionUnavailableException {
		Solution tempSolution = null;
		try {
			waCommunicator.getSolutionFromWolframAlpha(equation);
			if (waCommunicator.isSolutionAvailable()) {
				tempSolution = waCommunicator.getSolution();
			} else {
				//if the solution could not be retrieved send a dummy Solution filled with "Did not receive solution." text
				tempSolution = new Solution();
				tempSolution.setSolutionStatus("Did not receive solution.");
				tempSolution
						.setInputInterpretation("Did not receive solution.");
				tempSolution.setSolution("Did not receive solution.");
				tempSolution.setClassification("Did not receive solution.");
			}
		} catch (IOException e) {
			Log.e("Error",e.getMessage());
		} catch (SAXException e) {
			Log.e("Error",e.getMessage());
		} catch (ParserConfigurationException e) {
			Log.e("Error",e.getMessage());
		}
		return tempSolution;
	}

	/***
	 * This method is used to acquire the solution along with the images
	 * 
	 * @param equation
	 * @return <code>Solution</code>
	 * @throws ConnectionUnavailableException
	 * @throws ClientProtocolException
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ImageURLNotFoundException
	 */
	public Solution getSolutionWithBitmaps(String equation)
			throws ConnectionUnavailableException, ClientProtocolException,
			URISyntaxException, IOException, ImageURLNotFoundException {
		Solution tempSolution = getSolution(equation);
		// create an ImageHandler to acquire the images
		ImageHandler imageHandler = new ImageHandler();
		// acquire the images
		tempSolution.setSolutionImage(imageHandler.getBitmap(tempSolution
				.getSolutionImgURL()));
		tempSolution.setSolutionGraphImage(imageHandler.getBitmap(tempSolution
				.getSolutionGraphURL()));
		return tempSolution;
	}
}
