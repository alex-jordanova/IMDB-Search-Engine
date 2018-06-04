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

public class MoviesResponseProcessor implements ResponseProcessor {

	private Request request;
	private InputStream response;
	private static final String GENRE_FIELD = "genre";
	private static final String RATING_FIELD = "imdbrating";
	private static final String ACTOR_FIELD = "actors";
	private static final String DESC_ORDER = "desc";

	public MoviesResponseProcessor(Request request, InputStream response) {
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
			System.out.println("Can't read stream.");
		}

	}

	private List<String> sortAndFilter(List<String> movies) {
		for (Field field : request.getFields()) {
			if (field.getName().contains(ACTOR_FIELD)) {
				movies = filterByActors(movies, field.getAttributes());
			} else if (field.getName().contains(GENRE_FIELD)) {
				movies = filterByGenres(movies, field.getAttributes());
			} else {
				movies = sortByRatings(movies, field);
			}
		}
		return movies;
	}

	private List<String> sortByRatings(List<String> toSort, Field field) {
		toSort.sort(new Comparator<String>() {

			@Override
			public int compare(String movie1, String movie2) {
				String rating1 = DataAnalyzer.extractField(movie1, RATING_FIELD);
				String rating2 = DataAnalyzer.extractField(movie2, RATING_FIELD);
				return Double.compare(DataAnalyzer.getRatingScore(rating1), DataAnalyzer.getRatingScore(rating2));
			}

		});
		
		if (field.getName().contains(DESC_ORDER)) {
			Collections.reverse(toSort);
		}
		return toSort;
	}

	private List<String> filterByActors(List<String> toFilter, List<String> actors) {
		return toFilter.stream()
				.filter(movie -> DataAnalyzer
						.containsAllAttributes(DataAnalyzer.extractField(movie, ACTOR_FIELD), actors))
				.collect(Collectors.toList());
	}

	private List<String> filterByGenres(List<String> toFilter, List<String> genres) {
		return toFilter.stream()
				.filter(movie -> DataAnalyzer
						.containsAllAttributes(DataAnalyzer.extractField(movie, GENRE_FIELD), genres))
				.collect(Collectors.toList());
	}
}
