package com.martin.pixelworld.block;

import com.martin.pixelworld.Game;
import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class SnowBlock extends BlockSolid {

	private static float chanceOfFall = 0.1f;
	private static float snowToWaterTemp = 10f;
	private static float heatingRate = 1f;
	
	private float temp;
	
	public SnowBlock() {
		BlockType blockType = BlockType.SNOW;
		int color = ColorMath.generateColor(220, 230, 220, 230, 250, 255);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
		temp = 0;
	}
	
	public void update(int x, int y) {
		if(Math.random() > chanceOfFall) return;
		if(y == Game.height - 1) {
			temp += heatingRate;
			return;
		}
		
		if(temp > snowToWaterTemp) {
			World.removeBlock(x, y);
			World.addBlock(new WaterBlock(), x, y);
		}
		
		int dx = ((int) (Math.random() * 3)) - 1;
		
		int newX = x + dx;
		int newY = y + 1;
		if(World.getBlockAt(newX, newY) == null) {
			World.switchBlocksAt(x, y, newX, newY);
		} else {
			temp += heatingRate;
		}
	}
	
}
