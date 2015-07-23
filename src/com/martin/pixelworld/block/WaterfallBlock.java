package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class WaterfallBlock extends DirtBlock {

	int lifeSpan = 5;
	
	public WaterfallBlock() {
		BlockType blockType = BlockType.WATER_FALL;
		int color = ColorMath.generateColor(0, 0, 0, 0, 200, 255);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		if(lifeSpan <= 0) {
			World.removeBlock(x, y);
			return;
		}
		lifeSpan--;
		for(int dx=-3; dx<=3; dx++) {
			for(int dy=-3; dy<=3; dy++) {
				if(dx==0 && dy==0) continue;
				World.addBlock(new SeedBlock(), x+dx, y+dy);
			}
		}
	}
}
