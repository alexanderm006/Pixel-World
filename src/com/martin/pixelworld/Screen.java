package com.martin.pixelworld;

import com.martin.pixelworld.math.Physics;

public class Screen {

	public static int[] pixels = new int[Game.width * Game.height];

	public static void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
	}

	public static void setPixel(int x, int y, int color) {
		if(!Physics.InScreenBounds(x, y)) return;
		pixels[y * Game.width + x] = color;
	}
	
	public static int getPixel(int x, int y) {
		if(!Physics.InScreenBounds(x, y)) return 0x000000;
		return pixels[y * Game.width + x];
	}

}
