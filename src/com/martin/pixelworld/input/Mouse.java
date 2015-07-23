package com.martin.pixelworld.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.martin.pixelworld.Game;
import com.martin.pixelworld.God;

public class Mouse implements MouseListener, MouseMotionListener {
	
	public static int lastButtonPressed;
	public static boolean buttonDown;
	public static int x,y;
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX() / Game.scale;
		y = e.getY() / Game.scale;
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX() / Game.scale;
		y = e.getY() / Game.scale;
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent e) {
		lastButtonPressed = e.getButton();
		buttonDown = true;
	}

	public void mouseReleased(MouseEvent e) {
		buttonDown = false;
	}

	
	
}
