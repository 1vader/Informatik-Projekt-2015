package de.duererInfoProject.fruitNinja;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Universe extends JPanelBG {
	private Game game;
	private double GRAVITY = 0.2;
	private LinkedList<Item> items;
	
	public Universe (Game g, String img) {
		super(img);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				game.getGui().pauseUniverse();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(Universe.class.getResource("/de/duererInfoProject/fruitNinja/img/pause-icon.png")));
		lblNewLabel.setBounds(10, 11, 48, 48);
		add(lblNewLabel);
		game = g;
		items = new LinkedList<Item>();
	}
	
	//Override paint Method to also paint the game
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//Paint parts
		for (Item i : items) {
			i.paint(g2d);
		}
	}
	
	public double getGravity() {
		return GRAVITY;
	}
	
	public void addItem(Item i) {
		items.add(i);
	}
}
