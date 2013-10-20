/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

import com.sun.javafx.collections.ImmutableObservableList;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.ListBinding;
import javafx.beans.binding.ListExpression;
import javafx.beans.property.ListPropertyBase;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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
    private ChoiceBox statusChoiceBox;
    
    @FXML 
    private void handleAddButtonAction(ActionEvent event) {
        System.out.println("You clicked Add!");
        System.out.println("Broker Name    :" + BrokerageFirmName.getText());
        System.out.println("License Number :" + BrokerageFirmLicenseNumber.getText());
        System.out.println("Status         :" + statusChoiceBox.getValue());
       // StockTradingClient.MessageBox.MessageBox.main(null);
    }
        
    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        System.out.println("You clicked clear!");
        BrokerageFirmName.setText(null);
        BrokerageFirmLicenseNumber.setText(null);
        statusChoiceBox.valueProperty().unbind();
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopulateStatus();
    }    
    
   
    private void PopulateStatus()
    {
        ArrayList<String> status = new ArrayList();
        status.add("Active");
        status.add("Inactive");
        
        statusChoiceBox.setItems(FXCollections.observableArrayList(status));
    }

    
    
}
