/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import StockTradingServer.*;

import javafx.collections.ObservableList;
/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class BrokerFirmController implements Initializable {

    
    @FXML
    private TextField BrokerageFirmName;
    
    @FXML
    private TextField BrokerageFirmLicenseNumber;
    
    @FXML
    private TextField AddressStreet;
    
    
    @FXML
    private TextField AddressState;
        
    @FXML
    private TextField AddressCity;
            
    @FXML
    private TextField AddressZip;
                
    @FXML 
    private ChoiceBox<KeyValuePair> StatusChoiceBox = new ChoiceBox<KeyValuePair>();
    
    @FXML
    private ComboBox StatusComboBox;
    
    @FXML 
    private void handleAddButtonAction(ActionEvent event) {
        System.out.println("You clicked Add!");
        System.out.println("Broker Name    :" + BrokerageFirmName.getText());
        System.out.println("License Number :" + BrokerageFirmLicenseNumber.getText());
        System.out.println("Address Street :" + BrokerageFirmLicenseNumber.getText());
        System.out.println("Address City :" + AddressCity.getText());
        System.out.println("Address State :" + AddressState.getText());
        System.out.println("Address Zip         :" + AddressZip.getText());        

        StockTradingServer.BrokerageFirm brokerageFirm = new StockTradingServer.BrokerageFirm();
        brokerageFirm.setName(BrokerageFirmName.getText());
        brokerageFirm.setAddressStreet(AddressState.getText());
        brokerageFirm.setAddressCity(AddressCity.getText());
        brokerageFirm.setAddressState(AddressState.getText());
        brokerageFirm.setAddressZip(AddressZip.getText());
        brokerageFirm.setStatus(StatusChoiceBox.getValue().getKey());
        
        StockTradingServer.DatabaseConnector dbConnector = new StockTradingServer.DatabaseConnector();
        dbConnector.connectToDatabase();
        boolean status = dbConnector.insertNewBrokerageFirm(brokerageFirm);
        if (status)
        {
            System.out.println("Insert success!");
        }
        else
        {
            System.out.println("Insert Failed!");
        }
        
    }
        
    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        System.out.println("You clicked clear!");
        BrokerageFirmName.setText(null);
        BrokerageFirmLicenseNumber.setText(null);
        StatusChoiceBox.valueProperty().unbind();
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopulateStatus();
    }    
    
   
    private void PopulateStatus()
    {
        StockTradingServer.DatabaseConnector dbConnector = new StockTradingServer.DatabaseConnector();
        dbConnector.connectToDatabase();        
        
        ArrayList<StatusesOptions> statuses = dbConnector.selectAllStatuses();        
        
        StatusChoiceBox.getItems().add(new KeyValuePair(null, "Select Status"));

        for(StatusesOptions s : statuses)
        {
            StatusChoiceBox.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getName() ));
        }
        StatusChoiceBox.getSelectionModel().selectFirst();
        
    }   
}
