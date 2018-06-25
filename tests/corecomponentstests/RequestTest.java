package corecomponentstests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import bg.uni.sofia.fmi.mjt.imdb.corecomponents.Request;
import bg.uni.sofia.fmi.mjt.imdb.corecomponents.RequestType;

public class RequestTest {
	
	Request twinPeaks = new Request("get-tv-series twin peaks --season 1");

	@Test
	public void testGetTypeAsString() {
		assertEquals("tv", twinPeaks.getTypeAsString());
	}

	@Test
	public void testHasFieldTrue() {
		assertTrue(twinPeaks.hasField("season"));
	}

	@Test
	public void testHasFieldFalse() {
		assertFalse(twinPeaks.hasField("genre"));
	}

	
	@Test
	public void testGetFieldAttributes() {
		List<String> attributes = twinPeaks.getFieldAttributes("season");
		
		assertEquals("1", attributes.get(0));
	}

	@Test
	public void testGetSeasonNumber() {
		assertEquals("1", twinPeaks.getSeasonNumber());
	}

	@Test
	public void testHasSpecifiedFields() {
		assertTrue(twinPeaks.hasSpecifiedFields());
	}

	@Test
	public void testIsTypeTrue() {
		assertTrue(twinPeaks.isType(RequestType.TVSERIES));
	}
	
	@Test
	public void testIsTypeFalse() {
		assertFalse(twinPeaks.isType(RequestType.MOVIE));
	}

}
