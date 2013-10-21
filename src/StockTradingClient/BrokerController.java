/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

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
    private ChoiceBox StatusChoiceBox;
    
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
    
    private void PopulateBrokerageFirm()
    {
        ArrayList<String> brokerFrims = new ArrayList();
        brokerFrims.add("Fidelity Brokers");
        brokerFrims.add("Interactive Brokers");
        brokerFrims.add("Charles Schwab Brokerage");
        
        brokerageFirmComboBox.setItems(FXCollections.observableArrayList(brokerFrims));
    }
     
    
}
