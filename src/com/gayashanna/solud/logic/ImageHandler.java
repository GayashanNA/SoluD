package com.gayashanna.solud.logic;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.gayashanna.solud.exceptions.ImageURLNotFoundException;

/***
 * This class is used to acquire the Bitmap images when a url to an image is
 * available.
 * 
 * @author GayashanNA
 * 
 */
public class ImageHandler {
	/***
	 * This method is used to acquire the Bitmap images provided by a given url.
	 * 
	 * @param url
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ImageURLNotFoundException
	 */
	public Bitmap getBitmap(String url) throws URISyntaxException,
			ClientProtocolException, IOException, ImageURLNotFoundException { // pass
																				// the
																				// complete
																				// web
																				// url

		if ("".contentEquals(url)) {
			throw new ImageURLNotFoundException("url is empty");
		} else {
			Bitmap bmp = null;
			// Creating a HTTP client to communicate with the web service
			HttpClient client = new DefaultHttpClient();
			// generate URL
			URI imageURI = new URI(url);
			HttpGet req = new HttpGet();
			req.setURI(imageURI);
			// execute the GET request
			HttpResponse response = client.execute(req);
			bmp = BitmapFactory.decodeStream(response.getEntity().getContent()); // BitmapFactory
																					// decodes
																					// the
																					// InputStream
																					// of
																					// the
																					// HttpResponse
			return bmp;
		}
	}

}
