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
import StockTradingServer.CustomerInfo;
import StockTradingServer.User;
import StockTradingServer.Validator;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class CustomerController implements Initializable {

    @FXML private TextField CustomerFirstName;
    @FXML private TextField CustomerLastName;
    @FXML private TextField CustomerSSN;
    @FXML private TextField AddressStreet;
    @FXML private TextField AddressCity;
    @FXML private TextField AddressState;
    @FXML private TextField AddressZip;  
    @FXML private TextField Email; 
    @FXML private TextField PhoneMobile; 
    @FXML private TextField PhoneOther;    
    @FXML private ChoiceBox<KeyValuePair> StatusChoiceBox = new ChoiceBox<>();  
    //@FXML private ComboBox<KeyValuePair> brokerageFirmComboBox = new ComboBox<>();   
    @FXML private ListView<KeyValuePair> CustomerListView = new ListView<>();
    
    @FXML private Label Message;
    
    @FXML private Button btnAdd;
    @FXML private Button btnSave;
    @FXML private Button btnClear;
    
    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        
        
        ClearScreen();
        SetScreenModeAddNew();
        
    }
    
    @FXML 
    private void handleAddButtonAction(ActionEvent event) 
    {
        
        CustomerInfo customer = new CustomerInfo();
        
        customer.setFirstName(CustomerFirstName.getText());
        customer.setLastName(CustomerLastName.getText());
        customer.setEmail(Email.getText());
        customer.setPhone(PhoneMobile.getText());        
        //TODO
        // ssn
        // phone other
        // address : street, city, state, zip

        if(StatusChoiceBox.getValue().getKey() != null)
        {
            customer.setStatusId( Integer.parseInt(StatusChoiceBox.getValue().getKey()));
        }
        
        Message.setText(Enumeration.Database.DB_REQUEST_INITIATED);
        Validator validator = Utility.AddCustomer(customer);       
        
        if (validator.isVerified())
        {
            Utility.PopulateCustomers(CustomerListView, Utility.getCurrentUser_BrokerageFirmID());
            Message.setText(Enumeration.Database.DB_INSERT_SUCCESS);
        }
        else
        {
            Message.setText(validator.getStatus());
        }    
        
    }
    
    @FXML 
    private void handleSaveButtonAction(ActionEvent event) 
    {
        KeyValuePair keyValue = CustomerListView.getSelectionModel().getSelectedItem();
        
        if (keyValue.getKey() == null)
        {
            Message.setText("Cannot find the Customer ID.");
            return;
        }
        
        CustomerInfo customer = new CustomerInfo();
        
        customer.setId(Integer.parseInt(keyValue.getKey()));
        customer.setFirstName(CustomerFirstName.getText());
        customer.setLastName(CustomerLastName.getText());
        customer.setEmail(Email.getText());
        customer.setPhone(PhoneMobile.getText());        
        //TODO
        // ssn
        // phone other
        // address : street, city, state, zip

        if(StatusChoiceBox.getValue().getKey() != null)
        {
            customer.setStatusId( Integer.parseInt(StatusChoiceBox.getValue().getKey()));
        }
        
        Message.setText(Enumeration.Database.DB_REQUEST_INITIATED);
        Validator validator = Utility.UpdateCustomer(customer);       
        
        if (validator.isVerified())
        {
            Utility.PopulateCustomers(CustomerListView, Utility.getCurrentUser_BrokerageFirmID());
            Message.setText(Enumeration.Database.DB_UPDATE_SUCCESS);
        }
        else
        {
            Message.setText(validator.getStatus());
        }  
        CustomerListView.getSelectionModel().select(keyValue);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Utility.PopulateStatus(StatusChoiceBox);
        Utility.PopulateCustomers(CustomerListView, Utility.getCurrentUser_BrokerageFirmID());
        //PopulateBrokers();
        
        SetScreenModeAddNew();
    }
    @FXML
    public void ShowDetails()
    {
        ClearScreen();
        
        if (CustomerListView.getItems().isEmpty() || CustomerListView.getSelectionModel().getSelectedItem() == null)
        {
            return;
        }       
       
        KeyValuePair keyValue = CustomerListView.getSelectionModel().getSelectedItem();       
       
        CustomerInfo customer = Utility.GetCustomerInfo(Integer.parseInt( keyValue.getKey()));
        
        CustomerFirstName.setText(customer.getFirstName());
        CustomerLastName.setText(customer.getLastName());
//        CustomerSSN.setText();
//        AddressStreet.setText();
//        AddressState.setText();
//        AddressCity.setText();
//        AddressZip.setText();       
        Email.setText(customer.getEmail());  
        PhoneMobile.setText(customer.getPhone());  
//        PhoneOther.setText();  
        
        StatusChoiceBox.getSelectionModel().select(customer.getStatusId());
        
        Message.setText(null);
        
        SetScreenModeEdit();        
    }
    private void ClearScreen()
    {
        CustomerFirstName.clear();
        CustomerLastName.clear();
        CustomerSSN.clear();
        AddressStreet.clear();
        AddressState.clear();
        AddressCity.clear();
        AddressZip.clear();       
        Email.clear();  
        PhoneMobile.clear();  
        PhoneOther.clear();  
        
        Message.setText(null);

    }
    
    private void SetScreenModeAddNew()
    {
        btnAdd.disableProperty().set(false);    // Add Enabled
        btnSave.disableProperty().set(true);    // Save Disabled
        
        StatusChoiceBox.getSelectionModel().selectFirst();
        CustomerListView.getSelectionModel().select(null);
    }
    
    private void SetScreenModeEdit()
    {
        btnAdd.disableProperty().set(true);         // Add Disabled
        btnSave.disableProperty().set(false);       // Save Enabled
    }
}
