package com.ltts.snakegame.model;


/**
 * Represents a Part of the Snake
 * @author Vishnu J
 *
 */
public class SnakeBody{
	
	
	private Score pos;
	private Score old_pos;
	

	public SnakeBody(Score pos){
		this.pos = new Score(pos.getX(), pos.getY()+Game.SNAKE_PART_SIZE);
		this.old_pos = null;
	}

	public Score getPosition(){
		return this.pos;
	}
	

	public Score getOldPosition(){
		return this.old_pos;
	}
	

	public void setPosition(Score s){
		this.old_pos = this.pos;
		this.pos = s;
	}
	

	public boolean isHit(Score s){
		return this.pos.gotHit(s, 10);
	}
}