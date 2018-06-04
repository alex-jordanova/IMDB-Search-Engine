package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;


import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class Cache {
	private static Map<String, Path> movieDatabase;
	private static Map<String, Path> seasonsDatabase;

	public Cache() {
		CacheManager.prepareDirectories();
		movieDatabase = CacheManager.loadDirectory(CacheManager.MOVIE_DIRECTORY);
		seasonsDatabase = CacheManager.loadDirectory(CacheManager.SERIES_DIRECTORY);
	}

	public synchronized byte[] fetchMovieData(ClientProcessor client) {
		if (!movieDatabase.containsKey(client.getRequest().getMovieTitle())) {
			saveMovieToDatabase(client);
		}
		return convertToByteArr(movieDatabase.get(client.getRequest().getMovieTitle()));
	}

	public synchronized byte[] fetchPoster(ClientProcessor client) {
		Path posterPath = createDestinationPath(client.getRequest(), CacheManager.JPG_EXTENSION);
		OmdbManager.downloadPoster(extractPosterURL(client.getRequest().getMovieTitle()), posterPath.toString());
		return convertToByteArr(posterPath);
	}

	public synchronized byte[] fetchEpisodesBySeason(ClientProcessor client) {
		String season = client.getRequest().getMovieTitle()
				+ client.getRequest().getFields().get(0).getAttributes().get(0);
		if (!seasonsDatabase.containsKey(season)) {
			saveSeasonToDatabase(client, season);
		}
		return convertToByteArr(seasonsDatabase.get(season));
	}
	
	public synchronized byte[] fetchAllMovies(ClientProcessor client) {
		Collection<Path> values = movieDatabase.values();
		ByteArrayOutputStream movies = new ByteArrayOutputStream();
		for (Path value : values) {
			byte[] movie = convertToByteArr(value);
			try {
				movies.write(movie);
			} catch (IOException e) {
				System.out.println("Cannot convert data to byte array.");
			}
		}
		return movies.toByteArray();
	}

	private synchronized void saveMovieToDatabase(ClientProcessor client) {
		Path destFile = createDestinationPath(client.getRequest(), CacheManager.TXT_EXTENSION);
		OmdbManager.downloadMovieData(client.getRequest().getMovieTitle(), destFile.toAbsolutePath().toString());
		movieDatabase.put(client.getRequest().getMovieTitle(), destFile);
	}

	private synchronized void saveSeasonToDatabase(ClientProcessor client, String season) {
		Path destFile = createDestinationPath(client.getRequest(), CacheManager.TXT_EXTENSION);
		String seasonNumber = client.getRequest().getFields().get(0).getAttributes().get(0);
		OmdbManager.downloadSeriesSeason(client.getRequest().getMovieTitle(),
				seasonNumber, destFile.toString());
		seasonsDatabase.put(client.getRequest().getMovieTitle() + seasonNumber, destFile);
	}

	private Path createDestinationPath(Request req, String extension) {
		if (req.getType().equals(RequestType.TVSERIES)) {
			return Paths.get(CacheManager.MAIN_DIRECTORY, req.getType().getType(),
					req.getMovieTitle() + req.getFields().get(0).getAttributes().get(0) + extension);
		}
		return Paths.get(CacheManager.MAIN_DIRECTORY, req.getType().getType(), req.getMovieTitle() + extension);
	}

	private synchronized String extractPosterURL(String searchedMovie) {
		Path filePath = movieDatabase.get(searchedMovie);
		String url = null;
		try (BufferedReader file = new BufferedReader(new FileReader(filePath.toFile()))) {
			String movieData = file.readLine();
			url = movieData.substring(movieData.indexOf(CacheManager.HTTP_PROTOCOL),
					movieData.indexOf('"', movieData.indexOf(CacheManager.HTTP_PROTOCOL)));
			
		} catch (IOException e) {
			System.out.println("Cannot open file.");
		}
		return url;
	}

	public byte[] convertToByteArr(Path filePath) {
		byte[] arr = null;
		try {
			arr = Files.readAllBytes(filePath);
		} catch (IOException e) {
			System.out.println("Cannot read file.");
		}
		return arr;
	}
	
}
