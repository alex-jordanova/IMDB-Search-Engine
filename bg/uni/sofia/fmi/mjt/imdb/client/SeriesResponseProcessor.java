package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SeriesResponseProcessor implements ResponseProcessor {
	private InputStream response; 
	
	public SeriesResponseProcessor(InputStream response) {
		this.response = response;
	}
	
	@Override
	public void process() {
		try (BufferedReader input = new BufferedReader(new InputStreamReader(response))) {
			System.out.println(DataAnalyzer.formatEpisodes(input.readLine()));
		} catch (IOException e) {
			System.out.println("Can't read stream.");
		}
	}

}
