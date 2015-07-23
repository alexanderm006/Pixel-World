package com.martin.pixelworld.block;

import com.martin.pixelworld.Stats;
import com.martin.pixelworld.World;
import com.martin.pixelworld.math.Physics;

public class BlockSolid extends Block {
	
	public void update(int x, int y) {
		gravity(x, y);
	}
	
	
	protected void gravity(int x, int y) {
		Stats.updates++;
		if(!Physics.InScreenBounds(x, y+1)) return;
		
		Block blockBelow = World.getBlockAt(x, y+1);
		if(blockBelow == null) {
			World.moveBlock(x, y, 0, 1);
		}
	}

}
