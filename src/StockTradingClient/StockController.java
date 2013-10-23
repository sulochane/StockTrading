/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

import StockTradingCommon.Enumeration;
import StockTradingServer.User;
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
public class StockController implements Initializable {


    @FXML private TextField StockName;
    @FXML private TextField Price;
    @FXML private TextField Quantity;
    @FXML private ChoiceBox<KeyValuePair> StatusChoiceBox = new ChoiceBox<>();
    @FXML private ListView<KeyValuePair> StocksListView = new ListView<>();        
    @FXML private Label Message;  
    
    @FXML private Button btnAdd;
    @FXML private Button btnSave;
    @FXML private Button btnClear;
    
    @FXML 
    private void handleAddButtonAction(ActionEvent event) {            

        Stock stock = new Stock();
        
        stock.setName(StockName.getText());       
        
        if (Quantity.getText().isEmpty())
        {
            Quantity.setText("0");
        }    
        if (Price.getText().isEmpty())
        {
             Price.setText("0");
        }  
        
        try
        {
            stock.setAmount( Integer.parseInt(Quantity.getText()));
        }     
        catch(NumberFormatException ex)
        {            
            Message.setText("Erro in price " + System.lineSeparator() + Enumeration.InputValidation.INPUT_VALIDATION_INVAILD_DOUBLE_FORMAT);
            return;
        }
        
        try
        {
            stock.setPrice(  Double.parseDouble(Price.getText()));
        }     
        catch(NumberFormatException ex)
        {            
            Message.setText("Erro in price " + System.lineSeparator() + Enumeration.InputValidation.INPUT_VALIDATION_INVAILD_DOUBLE_FORMAT);
            return;
        }
        stock.setStatusId(Integer.parseInt(StatusChoiceBox.getValue().getKey()));   
        
        Message.setText(Enumeration.Database.DB_REQUEST_INITIATED);
        Validator validator = Utility.AddStock(stock);
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
    private void handleSaveButtonAction(ActionEvent event) {            

        KeyValuePair keyValue =    StocksListView.getSelectionModel().getSelectedItem();
        
        
        if (keyValue.getKey() == null)
        {
            Message.setText("Cannot find the Stock ID.");
            return;
        }
        
        Stock stock = new Stock();
        stock.setId(Integer.parseInt(keyValue.getKey()));
        stock.setName(StockName.getText());       
        
        if (Quantity.getText().isEmpty())
        {
            Quantity.setText("0");
        }    
        if (Price.getText().isEmpty())
        {
             Price.setText("0");
        }  
        
        try
        {
            stock.setAmount( Integer.parseInt(Quantity.getText()));
        }     
        catch(NumberFormatException ex)
        {            
            Message.setText("Erro in price " + System.lineSeparator() + Enumeration.InputValidation.INPUT_VALIDATION_INVAILD_DOUBLE_FORMAT);
            return;
        }
        
        try
        {
            stock.setPrice(  Double.parseDouble(Price.getText()));
        }     
        catch(NumberFormatException ex)
        {            
            Message.setText("Erro in price " + System.lineSeparator() + Enumeration.InputValidation.INPUT_VALIDATION_INVAILD_DOUBLE_FORMAT);
            return;
        }    
        Message.setText(Enumeration.Database.DB_REQUEST_INITIATED);
        Validator validator = Utility.UpdateStock(stock);
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
    private void handleClearButtonAction(ActionEvent event) 
    {       
        StockName.clear();
        Price.clear();
        Quantity.clear();
        
        Message.setText(null);        
        
        SetScreenModeAddNew();
    }   
    
    @FXML
    private void ShowDetails()
    {
        if (StocksListView.getItems().isEmpty() || StocksListView.getSelectionModel().getSelectedItem() == null)
        {
            return;
        }
        KeyValuePair keyValue = StocksListView.getSelectionModel().getSelectedItem();       
       
        Stock stock = Utility.GetStockInfo(Integer.parseInt( keyValue.getKey()));
        
        StockName.setText(stock.getName());
        Quantity.setText(Integer.toString(stock.getAmount()));
        Price.setText(Utility.FormatNumber(stock.getPrice()));       
               
        StatusChoiceBox.getSelectionModel().select(stock.getStatusId());
        
        Message.setText(null);
        
        SetScreenModeEdit(); 
    }
                
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopulateStatus();       
        PopulateStocks();
    }    
    
    private void PopulateStatus()
    {
        Utility.PopulateStatus(StatusChoiceBox);        
    }  
    
    private void PopulateStocks()
    {
        Utility.PopulateStocks(StocksListView);
    }
    
    private void SetScreenModeAddNew()
    {
        btnAdd.disableProperty().set(false);    // Add Enabled
        btnSave.disableProperty().set(true);    // Save Disabled
        
        StatusChoiceBox.getSelectionModel().selectFirst();
        StocksListView.getSelectionModel().select(null);
    }
    
    private void SetScreenModeEdit()
    {
        btnAdd.disableProperty().set(true);    // Add Disabled
        btnSave.disableProperty().set(false);    // Save Enabled
    }    
}
