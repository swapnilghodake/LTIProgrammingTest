package com.test.lti.ltitest.databaseconnector;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

public class H2Connection {

	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String JDBC_DB_URL = "jdbc:h2:~/test";
	static final String JDBC_USER = "sa";
	static final String JDBC_PASS = "";

	public static DataSource getDbConnection() throws ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		DataSource ds = JdbcConnectionPool.create(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
		return ds;
	}

}
