package com.martin.pixelworld.block;

import com.martin.pixelworld.World;

public class PlantBlock extends BlockSolid {

	private int parentX, parentY, dx, dy;
	
	public PlantBlock(int parentX, int parentY, int dx, int dy, int color) {
		BlockType blockType = BlockType.PLANT;
		boolean flammable = true;
		initProperties(blockType, color, flammable);
		
		this.parentX = parentX;
		this.parentY = parentY;
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update(int x, int y) {
		gravity(x, y);
	}
	
}
