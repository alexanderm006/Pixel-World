package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class SeedBlock extends BlockSolid {
	
	private static final int maxHeight = 8, minHeight = 5;
	private static final int turnsTillGrow = 60;
	
	private int height, currentHeight;
	private int turnsSinceGrown;
	private boolean irrigated;
	private boolean fullGrown;
	
	public SeedBlock() {
		BlockType blockType = BlockType.SEED;
		int color = ColorMath.generateColor(245, 255, 191, 229, 59, 100);
		boolean flammable = true;
		initProperties(blockType, color, flammable);
		
		height = (int)(Math.random() * (maxHeight - minHeight)) + minHeight;
		turnsSinceGrown = 0;
		currentHeight = 0;
	}
	
	public void update(int x, int y) {
		if(!irrigated) {
			irrigated = isIrigated(x, y);
		}
		
//		tryIrrigation(x, y);
		
		gravity(x, y);
		
		if(currentHeight >= height) fullGrown = true;
		if(fullGrown) return;
		
		if(turnsSinceGrown >= turnsTillGrow) {
			turnsSinceGrown = 0;
			grow(x, y);
			currentHeight++;
		} else {
			turnsSinceGrown += 1;
		}
	}
	
	public void grow(int x, int y) {
		PlantBlock leaf = new PlantBlock(x, y, 0, -currentHeight, 0x99CC00);
		PlantBlock bark = new PlantBlock(x, y, 0, -currentHeight, 0x5C3D1F);
		
		if(irrigated) {
			if(currentHeight == 0 || World.getBlockAt(x, y-currentHeight) == null) {
				if(currentHeight == 0) {
					if(World.getBlockAt(x-1, y-currentHeight) == null) 
						World.addBlock(bark, x-1, y-currentHeight);
					
					if(World.getBlockAt(x+1, y-currentHeight) == null) 
						World.addBlock(bark, x+1, y-currentHeight);
				}
				
				else if(currentHeight < height - 1) {
					World.addBlock(bark, x, y-currentHeight);
					
					if(World.getBlockAt(x-1, y-currentHeight) == null) 
						World.addBlock(leaf, x-1, y-currentHeight);
					
					if(World.getBlockAt(x+1, y-currentHeight) == null) 
						World.addBlock(leaf, x+1, y-currentHeight);
				}
				
				else {
					World.addBlock(leaf, x, y-currentHeight);
				}
			}
			else {
				fullGrown = true;
			}
		}
	}
	
//	private void tryIrrigation(int x, int y) {
//		if(irrigated) return;
//		
//		for(int dx=-1; dx <= 1; dx++) {
//			if(irrigated) break;
//			for(int dy=-1; dy <= 0; dy++) {
//				System.out.println("called");
//				if(dx == 0 && dy == 0) continue;
//				Block block = World.getBlockAt(x+dx, y+dy);
//				if(block != null && block.blockType == BlockType.WATER){
//					World.removeBlock(x+dx, y+dy);
//					irrigated = true;
//					break;
//				}
//			}
//		}
//		
//	}
	
	private boolean isIrigated(int x, int y) {
		
		for(int dx=-2; dx<=2; dx++) {
			for(int dy=-2; dy<=4; dy++) {
				Block block = World.getBlockAt(x+dx, y+dy);
				if(block != null) {
					if(block.blockType == BlockType.WATER) return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean isTreeTogether() {
		return false;
	}

}
