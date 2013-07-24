package scrollsai;

import org.junit.Test;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Unit;

public class GameStateTests {
	
	@Test
	public void testToString() {
		try {
			GameState state = new GameState();
			for(int x = 0; x < 3; x++) {
				for(int y = 0; y < 5; y++) {
					state.getOurGrid()[x][y] = new Unit(x, y, 0, x, y);
					state.getTheirGrid()[x][y] = new Unit(x, y, 0, x, y);
				}
			}
			
			System.out.println(state);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCaptureScreen() {
		GameState state = new GameState();
		
		state.captureScreen();
	}
}
