package com.ltts.snakegame.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements KeyListener, Runnable{
	
	
	private static final long serialVersionUID = 1L;
	private Image dbimage;
	private Graphics dbg;
	private Thread th;
	private long score;
	private boolean roundEnd;
	public static int SPEED = 10;
	public static final int SNAKE_PART_SIZE = 10;
	private SnakeHead snake;
	private Food food;
	private Highscore highscore;
	

	public Game(int width, int height){
		super();
		super.setSize(width, height);
		// Initialize:
		roundEnd = false;
		snake = new SnakeHead();
		food = new Food(this.getSize().height, this.getSize().width, 10);
		highscore = Highscore.INSTANCE;
		// Initialize the Thread:
		th = new Thread(this);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		this.setBackground(Color.BLACK);
		this.setForeground(Color.BLUE);
		
		g.drawString("Score: "+this.score, 15, 15);
		g.drawString("High-Score: "+this.highscore.getHighscore(), 100, 15);
		
		if (!th.isAlive()&&!roundEnd()){
			g.drawString("Press any Key to Start the Game", 200, 170);
		} 
		if (roundEnd){	
			g.drawString("Game Over!", 200, 190);
			g.drawString("Score: "+score, 200, 220);
			if (checkHighscore(score)) g.drawString("New Highscore!", 200, 240);
		}
		
		this.collision();
		snake.moveAll();
		snake.paintSnake(g);
		food.paintFood(g);
		if (th == null) {
			th = new Thread(this);
		    th.start();
		}
		
		
	}

	private void collision(){
		if (food.isHit(snake.getPoint()) ){
			snake.addParts(4);
			this.score += 10;
		}
		if (snake.hitSelf()){
			this.roundEnd = true;
			System.out.println("Hit!");
		}
		if (snake.leftField(this.getSize().width, this.getSize().height)){
			this.roundEnd = true;
			System.out.println("Out!");
		}
	}
	

	public boolean roundEnd(){
		return roundEnd;
	}
	
	
	public void playAgain(){
		if (roundEnd){
			this.score = 0;
			this.snake = new SnakeHead();
			this.food = new Food(this.getSize().height, this.getSize().width, 10);
			this.roundEnd = false;
		}
	}

	public long getScore(){
		return score;
	}
	

	private boolean checkHighscore(long score){
		if (score > this.highscore.getHighscore()){
			this.highscore.writeHighscore(score);
			return true;
		} else return false;
	}
	

	@Override
	public void run() {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		while (!roundEnd){
			repaint();
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		repaint();
		
		Timer t = new Timer(2000, new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				playAgain();
				repaint();
			}
		});
		t.setRepeats(false);
		t.start();
		
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
	}
	
	
	
	
	public void update(Graphics g){
		if (dbimage == null){
			dbimage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbimage.getGraphics();
		}
		dbg.setColor(getBackground());
		paint(dbg);
		g.drawImage(dbimage, 0, 0, this);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!th.isAlive()){
			th = new Thread(this);
			th.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP){
			snake.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			snake.moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			snake.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT){
			snake.moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (roundEnd) {
					roundEnd = false;
					playAgain();
					repaint();
			}
		}
	}

	
	@Override public void keyPressed(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}

}