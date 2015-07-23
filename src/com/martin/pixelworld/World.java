package com.martin.pixelworld;

import com.martin.pixelworld.block.*;
import com.martin.pixelworld.math.Physics;

public class World {
	
	private static Block[][] blocks;
	
	public World() {
		blocks = new Block[Game.width][Game.height];
	}
	
	public void update() {
		for(int y=0; y<Game.height; y++) {
			for(int x=0; x<Game.width; x++) {
				if(blocks[x][y] != null) blocks[x][y].moved = false;
			}
		}
		
		for(int y=0; y<Game.height; y++) {
			for(int x=0; x<Game.width; x++) {
				if(blocks[x][y] != null && !blocks[x][y].moved) {
					blocks[x][y].update(x, y);
				}
			}
		}
	}
	
	public void render() {
		for(int y=0; y<Game.height; y++) {
			for(int x=0; x<Game.width; x++) {
				if(blocks[x][y] != null) blocks[x][y].render(x, y);
			}
		}
	}
	
	public static void moveBlock(int x, int y, int dx, int dy) {
		if(!Physics.InScreenBounds(x, y)) return;
		if(!Physics.InScreenBounds(x+dx, y+dy)) blocks[x][y] = null;
		if(blocks[x][y] == null) return;
		if(dx == 0 & dy == 0) return;
		
		blocks[x+dx][y+dy] = blocks[x][y];
		blocks[x+dx][y+dy].moved = true;
		blocks[x][y] = null;
	}
	
	public static Block getBlockAt(int x, int y) {
		if(!Physics.InScreenBounds(x, y)) return null;
		return blocks[x][y];
	}
	
	public static Block addBlock(Block block, int x, int y) {
		if(!Physics.InScreenBounds(x, y)) return null;
		if(blocks[x][y] != null) return null;
		
		blocks[x][y] = block;
		return blocks[x][y];
	}
	
	public static void removeBlock(int x, int y) { 
		if(!Physics.InScreenBounds(x, y)) return;
		blocks[x][y] = null;
	}
	
	public static void switchBlocksAt(int x1, int y1, int x2, int y2) {
		Block b1 = World.getBlockAt(x1, y1);
		Block b2 = World.getBlockAt(x2, y2);
		
		World.removeBlock(x1, y1);
		World.removeBlock(x2, y2);
		
		World.addBlock(b1, x2, y2);
		World.addBlock(b2, x1, y1);
	}
	
}
