package bg.uni.sofia.fmi.mjt.imdb.client;

public class CommandParser {
	private static final String DELIMITER = "\",";
	
	public static String extractField(String input, String field) {
		int fieldStartIndex = input.toLowerCase().indexOf(field) - 1;
		return input.substring(fieldStartIndex, input.indexOf(DELIMITER, fieldStartIndex) + 1);
	}	
	
	public static double getRatingScore(String rating) {
		StringBuilder number = new StringBuilder();
		for (int i = 0; i < rating.length(); ++i) {
			if (isDigit(rating.charAt(i)) || rating.charAt(i) == '.') {
				number.append(rating.charAt(i));
			}
		}
		return Double.parseDouble(number.toString());
	}
	
	private static boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}
	
/*	private static boolean containsField(String input, String fieldName) {
		return input.toLowerCase().contains(fieldName);
	}
	*/
}
