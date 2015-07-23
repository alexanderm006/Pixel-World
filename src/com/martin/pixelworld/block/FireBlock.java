package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class FireBlock extends BlockSolid {
	
	private float life;
	
	private float chanceOfDeath = 0.50f / 60.0f;
	
	private float chanceOfMove = 4.0f / 60.0f;
	
	private float velocity;
	
	public FireBlock(float initialVelocity) {
		this.velocity = initialVelocity;
		BlockType blockType = BlockType.FIRE;
		int color = ColorMath.generateColor(255, 255, 0, 100, 0, 0);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		
		if(World.getBlockAt(x, y-1) != null) {
			World.removeBlock(x, y);
		}
		
		life++;
		velocity += 0.01f;
		if(velocity > 1) {
			velocity = 1;
		}
		if(isWaterBelow(x, y)) {
			World.removeBlock(x, y);
		}
		
		if(Math.random() <= chanceOfDeath && life >= 20) {
			World.removeBlock(x, y);
		}
		
		if(Math.random() <= chanceOfMove) {
			spreadFire(x, y);
			updateFireColor();
			fireGravtiy(x, y);
		}
	}
	
	private void fireGravtiy(int x, int y) {
		int dx = 0;
		int dy = Math.round(velocity);
		
		double r = Math.random();
		if(r <= 0.33) {
			dx = -1;
		} else if(r <= 0.66) {
			dx = 0;
		} else {
			dx = 1;
		}
		
		Block block = World.getBlockAt(x+dx, y+dy);
		if(block == null) {
			World.moveBlock(x, y, dx, dy);
		}
	}
	
	private boolean spreadFire(int x, int y) {
		boolean spread = false;
		
		updateFireColor();
		for(int dx=-1; dx<=1; dx++) {
			for(int dy=-1; dy<= 1; dy++) {
				if(dx == 0 && dy == 0) continue;
				
				Block block = World.getBlockAt(x+dx, y+dy);
				if(block != null) {
					if(block.flammable == true) {
						World.removeBlock(x+dx, y+dy);
						World.addBlock(new FireBlock(1.0f), x+dx, y+dy);updateFireColor();
						spread = true;
					}
				}
			}
		}
		
		return spread;
	}
	
	private void updateFireColor() {
		color = ColorMath.generateColor(255, 255, 0, 100, 0, 0);
	}
	
	private boolean isWaterBelow(int x, int y) {
		Block belowBlock = World.getBlockAt(x, y+1);
		if(belowBlock == null) return false;
		if(belowBlock.blockType == BlockType.WATER) return true;
		return false;
	}
	
}
