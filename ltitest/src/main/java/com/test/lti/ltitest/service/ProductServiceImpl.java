package com.test.lti.ltitest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.lti.ltitest.dao.ProductDAO;
import com.test.lti.ltitest.entity.Products;
import com.test.lti.ltitest.util.configuration.Factory;

public class ProductServiceImpl implements ProductService {

	ProductDAO productDAO;

	public List<Products> getAllProducts() {
		try {

			productDAO = new Factory().getProductDAO();
			return productDAO.getAllProducts();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Products> getProductByCategory() {
		try {

			productDAO = new Factory().getProductDAO();
			return productDAO.getAllProductsByCategory();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Products> getProductsLessThanGivenPrice(Double price) {
		try {

			productDAO = new Factory().getProductDAO();
			return productDAO.getAllProductsLessThanPrice(price);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Products> getProductsGreaterThanGivenPrice(Double price) {
		try {

			productDAO = new Factory().getProductDAO();
			return productDAO.getAllProductsGreaterThanPrice(price);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Products> getAllProductsByCompany() {
		try {

			productDAO = new Factory().getProductDAO();
			return productDAO.getAllProductsByCompany();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Products> getProductsLessThanPriceForCompany(Double price, String company) {
		try {

			productDAO = new Factory().getProductDAO();
			return productDAO.getAllProductsLessThanPriceForCompany(price, company);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Products> getProductsGreaterThanPriceForCompany(Double price, String company) {
		try {

			productDAO = new Factory().getProductDAO();
			return productDAO.getAllProductsGreaterThanPriceForCompany(price, company);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Map<Products, Integer> getAllProductsWithDiscountPrice() {
		try {
			productDAO = new Factory().getProductDAO();
			List<Products> allProductsWithDiscountPrice = productDAO.getAllProductsWithDiscountPrice();
			Map<Products, Integer> productMap = new HashMap<Products, Integer>();
			for (Products product : allProductsWithDiscountPrice) {
				int price = (int) product.getPrice();
				int discount = (int) product.getDiscount();
				
				int discountValue = 0;				
				discountValue = (discount != 0) ? (price - (price * discount) / 100) : price;
				
				productMap.put(product, discountValue);
			}
			
			return productMap;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
