package de.duererInfoProject.fruitNinja;

import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;

import javax.swing.UIManager;

public class Game {
	
	private GUI gui;
	private Highscore highscore;
	private Kinect kinect;
	private final Universe universe;
	private Preferences preferences;
	private boolean activeGame;
	private int FRAME_TIME = 20; //Time between each repaint, 20 -> 50 fps

	public static void main(String[] args) {
		new Game();

	}
	
	public Game() {
		//Initialize other Classes
		preferences = Preferences.userNodeForPackage(de.duererInfoProject.fruitNinja.Game.class);
		universe = new Universe(this, Game.class.getResource("img/background.jpg").getPath());
		gui = new GUI(this);
		highscore = new Highscore();
		kinect = new Kinect();
		activeGame = false;
	}
	
	public void play() {
		universe.addItem(new Fruit(150, 4, -10, universe));
		activeGame = true;
		universe.repaint();
		repaintUniverseTimed(FRAME_TIME);
		//Other Option would be to give Thread.currentThread() in main to game and save it, to later let it call Thread.sleep()
	}
	
	//Repaints the universe after @time and calls itself if activeGame is true
	public void repaintUniverseTimed(int time) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				universe.repaint();
				if (activeGame) repaintUniverseTimed(FRAME_TIME);
			}
		}, time);
	}
	
	//Used by GUI Elements to set their Look and Feel
	public void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public GUI getGui() {
		return gui;
	}

	public Highscore getHighscore() {
		return highscore;
	}

	public Kinect getKinect() {
		return kinect;
	}

	public Universe getUniverse() {
		return universe;
	}
	
	public Preferences getPreferences() {
		return preferences;
	}

	public boolean isActiveGame() {
		return activeGame;
	}

	public void setActiveGame(boolean activeGame) {
		this.activeGame = activeGame;
	}
	
	public void log(String str) {
		System.out.println(str);
	}
}
