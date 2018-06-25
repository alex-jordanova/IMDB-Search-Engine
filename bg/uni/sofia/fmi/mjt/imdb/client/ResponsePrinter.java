package bg.uni.sofia.fmi.mjt.imdb.client;

import java.util.List;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Parser;

public class ResponsePrinter {
	
	public static void printFieldResults(String movieData, List<String> fields) {
		for (String field : fields) {
			System.out.println(Parser.getFieldValue(movieData, field));
		}
	}
	
	public static void printTitle(String movieData) {
		System.out.println(Parser.getFieldValue(movieData, Parser.TITLE_FIELD));
	}
	
	public static void printEpisodes(String movieData) {
		System.out.println(formatEpisodes(movieData));
	}
	
	public static void printMovies(List<String> movies) {
		for (String movie : movies) {
			System.out.println(movie);
		}
	}
	
	private static String formatEpisodes(String input) {
		StringBuilder episodes = new StringBuilder(input);
		episodes.delete(0, episodes.indexOf("[") + 2);
		episodes.delete(episodes.lastIndexOf("]") - 1, episodes.length());
		return episodes.toString().replace("},{", "\n");
	}
	
}
