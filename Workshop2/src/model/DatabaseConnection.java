package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

	private final String CONNECTION_URL = "jdbc:mysql://www.songho.se:3306/boatDB?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	private final String USERNAME = "groupproject";
	private final String PASSWORD = "workshop2";
	private Connection connectionDB;
	private boolean connection = false;

	public DatabaseConnection() {
		startConnection();
	}

	public void startConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Properties user = new Properties();
			user.put("user", USERNAME);
			user.put("password", PASSWORD);
			connectionDB = DriverManager.getConnection(CONNECTION_URL, user);
			connection = true;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			// System.out.println("DATABASE_CONNECTED: " + connection);
		}

	}

	public void closeConnection() {
		try {
			connectionDB.close();
			connection = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean isConnected() {
		return connection;
	}

	public Connection getConnection() {
		return connectionDB;
	}
}