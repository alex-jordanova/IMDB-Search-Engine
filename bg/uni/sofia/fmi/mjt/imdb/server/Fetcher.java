package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.IOException;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;

public interface Fetcher {
	Request getRequest();
	String getRequestedMovie(); 
	byte[] fetch() throws IOException;
}
