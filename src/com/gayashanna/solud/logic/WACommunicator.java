package com.gayashanna.solud.logic;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.gayashanna.solud.exceptions.ConnectionUnavailableException;
import com.gayashanna.solud.interfaces.WolframAlphaCommunicator;

/***
 * This class is responsible for communicating with the Wolfram | Alpha OMCE. It
 * queries the OMCE with the equation and acquires the XML file which is
 * returned as the solution container by the OMCE. The class then passes that
 * XML file to a XMLHandler class which extracts the required content.
 * 
 * @author GayashanNA
 * 
 */
public class WACommunicator implements WolframAlphaCommunicator {
	private static final String appID = "3LAUAL-JT4WEW8T25";
	private static final String baseURL = "http://api.wolframalpha.com/v2/query?appid=";
	private String outputFormat;
	private Solution solution;
	private boolean solutionAvailable;
	
	public WACommunicator() {
		setSolutionAvailable(false);
	}

	/**
	 * @return the outputFormat
	 */
	public String getOutputFormat() {
		return outputFormat;
	}

	/**
	 * @param outputFormat
	 *            the outputFormat to set
	 */
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	/**
	 * Acquires the solution from Wolfram Alpha OMCE
	 * 
	 * @param query
	 * @param outputformat
	 * @throws ConnectionUnavailableException
	 */
	public void getSolutionFromWolframAlpha(String query) throws IOException,
			SAXException, ParserConfigurationException {
		// construct the querying url
		URL urlToWolfram = encodeURL(query);
		// Handling XML file
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		XMLHandler handler = new XMLHandler();
		reader.setContentHandler(handler);
		// open the connection
		reader.parse(new InputSource(urlToWolfram.openStream()));
		// set the solution
		setSolution(handler.getSolution());
		// set the solution available flag
		setSolutionAvailable(true);
	}

	/***
	 * Encode the query to UTF-8 append it to the final URL
	 */
	public URL encodeURL(String query) throws UnsupportedEncodingException,
			MalformedURLException {
		// encode the url in UTF-8 format
		String encodedQuery = java.net.URLEncoder.encode(query, "UTF-8");
		StringBuilder sBuilder = new StringBuilder(baseURL);
		sBuilder.append(appID + "&input=" + encodedQuery + "&format="
				+ outputFormat);
		Log.e("URL_ENCODED", sBuilder.toString());
		URL url = new URL(sBuilder.toString());
		return url;
	}

	public Solution getSolution() {
		return solution;
	}

	private void setSolution(Solution solution) {
		this.solution = solution;
	}

	public boolean isSolutionAvailable() {
		return solutionAvailable;
	}

	private void setSolutionAvailable(boolean solutionAvailable) {
		this.solutionAvailable = solutionAvailable;
	}

}
