package bg.uni.sofia.fmi.mjt.imdb.client;

import java.util.List;

public class DataAnalyzer {
	private static final String DELIMITER = "\",";
	
	public static String extractField(String input, String field) {
		String in = input.toLowerCase();
		String output = "No such field.";
		int i = in.indexOf(field) - 1;
		if (in.contains(field)) {
			output = input.substring(i, in.indexOf(DELIMITER, i) + 1);
		}
		return output;
	}
	
	public static boolean containsAllAttributes(String field, List<String> attributes) {
		field = field.toLowerCase();
		for (String attribute : attributes) {
			if (!field.contains(attribute)) {
				return false;
			}
		}
		return true;
	}
	
	public static String formatEpisodes(String input) {
		StringBuilder episodes = new StringBuilder(input);
		episodes.delete(0, episodes.indexOf("[") + 2);
		episodes.delete(episodes.lastIndexOf("]") - 1, episodes.length());
		return episodes.toString().replace("},{", "\n");
	}
	
	
	public static double getRatingScore(String field) {
		StringBuilder number = new StringBuilder();
		for (int i = 0; i < field.length(); ++i) {
			if (('0' <= field.charAt(i) && field.charAt(i) <= '9') || field.charAt(i) == '.') {
				number.append(field.charAt(i));
			}
		}
		return Double.parseDouble(number.toString());
	}
	
}
