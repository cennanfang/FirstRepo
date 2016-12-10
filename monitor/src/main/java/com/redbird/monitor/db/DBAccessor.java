package com.redbird.monitor.db;

import com.redbird.monitor.exception.DBException;

/**
 * 
 * @author redbird
 *
 */
public interface DBAccessor {

	public int getCount() throws DBException;
}
