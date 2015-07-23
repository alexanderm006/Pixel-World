package com.martin.pixelworld.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.martin.pixelworld.God;
import com.martin.pixelworld.UI;
import com.martin.pixelworld.World;
import com.martin.pixelworld.block.BlockType;

public class Keyboard implements KeyListener {
	
	public void keyPressed(KeyEvent e) {
		int keyTyped = e.getKeyCode();
		if(keyTyped == KeyEvent.VK_SHIFT){
			God.shift_pressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int keyTyped = e.getKeyCode();
		
		if(keyTyped == KeyEvent.VK_SHIFT){
			God.shift_pressed = false;
		}
		
		if(keyTyped == KeyEvent.VK_1) {
			God.currentBlockType = BlockType.DIRT;
		} else if (keyTyped == KeyEvent.VK_2) {
			God.currentBlockType = BlockType.WATER;
		} else if (keyTyped == KeyEvent.VK_3) {
			God.currentBlockType = BlockType.STEEL;
		} else if (keyTyped == KeyEvent.VK_4) {
			God.currentBlockType = BlockType.SEED;
		} else if (keyTyped == KeyEvent.VK_5){
			God.currentBlockType = BlockType.STEAM;
		} else if (keyTyped == KeyEvent.VK_6) {
			God.currentBlockType = BlockType.FIRE;
		} else if (keyTyped == KeyEvent.VK_7) {
			God.currentBlockType = BlockType.OIL;
		} else if(keyTyped == KeyEvent.VK_8) {
			God.currentBlockType = BlockType.VIRUS;
		} else if(keyTyped == KeyEvent.VK_9){
			God.currentBlockType = BlockType.VOLCANO;
		} else if(keyTyped == KeyEvent.VK_0) {
			God.currentBlockType = BlockType.TELE_BLOCK;
		} else if(keyTyped == KeyEvent.VK_Q) {
			God.currentBlockType = BlockType.WATER_FALL;
		} else if(keyTyped == KeyEvent.VK_W) {
			God.currentBlockType = BlockType.FIRE_WORK;
		} else if(keyTyped == KeyEvent.VK_E) {
			God.currentBlockType = BlockType.ANTI_GRAVITY;
		} else if(keyTyped == KeyEvent.VK_R) {
			God.currentBlockType = BlockType.BOMB;
		} else if(keyTyped == KeyEvent.VK_T) {
			God.currentBlockType = BlockType.SNOW;
		} else if(keyTyped == KeyEvent.VK_Y) {
			God.currentBlockType = BlockType.NUCLEAR;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
