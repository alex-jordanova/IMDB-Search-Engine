package bg.uni.sofia.fmi.mjt.imdb.corecomponents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bg.uni.sofia.fmi.mjt.imdb.server.CacheLoader;

public class Parser {
	public static final String TITLE_FIELD = "title";
	public static final String ORDER_FIELD = "order";
	public static final String GENRE_FIELD = "genre";
	public static final String ACTORS_FIELD = "actors";
	public static final String RATING_FIELD = "imdbrating";
	public static final String DESC_ORDER = "desc";
	public static final String IMAGE_EXTENSION = ".jpg";
	
	private static final String DELIMITER = "\",";
	
	public static String getFieldValue(String input, String field) {
		int fieldStartIndex = input.toLowerCase().indexOf(field) - 1;
		return input.substring(fieldStartIndex, input.indexOf(DELIMITER, fieldStartIndex) + 1);
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
	
	
	public static String getMovieTitle(String[] words, Set<String> options) {
		StringBuilder title = new StringBuilder();
		
		int index = 1;
		while (index < words.length && !options.contains(words[index])) {
			title.append(words[index++]).append(" ");
		}
		
		return title.toString().trim();
	}

	public static RequestType getRequestType(String[] words) {
		  return RequestType.determineType(words[0]);
	}

	public static List<Field> getFields(String[] words, Set<String> options) {
		List<Field> fields = new ArrayList<>();
	
		int firstFieldIndex = findFirstField(words, options);
		while (firstFieldIndex != words.length) {
			if (options.contains(words[firstFieldIndex])) {
				fields.add(new Field(words[firstFieldIndex++]));
			} else {
				fields.get(fields.size() - 1).addAttribute(words[firstFieldIndex++]);
			}
		}
		
		return fields;
	}
	

	private static int findFirstField(String[] words, Set<String> options) {
		int index = 0;
		while (index < words.length && !options.contains(words[index])) {
			++index;
		}
	    return index;
	}
	
}
