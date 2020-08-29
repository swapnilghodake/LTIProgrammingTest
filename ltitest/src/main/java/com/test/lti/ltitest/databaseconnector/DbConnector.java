package com.test.lti.ltitest.databaseconnector;

import org.skife.jdbi.v2.DBI;

public class DbConnector {
	
	public DBI getDBI() throws Exception {			
		//return new DBI(ConnectionPool.setUpPool());		// For Mysql
		return new DBI(H2Connection.getDbConnection());		// For H2
	}
	
}
