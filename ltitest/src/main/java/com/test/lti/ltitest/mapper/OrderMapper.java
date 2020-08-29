package com.test.lti.ltitest.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.test.lti.ltitest.entity.Orders;

public class OrderMapper implements ResultSetMapper<Orders> {

	public Orders map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
		return new Orders(rs.getInt("orderid"), rs.getInt("productid"), rs.getInt("quantity"));
	}

}
