package com.gayashanna.solud.logic;

import android.graphics.Bitmap;

import com.googlecode.tesseract.android.TessBaseAPI;

/***
 * This class is responsible for handling the OCR library.
 * 
 * @author GayashanNA
 * 
 */
public class OCRHandler {
	private static final String LANG = "eng"; // Language package
	private TessBaseAPI OCRBaseApi; // TessBaseApi is used from the library
									// resource to handle OCR
	private static int pageSegmentMode = TessBaseAPI.PSM_AUTO; // set page
																// segmentation
																// mode

	/***
	 * Public constructor
	 */
	public OCRHandler() {
		OCRBaseApi = new TessBaseAPI();
	}

	/***
	 * This method decodes an image using the
	 * <code>tesseract-android-tools library</code> and returns a String
	 * 
	 * @param image
	 * @return <code>String</code> decoded text in UTF-8 encoded format
	 * @throws NullPointerException
	 */
	public String getDecodedText(Bitmap image) throws NullPointerException {
		if (image == null) { // if image is null
			throw new NullPointerException("Received image is null.");
		}
		image = image.copy(Bitmap.Config.ARGB_8888, true);
		OCRBaseApi = new TessBaseAPI();
		// Page segmentation mode
		OCRBaseApi.setPageSegMode(pageSegmentMode);
		// enable debugging
		OCRBaseApi.setDebug(true);
		// set location of the trained data
		OCRBaseApi.init("/mnt/sdcard/tesseract/", LANG);
		// set the image to be decoded
		OCRBaseApi.setImage(image);
		// get the text to be sent
		String recognizedText = OCRBaseApi.getUTF8Text();
		if (LANG == "eng") {
			// recognizedText = recognizedText.replaceAll("[^a-zA-Z0-9(*)*=*]+",
			// " ");
			recognizedText = java.net.URLDecoder.decode(recognizedText);
		}
		// finish the OCR process
		if (OCRBaseApi != null) {
			OCRBaseApi.clear();
			OCRBaseApi.end();
		}
		// returned the recognized text after trimming it
		return recognizedText.trim();
	}
}
