package com.martin.pixelworld.block;

import com.martin.pixelworld.Game;
import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class AntigravityBlock extends Block {

	public float acl;
	
	public AntigravityBlock() {
		BlockType blockType = BlockType.ANTI_GRAVITY;
		int color = ColorMath.rgbToHex(255, 0, 0);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int xp, int yp) {
		for(int y=yp+1; y<Game.height; y++) {
			Block block = World.getBlockAt(xp, y);
			if(block != null) {
				World.switchBlocksAt(xp, y, xp, y-1);
				break;
			}
		}
	}
	
}
