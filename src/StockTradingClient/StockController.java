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


/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class StockController implements Initializable {


    @FXML private TextField StockCompany;
    @FXML private TextField StockName;
    @FXML private TextField Price;
    @FXML private TextField Quantity;
    @FXML private ChoiceBox<KeyValuePair> StatusChoiceBox = new ChoiceBox<KeyValuePair>();
    @FXML private Label Message;    
    @FXML 
    private void handleAddButtonAction(ActionEvent event) {
            

        //TODO
        
        /*StockTradingServer.DatabaseConnector dbConnector = new StockTradingServer.DatabaseConnector();
        dbConnector.connectToDatabase();
        boolean status = dbConnector.insertNewBrokerageFirm(stockFirm);
        if (status)
        {
            System.out.println("Insert success!");
        }
        else
        {
            System.out.println("Insert Failed!");
        }*/
        
    }
        
    @FXML
    private void handleClearButtonAction(ActionEvent event) 
    {
        StockCompany.clear();
        StockName.clear();
        Price.clear();
        Quantity.clear();
        
        Message.setText(null);
        
        StatusChoiceBox.getSelectionModel().selectFirst();
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
