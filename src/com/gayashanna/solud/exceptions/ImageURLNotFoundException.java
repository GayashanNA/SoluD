package com.gayashanna.solud.exceptions;

/**
 * This exception is thrown when a url to an image is not found.
 * 
 * @author Gaiz
 * 
 */
public class ImageURLNotFoundException extends Exception {
	private static final long serialVersionUID = 8522241163643291032L;
	private String message;

	public ImageURLNotFoundException(String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

}
