package com.ltts.snakegame.model;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


/**
 * Class, which represents the Snake
 * @author Vishnu J
 *
 */
public class SnakeHead{

	private ArrayList<SnakeBody> parts;
	private Score pos;

	public SnakeHead(){
		this.pos = new Score(200, 200);
		this.parts = new ArrayList<SnakeBody>();
		parts.add(new SnakeBody(pos));
		parts.add(new SnakeBody(parts.get(0).getPosition()));
	}
	

	public Graphics paintSnake(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(pos.getX(), pos.getY(), Game.SNAKE_PART_SIZE, Game.SNAKE_PART_SIZE);
		g.setColor(Color.green);
		for (SnakeBody part: parts){
			g.fillRect(part.getPosition().getX(), part.getPosition().getY(), Game.SNAKE_PART_SIZE, Game.SNAKE_PART_SIZE);
		}
		return g;
	}

	public boolean leftField(int width, int height){
		if (pos.getX() > width || pos.getX() < 0 ||
			pos.getY() > height || pos.getY() < 0){
			return true;
		}
		return false;
	}
 boolean hitSelf(){
		for (SnakeBody part: parts){
			if (part.isHit(pos)) return true;
		}
		return false;
	}
	

	public void addParts(int n){
		for (int i = 0; i < n; i++){
			
			parts.add(new SnakeBody(parts.get( parts.size()-1 ).getPosition()));
		}
	}
	
	
	public Score getPoint(){
		return this.pos;
	}
	
	
	public void moveLeft(){
		pos.nextMoveDirection(Score.LEFT);
	}
	

	public void moveRight(){
		pos.nextMoveDirection(Score.RIGHT);
	}

	public void moveUp(){
		pos.nextMoveDirection(Score.UP);
	}
	

	public void moveDown(){
		pos.nextMoveDirection(Score.DOWN);
	}
	

	public void moveAll(){
		Score tmp = new Score(pos.getX(), pos.getY());
		pos.move(Game.SPEED);
		for (SnakeBody part: parts){
			part.setPosition(tmp);
			tmp = part.getOldPosition();
		}
	}
	
	
}
