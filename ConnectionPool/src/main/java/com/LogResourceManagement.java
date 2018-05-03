package com;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.CommonDataSource;

/**
 * 
* <p>Copyright： Copyright (c) 2017</p>
* <p>Company： 熠道大数据</p>
* @ClassName: LogResourceManagement  
* @Description: TODO(日志资源管理器)  
* @author liuhonbin  
* @date 2018年4月24日
 */
public abstract class LogResourceManagement implements CommonDataSource{
	
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
