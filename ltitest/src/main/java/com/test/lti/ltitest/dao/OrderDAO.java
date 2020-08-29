package com.test.lti.ltitest.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.test.lti.ltitest.entity.Orders;
import com.test.lti.ltitest.mapper.OrderMapper;

@RegisterMapper(OrderMapper.class)
public interface OrderDAO {

	@SqlUpdate("delete from orders where orderid= :orderId")
	void deleteOrder(@Bind("orderId") double orderid);

	@SqlQuery("select * from orders where orderid= :orderId")
	Orders getOrderId(@Bind("orderId") double orderid);

	@SqlUpdate("insert into orders (productid, quantity) values (:productId, :quantity)")
	void insertOrder(@Bind("productId") double productid,
			@Bind("quantity") int quantity);
	
	@SqlQuery("select * from orders order by 1")
	List<Orders> getAllOrders();
	
	
	
	/*@SqlUpdate("insert into orders (orderid, productid, quantity) values (:orderId, :productId, :quantity)")
	void insertOrder(@Bind("orderId") double orderid, @Bind("productId") double productid,
			@Bind("quantity") int quantity);*/

}
