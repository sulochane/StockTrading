/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

import StockTradingCommon.Enumeration;
import StockTradingServer.StatusesOptions;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import StockTradingCommon.Enumeration;

/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class BrokerController implements Initializable {

    @FXML private TextField BrokerName;
    @FXML private TextField BrokerSSN;
    @FXML private TextField AddressStreet;
    @FXML private TextField AddressCity;
    @FXML private TextField AddressState;
    @FXML private TextField AddressZip;  
    @FXML private ChoiceBox StatusChoiceBox;    
    @FXML private ComboBox brokerageFirmComboBox;   
    @FXML private Label Message;
    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        
        BrokerName.clear();
        BrokerSSN.clear();
        AddressStreet.clear();
        AddressState.clear();
        AddressCity.clear();
        AddressZip.clear();       
        
        Message.setText(null);
        
        StatusChoiceBox.getSelectionModel().selectFirst();
        brokerageFirmComboBox.getSelectionModel().select(null);
    }
    
    @FXML 
    private void handleAddButtonAction(ActionEvent event) 
    {
        StockTradingServer.DatabaseConnector dbConnector = new StockTradingServer.DatabaseConnector();
        dbConnector.connectToDatabase();
        
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
        PopulateBrokerageFirm();
    } 
    
    private void PopulateStatus()
    {
        Utility utility = new Utility();
        utility.PopulateStatus(StatusChoiceBox);        
    }  
    
    private void PopulateBrokerageFirm()
    {
        ArrayList<String> brokerFrims = new ArrayList();
        brokerFrims.add("Fidelity Brokers");
        brokerFrims.add("Interactive Brokers");
        brokerFrims.add("Charles Schwab Brokerage");
        
        brokerageFirmComboBox.setItems(FXCollections.observableArrayList(brokerFrims));
    }
     
    
}
