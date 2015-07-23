package com.martin.pixelworld.block;

import com.martin.pixelworld.Game;
import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class NuclearBlock extends BlockSolid {

	private static long lastTimePlaced;
	private static int minTimeUntilNextPlace = 3000;
	private static int snowY = 10;
	
	public NuclearBlock() {
		BlockType blockType = BlockType.NUCLEAR;
		int color = ColorMath.generateColor(255, 255, 0, 0, 255, 255);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
		lastTimePlaced = System.currentTimeMillis();
	}
	
	public void update(int x, int y) {
		
		for(int yp = 0; yp < snowY; yp++) {
			for(int xp = 0; xp < Game.width; xp++) {
				if(Math.random() > 0.75f) continue;
				World.removeBlock(xp, yp);
				World.addBlock(new SnowBlock(), xp, yp);
			}
		}
		
		for(int xp = 0; xp < Game.width; xp++) {
			for(int yp = snowY; yp < Game.height; yp++) {
				Block block = World.getBlockAt(xp, yp);
				
				if(block == null) {
					if(Math.random() > 0.25) {
						World.addBlock(new FireworkBlock(true), xp, yp);
					}
				}
				else if(block.blockType == BlockType.NUCLEAR) World.removeBlock(xp, yp);
				
				float r = (float)Math.random();
				
				if(r < 0.1) {
					World.removeBlock(xp, yp);
					World.addBlock(new FireBlock(1.0f), xp, yp);
				}
				else if(r < 0.125) {
					World.removeBlock(xp, yp);
					World.addBlock(new BombBlock(), xp, yp);
				}
				
			}
		}
		
		gravity(x, y);
	}
	
}
