package StockTradingServer;

import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import StockTradingCommon.Enumeration;

/*
 * Dmitriy Karmazin
 * CSCI-6545
 */

public class DatabaseConnector {
	private Connection con = null;

	// connect to DB
	public DatabaseConnector() {
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

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	/*
	 * This function returns an array list of the brokerage firms
	 */
	public ArrayList<BrokerageFirm> selectBrokerageFirmsAll() {
		ArrayList<BrokerageFirm> brokerageFirms = new ArrayList<BrokerageFirm>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM BROKERAGE_FIRM_INFO;";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {

				int id = res.getInt(1);
				String name = res.getString(2);
				String addressStreet = res.getString(3);
				String addressCity = res.getString(4);
				String addressState = res.getString(5);
				String addressZip = res.getString(6);
				String licenceNumber = res.getString(7);
				int status = res.getInt(8);

				BrokerageFirm brokerageFirm = new BrokerageFirm();
				brokerageFirm.setId(id);
				brokerageFirm.setName(name);
				brokerageFirm.setAddressStreet(addressStreet);
				brokerageFirm.setAddressCity(addressCity);
				brokerageFirm.setAddressState(addressState);
				brokerageFirm.setAddressZip(addressZip);
				brokerageFirm.setLicenceNumber(licenceNumber);
				brokerageFirm.setStatus(status);

				brokerageFirms.add(brokerageFirm);

			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return brokerageFirms;
	}

	/*
	 * This function returns a single brokerage firm based on a given id MySQL
	 * injection protection
	 */
	public BrokerageFirm selectBrokerageFirm(int idToSelect) {
		BrokerageFirm brokerageFirm = new BrokerageFirm();
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM BROKERAGE_FIRM_INFO WHERE ID = ?;";

		try {
			st = this.con.prepareStatement(query);
			st.setInt(1, idToSelect);

			ResultSet res = st.executeQuery();

			res.next();

			int id = res.getInt(1);
			String name = res.getString(2);
			String addressStreet = res.getString(3);
			String addressCity = res.getString(4);
			String addressState = res.getString(5);
			String addressZip = res.getString(6);
			String licenceNumber = res.getString(7);
			int status = res.getInt(8);

			brokerageFirm.setId(id);
			brokerageFirm.setName(name);
			brokerageFirm.setAddressStreet(addressStreet);
			brokerageFirm.setAddressCity(addressCity);
			brokerageFirm.setAddressState(addressState);
			brokerageFirm.setAddressZip(addressZip);
			brokerageFirm.setLicenceNumber(licenceNumber);
			brokerageFirm.setStatus(status);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return brokerageFirm;
	}

	/*
	 * This function adds a new brokerage firm to the database from the given
	 * class instance MySQL injection checked
	 */
	public Validator insertNewBrokerageFirm(BrokerageFirm newFirm) {
		// validate input
		Validator v = newFirm.validate();
		if (!v.isVerified()) {
			return v;
		}

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "INSERT INTO BROKERAGE_FIRM_INFO (NAME, ADDRESSSTREET, ADDRESSCITY, ADDRESSSTATE, ADDRESSZIP, LICENCENUMBER, STATUSID) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {

			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newFirm.getName());
			st.setString(2, newFirm.getAddressStreet());
			st.setString(3, newFirm.getAddressCity());
			st.setString(4, newFirm.getAddressState());
			st.setString(5, newFirm.getAddressZip());
			st.setString(6, newFirm.getLicenceNumber());
			st.setInt(7, newFirm.getStatus());

			int affectedRows = st.executeUpdate();

			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Could not insert into the table");
				return v;
			}

			// rs = st.getGeneratedKeys();
			// if (rs.next()) {
			// System.out.println(rs.getLong(1));
			// } else {
			// throw new SQLException(
			// "Creating user failed, no generated key obtained.");
			// }

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		v.setVerified(true);
		v.setStatus("Success");

		return v;
	}

