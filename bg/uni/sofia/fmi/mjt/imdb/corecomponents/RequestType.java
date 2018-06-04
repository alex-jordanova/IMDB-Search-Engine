package bg.uni.sofia.fmi.mjt.imdb.corecomponents;

public enum RequestType {
	MOVIE("movie"),
	POSTER("poster"),
	TVSERIES("tv"),
	MOVIES("movies");
	
	private String type;
	
	private RequestType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
