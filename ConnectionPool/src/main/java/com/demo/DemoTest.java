package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import com.SuperDataSource;
import com.pool.SuperPoolConnection;

public class DemoTest {


	
	public static void main(String[] args) throws SQLException {
      SuperDataSource dataSource = new SuperDataSource();
      dataSource.setUsername("root");
      dataSource.setPassword("123456");
      dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
      dataSource.setDriverClass("com.mysql.jdbc.Driver");
      dataSource.setInitialSize(1);
      dataSource.setMaxIdle(20);
      Connection a =  dataSource.getConnection();
	  System.out.println(a.toString());
//	  a.close();
	  Connection b = dataSource.getConnection();
	  System.out.println(b.toString());
	}
}
