package com.ltts.snakegame.model;

/**
 * Simply represents a Point with X and Y-Position
 * @author Vishnu J
 *
 */
public class Score {

	private int x;
	private int y;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	private int nextDir;
	

	public Score(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	public void nextMoveDirection(int direction){
		switch (direction) {
		case LEFT:
			if (nextDir != RIGHT){
				nextDir = direction;
			}
			break;
		case RIGHT:
			if (nextDir != LEFT){
				nextDir = direction;
			}
			break;
		case UP:
			if (nextDir != DOWN){
				nextDir = direction;
			}
			break;
		case DOWN:
			if (nextDir != UP){
				nextDir = direction;
			}
		}
		
	}
	

	public void move(int speed){
		switch (nextDir){
		case LEFT:
			this.x -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		case UP:
			this.y -= speed;
			break;
		case DOWN:
			this.y += speed;
			break;
		default:
			System.err.println("Can't move...");
		}
	}
	

	public int getX(){
		return x;
	}
	
	
	public int getY(){
		return y;
	}
	

	public String toString(){
		return x+"|"+y;
	}
	

	public boolean gotHit(Score s, int tollerance){
		double x = s.getX() - this.x;
		double y = s.getY() - this.y;
		double distance = Math.sqrt ((x*x) + (y*y));
		if (distance < tollerance) {
			return true;
		} else {
			return false;
		}
	}
}