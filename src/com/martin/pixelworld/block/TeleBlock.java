package com.martin.pixelworld.block;

import com.martin.pixelworld.Game;
import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class TeleBlock extends BlockSolid {

	public TeleBlock() {
		BlockType blockType = BlockType.TELE_BLOCK;
		int color = ColorMath.rgbToHex(0, 0, 255);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		gravity(x, y);
		teleportBlocksAround(x, y);
	}
	
	private void teleportBlocksAround(int x, int y) {
		for(int dx=-1; dx<=1; dx++) {
			for(int dy=-1; dy<=1; dy++) {
				if(dx == 0 && dy == 0) continue;
				Block block = World.getBlockAt(x+dx, y+dy);
				if(block == null) continue;
				
				int randomX = (int) (Math.random()*(Game.width-1));
				int randomY = (int) (Math.random()*(Game.height-1));
				
				Block randomBlock = World.getBlockAt(randomX, randomY);
				
				World.removeBlock(randomX, randomY);
				World.addBlock(block, randomX, randomY);
				World.removeBlock(x, y);
				World.addBlock(randomBlock, x, y);
				
			}
		}
	}
	
}
