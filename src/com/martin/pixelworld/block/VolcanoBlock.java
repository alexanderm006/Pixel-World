package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class VolcanoBlock extends Block {
	
	public VolcanoBlock() {
		BlockType blockType = BlockType.VOLCANO;
		int color = ColorMath.generateColor(0, 0, 0, 0, 0, 0);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	boolean first = false;
	public void update(int x, int y) {
		if(first == false) World.addBlock(new VolcanoFireBlock(), x, y-1);
		first = true;
		erupt(x, y);
	}
	
	public void erupt(int x, int y) {
		if(Math.random() <= 0.4) {
			for(int dx=-2; dx<=4; dx++ ){
				World.addBlock(new VolcanoFireBlock(), x+dx, y-1);
			}
		}
	}

}
