package com.martin.pixelworld;

import java.awt.Color;
import java.awt.Graphics;

import com.martin.pixelworld.block.BlockType;
import com.martin.pixelworld.math.ColorMath;

public class UI {
	
	private static int topLeftX = 0;
	private static int topLeftY = 10;
	private static int paddingY = 15;
	private static String[] keys = {
		"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "q", "w", "e", "r", "t", "y"
	};
	
	public static void drawUI(Graphics g) {
		g.drawString("Currently selected: " + God.currentBlockType.toString(), topLeftX, topLeftY);
		int y = topLeftY + paddingY;
		g.drawString("Press key to toggle type...", topLeftX, y);
		y += paddingY;
		
		BlockType[] blockTypes = BlockType.values();
		for(int i=0; i<keys.length; i++) {
			g.drawString(keys[i] + "\t-\t" + blockTypes[i].toString(), topLeftX, y);
			y += paddingY;
		}
	}
	
}

//old bad UI
//public class UI {
//	
//	public static int currentlySelected;
//	public static String currentlySelectedString = "dirt";
//	
//	private static int selectedColor = ColorMath.rgbToHex(255, 255, 0);
//	private static int boxSize = 9;
//	
//	private static int time;
//	
//	public static void init() {
//		loadLettersNumbers();
//	}
//	
//	public static void render() {
//		time++;
//		if(time > 0) {
//			renderBoxes();
//			renderBoxNumbers();
//			renderSelectedName();
//		}
//	}
//	
//	private static void renderSelectedName() {
//		renderString(boxSize*8, boxSize/2-2, currentlySelectedString);
//	}
//	
//	private static void renderBoxNumbers() {
//		for(int i=0; i<7; i++) {
//			renderChar(boxSize/2-1+i*boxSize+i, boxSize/2-2, String.valueOf(i+1).toCharArray()[0]);
//		}
//	}
//	
//	private static void renderBoxes() {
//		for(int i=0; i<7; i++) {
//			int color = 0x000000;
//			if(currentlySelected == i) color = selectedColor;
//			renderBox(i*boxSize+i, 0, boxSize, boxSize, color);
//		}
//	}
//	
//	private static void renderString(int x, int y, String string) {
//		char[] stringArray = string.toCharArray();
//		for(int i=0; i<stringArray.length; i++) {
//			char c = stringArray[i];
//			renderChar(x + Bitmap.tileSize * i, y, c);
//		}
//	}
//	
//	private static void renderChar(int x, int y, char c) {
//		int ascii = (int)c;
//		int ty = 0;
//		if(ascii >= 48 && ascii <= 57) {
//			//number
//			ascii -= 48;
//			Bitmap.numbers[ascii].render(x, y);
//		} else if (ascii >= 97 && ascii <= 122) {
//			//letter
//			ascii -= 97;
//			Bitmap.letters[ascii].render(x, y);
//		}
//	}
//	
//	private static void renderBox(int x, int y, int width ,int height, int outlineColor) {
//		for(int dx=0; dx<width; dx++) {
//			Screen.setPixel(x+dx, y, outlineColor);
//			Screen.setPixel(x+dx, y+height-1, outlineColor);
//		}
//		
//		for(int dy=0; dy<width; dy++) {
//			Screen.setPixel(x, y+dy, outlineColor);
//			Screen.setPixel(x+width-1, y+dy, outlineColor);
//		}
//	}
//	
//	private static void loadLettersNumbers() {
//		Bitmap.letters = new Bitmap[26];
//		Bitmap.numbers = new Bitmap[26];
//		
//		int size = 0;
//		boolean[] breaks = new boolean[Bitmap.keyBitmap.width];
//		int lastBreak = 0;
//		
//		for(int x=0; x<Bitmap.keyBitmap.width; x++) {
//			for(int y=0; y<5; y++) {
//				int color = Bitmap.keyBitmap.pixels[y*Bitmap.keyBitmap.width+x];
//				if(color != -16766155) break;
//				else if(y==4) {
//					breaks[x] = true;
//				}
//			}
//		}
//		
//		for(int i=0; i<breaks.length; i++) {
//			if(breaks[i]) {
//				if(lastBreak == 0) {
//					Bitmap.letters[size] = Bitmap.keyBitmap.subBitmap(0, 0, i, 5);
//					lastBreak = i;
//					size++;
//				}
//				else {
//					Bitmap.letters[size] = Bitmap.keyBitmap.subBitmap(lastBreak+1, 0, i-lastBreak, 5);
//					lastBreak = i;
//					size++;
//				}
//			}
//		}
//		
//		size = 0;
//		breaks = new boolean[Bitmap.keyBitmap.width];
//		lastBreak = 0;
//		
//		for(int x=0; x<Bitmap.keyBitmap.width; x++) {
//			for(int y=5; y<10; y++) {
//				int color = Bitmap.keyBitmap.pixels[y*Bitmap.keyBitmap.width+x];
//				if(color != -16766155) {
//					break;
//				}
//				else if(y==9) {
//					breaks[x] = true;
//				}
//			}
//		}
//		
//		for(int i=0; i<breaks.length; i++) {
//			if(breaks[i]) {
//				if(lastBreak == 0) {
//					Bitmap.numbers[size] = Bitmap.keyBitmap.subBitmap(0, 0, i, 5);
//					lastBreak = i;
//					size++;
//				}
//				else {
//					Bitmap.numbers[size] = Bitmap.keyBitmap.subBitmap(lastBreak+1, 5, i-lastBreak, 5);
//					lastBreak = i;
//					size++;
//				}
//			}
//		}
//	}
//	
//}

