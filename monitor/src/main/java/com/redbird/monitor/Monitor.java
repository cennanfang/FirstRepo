package com.redbird.monitor;

import com.redbird.monitor.action.ActionHandler;
import com.redbird.monitor.action.MP3Handler;
import com.redbird.monitor.config.Configuration;
import com.redbird.monitor.db.DBChecker;
import com.redbird.monitor.db.ExcuteChecker;
import com.redbird.monitor.exception.DBException;

/**
 * 
 * @author redbird
 *
 */
public class Monitor implements Runnable {
	private ActionHandler action;
	private Configuration config;
	private boolean isNeedRemind;
	private DBChecker dbChecker;
	
	public Monitor(Configuration config) {
		this.config = config;
		this.action = MP3Handler.getInstance();
		this.dbChecker = new ExcuteChecker(config);
	}
	

	@Override
	public void run() {
		int dbCheckSpace = config.getDbCheckSpace();
		isNeedRemind = true;
		
		while (true) {
			try {
				isNeedRemind = dbChecker.isCountChange();
				if(isNeedRemind) {
					action.handleAction(config);
					isNeedRemind = false;
				}
				Thread.sleep(dbCheckSpace);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (DBException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopAction() {
		action.stopAction();
		isNeedRemind = true;
	}
}
