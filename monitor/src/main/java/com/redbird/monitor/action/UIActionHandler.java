package com.redbird.monitor.action;

import com.redbird.monitor.config.Configuration;

/**
 * 
 * @author redbird
 *
 */
public interface UIActionHandler {

	/**
	 * 点击测试播放的时候
	 */
	public String onClickPlayBtn(Configuration config, boolean flag);
	
	/**
	 * 点击连接测试的时候
	 */
	public String onClickDBConnBtn();
}
