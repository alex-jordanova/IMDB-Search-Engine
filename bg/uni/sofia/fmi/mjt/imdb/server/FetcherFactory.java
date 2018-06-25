package bg.uni.sofia.fmi.mjt.imdb.server;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class FetcherFactory {
	public static Fetcher get(Request request, Cache cache) {
		if (request.isType(RequestType.POSTER)) {
			return new PosterFetcher(request, cache);
		} else if (request.isType(RequestType.MOVIE)) {
			return new MovieFetcher(request, cache);
		} else {
			return new SeriesFetcher(request, cache);
		}
	}
	
}
