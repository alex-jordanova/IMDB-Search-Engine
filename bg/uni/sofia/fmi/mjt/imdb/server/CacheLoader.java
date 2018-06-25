package bg.uni.sofia.fmi.mjt.imdb.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Parser;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class CacheLoader {
	public static final String MAIN_DIRECTORY = "cache";
	public static final String MOVIE_DIRECTORY = "movie";
	public static final String POSTER_DIRECTORY = "poster";
	public static final String SERIES_DIRECTORY = "tv";
	public static final String TXT_EXTENSION = ".txt";
	public static final String JPG_EXTENSION = ".jpg";
	public static final String HTTP_PROTOCOL = "http";

	public static void createCacheDirectories() {
		createDirectory(MAIN_DIRECTORY);
		createDirectory(MAIN_DIRECTORY + "\\" + POSTER_DIRECTORY);
		createDirectory(MAIN_DIRECTORY + "\\" + MOVIE_DIRECTORY);
		createDirectory(MAIN_DIRECTORY + "\\" + SERIES_DIRECTORY);
	}

	public static Map<String, Path> loadCacheDirectory(String directory) {
		Map<String, Path> movies = new HashMap<>();

		try (DirectoryStream<Path> movieDir = Files.newDirectoryStream(Paths.get(MAIN_DIRECTORY, directory))) {
			for (Path entry : movieDir) {
				movies.put(removeExtension(entry.getFileName().toString()), entry);
			}
		} catch (DirectoryIteratorException e) {
			System.out.println("Can't iterate through the directory.");
		} catch (IOException e1) {
			System.out.println("Can't access file.");
		}
		
		return movies;
	}
	
	public static Path createDestinationPath(Request request, String extension) {
		String fileName = "";
		
		if (request.isType(RequestType.TVSERIES)) {
			fileName = Parser.getSeasonName(request) + extension;
			return Paths.get(MAIN_DIRECTORY, request.getType().getType(), fileName);
		}
		
		fileName = request.getMovieTitle() + extension;
		return Paths.get(CacheLoader.MAIN_DIRECTORY, request.getTypeAsString(), fileName);
	}

	private static void createDirectory(String directoryName) {
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}

	private static String removeExtension(String file) {
		StringBuilder fileName = new StringBuilder(file);
		fileName.delete(fileName.indexOf(TXT_EXTENSION), fileName.length());
		return fileName.toString();
	}
}
