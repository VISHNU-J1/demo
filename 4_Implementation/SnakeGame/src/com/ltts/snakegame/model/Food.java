package com.ltts.snakegame.model;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * This class represents the Pray, which increases the Size
 * of the Snake
 * @author Vishnu J
 *
 */
public class Food {
	
	
	private Score pos;
	private Random rnd;
	private int field_height;
	private int field_width;
	private int size;

	public Food(int height, int width, int size){
		this.field_height = height -10;
		this.field_width = width -10;
		this.size = size;
		this.rnd = new Random();
		this.eat();
	}

	private void eat(){
		this.pos = new Score(rnd.nextInt(field_width), rnd.nextInt(field_height));
	}
	

	public void setSize(int size){
		this.size = size;
	}
	

	public Graphics paintFood(Graphics g){
		// Draw the Prey:
		g.setColor(Color.red);
		g.fillOval(pos.getX(), pos.getY(), size, size);
		return g;
	}
	
	/**
	 * Tests if the Prey was hit/eaten by the Snake
	 * @param s The Point of the Snake-Head
	 * @return True if hit, otherwise false
	 */
	public boolean isHit(Score s){
		if (pos.gotHit(s, 10)){
			this.eat();
			return true;
		}
		return false;
	}
	
}


