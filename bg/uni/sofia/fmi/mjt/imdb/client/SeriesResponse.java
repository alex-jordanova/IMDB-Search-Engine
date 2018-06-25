package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SeriesResponse implements Response {
	private InputStream response; 
	
	public SeriesResponse(InputStream response) {
		this.response = response;
	}
	
	@Override
	public void process() {
		try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(response))) {
			ResponsePrinter.printEpisodes(responseReader.readLine());
		} catch (IOException e) {
			System.out.println("Problem reading input stream!");
		}
	}

}
