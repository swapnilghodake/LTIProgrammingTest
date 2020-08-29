package com.test.lti.ltitest.service;

import java.util.List;
import java.util.Map;

import com.test.lti.ltitest.entity.Products;

public interface ProductService {

	List<Products> getAllProducts();

	List<Products> getProductByCategory();

	List<Products> getProductsLessThanGivenPrice(Double price);

	List<Products> getProductsGreaterThanGivenPrice(Double price);

	List<Products> getAllProductsByCompany();

	List<Products> getProductsLessThanPriceForCompany(Double price, String company);

	List<Products> getProductsGreaterThanPriceForCompany(Double price, String company);

	Map<Products, Integer> getAllProductsWithDiscountPrice();
}