	/*
	 * This function updates a specified brokerage firm with provided
	 * information from the brokerage class
	 */
	public Validator updateBrokerageFirm(int idToUpdate,
			BrokerageFirm firmToUpdate) {
		// validate input
		Validator v = firmToUpdate.validate();
		if (!v.isVerified()) {
			return v;
		}

		InputValidation iv = new InputValidation();
		Validator v2 = iv.validateIntGeneral(idToUpdate, "idToUpdate");

		if (!v2.isVerified()) {
			return v2;
		}

		PreparedStatement st = null;

		String query = "UPDATE BROKERAGE_FIRM_INFO SET NAME = ?, ADDRESSSTREET = ?, ADDRESSCITY = ?, ADDRESSSTATE = ?, ADDRESSZIP = ?, LICENCENUMBER = ?, STATUSID = ? WHERE ID = ?;";

		try {

			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, firmToUpdate.getName());
			st.setString(2, firmToUpdate.getAddressStreet());
			st.setString(3, firmToUpdate.getAddressCity());
			st.setString(4, firmToUpdate.getAddressState());
			st.setString(5, firmToUpdate.getAddressZip());
			st.setString(6, firmToUpdate.getLicenceNumber());
			st.setInt(7, firmToUpdate.getStatus());
			st.setInt(8, idToUpdate);

			int affectedRows = st.executeUpdate();

			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Update failed");
				return v;
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return v;
	}

