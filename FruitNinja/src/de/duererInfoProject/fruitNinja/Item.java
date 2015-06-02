package de.duererInfoProject.fruitNinja;

import java.awt.Color;
import java.awt.Graphics2D;

public /*abstract*/ class Item {
	
	private int x, y, speedX, diameter = 30;
	private double speedY;
	private Universe universe;
	
	public Item(int x, int speedX, double speedY, Universe universe) {
		this.universe = universe;
		this.x = x;
		this.y = universe.getHeight() + 40; //Items always start at the bottom
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	//Usually "abstract" only for testing, replace fillOval with drawImage
	public void paint(Graphics2D g2d) {
		move();
		Color c = g2d.getColor();
		g2d.setColor(new Color(255, 255, 255));
		g2d.fillOval(x, y, diameter, diameter);
		g2d.setColor(c);
	}
	
	public void move() {
		x += speedX;
		if (speedY >= 0 && speedY < 1) {
			speedY = 1;
		} else {
			speedY += universe.getGravity();
		}
		y += speedY;
		//System.out.println("X: " + x + ", Y: " + y + ", SpeedY: " + speedY);
	}
	
	public boolean offscreen() {
		if (x > universe.getWidth() || (x - diameter) < 0 || y > universe.getHeight() || (y - diameter) < 0) {
			return true;
		} else {
			return false;
		}
	}

}
