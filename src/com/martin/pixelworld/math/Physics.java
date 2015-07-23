package com.martin.pixelworld.math;

import com.martin.pixelworld.Game;

public class Physics {
	
	public static boolean InScreenBounds(int x, int y) {
		if(x >= 0 && y >= 0) {
			if(x < Game.width && y < Game.height) {
				return true;
			}
		}
		return false;
	}

}
