package bg.uni.sofia.fmi.mjt.imdb.corecomponents;

public enum RequestType {
	MOVIE("movie"),
	POSTER("poster"),
	TVSERIES("tv");
	
	private String type;
	
	private RequestType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public static RequestType determineType(String word) {
		if (word.contains(RequestType.POSTER.getType())) {
			return RequestType.POSTER;
		} else if (word.contains(RequestType.TVSERIES.getType())) {
			return RequestType.TVSERIES;
		} else {
			return RequestType.MOVIE;		
		}
	}
}
