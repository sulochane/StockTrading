package StockTradingServer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		System.out.println("This is the Stock Trading Systems - Server");

		DatabaseConnector dc = new DatabaseConnector();

//		java.util.Date date = new java.util.Date();
//		Timestamp dt = new Timestamp(date.getTime());
//		Timestamp et = new Timestamp(date.getTime());

//		CustomerInfo c = new CustomerInfo();
//		c.setFirstName("Bruce");
//		c.setLastName("Wayne");
//		c.setEmail("dasfffafass.asq");
//		c.setPhone("123-123-1231");
//		c.setStatusId(2);
//		
//		System.out.println(dc.updateCustomerInfo(7, c).getStatus());
		

		
		User br = new User();
		br.setFirstName("Bruce");
		br.setLastName("Wayne");
		br.setEmail("bwayne@asd.cas");
		br.setSsn("123-123-1232");
		br.setPassword("asdanw");
		br.setSalt("asd");
		br.setRoleId(2);
		br.setStatusId(1);
		br.setBrokerFirmId(11);
		
		System.out.println(dc.updateBroker(41, br).getStatus());
		
	 
		//System.out.println(dc.selectBrokersAllbyFirm(11).toString());		
//		Logger log = new Logger();
//		log.logDatabaseActivity("dkarmazi", "Select * From Users");
	}
	
	
	
	
	

}