package com.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.DatabaseConfiguration;

public class SuperPool {

	private DatabaseConfiguration configuration;
	// 已使用连接
	private ArrayBlockingQueue<SuperPoolConnection> use = new ArrayBlockingQueue<SuperPoolConnection>(1024);

	// 未使用连接
	private ArrayBlockingQueue<SuperPoolConnection> duse = new ArrayBlockingQueue<SuperPoolConnection>(1024);

	private boolean initDriver = false;

	private SuperPool() {

	}

	private void initDriver() {
		try {
			Class.forName(configuration.getDriverClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initDriver = true;
	}

	private SuperPoolConnection getConnection() {
		if (!initDriver) {
			initDriver();
		}
		Connection conn = null;
		SuperPoolConnection superPoolConnection = null;
		try {
			conn = DriverManager.getConnection(configuration.getJdbcUrl(), configuration.getUsername(),
					configuration.getPassword());
			superPoolConnection = new SuperPoolConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return superPoolConnection;
	}

	private static class Singteton {
		private static SuperPool superPool;
		static {
			superPool = new SuperPool();
		}

		public static SuperPool getInstance() {
			return superPool;
		}
	}

	public static SuperPool getInstance() {
		return Singteton.getInstance();
	}

	public DatabaseConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(DatabaseConfiguration configuration) {
		this.configuration = configuration;
	}

	public SuperPoolConnection getSuperPoolConnection(long waitTime) throws InterruptedException {
		SuperPoolConnection superPoolConnection = null;
		while (true) {
			if (use.size() + duse.size() <= getConfiguration().getMaxIdle()) {
				if (duse.size() <= 0) {
					superPoolConnection = getConnection();
					use.put(superPoolConnection);
					return superPoolConnection;
				} else {
					return duse.take();
				}
			} else {
				wait(waitTime);
			}
		}
	}

	public void addUse(SuperPoolConnection use) {
		this.use.add(use);
	}

	public void removeUse(SuperPoolConnection use) {
		this.use.remove(use);
	}

	public void addDuse(SuperPoolConnection duse) {
		this.duse.add(duse);
	}

	public void removeDuse(SuperPoolConnection duse) {
		this.duse.remove(duse);
	}

}
