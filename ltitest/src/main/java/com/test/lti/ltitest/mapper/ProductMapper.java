package com.test.lti.ltitest.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.test.lti.ltitest.entity.Products;

public class ProductMapper implements ResultSetMapper<Products> {

	public Products map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
		return new Products(rs.getInt("id"), rs.getString("category"), rs.getString("company"), rs.getString("product"),
				rs.getString("color"), rs.getString("description"), rs.getDouble("price"), rs.getFloat("discount"),
				rs.getInt("stock"));
	}

}
