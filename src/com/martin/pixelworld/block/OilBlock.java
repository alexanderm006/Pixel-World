package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class OilBlock extends BlockLiquid {

	public boolean ignited;
	
	private float chanceColorChange = 1.0f / 180.0f;
	private float chanceOfFloat = 1.0f / 5.0f;
	private float chanceOfIgnite = 1.0f / 10.0f;
	private float chanceOfVapor = 1.0f / 300.0f;
	
	public OilBlock() {
		BlockType blockType = BlockType.OIL;
		int color = generateColor();
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		if(Math.random() <= chanceOfFloat) floatUp(x, y);
		gravity(x, y);
		if(!ignited && Math.random() <= chanceOfIgnite) tryIgnite(x, y);
		
		if(ignited) {
			//color change
			if(Math.random() <= chanceColorChange) color = generateIgnitedColor();
			if(Math.random() <= chanceOfVapor && World.getBlockAt(x, y-1) == null) {
				World.addBlock(new FireBlock(-1), x, y-1);
			}
		}
		
//		Block leftBlock = World.getBlockAt(x-1, y);
//		Block rightBlock = World.getBlockAt(x+1, y);
//		
//		BlockType leftType = (leftBlock == null) ? null : leftBlock.blockType;
//		BlockType rightType = (rightBlock == null) ? null : rightBlock.blockType;
//		
//		if(leftType == BlockType.WATER && rightType != BlockType.WATER) {
//			World.switchBlocksAt(x, y, x-1, y);
//		}
//		if(rightType == BlockType.WATER && leftType != BlockType.WATER) {
//			World.switchBlocksAt(x, y, x+1, y);
//		}
	}
	
	private void tryIgnite(int x, int y) {
		for(int dx=-1; dx<=1; dx++) {
			for(int dy=-1; dy<=1; dy++) {
				Block block = World.getBlockAt(x+dx, y+dy);
				if(block != null) {
					if(block.blockType == BlockType.OIL) {
						OilBlock oilBlock = (OilBlock) World.getBlockAt(x+dx, y+dy);
						if(oilBlock.ignited) {
							ignited = true;
							color = generateIgnitedColor();
						}
					}
					else if(block.blockType == BlockType.FIRE) {
						ignited = true;
						color = generateIgnitedColor();
					}
				}
			}
		}
	}
	
	private int generateColor() {
		int max_c = 80;
		int min_c = 50;
		int c = (int)(Math.random() * (max_c - min_c) + min_c);
		return ColorMath.rgbToHex(c, c, c);
	}
	
	private int generateIgnitedColor() {
		return ColorMath.generateColor(130, 191, 0, 0, 36, 52);
	}
	
	private void floatUp(int x, int y) {
		Block blockAbove = World.getBlockAt(x, y-1);
		
		Block blockAboveRight = World.getBlockAt(x+1, y-1);
		Block blockAboveLeft = World.getBlockAt(x-1, y-1);
		Block blockRight = World.getBlockAt(x+1, y);
		Block blockLeft = World.getBlockAt(x-1, y);
		
		if(blockAbove != null && blockAbove.blockType == BlockType.WATER) {
			World.switchBlocksAt(x, y, x, y-1);
		}
		
		else if(blockAboveRight != null && blockAboveRight.blockType == BlockType.WATER && blockRight != null && blockRight.blockType == BlockType.WATER) {
			World.switchBlocksAt(x, y, x+1, y);
		}
		
		else if(blockAboveLeft != null && blockAboveLeft.blockType == BlockType.WATER && blockLeft != null && blockLeft.blockType == BlockType.WATER) {
			World.switchBlocksAt(x, y, x-1, y);
		}
	}
	
}
