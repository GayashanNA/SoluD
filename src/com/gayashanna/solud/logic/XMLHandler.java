package com.gayashanna.solud.logic;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.app.Activity;

/***
 * This class is responsible for extracting content from the XML files which are
 * returned as responses for the queries to the Wolfram | Alpha OMCE. The
 * extracted data is put into a new instance of a Solution. Other classes can
 * acquire the solutions from this class using getSolution() method.
 * 
 * @author GayashanNA
 * 
 */
public class XMLHandler extends DefaultHandler {
	private Solution currSolution;
	private boolean prevInput, prevClass, prevAlt, prevSol, prevPlot;

	public XMLHandler() throws FileNotFoundException, IOException {
		currSolution = new Solution();
		prevInput = false; // if previous pod id=Input
		prevClass = false; // if previous pod id=ODEClassification
		prevAlt = false; // if previous pod id=AlternateForm
		prevSol = false; // if previous pod id=DifferentialEquationSolution
		prevPlot = false; // if previous pod id=PlotsOfSampleIndividualSolutions
	}

	public XMLHandler(Activity activity) {
		currSolution = new Solution();
		prevInput = false; // if previous pod id=Input
		prevClass = false; // if previous pod id=ODEClassification
		prevAlt = false; // if previous pod id=AlternateForm
		prevSol = false; // if previous pod id=DifferentialEquationSolution
		prevPlot = false; // if previous pod id=PlotsOfSampleIndividualSolutions
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("queryresult".equalsIgnoreCase(localName)) {
			currSolution.setSolutionStatus(attributes.getValue("success"));
		} else if ("pod".equalsIgnoreCase(localName)) {
			rememberPodID(attributes);
		} else if ("img".equalsIgnoreCase(localName)) {
			extractDataFromImgTag(attributes);
		}
	}

	/**
	 * extract content from the img tag element of the XML file
	 * 
	 * @param attributes
	 */
	private void extractDataFromImgTag(Attributes attributes) {
		// if the previous element in the xml file was Input
		if (prevInput) {
			currSolution.setInputInterpretation(attributes.getValue("title"));
			currSolution.setInputInterpretImgURL(attributes.getValue("src"));
			prevInput = false;
		} else if (prevClass) {// if the previous element in the xml file was
								// Classification
			currSolution.setClassification(attributes.getValue("title"));
			prevClass = false;
		} else if (prevAlt) {// if the previous element in the xml file was
								// Alternative solution
			prevAlt = false;
		} else if (prevSol) { // if the previous element in the xml file was
								// Solution
			currSolution.setSolution(attributes.getValue("title"));
			currSolution.setSolutionImgURL(attributes.getValue("src"));
			prevSol = false;
		} else if (prevPlot) { // if the previous element in the xml file was
								// the plot of the solution
			currSolution.setSolutionGraphURL(attributes.getValue("src"));
			prevPlot = false;
		}
	}

	/**
	 * remembers the previous pod ID
	 * 
	 * @param attributes
	 */
	private void rememberPodID(Attributes attributes) {
		if ("Input".equalsIgnoreCase(attributes.getValue("id"))) {
			prevInput = true;
		} else if ("ODEClassification".equalsIgnoreCase(attributes
				.getValue("id"))) {
			prevClass = true;
		} else if ("AlternateForm".equalsIgnoreCase(attributes.getValue("id"))) {
			prevAlt = true;
		} else if ("DifferentialEquationSolution".equalsIgnoreCase(attributes
				.getValue("id"))) {
			prevSol = true;
		} else if ("PlotsOfSampleIndividualSolutions"
				.equalsIgnoreCase(attributes.getValue("id"))
				|| "PlotsOfSampleIndividualSolution"
						.equalsIgnoreCase(attributes.getValue("id"))) {
			prevPlot = true;
		}
	}

	/**
	 * Returns the solution
	 * 
	 * @return
	 */
	public Solution getSolution() {
		return currSolution;
	}
}
