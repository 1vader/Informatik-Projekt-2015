package de.duererInfoProject.fruitNinja;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fruit extends Item {

	private BufferedImage img;
	private int x, y, speedX, rot;
	private double speedY;
	private Universe universe;

	public Fruit(int x, int speedX, double speedY, Universe universe) {
		this.universe = universe;
		this.x = x;
		this.y = universe.getHeight() + 40; //Items always start at the bottom
		this.speedX = speedX;
		this.speedY = speedY;
		this.rot = 0;
		try {
			this.img = ImageIO.read(new File(Fruit.class.getResource("img/melon.png").getPath()));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void paint(Graphics2D g2d) {
		move();
		AffineTransform old = g2d.getTransform();
		g2d.rotate(Math.toRadians(rot), x, y); // Rotate the Graphics2D Element by rot around (x, y)
		g2d.drawImage(img, x - (img.getWidth() / 2), y - (img.getHeight() /2), null); //Draw the Image with the center at (x, y)
		g2d.setTransform(old); // Reset rotation
	}
	
	public void move() {
		x += speedX;
		if (speedY >= 0 && speedY < 1) {
			speedY = 1;
		} else {
			speedY += universe.getGravity();
		}
		y += speedY;
		rot += speedX;
	}
	
	public boolean offscreen() {
		return super.offscreen(x, y, img.getWidth(), img.getHeight(), universe);
	}
}
