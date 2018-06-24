package bg.uni.sofia.fmi.mjt.imdb.server;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;


public class OmdbConnector {
	private static final String OMDB_URL = "http://www.omdbapi.com/?t=";
	private static final String SEASON_QUERY = "&Season=";
	private static final String API_KEY = "&apikey=a3aa8a24";

	public static void downloadMovie(String movie, String destFile) throws MalformedURLException {
		String movieURL = OMDB_URL + movie.replace(" ", "%20") + API_KEY;
		download(movieURL, destFile);
	}
	
	public static void downloadSeriesSeason(String movie, String seasonNum, String destFile) throws MalformedURLException {
		String movieURL = OMDB_URL + movie.replace(" ", "%20") + SEASON_QUERY + seasonNum + API_KEY;
		download(movieURL, destFile);
	}
	
	public static void downloadPoster(String posterURL, String destFile) {
		try {
			BufferedImage poster = ImageIO.read(new URL(posterURL));
			ImageIO.write(poster, "jpg", new File(destFile));
		} catch (IOException e) {
			System.out.println("Image not found!");
		}
	}
	
	private static void download(String sourceURL, String destFile) throws MalformedURLException {
		URL source = new URL(sourceURL);
		try (BufferedReader urlReader = new BufferedReader(new InputStreamReader(source.openStream()));
				PrintWriter destination = new PrintWriter(new FileWriter(destFile)) ) {
				destination.println(urlReader.readLine());
		} catch (IOException e) {
			System.out.println("Can't save movie data to " + destFile + ".");
		}
	}
}
