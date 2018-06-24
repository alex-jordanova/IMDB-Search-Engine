package bg.uni.sofia.fmi.mjt.imdb.corecomponents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import bg.uni.sofia.fmi.mjt.imdb.server.CacheLoader;

public class Parser {
	public static final String TITLE_FIELD = "title";
	public static final String ORDER_FIELD = "order";
	public static final String RATING_FIELD = "imdbrating";
	public static final String DESC_ORDER = "desc";
	public static final String IMAGE_EXTENSION = ".jpg";
	
	private static final String DELIMITER = "\",";
	
	public static String getFieldValue(String input, String field) {
		int fieldStartIndex = input.toLowerCase().indexOf(field) - 1;
		return input.substring(fieldStartIndex, input.indexOf(DELIMITER, fieldStartIndex) + 1);
	}	
	
	public static double getRating(String ratingField) {
		StringBuilder rating = new StringBuilder();
		for (int i = 0; i < ratingField.length(); ++i) {
			if (isDigit(ratingField.charAt(i)) || ratingField.charAt(i) == '.') {
				rating.append(ratingField.charAt(i));
			}
		}
		return Double.parseDouble(ratingField.toString());
	}
	
	public static String getPosterURL(Path searchedMoviePath) {
		String posterURL = "";
		
		try (BufferedReader file = new BufferedReader(new FileReader(searchedMoviePath.toFile()))) {
			String movieData = file.readLine();
			int urlStartIndex = movieData.indexOf(CacheLoader.HTTP_PROTOCOL);
			int urlEndIndex = movieData.indexOf('"', urlStartIndex);
			
			posterURL = movieData.substring(urlStartIndex, urlEndIndex);
			
		} catch (IOException e) {
			System.out.println("Cannot open file.");
		}
		
		return posterURL;
	}
	
	public static String getSeasonName(Request requestedMovie) {
		return requestedMovie.getMovieTitle() + requestedMovie.getSeasonNumber();
	}
	
	private static boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}
	
	
	
/*	private static boolean containsField(String input, String fieldName) {
		return input.toLowerCase().contains(fieldName);
	}
	*/
}
