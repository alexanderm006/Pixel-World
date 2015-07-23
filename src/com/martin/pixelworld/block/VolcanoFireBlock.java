package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class VolcanoFireBlock extends Block {

	private static float gravity = -0.05f;
	private float velocity = -0.4f;
	private float velocity_x = 0f;
	
	private float fx = 0, fy = 0;
	
	public VolcanoFireBlock() {
		BlockType blockType = BlockType.VOLCANO_FIRE;
		int color = ColorMath.generateColor(200, 250, 50, 100, 0, 15);
		boolean flammable = false;
		velocity += (Math.random() - 0.5) * 0.2;
		velocity_x = (float) ((Math.random()-0.5) * 0.15);
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		fy += velocity;
		fx += velocity_x;
		velocity -= gravity;
		
		if(World.getBlockAt(x+(int)fx, y+(int)fy) == null) World.moveBlock(x, y, (int)fx, (int)fy);
	}
	
}
