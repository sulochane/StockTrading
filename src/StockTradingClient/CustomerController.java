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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import StockTradingCommon.Enumeration;

/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class CustomerController implements Initializable {

    @FXML private TextField CustomerName;
    @FXML private TextField CustomerSSN;
    @FXML private TextField AddressStreet;
    @FXML private TextField AddressCity;
    @FXML private TextField AddressState;
    @FXML private TextField AddressZip;  
    @FXML private TextField Email; 
    @FXML private TextField PhoneMobile; 
    @FXML private TextField PhoneOther; 
    @FXML private ChoiceBox StatusChoiceBox;    
    @FXML private Label Message;
    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        
        CustomerName.clear();
        CustomerSSN.clear();
        AddressStreet.clear();
        AddressState.clear();
        AddressCity.clear();
        AddressZip.clear();       
        Email.clear();  
        PhoneMobile.clear();  
        PhoneOther.clear();  
        
        Message.setText(null);
        
        StatusChoiceBox.getSelectionModel().selectFirst();
        
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
        PopulateStatus();
    }
    
    private void PopulateStatus()
    {
        Utility utility = new Utility();
        utility.PopulateStatus(StatusChoiceBox);        
    } 
}
