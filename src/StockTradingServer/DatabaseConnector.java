package StockTradingServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {
	private Connection con = null;

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
				String status = res.getString(8);

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

	public BrokerageFirm selectBrokerageFirm(int idToSelect) {
		BrokerageFirm brokerageFirm = new BrokerageFirm();

		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM BROKERAGE_FIRM_INFO WHERE ID = \""
				+ idToSelect + "\" ;";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			res.next();
			int id = res.getInt(1);
			String name = res.getString(2);
			String addressStreet = res.getString(3);
			String addressCity = res.getString(4);
			String addressState = res.getString(5);
			String addressZip = res.getString(6);
			String licenceNumber = res.getString(7);
			String status = res.getString(8);

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

	public boolean insertNewBrokerageFirm(BrokerageFirm newFirm) {
		Statement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO BROKERAGE_FIRM_INFO (NAME, ADDRESSSTREET,ADDRESSCITY, ADDRESSSTATE, ADDRESSZIP, LICENCENUMBER, STATUSID)"
				+ " VALUES ("
				+ "\""
				+ newFirm.getName()
				+ "\",\""
				+ newFirm.getAddressStreet()
				+ "\",\""
				+ newFirm.getAddressCity()
				+ "\",\""
				+ newFirm.getAddressState()
				+ "\",\""
				+ newFirm.getAddressZip()
				+ "\",\""
				+ newFirm.getLicenceNumber()
				+ "\",\""
				+ newFirm.getStatus() + "\")";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query,
					Statement.RETURN_GENERATED_KEYS);

			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}

			rs = st.getGeneratedKeys();
			if (rs.next()) {
				System.out.println(rs.getLong(1));
			} else {
				throw new SQLException(
						"Creating user failed, no generated key obtained.");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

	public boolean updateBrokerageFirm(int idToUpdate,
			BrokerageFirm firmToUpdate) {
		Statement st = null;
		ResultSet rs = null;

		String query = "UPDATE BROKERAGE_FIRM_INFO SET" + " NAME = \""
				+ firmToUpdate.getName() + "\", ADDRESSSTREET = \""
				+ firmToUpdate.getAddressStreet() + "\", ADDRESSCITY = \""
				+ firmToUpdate.getAddressCity() + "\", ADDRESSSTATE = \""
				+ firmToUpdate.getAddressState() + "\", ADDRESSZIP = \""
				+ firmToUpdate.getAddressZip() + "\", LICENCENUMBER = \""
				+ firmToUpdate.getLicenceNumber() + "\", STATUSID = \""
				+ firmToUpdate.getStatus() + "\" WHERE ID = \"" + idToUpdate
				+ "\";";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query);

			if (affectedRows == 0) {
				throw new SQLException("Update failed");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
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

	public ArrayList<CustomerInfo> selectCustomerInfoAll() {
		ArrayList<CustomerInfo> customerInfoAll = new ArrayList<CustomerInfo>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM CUSTOMER_INFO;";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {

				int id = res.getInt(1);
				String firstName = res.getString(2);
				String lastName = res.getString(3);
				String email = res.getString(4);
				String phone = res.getString(5);

				CustomerInfo customer = new CustomerInfo();
				customer.setId(id);
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setEmail(email);
				customer.setPhone(phone);

				customerInfoAll.add(customer);

			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return customerInfoAll;
	}

	public CustomerInfo selectCustomerInfo(int idToSelect) {
		CustomerInfo customer = new CustomerInfo();

		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM CUSTOMER_INFO WHERE id = \"" + idToSelect
				+ "\";";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			res.next();

			int id = res.getInt(1);
			String firstName = res.getString(2);
			String lastName = res.getString(3);
			String email = res.getString(4);
			String phone = res.getString(5);

			customer.setId(id);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
			customer.setPhone(phone);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return customer;
	}

	public boolean insertNewCustomerInfo(CustomerInfo newCustomer) {
		Statement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO CUSTOMER_INFO (FIRSTNAME, LASTNAME, EMAIL, PHONE)"
				+ " VALUES ("
				+ "\""
				+ newCustomer.getFirstName()
				+ "\",\""
				+ newCustomer.getLastName()
				+ "\",\""
				+ newCustomer.getEmail()
				+ "\",\"" + newCustomer.getPhone() + "\")";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query,
					Statement.RETURN_GENERATED_KEYS);

			if (affectedRows == 0) {
				throw new SQLException("Insert failed");
			}

			rs = st.getGeneratedKeys();
			if (rs.next()) {
				System.out.println(rs.getLong(1));
			} else {
				throw new SQLException("No generated key obtained.");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

	public boolean updateCustomerInfo(int idToUpdate,
			CustomerInfo customerToUpdate) {
		Statement st = null;
		ResultSet rs = null;

		String query = "UPDATE CUSTOMER_INFO SET" + " FIRSTNAME = \""
				+ customerToUpdate.getFirstName() + "\", LASTNAME = \""
				+ customerToUpdate.getLastName() + "\", EMAIL = \""
				+ customerToUpdate.getEmail() + "\", PHONE = \""
				+ customerToUpdate.getPhone() + "\" WHERE ID = \"" + idToUpdate
				+ "\";";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query);

			if (affectedRows == 0) {
				throw new SQLException("Update failed");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

	public ArrayList<Stock> selectStockAll() {
		ArrayList<Stock> stocksAll = new ArrayList<Stock>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM STOCKS;";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {

				int id = res.getInt(1);
				String name = res.getString(2);
				int amount = res.getInt(3);
				int price = res.getInt(4);

				Stock stock = new Stock();
				stock.setId(id);
				stock.setName(name);
				stock.setAmount(amount);
				stock.setPrice(price);

				stocksAll.add(stock);

			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return stocksAll;
	}

	public Stock selectStock(int idToSelect) {
		Stock stock = new Stock();

		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM STOCKS WHERE id = \"" + idToSelect
				+ "\";";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			res.next();

			int id = res.getInt(1);
			String name = res.getString(2);
			int amount = res.getInt(3);
			int price = res.getInt(4);

			stock.setId(id);
			stock.setName(name);
			stock.setAmount(amount);
			stock.setPrice(price);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return stock;
	}

	public boolean insertNewStock(Stock newStock) {
		Statement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO STOCKS (NAME, AMOUNT, PRICE)" + " VALUES ("
				+ "\"" + newStock.getName() + "\",\"" + newStock.getAmount()
				+ "\",\"" + newStock.getPrice() + "\")";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query,
					Statement.RETURN_GENERATED_KEYS);

			if (affectedRows == 0) {
				throw new SQLException("Insert failed");
			}

			rs = st.getGeneratedKeys();
			if (rs.next()) {
				System.out.println(rs.getLong(1));
			} else {
				throw new SQLException("No generated key obtained.");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

	public boolean updateStock(int idToUpdate, Stock stock) {
		Statement st = null;
		ResultSet rs = null;

		String query = "UPDATE STOCKS SET" + " NAME = \"" + stock.getName()
				+ "\", AMOUNT = \"" + stock.getAmount() + "\", PRICE = \""
				+ stock.getPrice() + "\" WHERE ID = \"" + idToUpdate + "\";";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query);

			if (affectedRows == 0) {
				throw new SQLException("Update failed");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

	public ArrayList<User> selectBrokersAll() {
		ArrayList<User> usersAll = new ArrayList<User>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT U.*, BFI.ID "
				+ "FROM USERS AS U, BROKERAGE_FIRM_INFO AS BFI, HAS_FIRM_BROKERS AS HFB "
				+ "WHERE U.ID = HFB.BROKERID " + "AND HFB.FIRMID = BFI.ID;";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {

				int id = res.getInt(1);
				String firstName = res.getString(2);
				String lastName = res.getString(3);
				String email = res.getString(4);
				String ssn = res.getString(5);
				String password = res.getString(6);
				String salt = res.getString(7);
				int roleId = res.getInt(8);
				int statusId = res.getInt(9);
				int brokerFirmId = res.getInt(10);

				User user = new User();
				user.setId(id);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setEmail(ssn);
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

	public User selectBrokerUser(int idToSelect) {
		User user = new User();

		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT U.*, BFI.ID "
				+ "FROM USERS AS U, BROKERAGE_FIRM_INFO AS BFI, HAS_FIRM_BROKERS AS HFB "
				+ "WHERE U.ID = HFB.BROKERID " + "AND HFB.FIRMID = BFI.ID "
				+ "AND U.ID = \"" + idToSelect + "\";";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			res.next();

			int id = res.getInt(1);
			String firstName = res.getString(2);
			String lastName = res.getString(3);
			String email = res.getString(4);
			String ssn = res.getString(5);
			String password = res.getString(6);
			String salt = res.getString(7);
			int roleId = res.getInt(8);
			int statusId = res.getInt(9);
			int brokerFirmId = res.getInt(10);

			user.setId(id);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setSsn(ssn);
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

	public boolean insertNewUser(User newUser) {
		Statement st, st2 = null;
		ResultSet rs, rs2 = null;

		String query = "INSERT INTO USERS (FIRSTNAME, LASTNAME, EMAIL, SSN, PASSWORD, SALT, ROLEID, STATUSID)"
				+ " VALUES ("
				+ "\""
				+ newUser.getFirstName()
				+ "\",\""
				+ newUser.getLastName()
				+ "\",\""
				+ newUser.getEmail()
				+ "\",\""
				+ newUser.getSsn()
				+ "\",\""
				+ newUser.getPassword()
				+ "\",\""
				+ newUser.getSalt()
				+ "\",\""
				+ newUser.getRoleId()
				+ "\",\"" + newUser.getStatusId() + "\")";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query,
					Statement.RETURN_GENERATED_KEYS);

			if (affectedRows == 0) {
				throw new SQLException("Insert failed");
			}

			rs = st.getGeneratedKeys();
			if (rs.next()) {
				int insertedUserId = (int) rs.getLong(1);

				String query2 = "INSERT INTO HAS_FIRM_BROKERS (FIRMID, BROKERID, STATUSID)"
						+ "VALUES (\""
						+ newUser.getBrokerFirmId()
						+ "\", \""
						+ insertedUserId + "\", 1);";

				st.executeUpdate(query);

			} else {
				throw new SQLException("No generated key obtained.");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

	public boolean insertFirmBroker(int firmId, int brokerId) {
		Statement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO HAS_FIRM_BROKERS (FIRMID, BROKERID, STATUSID)"
				+ "VALUES (\"" + firmId + "\", 1, 1)";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query,
					Statement.RETURN_GENERATED_KEYS);

			if (affectedRows == 0) {
				throw new SQLException("Insert failed");
			}

			rs = st.getGeneratedKeys();
			if (rs.next()) {
				int insertedId = (int) rs.getLong(1);

			} else {
				throw new SQLException("No generated key obtained.");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

	public boolean updateUser(int idToUpdate, User user) {
		Statement st = null;
		ResultSet rs = null;

		String query = "UPDATE USERS SET FIRSTNAME = \"" + user.getFirstName()
				+ "\", LASTNAME = \"" + user.getLastName() + "\", EMAIL = \""
				+ user.getEmail() + "\", PASSWORD = \"" + user.getPassword()
				+ "\", SALT = \"" + user.getSalt() + "\", PASSWORD = \""
				+ user.getPassword() + "\", ROLEID = \"" + user.getRoleId()
				+ "\", STATUSID = \"" + user.getStatusId() + "\" WHERE ID = \""
				+ idToUpdate + "\";";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query);

			if (affectedRows == 0) {
				throw new SQLException("Update failed");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

	public ArrayList<Order> selectOrdersAll() {
		ArrayList<Order> ordersAll = new ArrayList<Order>();
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ORDERS;";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			while (res.next()) {
				int orderId = res.getInt(1);
				int brokerId = res.getInt(2);
				int stockId = res.getInt(3);
				int amount = res.getInt(4);
				Date dateIssued = res.getDate(5);
				Date dateExpiration = res.getDate(6);
				int statusId = res.getInt(7);
				int typeId = res.getInt(8);

				Order order = new Order();
				order.setOrderId(orderId);
				order.setBrokerId(brokerId);
				order.setStockId(stockId);
				order.setAmount(amount);
				order.setDateIssued(dateIssued);
				order.setDateExpiration(dateExpiration);
				order.setStatusId(statusId);
				order.setTypeId(typeId);

				ordersAll.add(order);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return ordersAll;
	}

	public Order selectOrder(int idToSelect) {
		Order order = new Order();

		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ORDERS WHERE ORDERID = \"" + idToSelect
				+ "\";";

		try {
			st = this.con.createStatement();
			ResultSet res = st.executeQuery(query);

			res.next();

			int orderId = res.getInt(1);
			int brokerId = res.getInt(2);
			int stockId = res.getInt(3);
			int amount = res.getInt(4);
			Date dateIssued = res.getDate(5);
			Date dateExpiration = res.getDate(6);
			int statusId = res.getInt(7);
			int typeId = res.getInt(8);

			order.setOrderId(orderId);
			order.setBrokerId(brokerId);
			order.setStockId(stockId);
			order.setAmount(amount);
			order.setDateIssued(dateIssued);
			order.setDateExpiration(dateExpiration);
			order.setStatusId(statusId);
			order.setTypeId(typeId);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return order;
	}

	public boolean insertNewOrder(Order newOrder) {
		Statement st = null;
		ResultSet rs = null;

		String query = "INSERT INTO ORDERS (BROKERID, STOCKID, AMOUNT, DATEISSUED, DATEEXPIRATION, STATUSID, TYPEID)"
				+ " VALUES ("
				+ "\""
				+ newOrder.getBrokerId()
				+ "\",\""
				+ newOrder.getStockId()
				+ "\",\""
				+ newOrder.getAmount()
				+ "\",\""
				+ newOrder.getDateIssued()
				+ "\",\""
				+ newOrder.getDateExpiration()
				+ "\",\""
				+ newOrder.getStatusId()
				+ "\",\""
				+ newOrder.getTypeId()
				+ "\")";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query,
					Statement.RETURN_GENERATED_KEYS);

			if (affectedRows == 0) {
				throw new SQLException("Insert failed");
			}

			rs = st.getGeneratedKeys();
			if (rs.next()) {
				System.out.println(rs.getLong(1));
			} else {
				throw new SQLException("No generated key obtained.");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

	public boolean updateOrder(int idToUpdate, Order order) {
		Statement st = null;
		ResultSet rs = null;

		String query = "UPDATE ORDERS SET" + " BROKERID = \""
				+ order.getBrokerId() + "\", STOCKID = \"" + order.getStockId()
				+ "\", AMOUNT = \"" + order.getAmount() + "\", DATEISSUED = \""
				+ order.getDateIssued() + "\", DATEEXPIRATION = \""
				+ order.getDateExpiration() + "\", STATUSID = \""
				+ order.getStatusId() + "\", TYPEID = \"" + order.getTypeId()
				+ "\" WHERE ORDERID = \"" + idToUpdate + "\";";

		try {

			st = this.con.createStatement();

			int affectedRows = st.executeUpdate(query);

			if (affectedRows == 0) {
				throw new SQLException("Update failed");
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.WARNING, ex.getMessage(), ex);
		}

		return true;
	}

}