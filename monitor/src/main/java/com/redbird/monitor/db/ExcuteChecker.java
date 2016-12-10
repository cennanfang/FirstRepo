package com.redbird.monitor.db;

import com.redbird.monitor.config.Configuration;
import com.redbird.monitor.exception.DBException;

/**
 * 
 * @author redbird
 *
 */
public class ExcuteChecker implements DBChecker {
	private int current = -1;
	private DBAccessor dbAccessor;

	public ExcuteChecker(Configuration config) {
		switch (config.getDbType()) {
		case Configuration.DB_TYPE_MYSQL:
			this.dbAccessor = MySQLAcessor.getInstance()
			.init(config.getDbHost(), 
				  config.getDbPort(), 
				  config.getDbUser(), 
				  config.getDbPasswd(), 
				  config.getDbTableName());
			break;
		case Configuration.DB_TYPE_SQLSERVER_2000:
			System.out.println("check sql server 2000, no class is implemented.");
			break;

		default:
			throw new RuntimeException("no database type is spacified.");
			/*break;*/
		}
	}

	public boolean isCountChange() throws DBException {
		int cnt = dbAccessor.getCount();
		System.out.println("cnt = " + cnt + " current = " + current);
		if (current == -1) {
			current = cnt;
		}
		if (current == cnt) {
			return false;
		} else {
			current = cnt;
			return true;
		}
	}

}