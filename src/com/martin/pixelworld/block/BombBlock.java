package com.martin.pixelworld.block;

import com.martin.pixelworld.Game;
import com.martin.pixelworld.World;
import com.martin.pixelworld.math.ColorMath;

public class BombBlock extends BlockSolid {

	public static int esize = 5;
	public static float fireProb = 0.2f;
	public static float steamProb = 0.8f;
	
	public BombBlock() {
		boolean flammable = false;
		int color = ColorMath.rgbToHex(100, 100, 100);
		BlockType blockType = BlockType.BOMB;
		initProperties(blockType, color, flammable);
	}
	
	public void update(int xp, int yp) {
		gravity(xp, yp);
		Block blockBelow = World.getBlockAt(xp, yp+1);
		Block blockAbove = World.getBlockAt(xp, yp-1);
		if((blockBelow != null && blockBelow.blockType != BlockType.BOMB ) || 
				(blockAbove != null && blockAbove.blockType != BlockType.BOMB) || 
				yp == Game.height - 1) {
			explode(xp, yp);
		}
	}
	
	private void explode(int xp, int yp) {
		World.removeBlock(xp, yp);
		for(int l=1; l<esize; l++) {
			for(int x=-l; x<=l; x++) {
				Block block1 = World.getBlockAt(xp+x, yp+l);
				Block block2 = World.getBlockAt(xp+x, yp+(esize*2-l));
				if(block1 != null && block1.blockType != BlockType.BOMB) World.removeBlock(xp+x, yp+l);
				if(block2 != null && block2.blockType != BlockType.BOMB) World.removeBlock(xp+x, yp+(esize*2-l));
				if(Math.random() > fireProb) {
					World.addBlock(new FireBlock((float)Math.random()-0.5f), x+xp, yp+l);
				} else if(Math.random() > steamProb) {
					World.addBlock(new SteamBlock(), x+xp, yp+l);
				}
				if(Math.random() > fireProb) {
					World.addBlock(new FireBlock((float)Math.random()-0.5f), x+xp, yp+(esize*2-l));
				} else if(Math.random() > steamProb) {
					World.addBlock(new SteamBlock(), x+xp, yp+(esize*2-l));
				}
			}
		}
	}

}
