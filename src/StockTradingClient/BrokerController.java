/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class BrokerController implements Initializable {

    @FXML
    private ChoiceBox statusChoiceBox;
    
    @FXML
    private ComboBox brokerageFirmComboBox;
    
    @FXML
    public void Clear()
    {
        this.Clear();        
    }
    @FXML
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopulateStatus();
        PopulateBrokerageFirm();
    } 
    
    private void PopulateStatus()
    {
        ArrayList<String> status = new ArrayList();
        status.add("Active");
        status.add("Inactive");
        
        statusChoiceBox.setItems(FXCollections.observableArrayList(status));
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
