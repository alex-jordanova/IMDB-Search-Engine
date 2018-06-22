package bg.uni.sofia.fmi.mjt.imdb.client;

import bg.uni.sofia.fmi.mjt.imdb.exceptions.NoSuchMovieException;

public interface Response {
	void process() throws NoSuchMovieException, NoSuchFieldException;
}
