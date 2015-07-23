package com.martin.pixelworld;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bitmap {
	public static Bitmap keyBitmap = new Bitmap("/keySpriteTest.png");
	public static Bitmap[] letters;
	public static Bitmap[] numbers;
	
	public static final int tileSize = 5;
	
	public int[] pixels;
	public int width;
	public int height;
	
	public Bitmap() {
		
	}
	
	public Bitmap(String path) {
		load(path);
	}
	
	public void render(int x, int y) {
		for(int dy=0; dy<height; dy++) {
			for(int dx=0; dx<width; dx++) {
				Screen.setPixel(x+dx, y+dy, pixels[dy*width+dx]);
			}
		}
	}
	
	private void load(String path) {
		try {
			BufferedImage image = ImageIO.read(Bitmap.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width*height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Bitmap subBitmap(int x, int y, int width, int height) {
		Bitmap bitmap = new Bitmap();
		bitmap.width = width;
		bitmap.height = height;
		bitmap.pixels = new int[width*height];
		
		for(int dy=0; dy<height; dy++) {
			for(int dx=0; dx<width; dx++) {
				bitmap.pixels[dy*width+dx] = pixels[(dy+y)*this.width+(dx+x)];
			}
		}
		
		return bitmap;
	}

}
