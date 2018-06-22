package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Field;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class MoviesResponse implements Response {

	private Request request;
	private InputStream response;
	private static final String ORDER_FIELD = "order";
	private static final String RATING_FIELD = "imdbrating";
	private static final String DESC_ORDER = "desc";

	public MoviesResponse(Request request, InputStream response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public void process() {
		try (BufferedReader input = new BufferedReader(new InputStreamReader(response))) {
			List<String> movies = new ArrayList<>();
			
			while (input.ready()) {
				movies.add(input.readLine());
			}
			
			movies = sortAndFilter(movies);
			
			for (String movie : movies) {
				System.out.println(movie);
			}
		} catch (IOException e) {
			System.out.println("Problem reading input stream!");
		}

	}

	private List<String> sortAndFilter(List<String> movies) {
		for (Field field : request.getFields()) {
			if (field.getName().contains(ORDER_FIELD)) {
				movies = sortByRatings(movies, field);
			} else {
				movies = filter(movies, field.getName(), field.getAttributes());
			}
		}
		return movies;
	}

	private List<String> sortByRatings(List<String> toSort, Field field) {
		toSort.sort(new Comparator<String>() {

			@Override
			public int compare(String firstMovie, String secondMovie) {
				String firstMovRating = CommandParser.extractField(firstMovie, RATING_FIELD);
				String secondMovRating = CommandParser.extractField(secondMovie, RATING_FIELD);
				return Double.compare(CommandParser.getRatingScore(firstMovRating),
						CommandParser.getRatingScore(secondMovRating));
			}

		});
		
		if (field.hasAttribute(DESC_ORDER)) {
			Collections.reverse(toSort);
		}
		
		return toSort;
	}

	private List<String> filter(List<String> toFilter, String filter, List<String> attributes) {
		return toFilter.stream()
				.filter(movie -> hasSearchedAttributes(CommandParser.extractField(movie, filter), attributes))
				.collect(Collectors.toList());
	}
	
	private boolean hasSearchedAttributes(String listed, List<String> searched) {
		listed = listed.toLowerCase();
		
		for (String attribute : searched) {
			if (!listed.contains(attribute)) {
				return false;
			}
		}
		
		return true;
	}

}
