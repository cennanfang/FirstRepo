package com.redbird.monitor.db;

import com.redbird.monitor.exception.DBException;

/**
 * 检测数据库
 * @author redbird
 *
 */
public interface DBChecker {
	
	public boolean isCountChange() throws DBException;
}
