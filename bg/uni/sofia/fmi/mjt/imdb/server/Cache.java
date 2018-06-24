package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Parser;

public class Cache {
	private static Map<String, Path> movieDatabase;
	private static Map<String, Path> seasonsDatabase;

	
	public Cache() {
		CacheLoader.createCacheDirectories();
		movieDatabase = CacheLoader.loadCacheDirectory(CacheLoader.MOVIE_DIRECTORY);
		seasonsDatabase = CacheLoader.loadCacheDirectory(CacheLoader.SERIES_DIRECTORY);
	}

	public synchronized byte[] fetchMovieData(Fetcher fetcher) throws IOException {
		if (!movieDatabase.containsKey(fetcher.getRequestedMovie())) {
			saveMovieToCache(fetcher);
		}
		
		Path moviePath = movieDatabase.get(fetcher.getRequestedMovie());
		return Files.readAllBytes(moviePath);
	}

	public synchronized byte[] fetchPoster(Fetcher fetcher) throws IOException {
		Path posterPath = CacheLoader.createDestinationPath(fetcher.getRequest(), CacheLoader.JPG_EXTENSION);
		String posterURL = Parser.getPosterURL(movieDatabase.get(fetcher.getRequestedMovie()));
		OmdbConnector.downloadPoster(posterURL, posterPath.toString());
		return Files.readAllBytes(posterPath);
	}

	public synchronized byte[] fetchEpisodesBySeason(Fetcher fetcher) throws IOException {
		String season = Parser.getSeasonName(fetcher.getRequest());
		
		if (!seasonsDatabase.containsKey(season)) {
			saveSeasonToCache(fetcher, season);
		}
		
		Path seasonPath = seasonsDatabase.get(season);
		return Files.readAllBytes(seasonPath);
	}
	
	public synchronized byte[] fetchAllMovies(Fetcher fetcher) throws IOException {
		Collection<Path> movieEntries = movieDatabase.values();
		ByteArrayOutputStream allMovies = new ByteArrayOutputStream();
		
		for (Path entry : movieEntries) {
			allMovies.write(Files.readAllBytes(entry));
		}

		return allMovies.toByteArray();
	}

	
	
	private synchronized void saveMovieToCache(Fetcher fetcher) throws MalformedURLException {
		Path destFile = CacheLoader.createDestinationPath(fetcher.getRequest(), CacheLoader.TXT_EXTENSION);
		OmdbConnector.downloadMovie(fetcher.getRequestedMovie(), destFile.toAbsolutePath().toString());
		movieDatabase.put(fetcher.getRequestedMovie(), destFile);
	}

	private synchronized void saveSeasonToCache(Fetcher fetcher, String season) throws MalformedURLException {
		Path destFile = CacheLoader.createDestinationPath(fetcher.getRequest(), CacheLoader.TXT_EXTENSION);
		String seasonNumber = fetcher.getRequest().getSeasonNumber(); 		
		OmdbConnector.downloadSeriesSeason(fetcher.getRequestedMovie(),
				seasonNumber, destFile.toString());
		seasonsDatabase.put(Parser.getSeasonName(fetcher.getRequest()), destFile);
	}

}
