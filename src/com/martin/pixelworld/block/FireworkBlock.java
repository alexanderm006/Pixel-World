package com.martin.pixelworld.block;

import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class FireworkBlock extends Block {
	
	public static float gravity = 0.005f, ymin = 1.0f;
	public static float yamp = 0.65f, xamp = 0.5f;
	
	public int r, g, b;
	private boolean initalSpawn;
	public float xDir, yDir;
	private float xp, yp;
	
	public FireworkBlock(boolean initalSpawn) {
		BlockType blockType = BlockType.FIRE_WORK;
		int r = (int)(Math.random() * 255);
		int g = (int)(Math.random() * 255);
		int b = (int)(Math.random() * 255);
		this.r = r;
		this.g = g;
		this.b = b;
		int color = ColorMath.rgbToHex(r, g, b);
		boolean flammable = false;
		this.initalSpawn = initalSpawn;
		initProperties(blockType, color, flammable);
	}
	
	public FireworkBlock(int color) {
		BlockType blockType = BlockType.FIRE_WORK;
		boolean flammable = false;
		this.initalSpawn = false;
		initProperties(blockType, color, flammable);
	}

	boolean firstUpdate = true;
	boolean wasFirstUpdate = false;
	int numToSpawn = 100;
	
	public void update(int px, int py) {
		int n = 4;
		if(firstUpdate) {
			if(initalSpawn) {
				for(int x=-n; x<=n; x++) {
					for(int y=-n; y<=n; y++) {
						if(x == 0 && y == 0) continue;
						if(World.getBlockAt(x, y) != null) continue;
						int rr = this.r + (int)(Math.random() * 5);
						int gg = this.g + (int)(Math.random() * 5);
						int bb = this.b + (int)(Math.random() * 5);
						int color = ColorMath.rgbToHex(rr, gg, bb);
						World.addBlock(new FireworkBlock(color), px+x, py+y);
					}
				}
			}
			firstUpdate = false;
			xDir = (float)Math.cos(Math.PI * Math.random()) * xamp;
			yDir = -(float)Math.sin(Math.PI * Math.random()) * yamp;
			xp = px;
			yp = py;
			wasFirstUpdate = true;
		} else {
			xp += xDir;
			yp += yDir;
			yDir += gravity;
			
			if(yDir > ymin) yDir = ymin;
			int nx = (int)xp;
			int ny = (int)yp;
			if(World.getBlockAt(nx, ny) != null && World.getBlockAt(nx, ny).blockType != BlockType.FIRE_WORK) {
				World.removeBlock(px, py);
			}
			else if (nx != px || ny != py) {
				World.switchBlocksAt(px, py, nx, ny);
			}
		}
	}

}
