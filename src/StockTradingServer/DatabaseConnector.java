package StockTradingServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	private Connection con = null;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void connectToDatabase() {
		// Basic configuration - to be moved to config file
		String url = "jdbc:mysql://192.30.164.204:3306/repo6545";
		String user = "repo6545";
		String password = "MF4@2163G!8d2L4";

		try {
			Connection con = DriverManager.getConnection(url, user, password);
			setCon(con);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
