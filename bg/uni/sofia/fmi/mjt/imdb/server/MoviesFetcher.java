package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.IOException;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public class MoviesFetcher implements Fetcher {
	private Request request;
	private Cache cache;
	
	public MoviesFetcher(Request request, Cache cache) {
		this.request = request;
		this.cache = cache;
	}

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public byte[] fetch() throws IOException {
		return cache.fetchAllMovies(this);
	}

	@Override
	public String getRequestedMovie() {
		return request.getMovieTitle();
	}

}
