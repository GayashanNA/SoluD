package com.gayashanna.solud.interfaces;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.gayashanna.solud.exceptions.ConnectionUnavailableException;
import com.gayashanna.solud.logic.Solution;

/***
 * This interface is used to implement the communication with Wolfram | Alpha
 * OMCE.
 * 
 * @author GayashanNA
 * 
 */
public interface WolframAlphaCommunicator {
	public void getSolutionFromWolframAlpha(String query)
			throws UnsupportedEncodingException, MalformedURLException,
			IOException, SAXException, ParserConfigurationException,
			ConnectionUnavailableException;

	public URL encodeURL(String query) throws UnsupportedEncodingException,
			MalformedURLException;

	public void setOutputFormat(String outputFormat);

	public boolean isSolutionAvailable();

	public Solution getSolution();
}
