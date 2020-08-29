package com.test.lti.ltitest.exception;

public class StockNotAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockNotAvailableException() {
		super("Stock not available For given product");
	}

	
}
