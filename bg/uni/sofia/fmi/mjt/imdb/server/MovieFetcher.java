package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.IOException;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class MovieFetcher implements Fetcher {
	private Request request;
	private Cache cache;
	
	public MovieFetcher(Request request, Cache cache) {
		this.request = request;
		this.cache = cache;
	}

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public byte[] fetch() throws IOException {
		return cache.fetchMovieData(this);
	}

	@Override
	public String getRequestedMovie() {
		return request.getMovieTitle();
	}
	
}
