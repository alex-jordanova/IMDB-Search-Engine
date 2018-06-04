package bg.uni.sofia.fmi.mjt.imdb.corecomponents;

public class Movie {
	private String name;
	private String type;
	
	public Movie() {
		name = type = null;
	}
	
	public Movie(String name, String type) {
		this.name = name.toLowerCase();
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
}
