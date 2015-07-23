package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class SteamBlock extends Block {

	private int turnsTillCondensate = 300;
	private int turnsTillMove = 5;
	private int turnsSinceMove;
	
	private int maxHeight = 15;
	private int minHeight = 25;
	
	public SteamBlock() {
		BlockType blockType = BlockType.STEAM;
		int color = ColorMath.generateColor(200, 220, 200, 220, 200, 220);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		turnsTillCondensate--;
		turnsSinceMove++;
		
		if(turnsTillCondensate <= 0) {
			World.removeBlock(x, y);
			World.addBlock(new WaterBlock(), x, y);
		} else {
			if(turnsSinceMove >= turnsTillMove) {
				int dy = (int)(Math.random() * 2) - 1;
				
				if(y > minHeight) {
					dy = -1;
				}
				
				else if(y < maxHeight) {
					dy = 1;
				}
				
				move(dy, x, y);
				turnsSinceMove = 0;
			}
		}
	}
	
	private void move(int dy , int x, int y) {
		int dx = 0;
		double r = Math.random();
		
		if(r <= 0.33) dx = -1;
		else if(r <= 0.66) dx = 0;
		else dx = 1;
		
		Block sideBlock = World.getBlockAt(x+dx, y);
		if(sideBlock != null && sideBlock.blockType != BlockType.WATER) return;
//		System.out.println("called");
		
		if(World.getBlockAt(x+dx, y+dy) == null) {
			World.moveBlock(x, y, dx, dy);
		}
	}
	
}
