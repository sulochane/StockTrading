package StockTradingServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	/*
	 * This function returns an ArrayList<BrokerageFirm> of all brokerage firms
	 */
	public ArrayList<BrokerageFirm> selectAllBrokerageFirms() {
		ArrayList<BrokerageFirm> brokerageFirms = new ArrayList<BrokerageFirm>();
		Connection con = this.con;
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM BROKERAGE_FIRM_INFO;";

		try {
			st = con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {

				int id = res.getInt(1);
				String name = res.getString(2);
				String addressStreet = res.getString(3);
				String addressCity = res.getString(4);
				String addressState = res.getString(5);
				String addressZip = res.getString(5);

				BrokerageFirm brokerageFirm = new BrokerageFirm();
				brokerageFirm.setId(id);
				brokerageFirm.setName(name);
				brokerageFirm.setAddressStreet(addressStreet);
				brokerageFirm.setAddressCity(addressCity);
				brokerageFirm.setAddressState(addressState);
				brokerageFirm.setAddressZip(addressZip);

				brokerageFirms.add(brokerageFirm);

			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return brokerageFirms;
	}

	
//	public ArrayList<StatusesOptions> selectAllStatuses() {
//		ArrayList<StatusesOptions> brokerageFirms = new ArrayList<StatusesOptions>();
//		Connection con = this.con;
//		Statement st = null;
//		ResultSet rs = null;
//		String query = "SELECT * FROM DIC_STATUSES;";
//
//		try {
//			st = con.createStatement();
//			ResultSet res = st.executeQuery(query);
//
//			while (res.next()) {
//
//				int id = res.getInt(1);
//				String name = res.getString(2);
//				String addressStreet = res.getString(3);
//				String addressCity = res.getString(4);
//				String addressState = res.getString(5);
//				String addressZip = res.getString(5);
//
//				BrokerageFirm brokerageFirm = new BrokerageFirm();
//				brokerageFirm.setId(id);
//				brokerageFirm.setName(name);
//				brokerageFirm.setAddressStreet(addressStreet);
//				brokerageFirm.setAddressCity(addressCity);
//				brokerageFirm.setAddressState(addressState);
//				brokerageFirm.setAddressZip(addressZip);
//
//				brokerageFirms.add(brokerageFirm);
//
//			}

		
		
		
//	}
	
	
	
	
	
	
	
	
	
	
	public boolean insertNewBrokerageFirm() {

		Connection con = this.con;
		Statement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO BROKERAGE_FIRM_INFO (NAME, ADDRESS_STREET,ADDRESS_CITY, ADDRESS_STATE, ADDRESS_ZIP, STATUS) VALUES (\"Virginia Brokers\", \"2000 S Eads Street\", \"Arlington\", \"VA\", \"22202\", \"1\")";

		try {

			st = con.createStatement();

			int affectedRows = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}
			
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				System.out.println(rs.getLong(1));
	        } else {
	            throw new SQLException("Creating user failed, no generated key obtained.");
	        }
			

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

}
