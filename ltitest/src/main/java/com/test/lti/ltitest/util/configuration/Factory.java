package com.test.lti.ltitest.util.configuration;

import org.skife.jdbi.v2.DBI;

import com.test.lti.ltitest.dao.OrderDAO;
import com.test.lti.ltitest.dao.ProductDAO;
import com.test.lti.ltitest.databaseconnector.DbConnector;

public class Factory {

	public ProductDAO getProductDAO() throws Exception {

		DBI dbi = new DbConnector().getDBI();

		ProductDAO productDAO = dbi.open(ProductDAO.class);

		return productDAO;
	}

	public OrderDAO getOrderDAO() throws Exception {

		DBI dbi = new DbConnector().getDBI();

		OrderDAO orderDAO = dbi.open(OrderDAO.class);

		return orderDAO;
	}

	/*
	 * public static Object getInstance(String className) { DBI dbi = null; try {
	 * dbi = new DbConnector().getDBI(); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * switch (className) { case "ProductDAO": return dbi.open(ProductDAO.class);
	 * 
	 * case "OrderDAO": return dbi.open(ProductDAO.class);
	 * 
	 * }
	 * 
	 * return null; }
	 */
}
