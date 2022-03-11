package com.ltts.snakegame;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.ltts.snakegame.model.*;

public class Main{
	

	private JFrame f;
	
	private Game game;
	
	
	private Main(){
		this.createGui();
	}	
	
	
	 
	private void createGui(){
		f = new JFrame("SnakeGame");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent w){
				System.exit(0);
			}
		});
		f.setResizable(false);
		f.setSize(600, 400);
		// The Game:
		game = new Game(590, 390);
		f.add(game);
		// Add the KeyListener:
		f.addKeyListener(game);
		f.setVisible(true);
	}
	
	/**
	 * Entrance point of the Game
	 * @param args Parameters for the Program.
	 */
	public static void main(String[] args){
		new Main();
	}

}