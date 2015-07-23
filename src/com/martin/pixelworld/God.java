package com.martin.pixelworld;

import com.martin.pixelworld.block.*;
import com.martin.pixelworld.input.Mouse;

public class God {
	
	public static BlockType currentBlockType = BlockType.DIRT;
	private int mouse_x, mouse_y;
	public static boolean shift_pressed;
	
	//0=up 1=right 2=down 3=left
	private int shift_direction;
	
	public void update() {
		setMousePosition();
		
		if(Mouse.buttonDown) {
			if(Mouse.lastButtonPressed == 1) {
				addBlock(mouse_x, mouse_y);
			}
			
			else if(Mouse.lastButtonPressed == 3) {
				removeBlock(mouse_x, mouse_y);
			}
		}
	}
	
	//hack - right now it's only a horizontal shift, need to implement a shift direction function thing
	private void setMousePosition() {
		mouse_x = Mouse.x;
		if(!shift_pressed) mouse_y = Mouse.y;
	}
	
	private void addBlock(int x, int y) {
		Block block;
		
		if(currentBlockType == BlockType.DIRT) {
			block = new DirtBlock();
		} else if(currentBlockType == BlockType.WATER) {
			block = new WaterBlock();
		} else if(currentBlockType == BlockType.STEEL) {
			block = new SteelBlock();
		} else if(currentBlockType == BlockType.SEED) {
			block = new SeedBlock();
		} else if(currentBlockType == BlockType.STEAM) {
			block = new SteamBlock();
		} else if(currentBlockType == BlockType.FIRE) {
			block = new FireBlock(1.0f);
		} else if(currentBlockType == BlockType.OIL) {
			block = new OilBlock();
		} else if(currentBlockType == BlockType.VIRUS) {
			block = new VirusBlock();
		} else if(currentBlockType == BlockType.VOLCANO) {
			block = new VolcanoBlock();
		} else if(currentBlockType == BlockType.TELE_BLOCK) {
			block = new TeleBlock();
		} else if(currentBlockType == BlockType.WATER_FALL) {
			block = new WaterfallBlock();
		} else if(currentBlockType == BlockType.FIRE_WORK) {
			block = new FireworkBlock(true);
		} else if(currentBlockType == BlockType.ANTI_GRAVITY) {
			block = new AntigravityBlock();
		} else if(currentBlockType == BlockType.BOMB) {
			block = new BombBlock();
		} else if(currentBlockType == BlockType.SNOW) {
			block = new SnowBlock();
		} else if(currentBlockType == BlockType.NUCLEAR) {
			block = new NuclearBlock();
		}
		else {
			block = new DirtBlock();
		}
		
		World.addBlock(block, x, y);
	}
	
	private void removeBlock(int x, int y) {
		World.removeBlock(x, y);
	}
	
//	public static void mousePressed(int button, int x, int y) {
//		//Create
//		if(Mouse.lastPressedButton == 1) {
//			addBlock(x, y);
//		}
//		
//		//Destroy
//		else if(Mouse.lastPressedButton == 3) {
//			removeBlock(x, y);
//		}
//	}
//	
//	private static void addBlock(int x, int y) {
//		Block block = new DirtBlock();
//		World.addBlock(block, x, y);
//	}
//	
//	private static void removeBlock(int x, int y) {
//		World.removeBlock(x, y);
//	}

}
