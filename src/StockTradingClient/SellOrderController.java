/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class SellOrderController implements Initializable {

    @FXML private TextField CustomerSSN;
    @FXML private TextField AddressStreet;
    @FXML private TextField AddressCity;
    @FXML private TextField AddressState;
    @FXML private TextField AddressZip;  
    @FXML private TextField Email;  
    @FXML private TextField PhoneMobile;  
    @FXML private TextField PhoneOther;  
    @FXML private TextField SettlementDate;  
    @FXML private TextField StockAskPrice;  
    @FXML private TextField StockQuantity;  
    
    @FXML private ComboBox CustomerName;  
    @FXML private ComboBox StockNames;  
    
    @FXML private Label Message;
    
    @FXML
    private void handleClearButtonAction(ActionEvent event) 
    {
        CustomerSSN.clear();
        AddressStreet.clear();
        AddressCity.clear();
        AddressState.clear();
        AddressZip.clear();  
        Email.clear();  
        PhoneMobile.clear();  
        PhoneOther.clear();  
        SettlementDate.clear();
        StockAskPrice.clear(); 
        StockQuantity.clear(); 
        
        Message.setText(null);
        
        CustomerName.getSelectionModel().selectFirst();
        StockNames.getSelectionModel().selectFirst();
    }
    
    @FXML 
    private void handleAddButtonAction(ActionEvent event) 
    {
        //TODO
        
        /*StockTradingServer.BrokerageFirm brokerageFirm = new StockTradingServer.BrokerageFirm();
        
        boolean status = dbConnector.insertNewBrokerageFirm(brokerageFirm);
        if (status)
        {
            Message.setText(Enumeration.Database.DB_INSERT_SUCCESS);
        }
        else
        {
            Message.setText(Enumeration.Database.DB_INSERT_SUCCESS);
        }*/
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopulateCustomers();
        PopulateStocks();
    }
    
    private void PopulateCustomers()
    {
        Utility utility = new Utility();
        utility.PopulateCustomers(CustomerName);        
    }  
    
    private void PopulateStocks()
    {
        Utility utility = new Utility();
        utility.PopulateStocks(StockNames);        
    }  
        
}
