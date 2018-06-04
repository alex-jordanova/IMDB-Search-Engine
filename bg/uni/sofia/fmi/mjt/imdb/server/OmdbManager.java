package bg.uni.sofia.fmi.mjt.imdb.server;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.imageio.ImageIO;


public class OmdbManager {
	private static final String OMDB_URL = "http://www.omdbapi.com/?t=";
	private static final String SEASON_QUERY = "&Season=";
	private static final String API_KEY = "&apikey=a3aa8a24";

	public static void downloadMovieData(String movie, String destFile) {
		downloadData(OMDB_URL + movie.replace(" ", "%20") + API_KEY, destFile);
	}
	
	public static void downloadSeriesSeason(String movie, String seasonNum, String destFile) {
		downloadData(OMDB_URL + movie.replace(" ", "%20") + SEASON_QUERY + seasonNum + API_KEY, destFile);
	}
	
	public static void downloadPoster(String posterURL, String destFile) {
		try {
			BufferedImage poster = ImageIO.read(new URL(posterURL));
			ImageIO.write(poster, "jpg", new File(destFile));
		} catch (IOException e) {
			System.out.println("Image not found!");
		}
	}
	
	private static void downloadData(String fullURL, String destFile) {
		try {
			URL omdbMovie = new URL(fullURL);
			saveToFile(omdbMovie, destFile);
		} catch (IOException e) {
			System.out.println("Cannot download oMDB data to server.");
		}
	}
	
	private static void saveToFile(URL link, String destFile) {
		try (BufferedReader movieData = new BufferedReader(new InputStreamReader(link.openStream()))) {
			try (PrintWriter outputFile = new PrintWriter(new FileWriter(destFile))) {
				outputFile.println(movieData.readLine());
			} catch (IOException e) {
				System.out.println("Cannot save oMDB data to file.");
			}
		} catch (IOException e) {
			System.out.println("Cannot connect to oMDB.");
		}
	}
}
