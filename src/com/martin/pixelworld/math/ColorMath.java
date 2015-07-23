package com.martin.pixelworld.math;

public class ColorMath {
	
	public static int generateColor(int minR, int maxR, int minG, int maxG, int minB, int maxB) {
		int r = (int)(Math.random() * (maxR - minR) + minR);
		int g = (int)(Math.random() * (maxG - minG) + minG);
		int b = (int)(Math.random() * (maxB - minB) + minB);
		int color = r;
		color = (color << 8) + g;
		color = (color << 8) + b;
		return color;
	}
	
	public static int rgbToHex(int r, int g, int b) {
		int color = r;
		color = (color << 8) + g;
		color = (color << 8) + b;
		return color;
	}

}
