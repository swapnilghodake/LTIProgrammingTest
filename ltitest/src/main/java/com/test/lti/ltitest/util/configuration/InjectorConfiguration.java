package com.test.lti.ltitest.util.configuration;

import com.google.inject.AbstractModule;
import com.test.lti.ltitest.dao.DatabaseInitializationDAO;
import com.test.lti.ltitest.service.DbInitializeService;
import com.test.lti.ltitest.service.DbInitializeServiceImpl;
import com.test.lti.ltitest.service.OrderService;
import com.test.lti.ltitest.service.OrderServiceImpl;
import com.test.lti.ltitest.service.ProductService;
import com.test.lti.ltitest.service.ProductServiceImpl;

public class InjectorConfiguration extends AbstractModule {

	@Override
	protected void configure() {

		bind(ProductService.class).to(ProductServiceImpl.class);
		bind(OrderService.class).to(OrderServiceImpl.class);
		bind(DbInitializeService.class).to(DbInitializeServiceImpl.class);
		bind(DatabaseInitializationDAO.class);
	}

}
