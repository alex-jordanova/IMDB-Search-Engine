package bg.uni.sofia.fmi.mjt.imdb.corecomponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Request {
	private String movieTitle;
	private RequestType type;
	private List<Field> fields;
	private static final Set<String> options = new HashSet<>(
			Arrays.asList("--fields", "--season"));

	public Request(String request) {
		String[] words = request.split("[^a-zA-Z0-9:'-]+");
		movieTitle = Parser.getMovieTitle(words, options);
		type = Parser.getRequestType(words);
	    fields = Parser.getFields(words, options);
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public RequestType getType() {
		return type;
	}
	
	public String getTypeAsString() {
		return type.getType();
	}

	public List<Field> getFields() {
		return fields;
	}

	public boolean hasField(String fieldName) {
		for (Field field : fields) {
			if (field.getName().contains(fieldName)) return true;
		}
		return false;
	}
	
	public List<String> getFieldAttributes(String fieldName) {
		
		for (Field field : fields) {
			if (field.getName().contains(fieldName)) {
				return field.getAttributes();
			}
		}
		
		return new ArrayList<>();
		
	}
	
	public String getSeasonNumber() {
		return fields.get(0).getAttribute(0);
	}
	
	public boolean hasSpecifiedFields() {
		return !fields.isEmpty();
	}
	
	public boolean isType(RequestType type) {
		return this.type.equals(type);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((movieTitle == null) ? 0 : movieTitle.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Request other = (Request) obj;
		if (fields == null) {
			if (other.fields != null) {
				return false;
			}
		} else if (!fields.equals(other.fields)) {
			return false;
		}
		if (movieTitle == null) {
			if (other.movieTitle != null) {
				return false;
			}
		} else if (!movieTitle.equals(other.movieTitle)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

}
