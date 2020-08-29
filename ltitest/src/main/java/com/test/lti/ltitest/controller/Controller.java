package com.test.lti.ltitest.controller;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.test.lti.ltitest.entity.Orders;
import com.test.lti.ltitest.entity.Products;
import com.test.lti.ltitest.exception.OrderNotFoundException;
import com.test.lti.ltitest.exception.StockNotAvailableException;
import com.test.lti.ltitest.service.DbInitializeService;
import com.test.lti.ltitest.service.OrderService;
import com.test.lti.ltitest.service.ProductService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Controller extends AbstractVerticle {

	ProductService productService;
	OrderService orderService;
	DbInitializeService dbInitializeService;

	@Inject
	public void setProductService(ProductService ps) {
		this.productService = ps;
	}

	@Inject
	public void setOrderService(OrderService os) {
		this.orderService = os;
	}

	@Inject
	public void setDbInitializeService(DbInitializeService db) {
		this.dbInitializeService = db;
	}

	@Override
	public void start() {

		System.out.println("Starting server");
		Vertx vertx = Vertx.factory.vertx();
		HttpServer httpServer = vertx.createHttpServer();

		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.get("/AllProductsList").handler(this::ListOfAllProductsHandler);
		router.get("/ProductListByCategory").handler(this::ListOfProductsByCategoryHandler);
		router.get("/ProductsLessThanGivenPrice/:price").handler(this::ListOfProductsLessThanGivenPrice);
		router.get("/ProductsGreaterThanGivenPrice/:price").handler(this::ListOfProductsGreaterThanGivenPrice);
		router.get("/AllProductsListWithDiscountPrice").handler(this::ListOfAllProductsWithDiscPriceHandler);

		router.get("/AllProductsListByCompany").handler(this::ListProductsByCompanyHandler);
		router.get("/ProductsLessThanPriceForCompany/:price/:company").handler(this::ProductsLessThanPriceForCompany);
		router.get("/ProductsGreaterThanPriceForCompany/:price/:company")
				.handler(this::ProductsGreaterThanPriceForCompany);

		router.get("/getAllOrders").handler(this::ListOfAllOrdersHandler);
		router.delete("/DeleteOrder/:id").handler(this::DeleteOrderHandler);
		router.post("/PlaceOrder").handler(this::PlaceOrderHandler);

		httpServer.requestHandler(router::accept).listen(9999);
		System.out.println("Server started...");
		DbInitialize();

	}

	private void ListOfAllProductsHandler(RoutingContext routingContext) {
		JsonArray arr = new JsonArray();
		List<Products> products = productService.getAllProducts();
		arr.add(products);
		routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
	}

	private void ListOfProductsByCategoryHandler(RoutingContext routingContext) {
		JsonArray arr = new JsonArray();
		List<Products> products = productService.getProductByCategory();
		arr.add(products);
		routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
	}

	private void ListOfProductsLessThanGivenPrice(RoutingContext routingContext) {
		String price = routingContext.request().getParam("price");
		HttpServerResponse response = routingContext.response();
		if (price == null) {
			sendError(400, response);
		} else {
			JsonArray arr = new JsonArray();
			List<Products> products = productService.getProductsLessThanGivenPrice(Double.parseDouble(price));
			arr.add(products);
			response.putHeader("content-type", "application/json").end(arr.encodePrettily());
		}
	}

	private void ListOfProductsGreaterThanGivenPrice(RoutingContext routingContext) {
		String price = routingContext.request().getParam("price");
		HttpServerResponse response = routingContext.response();
		if (price == null) {
			sendError(400, response);
		} else {
			JsonArray arr = new JsonArray();
			List<Products> products = productService.getProductsGreaterThanGivenPrice(Double.parseDouble(price));
			arr.add(products);
			response.putHeader("content-type", "application/json").end(arr.encodePrettily());
		}
	}

	private void ListProductsByCompanyHandler(RoutingContext routingContext) {
		JsonArray arr = new JsonArray();
		List<Products> products = productService.getAllProductsByCompany();
		arr.add(products);
		routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
	}

	private void ProductsLessThanPriceForCompany(RoutingContext routingContext) {
		String price = routingContext.request().getParam("price");
		String company = routingContext.request().getParam("company");
		HttpServerResponse response = routingContext.response();
		if (price == null || company == null) {
			sendError(400, response);
		} else {
			JsonArray arr = new JsonArray();
			List<Products> products = productService.getProductsLessThanPriceForCompany(Double.parseDouble(price),
					company);
			arr.add(products);
			response.putHeader("content-type", "application/json").end(arr.encodePrettily());
		}
	}

	private void ProductsGreaterThanPriceForCompany(RoutingContext routingContext) {
		String price = routingContext.request().getParam("price");
		String company = routingContext.request().getParam("company");
		HttpServerResponse response = routingContext.response();
		if (price == null || company == null) {
			sendError(400, response);
		} else {
			JsonArray arr = new JsonArray();
			List<Products> products = productService.getProductsGreaterThanPriceForCompany(Double.parseDouble(price),
					company);
			arr.add(products);
			response.putHeader("content-type", "application/json").end(arr.encodePrettily());
		}
	}

	private void ListOfAllOrdersHandler(RoutingContext routingContext) {
		JsonArray arr = new JsonArray();
		List<Orders> products = orderService.getAllOrders();
		arr.add(products);
		routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
	}

	private void DeleteOrderHandler(RoutingContext routingContext) {
		int statusCode = 0;
		String message = "";
		String orderId = routingContext.request().getParam("id");
		System.out.println("orderid=" + orderId);
		HttpServerResponse response = routingContext.response();
		if (orderId == null) {
			statusCode = 400;
			sendError(statusCode, response);
		} else {			
			try {
				orderService.deleteOrder(Integer.parseInt(orderId));
				statusCode = 200;
				message = "Order deleted successfully";
			} catch (OrderNotFoundException e) {
				statusCode = 204;
				message = "Order not found";
				//e.printStackTrace();
			}
			response.setStatusCode(statusCode).end(message);
		}
	}

	private void PlaceOrderHandler(RoutingContext routingContext) {
		int statusCode = 0;
		String message = "";
		Orders order = new Gson().fromJson(routingContext.getBodyAsJsonArray().getJsonObject(0) + "", Orders.class);
		HttpServerResponse response = routingContext.response();

		if (order == null) {
			statusCode = 400;
			sendError(statusCode, response);
		} else {
			try {
				orderService.insertOrder(order);
				statusCode = 201;
				message = "Order placed successfully";
			} catch (StockNotAvailableException e) {
				statusCode = 412;
				message = "Stock not available for this product";
				//e.printStackTrace();
			}
			response.setStatusCode(statusCode).end(message);
		}
	}

	private void ListOfAllProductsWithDiscPriceHandler(RoutingContext routingContext) {
		Map<Products, Integer> allProductsWithDiscountPrice = productService.getAllProductsWithDiscountPrice();
		JsonArray arr = new JsonArray();
		arr.add(allProductsWithDiscountPrice);
		routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
	}

	private void sendError(int statusCode, HttpServerResponse response) {
		response.setStatusCode(statusCode).end();
	}

	private void DbInitialize() {
		try {
			dbInitializeService.initializeDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
