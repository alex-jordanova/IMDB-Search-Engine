package corecomponentstests;

import static org.junit.Assert.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Field;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Parser;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class ParserTest {
	
	private static final Set<String> options = new HashSet<>(
			Arrays.asList("--fields", "--season"));
  
	String movieData = "{\"Title\":\"La La Land\",\"Year\":\"2016\",\"Rated\":\"PG-13\",\"Released\":\"25 Dec 2016\",\"Runtime\":\"128 min\",\"Genre\":\"Comedy, Drama, Music\",\"Director\":\"Damien Chazelle\",\"Writer\":\"Damien Chazelle\",\"Actors\":\"Ryan Gosling, Emma Stone, Amiée Conn, Terry Walters\",\"Plot\":\"While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.\",\"Language\":\"English\",\"Country\":\"USA, Hong Kong\",\"Awards\":\"Won 6 Oscars. Another 212 wins & 251 nominations.\",\"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BMzUzNDM2NzM2MV5BMl5BanBnXkFtZTgwNTM3NTg4OTE@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.1/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"92%\"},{\"Source\":\"Metacritic\",\"Value\":\"93/100\"}],\"Metascore\":\"93\",\"imdbRating\":\"8.1\",\"imdbVotes\":\"345,605\",\"imdbID\":\"tt3783958\",\"Type\":\"movie\",\"DVD\":\"25 Apr 2017\",\"BoxOffice\":\"$151,058,124\",\"Production\":\"Liongate Films\",\"Website\":\"http://www.lalaland.movie/\",\"Response\":\"True\"}\r\n";
	
	@Test
	public void testGetFieldValue() {
        String field = "year";
		assertEquals("\"Year\":\"2016\"", Parser.getFieldValue(movieData, field));
	}

	@Test
	public void testGetPosterURL() {
		String posterURL = "https://images-na.ssl-images-amazon.com/images/M/MV5BMzUzNDM2NzM2MV5BMl5BanBnXkFtZTgwNTM3NTg4OTE@._V1_SX300.jpg";
		assertEquals(posterURL, Parser.getPosterURL(Paths.get("cache", "movie", "la la land.txt")));
	}

	@Test
	public void testGetSeasonName() {
		Request twinPeaks = new Request("get-tv-series twin peaks --season 1");
		assertEquals("twin peaks1", Parser.getSeasonName(twinPeaks));
	}

	@Test
	public void testGetMovieTitle() {
		String command = "get-movie ready player one";
		String[] words = command.split("[^a-zA-Z0-9:'-]+");
		
		assertEquals("ready player one", Parser.getMovieTitle(words, options));
	}

	@Test
	public void testGetRequestType() {
		String command = "get-movie ready player one";
		String[] words = command.split("[^a-zA-Z0-9:'-]+");
		
		assertEquals(RequestType.MOVIE, Parser.getRequestType(words));
	}

	@Test
	public void testGetFields() {
		String command = "get-movie ready player one --fields year, genre";
		String[] words = command.split("[^a-zA-Z0-9:'-]+");
		
		List<Field> fields = new ArrayList<>();
		fields.add(new Field("--fields"));
		fields.get(0).addAttribute("year");
		fields.get(0).addAttribute("genre");
		
		List<Field> result = Parser.getFields(words, options);
		
		assertEquals(fields.get(0).getAttribute(0), result.get(0).getAttribute(0));
	}

}
