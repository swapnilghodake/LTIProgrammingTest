package com.test.lti.ltitest.service;

import java.util.List;

import com.test.lti.ltitest.entity.Orders;
import com.test.lti.ltitest.exception.OrderNotFoundException;
import com.test.lti.ltitest.exception.StockNotAvailableException;

public interface OrderService {

	void deleteOrder(int orderId) throws OrderNotFoundException;

	void insertOrder(Orders order) throws StockNotAvailableException;
	
	List<Orders> getAllOrders();
	
}
