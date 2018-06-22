package bg.uni.sofia.fmi.mjt.imdb.client;

import java.util.List;

public class OutputFormatter {
	
	private static final String TITLE_FIELD = "title";
	
	public static void printFieldResults(String movieData, List<String> fields) throws NoSuchFieldException {
		for (String field : fields) {
			System.out.println(CommandParser.extractField(movieData, field));
		}
	}
	
	public static void printTitle(String movieData) throws NoSuchFieldException {
		System.out.println(CommandParser.extractField(movieData, TITLE_FIELD));
	}
	
	public static void printEpisodes(String movieData) {
		System.out.println(OutputFormatter.formatEpisodes(movieData));
	}
	
	private static String formatEpisodes(String input) {
		StringBuilder episodes = new StringBuilder(input);
		episodes.delete(0, episodes.indexOf("[") + 2);
		episodes.delete(episodes.lastIndexOf("]") - 1, episodes.length());
		return episodes.toString().replace("},{", "\n");
	}
	
}
