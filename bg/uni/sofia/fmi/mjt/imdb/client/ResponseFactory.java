package bg.uni.sofia.fmi.mjt.imdb.client;

import java.io.InputStream;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class ResponseFactory {
	public static Response get(Request request, InputStream response) {
		if (request.isType(RequestType.POSTER)) {
			return new PosterResponse(request, response);
		} else if (request.isType(RequestType.TVSERIES)) {
			return new SeriesResponse(response);
		} else {
			return new MovieResponse(request, response);
		} 
	}
}
