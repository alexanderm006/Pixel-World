package com.martin.pixelworld.block;

import com.martin.pixelworld.Screen;
import com.martin.pixelworld.World;
import com.martin.pixelworld.math.Physics;

public abstract class Block {
	
	public BlockType blockType;
	protected int color;
	protected boolean flammable;
	
	public boolean moved;
	
	public Block() {
		
	}
	
	protected void initProperties(
			BlockType blockType, 
			int color, 
			boolean flammable) {
		
		this.blockType = blockType;
		this.color = color;
		this.flammable = flammable;
	}
	
	public abstract void update(int x, int y);
	
	public void render(int x, int y) {
		Screen.setPixel(x, y, color);
	}
	
	public BlockType getBlockType() {
		return blockType;
	}
	
}
