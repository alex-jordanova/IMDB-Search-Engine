package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.InputStream;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class ResponseFactory {
	public static Response get(Request request, InputStream response) {
		if (request.getType().equals(RequestType.POSTER)) {
			return new PosterResponse(request, response);
		} 
		if (request.getType().equals(RequestType.TVSERIES)) {
			return new SeriesResponse(response);
		}
		if (request.getType().equals(RequestType.MOVIE)) {
			return new MovieResponse(request, response);
		} else {
			return new MoviesResponse(request, response);	
		}
	}
}
