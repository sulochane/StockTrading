/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package StockTradingServer;

import java.sql.Connection;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println("This is the Stock Trading Systems - Server");

		DatabaseConnector dc = new DatabaseConnector();
		dc.connectToDatabase();

		Connection con = dc.getCon();

		dc.insertNewBrokerageFirm();
		

	}

}