package com.redbird.monitor.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Configuration {

	public static final String DB_TYPE_SQLSERVER_2000 = "SQL Server 2000";
	public static final String DB_TYPE_MYSQL = "MySQL";
	public static final int DB_TYPE_MYSQL_PORT = 3306;
	public static final int DB_TYPE_SQLSERVER_PORT = 1433;
	public static final int DEFAULT_DB_CHECK_SPACE= 5000;
	public static final String DEFAULT_CONFIG_FILE = "monitor.properties";
	public static final String CUREENT_PATH = System.getProperty("user.dir") + "/";
	
	public static final String CFG_KEY_DB_TYPE = "db_type";
	public static final String CFG_KEY_DB_HOST = "db_host";
	public static final String CFG_KEY_DB_PORT = "db_port";
	public static final String CFG_KEY_DB_USER = "db_user";
	public static final String CFG_KEY_DB_PASSWD = "db_passwd";
	public static final String CFG_KEY_DB_MP3FILE = "mp3_file_path";
	public static final String CFG_KEY_DB_CHECK_SPACE = "db_check_space";
	public static final String CFG_KEY_DB_TABLE_NAME = "db_table_name";
	
	private String dbType;
	private String dbHost;
	private String dbUser;
	private String dbPasswd;
	private String mp3File;
	private String dbTableName;
	private int dbCheckSpace;
	private int dbPort;

	public void init() {
		Properties prop = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(CUREENT_PATH + DEFAULT_CONFIG_FILE);
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dbType = prop.getProperty(CFG_KEY_DB_TYPE);
			dbHost = prop.getProperty(CFG_KEY_DB_HOST);
			dbUser = prop.getProperty(CFG_KEY_DB_USER);
			dbPasswd = prop.getProperty(CFG_KEY_DB_PASSWD);
			mp3File = prop.getProperty(CFG_KEY_DB_MP3FILE);
			dbTableName = prop.getProperty(CFG_KEY_DB_TABLE_NAME);
			String checkSpace = prop.getProperty(CFG_KEY_DB_CHECK_SPACE);
			String dbPortStr = prop.getProperty(CFG_KEY_DB_PORT);
			
			
			
			if(mp3File == null || "".equals(mp3File)) {
				mp3File = CUREENT_PATH + "qfdy.mp3";
			}
			if(dbType == null || "".equals(dbType)) {
				dbType = DB_TYPE_SQLSERVER_2000;
			}
			if(dbHost == null || "".equals(dbHost)) {
				dbHost = "localhost";
			}
			if(DB_TYPE_SQLSERVER_2000.equals(dbType)) {
				if(dbUser == null || "".equals(dbUser)) {
					dbUser = "sa";
				}
				if(dbPortStr == null || "".equals(dbPortStr)) {
					dbPort = DB_TYPE_SQLSERVER_PORT;
				} else {
					dbPort = Integer.valueOf(dbPortStr);
				}
			} else if(DB_TYPE_MYSQL.equals(dbType)) {
				if(dbUser == null || "".equals(dbUser)) {
					dbUser = "root";
				}
				if(dbPortStr == null || "".equals(dbPortStr)) {
					dbPort = DB_TYPE_MYSQL_PORT;
				} else {
					dbPort = Integer.valueOf(dbPortStr);
				}
			}
			if(checkSpace == null || "".equals(checkSpace)) {
				dbCheckSpace = DEFAULT_DB_CHECK_SPACE;
			} else {
				dbCheckSpace = Integer.valueOf(checkSpace);
			}
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void store(Map<String, String> properties) {
		Properties prop = new Properties();
		FileOutputStream oFile = null;
		try {
			oFile = new FileOutputStream(CUREENT_PATH + "/" + DEFAULT_CONFIG_FILE);
			Set<Map.Entry<String, String>> values = properties.entrySet();
			for (Map.Entry<String, String> entry : values) {
				prop.setProperty(entry.getKey(), entry.getValue());
			}
			prop.store(oFile, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(oFile != null) {
					oFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPasswd() {
		return dbPasswd;
	}

	public void setDbPasswd(String dbPasswd) {
		this.dbPasswd = dbPasswd;
	}

	public String getMp3File() {
		return mp3File;
	}

	public void setMp3File(String mp3File) {
		this.mp3File = mp3File;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public int getDbCheckSpace() {
		return dbCheckSpace;
	}

	public String getDbTableName() {
		return dbTableName;
	}

	public int getDbPort() {
		return dbPort;
	}
}
