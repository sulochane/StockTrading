/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package StockTradingCommon;

/**
 * @date : Oct 21, 2013
 * @author : Hirosh Wickramasuriya
 */
public class Enumeration {

	public static class Database {
		public final static String DB_REQUEST_INITIATED = "Please Wait, your requst is in progress...";

		public final static String DB_INSERT_SUCCESS = "Record inserted successfully.";
		public final static String DB_INSERT_FAILURE = "Failed to insert a record.";

		public final static String DB_UPDATE_SUCCESS = "Record updated successfully.";
		public final static String DB_UPDATE_FAILURE = "Failed to update a record.";
	}

	public static class UserRole {
		public final static int USER_ROLE_ADMIN = 1;
		public final static int USER_ROLE_BROKER = 2;
	}

	public static class BrokerageFirm {
		public final static int BROKERAGE_FIRM_STRING_LENGTH = 128;
		public final static int BROKERAGE_FIRM_STATUS_ACTIVE_ID = 1;
		public final static int BROKERAGE_FIRM_STATUS_INACTIVE_ID = 2;

	}

	public static class OrderType {
		public final static int BUYING_ORDER = 1;
		public final static int SELLING_ORDER = 2;
	}

	public static class Broker {
            public final static int BROKER_SELECT_PARAM_EMPTY = 0;
            public final static String BROKER_ENCRYPT_IV = "ABAFACAFAA5ABBAA";
            public final static String BROKER_ENCRYPT_KEY = "0123456789abcdef";
            

        }
}
