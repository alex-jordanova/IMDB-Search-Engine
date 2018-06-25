package servertests;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;

import org.junit.Test;

import bg.uni.sofia.fmi.mjt.imdb.server.OmdbConnector;

public class OmdbConnectorTest {

	@Test
	public void testDownloadMovie() throws MalformedURLException {
		String movieName = "ready player one";
		String destFile = "test.txt";
		
		OmdbConnector.downloadMovie(movieName, destFile);
		
		assertTrue(new File(destFile).exists());
	}

	@Test
	public void testDownloadSeriesSeason() throws MalformedURLException {
		String seriesName = "twin peaks";
		String destFile = "seriestest.txt";
		
		OmdbConnector.downloadSeriesSeason(seriesName, "1", destFile);
		
		assertTrue(new File(destFile).exists());
	}

	@Test
	public void testDownloadPoster() {
		String posterURL = "https://m.media-amazon.com/images/M/MV5BY2JiYTNmZTctYTQ1OC00YjU4LWEwMjYtZjkwY2Y5MDI0OTU3XkEyXkFqcGdeQXVyNTI4MzE4MDU@._V1_UX182_CR0,0,182,268_AL_.jpg";
		String destFile = "test.jpg";
		
		OmdbConnector.downloadPoster(posterURL, destFile);
		
		assertTrue(new File(destFile).exists());
	}

}
