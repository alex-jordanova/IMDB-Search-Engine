package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.InputStream;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class ResponseProcessors {
	public static ResponseProcessor get(Request request, InputStream response) {
		if (request.getType().equals(RequestType.POSTER)) {
			return new PosterResponseProcessor(request, response);
		} 
		if (request.getType().equals(RequestType.TVSERIES)) {
			return new SeriesResponseProcessor(response);
		}
		if (request.getType().equals(RequestType.MOVIE)) {
			return new MovieResponseProcessor(request, response);
		} 
		return new MoviesResponseProcessor(request, response);
	}
}
