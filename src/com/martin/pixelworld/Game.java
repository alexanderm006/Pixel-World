package com.martin.pixelworld;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.martin.pixelworld.block.Block;
import com.martin.pixelworld.block.BlockType;
import com.martin.pixelworld.input.Keyboard;
import com.martin.pixelworld.input.Mouse;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final String title = "PixelWorld";
	public static final int width = 120;
	public static final int height = 75;
	public static final int scale = 8;
	public static final int fps = 60;

	private boolean running = false;

	private BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Thread thread;
	private JFrame frame;
	private World world;
	private Mouse mouse;
	private Keyboard keyboard;
	private God god;
	private Bitmap bitmap;
	
	public Game() {
		// Canvas init
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		// Frame init
		frame = new JFrame();
		frame.setResizable(true);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(Game.class.getClass().getResource("/icon.png")).getImage());
		frame.setVisible(true);
		
		// Input init
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		
		world = new World();
		god = new God();
		bitmap = new Bitmap();
		
//		UI.init();
	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
//		showMessage();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.currentTimeMillis();
		while (running) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastTime > 1000 / fps) {
				Stats.updates = 0;
				update();
				render();
//				System.out.println(Stats.updates);
				lastTime = System.currentTimeMillis();
			}
			
		}
	}
	
	public void update() {
		world.update();
		god.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Screen.clear();
		world.render();
//		UI.render();
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = Screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		UI.drawUI(g);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		System.out.println("Game started");
		Game game = new Game();
		game.start();
	}
}
