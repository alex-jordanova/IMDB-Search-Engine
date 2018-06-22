package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.exceptions.NoSuchMovieException;

public class MovieResponse implements Response {

	private Request request;
	private InputStream response; 
	private static final String FIELDS_OPTION = "--fields";
	
	public MovieResponse(Request request, InputStream response) {
		this.request = request;
		this.response = response;
	}
	
	@Override
	public void process() throws NoSuchMovieException, NoSuchFieldException {
		try (BufferedReader input = new BufferedReader(new InputStreamReader(response))) {
			String movieData = input.readLine();
			
		/*	if (!isFoundMovie(movieData)) {
				throw new NoSuchMovieException("The specified movie wasn't found!");
			}
			*/
			
			if (request.hasSpecifiedFields()) {
				OutputFormatter.printTitle(movieData);
				OutputFormatter.printFieldResults(movieData, request.getFieldAttributes(FIELDS_OPTION));
			} else {
				System.out.println(movieData);
			}
			
		} catch (IOException e) {
			System.out.println("Problem reading input stream.");
		}
	}


}
