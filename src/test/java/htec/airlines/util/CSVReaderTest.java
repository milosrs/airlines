package htec.airlines.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;

import htec.airlines.utility.CSVReader;

public class CSVReaderTest {
	private static String AIRPORTS_FILENAME = "/resources/airports.txt";
	
	@Test
	public void readCSV_fromInputStream_shouldNotBeNull() throws Exception {
		InputStream resource = getClass().getResourceAsStream(AIRPORTS_FILENAME);
		assertNotNull(resource);
		
		List<List<String>> tokens = CSVReader.readCSVTokens(resource);
		
		assertNotNull(tokens);
		assertTrue(!tokens.isEmpty());
	}
	
	@Test
	public void readCSV_fromUrl_shouldNotBeNull() throws Exception {
		URL url = getClass().getResource(AIRPORTS_FILENAME);
		assertNotNull(url);
		
		String filePath = url.getPath();
		assertNotNull(filePath);
		
		List<List<String>> tokens = CSVReader.readCSVTokens(filePath);
		
		assertNotNull(tokens);
		assertTrue(!tokens.isEmpty());
	}
}
