package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;
import com.martin.pixelworld.math.Physics;

public class DirtBlock extends BlockSolid {
	
	public DirtBlock() {
		BlockType blockType = BlockType.DIRT;
		int color = ColorMath.generateColor(95, 158, 55, 80, 0, 0);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		gravity(x, y);
		if(canSpawnGrass(x, y)) growGrass(x, y);
	}
	
	private void growGrass(int x, int y) {
		float time = 10.0f;
		if(Math.random() <= 1.0 / (60.0 * time)) {
			World.addBlock(new GrassBlock(), x, y-1);
		}
	}
	
	private boolean canSpawnGrass(int x, int y) {
		Block block = World.getBlockAt(x, y-1);
		
		if(Physics.InScreenBounds(x, y-1)) {
			if(block == null) {
				return true;
			}
		}
		
		return false;
	}

}
