package com.test.lti.ltitest.dao;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.test.lti.ltitest.databaseconnector.DbConnector;

public class DatabaseInitializationDAO {

	public void InitializeDB() throws Exception {

		System.out.println("DB initialise ---------------------------------------------------start");
		DBI dbi = new DbConnector().getDBI();

		Handle handle = dbi.open();

		handle.execute("DROP TABLE IF EXISTS products");
		handle.execute("DROP TABLE IF EXISTS orders");

		handle.execute(
				"CREATE TABLE products (id INT AUTO_INCREMENT  PRIMARY KEY, category VARCHAR(250) NOT NULL, company VARCHAR(250) NOT NULL, product VARCHAR(250) NOT NULL, color VARCHAR(50) NOT NULL, description VARCHAR(250), price DECIMAL(18,2) NOT NULL, discount DECIMAL (4, 2) NOT NULL, stock INT)");
		handle.execute(
				"CREATE TABLE orders (orderid INT AUTO_INCREMENT  PRIMARY KEY, productid int, quantity int, FOREIGN KEY (productid) REFERENCES products(id))");

		handle.execute(
				"insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('mobiles','Apple','AP1','Black','description for mobile',70000,13,11)");
		handle.execute(
				"insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('mobiles','Samsung','SP1','Grey','description for mobile',50000,2,2)");
		handle.execute(
				"insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('mobiles','MI','MP1','Black','description for mobile',20000,9,35)");
		handle.execute(
				"insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Computers','Intel','IL1','Grey','description for computers',67000,0,106)");
		handle.execute(
				"insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Computers','Intel','IL2','Black','description for computers',74000,0,300)");
		handle.execute(
				"insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Computers','Lenovo','LL1','Black','description for computers',80000,10,138)");
		handle.execute(
				"insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Television','LG','LT1','Black','description for television',42500,8,62)");
		handle.execute(
				"insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Television','Samsung','ST1','Grey','description for television',58360,16,168)");
		
		
		
		//handle.execute("insert into orders (orderid ,productid,quantity ) values (1, 1, 10)");

		System.out.println("DB initialise ---------------------------------------------------end");

	}

}
