<<<<<<< HEAD
package bg.uni.sofia.fmi.mjt.imdb.server;

import java.net.Socket;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class ClientProcessors {
	public static ClientProcessor get(Socket clientSocket, Request request, Cache cache) {
		if (request.getType().equals(RequestType.POSTER)) {
			return new PosterClientProcessor(clientSocket, request, cache);
		} else if (request.getType().equals(RequestType.MOVIE)) {
			return new MovieClientProcessor(clientSocket, request, cache);
		} else if (request.getType().equals(RequestType.TVSERIES)) {
			return new SeriesClientProcessor(clientSocket, request, cache);
		}
		return new MoviesClientProcessor(clientSocket, request, cache);
	}
	
}
=======
package bg.uni.sofia.fmi.mjt.imdb.server;

import java.net.Socket;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class ClientProcessors {
	public static ClientProcessor get(Socket clientSocket, Request request, Cache cache) {
		if (request.getType().equals(RequestType.POSTER)) {
			return new PosterClientProcessor(clientSocket, request, cache);
		} else if (request.getType().equals(RequestType.MOVIE)) {
			return new MovieClientProcessor(clientSocket, request, cache);
		} else if (request.getType().equals(RequestType.TVSERIES)) {
			return new SeriesClientProcessor(clientSocket, request, cache);
		}
		return new MoviesClientProcessor(clientSocket, request, cache);
	}
	
}
>>>>>>> 3d073b38f6e2da80cf7ee58169d9aae5b7b9ef48
