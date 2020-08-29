package com.test.lti.ltitest.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.test.lti.ltitest.entity.Products;
import com.test.lti.ltitest.mapper.ProductMapper;

@RegisterMapper(ProductMapper.class)
public interface ProductDAO {

	// Requirement1 - products across all the categories
	@SqlQuery("select * from products order by 1")
	List<Products> getAllProducts();

	// Requirement2.1 - all products by categories
	@SqlQuery("select * from products order by category")
	List<Products> getAllProductsByCategory();

	// Requirement2.2 - all products where price lesser than given price
	@SqlQuery("select * from products where price <= :price")
	List<Products> getAllProductsLessThanPrice(@Bind("price") double lessPrice);

	// Requirement2.3 - all products where price greater than given price
	@SqlQuery("select * from products where price >= :price")
	List<Products> getAllProductsGreaterThanPrice(@Bind("price") double grePrice);

	// Requirement3.1 - all products by company
	@SqlQuery("select * from products order by company")
	List<Products> getAllProductsByCompany();

	// Requirement3.2 - all products where price lesser than given price for that
	// company
	@SqlQuery("select * from products where price <= :price and company= :comapanyName")
	List<Products> getAllProductsLessThanPriceForCompany(@Bind("price") double lessPrice,
			@Bind("comapanyName") String comapanyName);

	// Requirement3.3 - all products where price greater than given price for that
	// company
	@SqlQuery("select * from products where price >= :price and company= :comapanyName")
	List<Products> getAllProductsGreaterThanPriceForCompany(@Bind("price") double greaterPrice,
			@Bind("comapanyName") String comapanyName);

	// Requirement4 - discounted price of all the products by category
	// @SqlQuery("select p.*, (p.price - (p.price * (p.discount/100))) as discountedprice from products p order by p.category")
	@SqlQuery("select p.* from products p order by p.category")
	List<Products> getAllProductsWithDiscountPrice();

	@SqlQuery("select * from products where price BETWEEN :greaterPrice AND :lesserPrice ")
	List<Products> getAllProductsInBetweenPrice(@Bind("greaterPrice") double greaterPrice,
			@Bind("lesserPrice") double lessPrice);

	@SqlQuery("select stock from products where id = :id")
	int getProductStockDetails(@Bind("id") int id);

	@SqlUpdate("update products set stock = :stock where id = :id")
	void updateStockForProduct(@Bind("stock") int stock, @Bind("id") int id);

}
