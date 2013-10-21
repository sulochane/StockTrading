/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package StockTradingClient;

import javafx.scene.control.*;
import StockTradingServer.*;
import java.util.ArrayList;
/**
 * @date    :   Oct 21, 2013
 * @author  :   Hirosh Wickramasuriya
 */
public class Utility 
{
    public void PopulateStatus(ChoiceBox choiceBox)
    {
        StockTradingServer.DatabaseConnector dbConnector = new StockTradingServer.DatabaseConnector();
        dbConnector.connectToDatabase();        
        
        ArrayList<StatusesOptions> statuses = dbConnector.selectAllStatuses();        
        
        choiceBox.getItems().add(new KeyValuePair(null, "Select Status"));

        for(StatusesOptions s : statuses)
        {
            choiceBox.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getName() ));
        }
        choiceBox.getSelectionModel().selectFirst();
    }
    
    public void PopulateCustomers(ComboBox comboBox)
    {
        StockTradingServer.DatabaseConnector dbConnector = new StockTradingServer.DatabaseConnector();
        dbConnector.connectToDatabase();        
        
        ArrayList<StatusesOptions> statuses = dbConnector.selectAllStatuses();        
        
        comboBox.getItems().add(new KeyValuePair(null, "Select Status"));

        for(StatusesOptions s : statuses)
        {
            comboBox.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getName() ));
        }
        comboBox.getSelectionModel().selectFirst();
    }
    
    public void PopulateStocks(ComboBox comboBox)
    {
        StockTradingServer.DatabaseConnector dbConnector = new StockTradingServer.DatabaseConnector();
        dbConnector.connectToDatabase();        
        
        ArrayList<StatusesOptions> statuses = dbConnector.selectAllStatuses();        
        
        comboBox.getItems().add(new KeyValuePair(null, "Select Status"));

        for(StatusesOptions s : statuses)
        {
            comboBox.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getName() ));
        }
        comboBox.getSelectionModel().selectFirst();
    }
    
}
