package scrollsai;

import java.util.Random;

import org.junit.Test;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.ScrollsAI;
import com.gmail.ellendar.scrollsai.scroll.Scroll;
import com.gmail.ellendar.scrollsai.scroll.UnitScroll;

public class GameLoopTests {
	
	@Test
	public void testGameLoop() {
		
		Random random = new Random();
		GameState state = new GameState();
		
		for(int i = 1; i < 7; i++) {
			Scroll scroll = new UnitScroll(i, i, 2, i);
			scroll.setName("foo");
			state.getHand().add(scroll);
		}
		
		while(true) {
			System.out.println(state);
			ScrollsAI.gameLoop(state);
			int rand = random.nextInt(7) + 1;
			state.getHand().add(new UnitScroll(rand, rand, 2, rand));
		}
	}
}
