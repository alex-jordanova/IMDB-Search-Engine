<<<<<<< HEAD
package bg.uni.sofia.fmi.mjt.imdb.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class PosterResponseProcessor implements ResponseProcessor {
	private Request request;
	private InputStream response; 
	private static final String IMAGE_EXTENSION = ".jpg";
	
	public PosterResponseProcessor(Request request, InputStream response) {
		this.request = request;
		this.response = response;
	}
	@Override
	public void process() {
		try {
			BufferedImage poster = ImageIO.read(response);
			ImageIO.write(poster, "jpg", Paths.get(request.getMovieTitle() + IMAGE_EXTENSION).toFile());
			System.out.println("Image successfully received.");
		} catch (IOException e) {
			System.out.println("Can't read stream.");
		}
	}

}
=======
package bg.uni.sofia.fmi.mjt.imdb.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class PosterResponseProcessor implements ResponseProcessor {
	private Request request;
	private InputStream response; 
	private static final String IMAGE_EXTENSION = ".jpg";
	
	public PosterResponseProcessor(Request request, InputStream response) {
		this.request = request;
		this.response = response;
	}
	@Override
	public void process() {
		try {
			BufferedImage poster = ImageIO.read(response);
			ImageIO.write(poster, "jpg", Paths.get(request.getMovieTitle() + IMAGE_EXTENSION).toFile());
			System.out.println("Image successfully received.");
		} catch (IOException e) {
			System.out.println("Can't read stream.");
		}
	}

}
>>>>>>> 3d073b38f6e2da80cf7ee58169d9aae5b7b9ef48
