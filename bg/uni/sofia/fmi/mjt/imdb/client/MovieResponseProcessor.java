<<<<<<< HEAD
package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class MovieResponseProcessor implements ResponseProcessor {

	private Request request;
	private InputStream response; 
	private static final String TITLE_FIELD = "title";
	
	public MovieResponseProcessor(Request request, InputStream response) {
		this.request = request;
		this.response = response;
	}
	
	@Override
	public void process() {
		try (BufferedReader input = new BufferedReader(new InputStreamReader(response))) {
			String data = input.readLine();
			if (request.getFields().isEmpty()) {
				System.out.println(data);
			} else {
				List<String> fields = request.getFields().get(0).getAttributes();
				System.out.println(DataAnalyzer.extractField(data, TITLE_FIELD));
				for (String field : fields) {
					System.out.println(DataAnalyzer.extractField(data, field));
				}
			}
		} catch (IOException e) {
			System.out.println("Can't read stream.");
		}
	}

}
=======
package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class MovieResponseProcessor implements ResponseProcessor {

	private Request request;
	private InputStream response; 
	private static final String TITLE_FIELD = "title";
	
	public MovieResponseProcessor(Request request, InputStream response) {
		this.request = request;
		this.response = response;
	}
	
	@Override
	public void process() {
		try (BufferedReader input = new BufferedReader(new InputStreamReader(response))) {
			String data = input.readLine();
			if (request.getFields().isEmpty()) {
				System.out.println(data);
			} else {
				List<String> fields = request.getFields().get(0).getAttributes();
				System.out.println(DataAnalyzer.extractField(data, TITLE_FIELD));
				for (String field : fields) {
					System.out.println(DataAnalyzer.extractField(data, field));
				}
			}
		} catch (IOException e) {
			System.out.println("Can't read stream.");
		}
	}

}
>>>>>>> 3d073b38f6e2da80cf7ee58169d9aae5b7b9ef48
