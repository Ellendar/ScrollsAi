package scrollsai;

import org.junit.Test;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.ScreenParser;

public class ScreenParserTests {
	
	@Test
	public void testHandParse() {
		ScreenParser parser = new ScreenParser();
		GameState state = new GameState();
		
		parser.parse(state);
		
		System.out.println(state);
	}
	
	@Test
	public void testReadOurGrid() {
		ScreenParser parser = new ScreenParser();
		GameState state = new GameState();
		state.captureScreen();
		
		parser.readOurGrid(state);
		
		System.out.println(state);
	}
}
