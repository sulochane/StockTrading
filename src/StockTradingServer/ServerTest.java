/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerTest {
    public static void main(String[] args) 
    {
        System.out.println("This is the Stock Trading Systems - Server");
        
        StockTradingClient.ClientTest client = new StockTradingClient.ClientTest();
        client.Test();
        StockTradingCommon.CommonTest common = new StockTradingCommon.CommonTest();
        common.Test();
        
        
        
        
        
        
		DatabaseConnector dc = new DatabaseConnector();
		dc.connectToDatabase();
		Connection con = dc.getCon();

		// TODO Auto-generated method stub
		Statement st = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://192.30.164.204:3306/repo6545";
		String user = "repo6545";
		String password = "MF4@2163G!8d2L4";

		try {
			st = con.createStatement();
			

			// rs = st.executeQuery("SELECT VERSION()");
			rs = st.executeQuery("SHOW TABLES");

			if (rs.next()) {
				System.out.println(rs.getString(1));
			}

			// st.execute(sql)

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseConnector.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger
						.getLogger(DatabaseConnector.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}

	}

        
        
        
        
        
        
        
        
    }
}
