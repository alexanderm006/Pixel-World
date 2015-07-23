package com.martin.pixelworld.block;

import com.martin.pixelworld.Stats;
import com.martin.pixelworld.World;
import com.martin.pixelworld.math.Physics;

public class BlockLiquid extends Block{

	protected int currentDirection;
	
	public BlockLiquid() {
		generateRandomDirection();
	}
	
	public void update(int x, int y) {
		gravity(x, y);
	}
	
	private void generateRandomDirection() {
		double r = Math.random();
		if(r >= 0.5) {
			currentDirection = 1;
		} else {
			currentDirection = -1;
		}
	}
	
	protected void gravity(int x, int y) {
		Stats.updates++;
		int dx = 0;
		int dy = 0;
		
		if(Physics.InScreenBounds(x, y+1) && World.getBlockAt(x, y+1) == null) {
			dy = 1;
		}
		
		else {
			if(World.getBlockAt(x+currentDirection, y) == null) {
				dx = currentDirection;
			} else if (World.getBlockAt(x-currentDirection, y) == null) {
				currentDirection *= -1;
				dx = currentDirection;
			}
		}
		
		World.moveBlock(x, y, dx, dy);
	}
	
}
