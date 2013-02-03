package com.gayashanna.solud.logic;

import android.graphics.Bitmap;

/***
 * This is the Solution class. The solutions for the equations are stored in
 * instances of this class.
 * 
 * @author GayashanNA
 * 
 */
public class Solution {
	private String solutionStatus, inputInterpretation, classification,
			solution, error;
	private String inputInterpretImgURL, solutionImgURL, solutionGraphURL;
	private Bitmap solutionImg,solutionGraphImg;

	// initialize the attributes in constructor
	public Solution() {
		solutionStatus = "Not successful.";
		inputInterpretation = "Not received.";
		classification = "Not received.";
		solution = "Not received.";
		error = "ERROR Occured.";
		inputInterpretImgURL = "";
		solutionImgURL = "";
		solutionGraphURL = "";
		solutionImg = null;
		solutionGraphImg = null;
	}

	/**
	 * @return the solutionGraphURL
	 */
	public String getSolutionGraphURL() {
		return solutionGraphURL;
	}

	/**
	 * @param solutionGraphURL
	 *            the solutionGraphURL to set
	 */
	public void setSolutionGraphURL(String solutionGraphURL) {
		this.solutionGraphURL = solutionGraphURL;
	}

	/**
	 * @return the solutionStatus
	 */
	public String getSolutionStatus() {
		return solutionStatus;
	}

	/**
	 * @param solutionStatus
	 *            the solutionStatus to set
	 */
	public void setSolutionStatus(String solutionStatus) {
		this.solutionStatus = solutionStatus;
	}

	/**
	 * @return the inputInterpretation
	 */
	public String getInputInterpretation() {
		return inputInterpretation;
	}

	/**
	 * @param inputInterpretation
	 *            the inputInterpretation to set
	 */
	public void setInputInterpretation(String inputInterpretation) {
		this.inputInterpretation = inputInterpretation;
	}

	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * @param classification
	 *            the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * @return the solution
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @param solution
	 *            the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}

	/**
	 * @return the inputInterpretImgURL
	 */
	public String getInputInterpretImgURL() {
		return inputInterpretImgURL;
	}

	/**
	 * @param inputInterpretImgURL
	 *            the inputInterpretImgURL to set
	 */
	public void setInputInterpretImgURL(String inputInterpretImgURL) {
		this.inputInterpretImgURL = inputInterpretImgURL;
	}

	/**
	 * @return the solutionImgURL
	 */
	public String getSolutionImgURL() {
		return solutionImgURL;
	}

	/**
	 * @param solutionImgURL
	 *            the solutionImgURL to set
	 */
	public void setSolutionImgURL(String solutionImgURL) {
		this.solutionImgURL = solutionImgURL;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	public Bitmap getSolutionImage() {
		return solutionImg;
	}

	public void setSolutionImage(Bitmap solutionImg) {
		this.solutionImg = solutionImg;
	}

	public Bitmap getSolutionGraphImage() {
		return solutionGraphImg;
	}

	public void setSolutionGraphImage(Bitmap solutionGraphImg) {
		this.solutionGraphImg = solutionGraphImg;
	}

}
