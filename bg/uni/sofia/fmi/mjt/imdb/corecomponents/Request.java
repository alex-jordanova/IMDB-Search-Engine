<<<<<<< HEAD
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
	private static final Set<String> keywords = new HashSet<>(
			Arrays.asList("--actors", "--order", "--genres", "--fields", "--season"));

	public Request(String request) {
		String[] words = request.split("[^a-zA-Z0-9':-]+");
		type = extractType(words);
		movieTitle = extractMovieTitle(words);
		extractFields(words);
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

	private String extractMovieTitle(String[] words) {
		int index = 1;
		StringBuilder title = new StringBuilder();
		while (index < words.length && !keywords.contains(words[index])) {
			title.append(" ").append(words[index++]);
		}
		return title.toString().trim();
	}

	private RequestType extractType(String[] words) {
		if (words[0].contains(RequestType.POSTER.getType())) {
			return RequestType.POSTER;
		} else if (words[0].contains(RequestType.TVSERIES.getType())) {
			return RequestType.TVSERIES;
		} else if (words[0].contains(RequestType.MOVIES.getType())) {
			return RequestType.MOVIES;
		}
		return RequestType.MOVIE;
	}

	private void extractFields(String[] words) {
		fields = new ArrayList<>();
		int i = 0;
		while (i < words.length && !keywords.contains(words[i])) {
			++i;
		}

		while (i != words.length) {
			if (keywords.contains(words[i])) {
				fields.add(new Field(words[i++]));
			} else {
				fields.get(fields.size() - 1).addAttribute(words[i++]);
			}
		}
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
=======
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
	private static final Set<String> keywords = new HashSet<>(
			Arrays.asList("--actors", "--order", "--genres", "--fields", "--season"));

	public Request(String request) {
		String[] words = request.split("[^a-zA-Z0-9':-]+");
		type = extractType(words);
		movieTitle = extractMovieTitle(words);
		extractFields(words);
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

	private String extractMovieTitle(String[] words) {
		int index = 1;
		StringBuilder title = new StringBuilder();
		while (index < words.length && !keywords.contains(words[index])) {
			title.append(" ").append(words[index++]);
		}
		return title.toString().trim();
	}

	private RequestType extractType(String[] words) {
		if (words[0].contains(RequestType.POSTER.getType())) {
			return RequestType.POSTER;
		} else if (words[0].contains(RequestType.TVSERIES.getType())) {
			return RequestType.TVSERIES;
		} else if (words[0].contains(RequestType.MOVIES.getType())) {
			return RequestType.MOVIES;
		}
		return RequestType.MOVIE;
	}

	private void extractFields(String[] words) {
		fields = new ArrayList<>();
		int i = 0;
		while (i < words.length && !keywords.contains(words[i])) {
			++i;
		}

		while (i != words.length) {
			if (keywords.contains(words[i])) {
				fields.add(new Field(words[i++]));
			} else {
				fields.get(fields.size() - 1).addAttribute(words[i++]);
			}
		}
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
>>>>>>> 3d073b38f6e2da80cf7ee58169d9aae5b7b9ef48
