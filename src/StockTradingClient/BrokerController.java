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
import javafx.scene.control.*;

import StockTradingCommon.Enumeration;
import StockTradingServer.*;
/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class BrokerController implements Initializable {

    @FXML private TextField BrokerFirstName;
    @FXML private TextField BrokerLastName;
    @FXML private TextField BrokerSSN;
    @FXML private TextField Email;
    @FXML private TextField Password1; 
    @FXML private TextField Password2; 
    
    @FXML private ChoiceBox<KeyValuePair> StatusChoiceBox = new ChoiceBox<>();  
    @FXML private ComboBox<KeyValuePair> brokerageFirmComboBox = new ComboBox<>();   
    @FXML private ListView<KeyValuePair> BrokersListView = new ListView<>();
    
    @FXML private Label Message;
    @FXML private Label Password;
    
    @FXML private Button btnAdd;
    @FXML private Button btnSave;
    @FXML private Button btnClear;
    
    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        
        Password1.visibleProperty().setValue(true);
        Password2.visibleProperty().setValue(true);
        Password.visibleProperty().setValue(true);        

        clearScreen();
        
        SetScreenModeAddNew();
    }
    
    private void clearScreen()
    {
        BrokerFirstName.clear();  
        BrokerLastName.clear();  
        BrokerSSN.clear();  
        Email.clear();  
        Password1.clear();
        Password2.clear();
        
        Message.setText(null);
    }
    
    @FXML 
    private void handleSaveButtonAction(ActionEvent event) 
    {        
        KeyValuePair keyValue =    BrokersListView.getSelectionModel().getSelectedItem();
        
        User broker = new User();
        if (keyValue.getKey() == null)
        {
            Message.setText("Cannot find the Broker ID.");
            return;
        }
        broker.setId(Integer.parseInt(keyValue.getKey()));        
        broker.setFirstName(BrokerFirstName.getText());
        broker.setLastName(BrokerLastName.getText());
        broker.setSsn(BrokerSSN.getText());
        broker.setEmail(Email.getText());
        broker.setPassword(Password1.getText());
        broker.setStatusId(Integer.parseInt(StatusChoiceBox.getValue().getKey()));
        broker.setRoleId(Enumeration.UserRole.USER_ROLE_BROKER);
        if(brokerageFirmComboBox.getValue().getKey() != null)
        {            
            broker.setBrokerFirmId(Integer.parseInt(brokerageFirmComboBox.getValue().getKey()));
        } 
        
        Message.setText(Enumeration.Database.DB_REQUEST_INITIATED);
        Validator validator = Utility.UpdateBroker(broker);
        if (validator.isVerified())
        {
            PopulateBrokers();
            Message.setText(Enumeration.Database.DB_UPDATE_SUCCESS);
        }
        else
        {
            Message.setText(validator.getStatus());
        } 
    }
    
    @FXML 
    private void handleAddButtonAction(ActionEvent event) 
    {
        if (!Password1.getText().equals(Password2.getText()))
        {
            Message.setText("Password confirmation does not match.");
            return;
        }

        User broker = new User();
        
        broker.setFirstName(BrokerFirstName.getText());
        broker.setLastName(BrokerLastName.getText());
        broker.setSsn(BrokerSSN.getText());
        broker.setEmail(Email.getText());
        broker.setPassword(Password1.getText());
        broker.setStatusId(Integer.parseInt(StatusChoiceBox.getValue().getKey()));
        broker.setRoleId(Enumeration.UserRole.USER_ROLE_BROKER);
        if(brokerageFirmComboBox.getValue().getKey() != null)
        {            
            broker.setBrokerFirmId(Integer.parseInt(brokerageFirmComboBox.getValue().getKey()));
        } 
        Message.setText(Enumeration.Database.DB_REQUEST_INITIATED);
        Validator validator = Utility.AddBroker(broker);
        if (validator.isVerified())
        {
            PopulateBrokers();
            Message.setText(Enumeration.Database.DB_INSERT_SUCCESS);
        }
        else
        {
            Message.setText(validator.getStatus());
        }         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utility.PopulateStatus(StatusChoiceBox);
        Utility.PopulateBrokerageFirms(brokerageFirmComboBox);
        PopulateBrokers();
        
        SetScreenModeAddNew();
    } 
    
    @FXML
    public void ShowDetails()
    {
        clearScreen();
        
        if (BrokersListView.getItems().isEmpty() || BrokersListView.getSelectionModel().getSelectedItem() == null)
        {
            return;
        }
        
        // Hide passwords
        Password1.visibleProperty().setValue(false);
        Password2.visibleProperty().setValue(false);
        Password.visibleProperty().setValue(false);
        
        KeyValuePair keyValue = BrokersListView.getSelectionModel().getSelectedItem();       
       
        User broker = Utility.GetBrokerInfo(Integer.parseInt( keyValue.getKey()));
        
        BrokerFirstName.setText(broker.getFirstName());
        BrokerLastName.setText(broker.getLastName());
        Email.setText(broker.getEmail());
        BrokerSSN.setText(broker.getSsn());
        
        StatusChoiceBox.getSelectionModel().select(broker.getStatusId());
        
        Message.setText(null);
        
        SetScreenModeEdit();        
    }
    
    @FXML
    public void handleShowBrokers(ActionEvent event)
    {
        PopulateBrokers();
    }
    

    public void PopulateBrokers()
    {
        clearScreen();
        KeyValuePair keyValue =    brokerageFirmComboBox.getSelectionModel().getSelectedItem();
        
        if (keyValue.getKey() == null)
        {
            Utility.PopulateBrokers( 0, BrokersListView);            
        }
        else
        {
            Utility.PopulateBrokers( Integer.parseInt(keyValue.getKey()), BrokersListView);
        }
        SetScreenModeAddNew();
    }

    private void SetScreenModeAddNew()
    {
        btnAdd.disableProperty().set(false);    // Add Enabled
        btnSave.disableProperty().set(true);    // Save Disabled
        
        StatusChoiceBox.getSelectionModel().selectFirst();
        BrokersListView.getSelectionModel().select(null);
    }
    
    private void SetScreenModeEdit()
    {
        btnAdd.disableProperty().set(true);         // Add Disabled
        btnSave.disableProperty().set(false);       // Save Enabled
    }
        
}
