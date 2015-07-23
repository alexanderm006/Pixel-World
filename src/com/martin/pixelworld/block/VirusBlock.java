package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class VirusBlock extends Block {

	public VirusBlock() {
		BlockType blockType = BlockType.VIRUS;
		int color = ColorMath.generateColor(240, 255, 0, 10, 250, 255);
		boolean flammable = false;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int x, int y) {
		if (Math.random() <= 0.8) {
			return;
		}
		
		int dx = (int)(Math.random() * 3) - 1;
		int dy = (int)(Math.random() * 3) - 1;
		
		Block block = World.getBlockAt(x+dx, y+dy);
		
		if(block == null) {
			World.moveBlock(x, y, dx, dy);
			x = x + dx;
			y = y + dy;
			
			for(int xx=-1; xx<=1; xx++) {
				for(int yy=-1; yy<=1; yy++) {
					if(xx == 0 & yy == 0) continue;
					Block blockAround = World.getBlockAt(x + xx, y + yy);
					if(blockAround != null) {
						if(blockAround.blockType == BlockType.VIRUS) {
							World.removeBlock(x + xx, y + yy);
						} else if(blockAround.blockType == BlockType.VOLCANO) {
							continue;
						} else {
							World.removeBlock(x + xx, y + yy);
							World.addBlock(new VirusBlock(), x + xx, y + yy);
						}
					}
				}
			}
		}
	}
	
	private void tryClump() {
		
	}
	
}
