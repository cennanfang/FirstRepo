package com.redbird.monitor.action;

import com.redbird.monitor.config.Configuration;
/**
 * 
 * @author redbird
 *
 */
public class MP3Handler implements ActionHandler{
	private static MP3Handler instance;
	private MP3Handler() {}
	
	public static MP3Handler getInstance() {
		if(instance == null) {
			instance = new MP3Handler();
		}
		return instance;
	}
	
	@Override
	public void handleAction(Configuration config) {
		MP3Player player = MP3Player.getInstance().init(config.getMp3File());
		System.out.println("handleAction");
		if(player.isStop()) {
			System.out.println("start paly");
			new Thread(player).start();
		}
	}

	@Override
	public void stopAction() {
		MP3Player.getInstance().stopPlay();
	}
}
