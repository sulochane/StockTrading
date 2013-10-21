package StockTradingServer;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		System.out.println("This is the Stock Trading Systems - Server");

		DatabaseConnector dc = new DatabaseConnector();

		
		java.util.Date date= new java.util.Date();

		Timestamp dt = new Timestamp(date.getTime());
		Timestamp et = new Timestamp(date.getTime());
		

		
		
		Order ord = new Order();
		ord.setBrokerId(1);
		ord.setStockId(1);
		ord.setAmount(10);
		ord.setDateIssued(dt);
		ord.setDateExpiration(et);
		ord.setStatusId(1);
		ord.setTypeId(2);

		dc.updateOrder(1, ord);
		//dc.insertNewOrder(ord);
		
		//System.out.println(dc.selectOrder(1));
		
		
	}

}