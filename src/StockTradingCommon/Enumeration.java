/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package StockTradingCommon;

/**
 * @date    :   Oct 21, 2013
 * @author  :   Hirosh Wickramasuriya
 */
public  class Enumeration {

    public static class Database
    {
        public final static String    DB_REQUEST_INITIATED = "Please Wait, your requst is in progress...";
        
        public final static String    DB_INSERT_SUCCESS = "Record inserted successfully.";
        public final static String    DB_INSERT_FAILURE = "Failed to insert a record.";
        
        public final static String    DB_UPDATE_SUCCESS = "Record updated successfully.";        
        public final static String    DB_UPDATE_FAILURE = "Failed to update a record.";
    }
    
    public static class UserRole
    {
         public final static int    USER_ROLE_ADMIN = 1;
         public final static int    USER_ROLE_BROKER = 2;
    }
}
