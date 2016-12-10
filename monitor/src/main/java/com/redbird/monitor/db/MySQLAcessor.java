package com.redbird.monitor.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.redbird.monitor.exception.DBException;

public class MySQLAcessor implements DBAccessor {
	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	
	private String url;
	private String user;
	private String passwd;
	private String tableName;
	private boolean isInit;
	
	private static MySQLAcessor instance;
	
	private MySQLAcessor() {}
	/**
	 * 在程序的生命周机中，只有一个线程使用此资源
	 * @return
	 */
	public static MySQLAcessor getInstance() {
		if(instance == null) {
			instance = new MySQLAcessor();
			instance.isInit = false;
		}
		return instance;
	}
	
	
	
	public MySQLAcessor init(String host, int port, String user, String passwd, String tableName) {
		this.url = "jdbc:mysql://" + host + ":" + port + "/monitor";
		this.user = user;
		this.passwd = passwd;
		this.tableName = tableName;
		this.isInit = true;
		return this;
	}
	
	public MySQLAcessor init(String user, String passwd, String tableName) {
		return init(HOST, PORT, user, passwd, tableName);
	}

	@Override
	public int getCount() throws DBException {
		if(!isInit) {
			throw new DBException("MySQLAcessor instance dose not init, "
					+ "please excute init(...) before excute this method.");
		}
		try {
			// 1.加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获得数据库的连接
			Connection conn = (Connection) DriverManager.getConnection(url, user, passwd);
			// 3.通过数据库的连接操作数据库，实现增删改查
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) cnt from " + tableName);// 选择import
																						// java.sql.ResultSet;
			while (rs.next()) {// 如果对象中有数据，就会循环打印出来
				return rs.getInt("cnt");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e.getMessage());
		}
		return -1;
	}

}
/*
 * drop table if exists `t_doc`; create table t_doc( `id` int(11) NOT NULL
 * unique AUTO_INCREMENT, `name` varchar(255) NOT NULL unique, PRIMARY KEY
 * (`id`) ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
 */
