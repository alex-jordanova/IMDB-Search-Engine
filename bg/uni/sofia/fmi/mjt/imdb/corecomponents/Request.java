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
			Arrays.asList("actors", "order", "genre", "fields", "season"));

	public Request(String request) {
		String[] words = request.split("[^a-zA-Z0-9:']+");
		movieTitle = extractMovieTitle(words);
		type = extractType(words);
	    fields = extractFields(words);
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public RequestType getType() {
		return type;
	}

	public List<Field> getFields() {
		return fields;
	}

	public List<String> getFieldAttributes(String fieldName) throws NoSuchFieldException {
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return field.getAttributes();
			}
		}
		
		throw new NoSuchFieldException("There is no " + fieldName + " field!");
	}
	
	public String getSeasonNumber() {
		return fields.get(0).getAttributes().get(0);
	}
	
	public boolean hasSpecifiedFields() {
		return !fields.isEmpty();
	}
	
	public boolean isType(RequestType type) {
		return this.type.equals(type);
	}
	
	//helper functions
	private String extractMovieTitle(String[] words) {
		StringBuilder title = new StringBuilder();
		
		int index = 1;
		while (index < words.length && !options.contains(words[index])) {
			title.append(words[index++]).append(" ");
		}
		
		return title.toString().trim();
	}

	private RequestType extractType(String[] words) {
		  return RequestType.determineType(words[0]);
	}

	private List<Field> extractFields(String[] words) {
		List<Field> fields = new ArrayList<>();
		
		int index = findFirstAttribute(words);
		while (index != words.length) {
			if (options.contains(words[index])) {
				fields.add(new Field(words[index++]));
			} else {
				fields.get(fields.size() - 1).addAttribute(words[index++]);
			}
		}
		
		return fields;
	}
	
	private int findFirstAttribute(String[] words) {
		int index = 0;
		while (index < words.length && !options.contains(words[index])) {
			++index;
		}
	    return index;
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
