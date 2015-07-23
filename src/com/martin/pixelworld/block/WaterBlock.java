package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;
import com.martin.pixelworld.math.Physics;

public class WaterBlock extends BlockLiquid {
	
	private float chanceCondensateWhenHeated = 1.0f / (60.0f * 5.0f);
	private int heatRange = 15;
	
	public WaterBlock() {
		blockType = BlockType.WATER;
		int color = ColorMath.generateColor(0, 82, 183, 206, 255, 255);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		if(Math.random() <= chanceCondensateWhenHeated) {
			if(isOnSurface(x, y) && isHeated(x, y)) {
				World.removeBlock(x, y);
				World.addBlock(new SteamBlock(), x, y);
			}
		}
		
		gravity(x, y);
		growSeed(x, y);
	}
	
	private boolean isOnSurface(int x, int y) {
		Block blockAbove = World.getBlockAt(x, y-1);
		if(blockAbove == null) return true;
		return false;
	}
	
	private void growSeed(int x, int y) {
		Block blockAbove = World.getBlockAt(x, y-1);
		if(blockAbove != null) {
			if(blockAbove.blockType == BlockType.DIRT) {
				float time = 30.0f;
				if(Math.random() <= 1.0 / (60.0 * time) ) {
					for(int dx=-4; dx<=4; dx++) {
						Block block = World.getBlockAt(x+dx, y-1);
						if(block != null) {
							if(block.blockType == BlockType.SEED) return;
						}
					}
					World.removeBlock(x, y-1);
					World.addBlock(new SeedBlock(), x, y-1);
				}
			}
		}
	}
	
	private boolean isHeated(int x, int y) {
		for(int dy=1; dy<=heatRange; dy++) {
			Block blockBelow = World.getBlockAt(x, y+dy);
			if(blockBelow != null) {
				if(blockBelow.blockType == BlockType.OIL) {
					OilBlock oilBlock = (OilBlock) World.getBlockAt(x, y+dy);
					if(oilBlock.ignited) return true;
				}
			}
		}
		return false;
	}
	
}
