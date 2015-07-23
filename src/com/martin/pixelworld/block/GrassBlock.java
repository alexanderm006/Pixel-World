package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class GrassBlock extends BlockSolid {

	public GrassBlock() {
		BlockType blockType = BlockType.GRASS;
		int color = ColorMath.generateColor(0, 0, 100, 150, 0, 0);
		boolean flammable = true;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		gravity(x, y);
		if(isBlockAbove(x, y)) World.removeBlock(x, y);
		
		if(isSeedBelow(x, y)) World.removeBlock(x, y);
		else if(isSeedBelow(x-1, y)) World.removeBlock(x, y);
		else if(isSeedBelow(x+1, y)) World.removeBlock(x, y);
	}
	
	private boolean isBlockAbove(int x, int y) {
		Block blockAbove = World.getBlockAt(x, y-1);
		if(blockAbove == null) return false;
		return true;
	}
	
	private boolean isSeedBelow(int x, int y) {
		Block blockBelow = World.getBlockAt(x, y+1);
		if(blockBelow == null) return false;
		if(blockBelow.blockType == BlockType.SEED) return true;
		return false;
	}
	
}
