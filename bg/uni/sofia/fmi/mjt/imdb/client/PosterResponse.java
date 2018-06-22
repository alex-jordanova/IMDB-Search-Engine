package bg.uni.sofia.fmi.mjt.imdb.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class PosterResponse implements Response {
	private Request request;
	private InputStream response; 
	private static final String IMAGE_EXTENSION = ".jpg";
	
	public PosterResponse(Request request, InputStream response) {
		this.request = request;
		this.response = response;
	}
	
	@Override
	public void process() {
		try {
			BufferedImage poster = ImageIO.read(response);
			ImageIO.write(poster, "jpg", Paths.get(request.getMovieTitle() + IMAGE_EXTENSION).toFile());
			System.out.println("Image successfully received."); //maybe a function printOperationResult that prints a message??
		} catch (IOException e) {
			System.out.println("Problem reading input stream!");
		}
	}

}
