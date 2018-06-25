package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class MovieResponse implements Response {

	private Request request;
	private InputStream response; 
	private static final String FIELDS_OPTION = "--fields";
	
	public MovieResponse(Request request, InputStream response) {
		this.request = request;
		this.response = response;
	}
	
	@Override
	public void process() throws IOException {
		try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(response))) {
			String movieData = responseReader.readLine();

			if (request.hasSpecifiedFields()) {
				ResponsePrinter.printTitle(movieData);
				ResponsePrinter.printFieldResults(movieData, request.getFieldAttributes(FIELDS_OPTION));
			} else {
				System.out.println(movieData);
			}
			
		} catch (IOException e) {
			System.out.println("Problem reading response from input stream!");
		} finally {
			response.close();
		}
	}


}