	/*
	 * This function returns all broker users 0 - all 1,2 - with certain status
	 */
	public ArrayList<User> selectBrokersAll(int pStatusId) {
		ArrayList<User> usersAll = new ArrayList<User>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM USERS WHERE ROLEID = 2";

		if (pStatusId != Enumeration.Broker.BROKER_SELECT_PARAM_EMPTY) {
			query += " AND STATUSID = \"" + pStatusId + "\"";
		}

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {

				int id = res.getInt(1);
				String firstName = res.getString(2);
				String lastName = res.getString(3);
				String email = res.getString(4);
				byte[] ssn = res.getBytes(5);
				String password = res.getString(6);
				String salt = res.getString(7);
				int roleId = res.getInt(8);
				int statusId = res.getInt(9);
				int brokerFirmId = res.getInt(10);

				// Decrypt sensitive data
				String iv = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_IV;
				String key = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_KEY;

				DataEncryptor de = new DataEncryptor();
				de.setIV(iv);

				String decryptedSsn = "";
				try {
					decryptedSsn = de.decrypt(ssn, key);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					decryptedSsn = "err";
				}
				// End sensitive data decryption

				User user = new User();
				user.setId(id);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setSsn(decryptedSsn);
				user.setPassword(password);
				user.setSalt(salt);
				user.setRoleId(roleId);
				user.setStatusId(statusId);
				user.setBrokerFirmId(brokerFirmId);

				usersAll.add(user);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return usersAll;
	}

	/*
	 * This function returns all broker users for a given firm 0 - all 1,2 -
	 * with certain status
	 */
	public ArrayList<User> selectBrokersAllbyFirm(int pFirmId) {
		ArrayList<User> usersAll = new ArrayList<User>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM USERS WHERE ROLEID = 2";

		if (pFirmId != Enumeration.Broker.BROKER_SELECT_PARAM_EMPTY) {
			query += " AND FIRMID = \"" + pFirmId + "\"";
		}

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {

				int id = res.getInt(1);
				String firstName = res.getString(2);
				String lastName = res.getString(3);
				String email = res.getString(4);
				byte[] ssn = res.getBytes(5);
				String password = res.getString(6);
				String salt = res.getString(7);
				int roleId = res.getInt(8);
				int statusId = res.getInt(9);
				int brokerFirmId = res.getInt(10);

				// Decrypt sensitive data
				String iv = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_IV;
				String key = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_KEY;

				DataEncryptor de = new DataEncryptor();
				de.setIV(iv);

				String decryptedSsn = "";
				try {
					decryptedSsn = de.decrypt(ssn, key);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					decryptedSsn = "err";
				}
				// End sensitive data decryption

				User user = new User();
				user.setId(id);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setSsn(decryptedSsn);
				user.setPassword(password);
				user.setSalt(salt);
				user.setRoleId(roleId);
				user.setStatusId(statusId);
				user.setBrokerFirmId(brokerFirmId);

				usersAll.add(user);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return usersAll;
	}

	/*
	 * This function returns a broker for a given userid
	 */
	public User selectBrokerUser(int idToSelect) {
		User user = new User();

		PreparedStatement st = null;
		String query = "SELECT * FROM USERS WHERE ID = ?";

		try {
			st = this.con.prepareStatement(query);
			st.setInt(1, idToSelect);

			ResultSet res = st.executeQuery();

			res.next();

			int id = res.getInt(1);
			String firstName = res.getString(2);
			String lastName = res.getString(3);
			String email = res.getString(4);
			byte[] ssn = res.getBytes(5);
			String password = res.getString(6);
			String salt = res.getString(7);
			int roleId = res.getInt(8);
			int statusId = res.getInt(9);
			int brokerFirmId = res.getInt(10);

			// Decrypt sensitive data
			String iv = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_IV;
			String key = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_KEY;

			DataEncryptor de = new DataEncryptor();
			de.setIV(iv);

			String decryptedSsn = "";
			try {
				decryptedSsn = de.decrypt(ssn, key);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				decryptedSsn = "err";
			}
			// End sensitive data decryption

			user.setId(id);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setSsn(decryptedSsn);
			user.setPassword(password);
			user.setSalt(salt);
			user.setRoleId(roleId);
			user.setStatusId(statusId);
			user.setBrokerFirmId(brokerFirmId);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return user;
	}

	/*
	 * This function inserts a new broker MySQL injection checked
	 */
	public Validator insertNewBroker(User newUser) {
		// validate input
		Validator v = newUser.validate();
		if (!v.isVerified()) {
			return v;
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO USERS (FIRSTNAME, LASTNAME, EMAIL, SSN, PASSWORD, SALT, ROLEID, STATUSID, FIRMID) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

		try {

			// Password hashing
			PasswordHasher ph = new PasswordHasher();
			String salt = ph.generateSalt();
			String passwordHashed = ph.sha512(newUser.getPassword(), salt);
			// end password hashing

			// Sensitive data encryption
			String iv = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_IV;
			String key = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_KEY;
			DataEncryptor de = new DataEncryptor();
			de.setIV(iv);

			byte[] ssnCipher = null;

			try {
				ssnCipher = de.encrypt(newUser.getSsn(), key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// end encryption

			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newUser.getFirstName());
			st.setString(2, newUser.getLastName());
			st.setString(3, newUser.getEmail());
			st.setBytes(4, ssnCipher);
			st.setString(5, passwordHashed);
			st.setString(6, salt);
			st.setInt(7, newUser.getRoleId());
			st.setInt(8, newUser.getStatusId());
			st.setInt(9, newUser.getBrokerFirmId());


			int affectedRows = st.executeUpdate();

			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Could not insert into the table");
				return v;
			}

			rs = st.getGeneratedKeys();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		v.setVerified(true);
		v.setStatus("Success");

		return v;
	}

	public Validator updateBroker(int idToUpdate, User user) {
		// validate input
		Validator v = user.validate();
		if (!v.isVerified()) {
			return v;
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		String query = "UPDATE USERS SET FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?, SSN = ?, PASSWORD = ?, SALT = ?, ROLEID = ?, STATUSID = ?, FIRMID = ? WHERE ID = ?";

		try {

			// Password hashing
			PasswordHasher ph = new PasswordHasher();
			String salt = ph.generateSalt();
			String passwordHashed = ph.sha512(user.getPassword(), salt);
			// end password hashing

			// Sensitive data encryption
			String iv = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_IV;
			String key = StockTradingCommon.Enumeration.Broker.BROKER_ENCRYPT_KEY;
			DataEncryptor de = new DataEncryptor();
			de.setIV(iv);

			byte[] ssnCipher = null;

			try {
				ssnCipher = de.encrypt(user.getSsn(), key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// end encryption

			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, user.getFirstName());
			st.setString(2, user.getLastName());
			st.setString(3, user.getEmail());
			st.setBytes(4, ssnCipher);
			st.setString(5, passwordHashed);
			st.setString(6, salt);
			st.setInt(7, user.getRoleId());
			st.setInt(8, user.getStatusId());
			st.setInt(9, user.getBrokerFirmId());
			st.setInt(10, idToUpdate);

			int affectedRows = st.executeUpdate();

			// log to DB
			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Could not update the table");
				return v;
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		v.setVerified(true);
		v.setStatus("Success");

		return v;
	}

	/*
	 * This function returns an arraylist of all the active stocks in the system
	 */
	public ArrayList<Stock> selectStockAll() {
		ArrayList<Stock> stocksAll = new ArrayList<Stock>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM STOCKS WHERE STATUSID = 1;";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {

				int id = res.getInt(1);
				String name = res.getString(2);
				int amount = res.getInt(3);
				double price = res.getDouble(4);
				int statusId = res.getInt(5);

				Stock stock = new Stock();
				stock.setId(id);
				stock.setName(name);
				stock.setAmount(amount);
				stock.setPrice(price);
				stock.setStatusId(statusId);

				stocksAll.add(stock);

			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return stocksAll;
	}

	/*
	 * This method returns a stock given a stock id
	 */
	public Stock selectStock(int idToSelect) {
		Stock stock = new Stock();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM STOCKS WHERE id = ?";

		try {
			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, idToSelect);

			ResultSet res = st.executeQuery();

			res.next();

			int id = res.getInt(1);
			String name = res.getString(2);
			int amount = res.getInt(3);
			double price = res.getDouble(4);
			int statusId = res.getInt(5);

			stock.setId(id);
			stock.setName(name);
			stock.setAmount(amount);
			stock.setPrice(price);
			stock.setStatusId(statusId);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return stock;
	}

	/*
	 * This function inserts a new stock into the database MySQL injection
	 * checked
	 */
	public Validator insertNewStock(Stock newStock) {
		Validator v = newStock.validate();
		if (!v.isVerified()) {
			return v;
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO STOCKS (NAME, AMOUNT, PRICE, STATUSID) VALUES (?, ?, ?, ?)";

		try {

			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newStock.getName());
			st.setInt(2, newStock.getAmount());
			st.setDouble(3, newStock.getPrice());
			st.setInt(4, newStock.getStatusId());

			int affectedRows = st.executeUpdate();

			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Could not insert into the table");
				return v;
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		v.setVerified(true);
		v.setStatus("Success");

		return v;
	}

	/*
	 * This function updates a stock in the database MySQL injections checked
	 */
	public Validator updateStock(int idToUpdate, Stock stock) {
		Validator v = stock.validate();
		if (!v.isVerified()) {
			return v;
		}

		PreparedStatement st = null;

		String query = "UPDATE STOCKS SET NAME = ?, AMOUNT = ?, PRICE = ?, STATUSID = ? WHERE ID = ?";

		try {
			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, stock.getName());
			st.setInt(2, stock.getAmount());
			st.setDouble(3, stock.getPrice());
			st.setInt(4, stock.getStatusId());
			st.setInt(5, idToUpdate);

			int affectedRows = st.executeUpdate();

			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Could not update the table");
				return v;
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		v.setVerified(true);
		v.setStatus("Success");

		return v;
	}

	/*
	 * This functions returns an array list of all the orders
	 */
	public ArrayList<Order> selectOrdersAll() {
		ArrayList<Order> ordersAll = new ArrayList<Order>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ORDERS WHERE STATUSID = 1";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {
				int orderId = res.getInt(1);
				int typeId = res.getInt(2);
				int brokerId = res.getInt(3);
				int customerId = res.getInt(4);
				int stockId = res.getInt(5);
				int amount = res.getInt(6);
				double price = res.getDouble(7);
				Timestamp dateIssued = res.getTimestamp(8);
				Timestamp dateExpiration = res.getTimestamp(9);
				int statusId = res.getInt(10);

				Order order = new Order();
				order.setOrderId(orderId);
				order.setTypeId(typeId);
				order.setBrokerId(brokerId);
				order.setCustomerId(customerId);
				order.setStockId(stockId);
				order.setAmount(amount);
				order.setPrice(price);
				order.setDateIssued(dateIssued);
				order.setDateExpiration(dateExpiration);
				order.setStatusId(statusId);

				ordersAll.add(order);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return ordersAll;
	}

	/*
	 * This function returns a particular order
	 */
	public Order selectOrder(int idToSelect) {
		Order order = new Order();

		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ORDERS WHERE ORDERID = ?";

		try {
			st = this.con.prepareStatement(query);
			st.setInt(1, idToSelect);
			ResultSet res = st.executeQuery();

			res.next();

			int orderId = res.getInt(1);
			int typeId = res.getInt(2);
			int brokerId = res.getInt(3);
			int customerId = res.getInt(4);
			int stockId = res.getInt(5);
			int amount = res.getInt(6);
			double price = res.getDouble(7);
			Timestamp dateIssued = res.getTimestamp(8);
			Timestamp dateExpiration = res.getTimestamp(9);
			int statusId = res.getInt(10);

			order.setOrderId(orderId);
			order.setTypeId(typeId);
			order.setBrokerId(brokerId);
			order.setCustomerId(customerId);
			order.setStockId(stockId);
			order.setAmount(amount);
			order.setPrice(price);
			order.setDateIssued(dateIssued);
			order.setDateExpiration(dateExpiration);
			order.setStatusId(statusId);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return order;
	}

	/*
	 * This function inserts a new order to the database
	 */
	public Validator insertNewOrder(Order newOrder) {
		Validator v = newOrder.validate();
		if (!v.isVerified()) {
			return v;
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO ORDERS (TYPEID, BROKERID, CUSTOMERID, STOCKID, AMOUNT, PRICE, DATEISSUED, DATEEXPIRATION, STATUSID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, newOrder.getTypeId());
			st.setInt(2, newOrder.getBrokerId());
			st.setInt(3, newOrder.getCustomerId());
			st.setInt(4, newOrder.getStockId());
			st.setInt(5, newOrder.getAmount());
			st.setDouble(6, newOrder.getPrice());
			st.setTimestamp(7, newOrder.getDateIssued());
			st.setTimestamp(8, newOrder.getDateExpiration());
			st.setInt(9, newOrder.getStatusId());

			System.out.println(st.toString());

			int affectedRows = st.executeUpdate();

			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Could not insert into the table");
				return v;
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		v.setVerified(true);
		v.setStatus("Success");

		return v;
	}

	/*
	 * This function updates an order
	 */
	public Validator updateOrder(int idToUpdate, Order order) {
		Validator v = order.validate();
		if (!v.isVerified()) {
			return v;
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		String query = "UPDATE ORDERS SET TYPEID = ?, BROKERID = ?, CUSTOMERID = ?, STOCKID = ?, AMOUNT = ?, PRICE = ?, DATEISSUED = ?, DATEEXPIRATION = ?, STATUSID = ? WHERE ORDERID = ?";

		try {
			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, order.getTypeId());
			st.setInt(2, order.getBrokerId());
			st.setInt(3, order.getCustomerId());
			st.setInt(4, order.getStockId());
			st.setInt(5, order.getAmount());
			st.setDouble(6, order.getPrice());
			st.setTimestamp(7, order.getDateIssued());
			st.setTimestamp(8, order.getDateExpiration());
			st.setInt(9, order.getStatusId());
			st.setInt(10, idToUpdate);

			int affectedRows = st.executeUpdate();

			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Could not update the table");
				return v;
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		v.setVerified(true);
		v.setStatus("Success");

		return v;
	}

	/*
	 * This function returns an array list of all active customers
	 */
	public ArrayList<CustomerInfo> selectCustomerInfoAll() {
		ArrayList<CustomerInfo> customerInfoAll = new ArrayList<CustomerInfo>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM CUSTOMER_INFO WHERE STATUSID = 1;";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {

				int id = res.getInt(1);
				String firstName = res.getString(2);
				String lastName = res.getString(3);
				String email = res.getString(4);
				String phone = res.getString(5);
				int statusId = res.getInt(6);

				CustomerInfo customer = new CustomerInfo();
				customer.setId(id);
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setEmail(email);
				customer.setPhone(phone);
				customer.setStatusId(statusId);

				customerInfoAll.add(customer);

			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return customerInfoAll;
	}

	// /*
	// * This function returns an array list of all active customers
	// */
	// public ArrayList<CustomerInfo> selectCustomerInfoByFirm(int firmId) {
	// ArrayList<CustomerInfo> customerInfoAll = new ArrayList<CustomerInfo>();
	// Statement st = null;
	// ResultSet rs = null;
	// String query = "";
	//
	// try {
	// st = this.con.createStatement();
	// ResultSet res = st.executeQuery(query);
	//
	// while (res.next()) {
	//
	// int id = res.getInt(1);
	// String firstName = res.getString(2);
	// String lastName = res.getString(3);
	// String email = res.getString(4);
	// String phone = res.getString(5);
	// int statusId = res.getInt(6);
	//
	// CustomerInfo customer = new CustomerInfo();
	// customer.setId(id);
	// customer.setFirstName(firstName);
	// customer.setLastName(lastName);
	// customer.setEmail(email);
	// customer.setPhone(phone);
	// customer.setStatusId(statusId);
	//
	// customerInfoAll.add(customer);
	//
	// }
	// } catch (SQLException ex) {
	// Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
	// lgr.log(Level.WARNING, ex.getMessage(), ex);
	// }
	//
	// return customerInfoAll;
	// }

	public CustomerInfo selectCustomerInfo(int idToSelect) {
		CustomerInfo customer = new CustomerInfo();

		PreparedStatement st = null;
		String query = "SELECT * FROM CUSTOMER_INFO WHERE id = ?";

		try {
			st = this.con.prepareStatement(query);
			st.setInt(1, idToSelect);

			ResultSet res = st.executeQuery();

			res.next();

			int id = res.getInt(1);
			String firstName = res.getString(2);
			String lastName = res.getString(3);
			String email = res.getString(4);
			String phone = res.getString(5);
			int statusId = res.getInt(6);

			customer.setId(id);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
			customer.setPhone(phone);
			customer.setStatusId(statusId);
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return customer;
	}

	public Validator insertNewCustomerInfo(CustomerInfo newCustomer) {
		Validator v = newCustomer.validate();
		if (!v.isVerified()) {
			return v;
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO CUSTOMER_INFO (FIRSTNAME, LASTNAME, EMAIL, PHONE, STATUSID) VALUES (?, ?, ?, ?, ?)";

		try {
			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newCustomer.getFirstName());
			st.setString(2, newCustomer.getLastName());
			st.setString(3, newCustomer.getEmail());
			st.setString(4, newCustomer.getPhone());
			st.setInt(5, newCustomer.getStatusId());

			int affectedRows = st.executeUpdate();

			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Could not insert into the table");
				return v;
			}

			rs = st.getGeneratedKeys();
			rs.next();
			rs.getLong(1);
			// System.out.println(rs.getLong(1));

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		v.setVerified(true);
		v.setStatus("Success");

		return v;
	}

	public Validator updateCustomerInfo(int idToUpdate,
			CustomerInfo customerToUpdate) {
		Validator v = customerToUpdate.validate();
		if (!v.isVerified()) {
			return v;
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		String query = "UPDATE CUSTOMER_INFO SET FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?, PHONE = ?, STATUSID = ? WHERE ID = ?";

		try {
			st = this.con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, customerToUpdate.getFirstName());
			st.setString(2, customerToUpdate.getLastName());
			st.setString(3, customerToUpdate.getEmail());
			st.setString(4, customerToUpdate.getPhone());
			st.setInt(5, customerToUpdate.getStatusId());
			st.setInt(6, idToUpdate);

			int affectedRows = st.executeUpdate();

			StockTradingServer.Logger logger = new StockTradingServer.Logger();
			logger.logDatabaseActivity(st.toString());

			if (affectedRows == 0) {
				v.setVerified(false);
				v.setStatus("Could not insert into the table");
				return v;
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		v.setVerified(true);
		v.setStatus("Success");

		return v;
	}

	public ArrayList<StatusesOptions> selectAllStatuses() {

		ArrayList<StatusesOptions> statusesList = new ArrayList<StatusesOptions>();

		Connection con = this.con;
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM DIC_STATUSES ORDER BY PRIORITY;";

		try {
			st = con.createStatement();
			ResultSet res = st.executeQuery(query);
			while (res.next()) {

				int id = res.getInt(1);
				String name = res.getString(2);

				StatusesOptions status = new StatusesOptions();
				status.setId(id);
				status.setName(name);

				statusesList.add(status);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return statusesList;

	}

}