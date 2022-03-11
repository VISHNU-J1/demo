package com.ltts.snakegame.model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Manages the High-Scores for the Game.
 * 
 * This enumeration is a Singleton!
 * @author Vishnu J
 *
 */
public enum Highscore {
	
	INSTANCE;
	
	private long highscore = -1;
	
	public long getHighscore(){
		if (highscore == -1){
		
			this.highscore = readHighscore();
		}
		return highscore;
	}
	

	public long readHighscore(){
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("highscore.conf"))));
			String input = in.readLine();
			return Long.parseLong(input);
		} catch (Exception e) {
			return 0;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	

	public void writeHighscore(long new_score){
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("highscore.conf"))));
			out.write(""+new_score);
			out.flush();
			this.highscore = new_score;
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
