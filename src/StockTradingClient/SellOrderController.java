/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

import StockTradingCommon.Enumeration;
import StockTradingServer.*;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class SellOrderController implements Initializable {               

    
    @FXML private TextField IssueDate;  
    @FXML private TextField ExpirationDate;
    @FXML private TextField StockAskPrice;  
    @FXML private TextField StockQuantity;      

    @FXML private ChoiceBox<KeyValuePair> StatusChoiceBox = new ChoiceBox<>();
    @FXML private ListView<KeyValuePair> SellOrderListView = new ListView<>();  
    @FXML private ComboBox<KeyValuePair> CustomerNameComboBox = new ComboBox<>(); 
    @FXML private ComboBox<KeyValuePair> StockNameComboBox = new ComboBox<>(); 
    
    @FXML private Label Message;
    @FXML private Label Email;
    @FXML private Label CurrentStockPrice;
        
    
    @FXML private Button btnAdd;
    @FXML private Button btnSave;
    @FXML private Button btnClear;
    
    @FXML
    private void handleClearButtonAction(ActionEvent event) 
    {

        Email.setText(null);  

        IssueDate.clear();
        ExpirationDate.clear();
        StockAskPrice.clear(); 
        StockQuantity.clear(); 
        
        CurrentStockPrice.setText(null);      
        Message.setText(null);       
               
        SetScreenModeAddNew();
        
    }
    
    @FXML 
    private void handleAddButtonAction(ActionEvent event) throws Exception
    {
        String errorMessage ="";
        Order order = new Order();
        
        // TODO: Set customer id
        if(CustomerNameComboBox.getValue().getKey() != null)
        {
            order.setCustomerId( Integer.parseInt(CustomerNameComboBox.getValue().getKey()));
        }

        if(StockNameComboBox.getValue().getKey() != null)
        {
            order.setStockId( Integer.parseInt(StockNameComboBox.getValue().getKey()));
        }
        
        order.setAmount(Integer.parseInt( StockQuantity.getText()));
        //TODO :
        order.setPrice(Integer.parseInt( StockAskPrice.getText()));
        
        if (!Utility.isValidDate(IssueDate.getText()))
        {
            errorMessage += "Invalid date format in Settlement Date. It should be in the form of MM-DD-YYYY.";
        }
        
        if (!Utility.isValidDate(ExpirationDate.getText()))
        {
            errorMessage += System.lineSeparator() + "Invalid date format in Expiration Date. It should be in the form of MM-DD-YYYY.";
        }
        
        if (!errorMessage.isEmpty())
        {
            Message.setText(errorMessage);
            return;
        }
        
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        java.util.Date settleDate = null;    
        java.util.Date expiryDate = null;
        try
        {
            settleDate = dateFormat.parse(IssueDate.getText());
            expiryDate = dateFormat.parse(ExpirationDate.getText());
        }
        catch (ParseException ex)
        {
            Message.setText("Invalid Date, Please check the format.");
            return;
        }
        //java.sql.Date sqlSettleDate = new java.sql.Date(settleDate.getTime());
        //java.sql.Date sqlExpiryDate = new java.sql.Date(expiryDate.getTime());
       
        Timestamp sqlSettleDate = new Timestamp(settleDate.getTime());
        Timestamp sqlExpiryDate = new Timestamp(expiryDate.getTime());
        
        order.setDateIssued(sqlSettleDate );        
        order.setDateExpiration(sqlExpiryDate );
        if(StatusChoiceBox.getValue().getKey() != null)
        {
            order.setStatusId( Integer.parseInt(StatusChoiceBox.getValue().getKey()));
        }
        
        Message.setText(Enumeration.Database.DB_REQUEST_INITIATED);
        Validator validator = Utility.AddSellingOrder(order);
        if (validator.isVerified())
        {
            PopulateStocks();
            Message.setText(Enumeration.Database.DB_INSERT_SUCCESS);
        }
        else
        {
            Message.setText(validator.getStatus());
        }         
    }
    
    @FXML
    private void handleSaveButtonAction(ActionEvent event) throws Exception
    {            
        String errorMessage = "";
        KeyValuePair keyValue = SellOrderListView.getSelectionModel().getSelectedItem();        
        
        if (keyValue.getKey() == null)
        {
            Message.setText("Cannot find the Order ID.");
            return;
        }
        
        Order order = new Order();
        
        // TODO: Set customer id
        
        if(CustomerNameComboBox.getValue().getKey() != null)
        {
            order.setCustomerId( Integer.parseInt(CustomerNameComboBox.getValue().getKey()));
        }

        if(StockNameComboBox.getValue().getKey() != null)
        {
            order.setStockId( Integer.parseInt(StockNameComboBox.getValue().getKey()));
        }
        
        order.setAmount(Integer.parseInt( StockQuantity.getText()));
        order.setPrice(Integer.parseInt( StockAskPrice.getText()));
        
        if (!Utility.isValidDate(IssueDate.getText()))
        {
            errorMessage += "Invalid date format in Settlement Date.";
        }
        
        if (!Utility.isValidDate(ExpirationDate.getText()))
        {
            errorMessage += System.lineSeparator() + "Invalid date format in Expiration Date." ;
        }
        
        if (!errorMessage.isEmpty())
        {
            System.out.println(errorMessage);
            Message.setText(errorMessage);
            return;
        }
        
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        java.util.Date settleDate = null;    
        java.util.Date expiryDate = null;
        try
        {
            settleDate = dateFormat.parse(IssueDate.getText());
            expiryDate = dateFormat.parse(ExpirationDate.getText());
        }
        catch (ParseException ex)
        {
            Message.setText("Invalid Date, Please check the format.");
            return;
        }
        //java.sql.Date sqlSettleDate = new java.sql.Date(settleDate.getTime());
        //java.sql.Date sqlExpiryDate = new java.sql.Date(expiryDate.getTime());
       
        Timestamp sqlSettleDate = new Timestamp(settleDate.getTime());
        Timestamp sqlExpiryDate = new Timestamp(expiryDate.getTime());
        
        order.setDateIssued(sqlSettleDate );        
        order.setDateExpiration(sqlExpiryDate );
        if(StatusChoiceBox.getValue().getKey() != null)
        {
            order.setStatusId( Integer.parseInt(StatusChoiceBox.getValue().getKey()));
        }
        
        Message.setText(Enumeration.Database.DB_REQUEST_INITIATED);
        Validator validator = Utility.UpdateSellingOrder(order);
        if (validator.isVerified())
        {
            PopulateStocks();
            Message.setText(Enumeration.Database.DB_UPDATE_SUCCESS);
        }
        else
        {
            Message.setText(validator.getStatus());
        }  
        
    }
    
    @FXML
    private void handleShowCustomerInfo()
    {
        if(
                (CustomerNameComboBox.getValue().getKey() != null) &&
                (!CustomerNameComboBox.getValue().getKey().equals("-1") )
          )
        {
            int customerId = Integer.parseInt(CustomerNameComboBox.getValue().getKey());
            CustomerInfo customer = Utility.GetCustomerInfo(customerId);
            Email.setText(customer.getEmail());
        }
        else
        {
            Email.setText(null);
        }        
    }
    
    @FXML
    private void handleShowStockInfo()
    {
        if( 
                (StockNameComboBox.getValue().getKey() != null  ) &&
                (!StockNameComboBox.getValue().getKey().equals( "-1" ) )
          )      
        {
            int stockId = Integer.parseInt(StockNameComboBox.getValue().getKey());
            Stock stock = Utility.GetStockInfo(stockId);
           
            CurrentStockPrice.setText(Utility.FormatNumber(stock.getPrice()));
        }
        else
        {
            CurrentStockPrice.setText(null);
        }  
    }
    
    @FXML
    public void ShowDetails()
    {
        if (SellOrderListView.getItems().isEmpty() || SellOrderListView.getSelectionModel().getSelectedItem() == null)
        {
            return;
        }
        if (SellOrderListView.getSelectionModel().getSelectedItem() == null)
        {
            return;
        }
        KeyValuePair keyValue = SellOrderListView.getSelectionModel().getSelectedItem();       
       
        Order order = Utility.GetSellingOrder(Integer.parseInt( keyValue.getKey()));
        
        StockQuantity.setText(Integer.toString( order.getAmount()));
        //TODO
        //StockBidPrice.setText(order.getPrice());        
                
        ExpirationDate.setText(new SimpleDateFormat("MM-dd-yyyy").format(order.getDateExpiration()));
        IssueDate.setText(new SimpleDateFormat("MM-dd-yyyy").format(order.getDateIssued())); 
        
        //TODO:
        //CustomerNameComboBox.getSelectionModel().select(order.getCustomerId());
        
        //TODO
        //CustomerInfo customer = Utility.GetCustomerInfo(order.getCustomerId() );
        //Email.setText(customer.getEmail());        
        
        StockNameComboBox.getSelectionModel().select(order.getStockId());
        StatusChoiceBox.getSelectionModel().select(order.getStatusId());
        
        Message.setText(null);
        
        SetScreenModeEdit();        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopulateCustomers();
        PopulateStocks();
        PopulateStatus();
        SetScreenModeAddNew();              
    }
    
    private void PopulateStatus()
    {
        Utility.PopulateStatus(StatusChoiceBox);        
    }   
    private void PopulateCustomers()
    {        
        Utility.PopulateCustomers(CustomerNameComboBox, Utility.getBrokerageFirmID());        
    }      
    private void PopulateStocks()
    {
        Utility.PopulateStocks(StockNameComboBox);        
    } 
    private void PopulateSellOrders()
    {
        // TODO
        //SellOrderListView
    }
    
    private void SetScreenModeAddNew()
    {
        btnAdd.disableProperty().set(false);    // Add Enabled
        btnSave.disableProperty().set(true);    // Save Disabled
        
        StatusChoiceBox.getSelectionModel().selectFirst();
        if (SellOrderListView.getItems().size()>0)
        {
            SellOrderListView.getSelectionModel().select(null);
        }
        
        CustomerNameComboBox.getSelectionModel().selectFirst();
        StockNameComboBox.getSelectionModel().selectFirst();
    }
    
    private void SetScreenModeEdit()
    {
        btnAdd.disableProperty().set(true);    // Add Disabled
        btnSave.disableProperty().set(false);    // Save Enabled
    }    
}
