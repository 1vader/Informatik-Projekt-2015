package de.duererInfoProject.fruitNinja;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Item {
	
	public abstract void paint(Graphics2D g2d);
	
	public abstract void move();
	
	public boolean offscreen(int x, int y, int width, int height, Universe universe) {
		if (x > universe.getWidth() || (x - width) < 0 || y > universe.getHeight() || (y - height) < 0) {
			return true;
		} else {
			return false;
		}
	}
}
