package com.test.lti.ltitest.service;

import com.google.inject.Inject;
import com.test.lti.ltitest.dao.DatabaseInitializationDAO;

public class DbInitializeServiceImpl implements DbInitializeService {

	DatabaseInitializationDAO databaseInitializationDAO;

	@Inject
	public void setdatabaseInitializationDAO(DatabaseInitializationDAO d) {
		this.databaseInitializationDAO = d;
	}

	@Override
	public void initializeDB() throws Exception {
		databaseInitializationDAO.InitializeDB();
	}

}
