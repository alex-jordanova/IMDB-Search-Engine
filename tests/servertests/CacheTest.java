package servertests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import org.junit.Test;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.server.Cache;
import bg.uni.sofia.fmi.mjt.imdb.server.Fetcher;
import bg.uni.sofia.fmi.mjt.imdb.server.FetcherFactory;

public class CacheTest {
	
	Cache cache = new Cache();

	@Test
	public void testCacheConstructorProperlyCreatingDirs() {
		
		assertTrue(Paths.get("cache", "movie").toFile().exists());
	}
	
	@Test
	public void testFetchMovieData() {
		Fetcher movieFetcher = FetcherFactory.get(new Request("get-movie ready player one"), cache);
		
		try (BufferedReader resultReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(cache.fetchMovieData(movieFetcher))))) {
			String movieData = "{\"Title\":\"Ready Player One\",\"Year\":\"2018\",\"Rated\":\"PG-13\",\"Released\":\"29 Mar 2018\",\"Runtime\":\"140 min\",\"Genre\":\"Action, Adventure, Sci-Fi\",\"Director\":\"Steven Spielberg\",\"Writer\":\"Zak Penn (screenplay by), Ernest Cline (screenplay by), Ernest Cline (based on the novel by)\",\"Actors\":\"Tye Sheridan, Olivia Cooke, Ben Mendelsohn, Lena Waithe\",\"Plot\":\"When the creator of a virtual reality world called the OASIS dies, he releases a video in which he challenges all OASIS users to find his Easter Egg, which will give the finder his fortune.\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BY2JiYTNmZTctYTQ1OC00YjU4LWEwMjYtZjkwY2Y5MDI0OTU3XkEyXkFqcGdeQXVyNTI4MzE4MDU@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.8/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"73%\"},{\"Source\":\"Metacritic\",\"Value\":\"64/100\"}],\"Metascore\":\"64\",\"imdbRating\":\"7.8\",\"imdbVotes\":\"130,459\",\"imdbID\":\"tt1677720\",\"Type\":\"movie\",\"DVD\":\"24 Jul 2018\",\"BoxOffice\":\"N/A\",\"Production\":\"Warner Bros. Pictures\",\"Website\":\"http://readyplayeronemovie.com/\",\"Response\":\"True\"}\r\n";
			
			assertEquals(movieData, resultReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFetchEpisodesBySeason() {
		Fetcher seriesFetcher = FetcherFactory.get(new Request("get-tv-series twin peaks --season 1"), cache);
		
		try (BufferedReader resultReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(cache.fetchMovieData(seriesFetcher))))) {
			String seriesData = "{\"Title\":\"Twin Peaks\",\"Year\":\"1990–1991\",\"Rated\":\"TV-MA\",\"Released\":\"08 Apr 1990\",\"Runtime\":\"47 min\",\"Genre\":\"Crime, Drama, Mystery\",\"Director\":\"N/A\",\"Writer\":\"Mark Frost, David Lynch\",\"Actors\":\"Kyle MacLachlan, Michael Ontkean, Mädchen Amick, Dana Ashbrook\",\"Plot\":\"An idiosyncratic FBI agent investigates the murder of a young woman in the even more idiosyncratic town of Twin Peaks.\",\"Language\":\"English, Icelandic, Afrikaans, Norwegian\",\"Country\":\"USA\",\"Awards\":\"Won 3 Golden Globes. Another 12 wins & 44 nominations.\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTExNzk2NjcxNTNeQTJeQWpwZ15BbWU4MDcxOTczOTIx._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.8/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"8.8\",\"imdbVotes\":\"142,038\",\"imdbID\":\"tt0098936\",\"Type\":\"series\",\"totalSeasons\":\"2\",\"Response\":\"True\"}\r\n";
			
			assertEquals(seriesData, resultReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
