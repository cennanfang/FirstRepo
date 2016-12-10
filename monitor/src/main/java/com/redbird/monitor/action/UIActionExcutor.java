package com.redbird.monitor.action;

import com.redbird.monitor.config.Configuration;

public class UIActionExcutor implements UIActionHandler{

	@Override
	public String onClickPlayBtn(Configuration config, boolean flag) {
		ActionHandler  action = MP3Handler.getInstance();
		if(flag) {
			action.handleAction(config);
		} else {
			action.stopAction();
		}
		return "Miusic is playing now.";
	}

	@Override
	public String onClickDBConnBtn() {
		return "handle DB Connect test.";
	}

}
