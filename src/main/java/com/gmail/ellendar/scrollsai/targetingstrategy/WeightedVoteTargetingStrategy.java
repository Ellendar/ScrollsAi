package com.gmail.ellendar.scrollsai.targetingstrategy;


import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;

/**
 * Simple weight vote among candidate strategies. Each strategy's target point is given
 * a value corresponding to its weight and the highest valued point is chosen.
 * TODO: Enhance targeting strategy to return a target mesh so strategies can vote based on
 * strategy weight and target confidence.
 * @author jgill
 *
 */
public class WeightedVoteTargetingStrategy implements TargetingStrategy {
	
	private TargetingStrategy[] strategies;
	private float[] weights;
	
	protected WeightedVoteTargetingStrategy(TargetingStrategy[] strategies, float[] weights) {
		this.strategies = strategies;
		this.weights = weights;
	}

	@Override
	public Point getTarget(GameState state) {
		float[][] candidates = new float[3][5];
		Point target = null;
		int i = 0;
		for(TargetingStrategy strategy : strategies) {
			target = strategy.getTarget(state);
			if(target != null) {
				candidates[target.x][target.y] += weights[i];
			}
			++i;
		}
		
		float greatest = 0f;
		target = null;
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 5; y++) {
				if(candidates[x][y] > greatest) {
					target = new Point(x,y);
					greatest = candidates[x][y];
				}
			}
		}
		
		return target;
	}

}
