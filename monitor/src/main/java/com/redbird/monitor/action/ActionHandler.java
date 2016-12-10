package com.redbird.monitor.action;

import com.redbird.monitor.config.Configuration;

public interface ActionHandler {

	// 处理
	public void handleAction(Configuration config);
	// 停止处理
	public void stopAction();
}
