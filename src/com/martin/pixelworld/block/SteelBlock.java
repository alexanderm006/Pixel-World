package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;
import com.martin.pixelworld.math.Physics;

public class SteelBlock extends BlockSolid {

	private boolean falling = false;
	
	public SteelBlock() {
		BlockType blockType = BlockType.STEEL;
		int color = generateColor();
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	private int generateColor() {
		int min = 135;
		int max = 150;
		int c = (int)(Math.random() * (max - min) + min);
		int color = ColorMath.rgbToHex(c, c, c);
		return color;
	}
	
	public void update(int x, int y) {
		Block leftBlock = World.getBlockAt(x-1, y);
		Block rightBlock = World.getBlockAt(x+1, y);
		
		Block belowBlock = World.getBlockAt(x, y+1);
		
		if(falling) {
			if(!Physics.InScreenBounds(x, y+1)) {
				falling = false;
			} else if (belowBlock != null) {
				falling = false;
			}  else {
				gravity(x, y);
				return;
			}
		}
		
		if(leftBlock == null && rightBlock == null) {
			falling = true;
			gravity(x, y);
		}
		
		else {
			boolean gravity = true;
			
			if(leftBlock != null) {
				if(leftBlock.blockType == BlockType.STEEL) {
					gravity = false;
				}
			}
			
			if(rightBlock != null) {
				if(rightBlock.blockType == BlockType.STEEL) {
					gravity = false;
				}
			}
			
			if(gravity) {
				gravity(x, y);
			}
		}
	}
	
}
