package com.test.lti.ltitest.service;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Transaction;

import com.test.lti.ltitest.dao.OrderDAO;
import com.test.lti.ltitest.dao.ProductDAO;
import com.test.lti.ltitest.entity.Orders;
import com.test.lti.ltitest.exception.OrderNotFoundException;
import com.test.lti.ltitest.exception.StockNotAvailableException;
import com.test.lti.ltitest.util.configuration.Factory;

public class OrderServiceImpl implements OrderService {

	OrderDAO orderDAO;
	ProductDAO productDAO;

	@Override
	public List<Orders> getAllOrders() {
		try {
			orderDAO = new Factory().getOrderDAO();
			return orderDAO.getAllOrders();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transaction
	public void deleteOrder(int orderId) throws OrderNotFoundException {
		try {
			orderDAO = new Factory().getOrderDAO();
			productDAO = new Factory().getProductDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Orders order = orderDAO.getOrderId(orderId);
		if (order == null) {
			throw new OrderNotFoundException();
		}
		int stockOfProduct = productDAO.getProductStockDetails(order.getProductId());
		orderDAO.deleteOrder(orderId);
		productDAO.updateStockForProduct((order.getQuantity() + stockOfProduct), order.getProductId());

	}

	@Override
	@Transaction
	public void insertOrder(Orders order) throws StockNotAvailableException {

		try {
			productDAO = new Factory().getProductDAO();
			orderDAO = new Factory().getOrderDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int stockOfProduct = productDAO.getProductStockDetails(order.getProductId());
		if (stockOfProduct < order.getQuantity()) {
			throw new StockNotAvailableException();
		}

		orderDAO.insertOrder(order.getProductId(), order.getQuantity());

		// updating stock after placing an order
		productDAO.updateStockForProduct((stockOfProduct - order.getQuantity()), order.getProductId());

	}

}
