/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingServer;


public class ServerTest {
    public static void main(String[] args) 
    {
        System.out.println("This is the Stock Trading Systems - Server");
        
        StockTradingClient.ClientTest client = new StockTradingClient.ClientTest();
        client.Test();
        StockTradingCommon.CommonTest common = new StockTradingCommon.CommonTest();
        common.Test();
    }
}
