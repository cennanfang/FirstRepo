package com.redbird.monitor.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * 
 * @author redbird
 *
 */
public class MP3Player implements Runnable {
	private static MP3Player instance = null;

	private String mp3File;
	private Player player;
	private boolean isPlay;
	
	public static MP3Player getInstance() {
		if(instance == null) {
			instance = new MP3Player();
		}
		return instance;
	}
	
	private MP3Player() {}
	
	public MP3Player init(String mp3File) {
		this.mp3File = mp3File;
		return this;
	}
	
	@Override
	public void run() {
		BufferedInputStream buffer = null;
		try {
			isPlay = true;
			while(isPlay) {
				buffer = new BufferedInputStream(new FileInputStream(mp3File));
				player = new Player(buffer);
				player.play();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} finally {
			if(buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void stopPlay() {
		if(player != null) {
			player.close();
			isPlay = false;
		}
	}
	
	public boolean isStop() {
		if(player == null || !isPlay) {
			return true;
		} else {
			return false;
		}
	}
}
