/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Sulochane
 */
public class MainController implements Initializable {
    
    @FXML
    private void handleButtonAction_btnAddBrokerageFirm(ActionEvent event) throws IOException{


        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
            MainController.class.getResource("BrokerFirm.fxml"));

        stage.setScene(new Scene(root));
        stage.setTitle("Brokerage Firms");
        stage.initModality(Modality.NONE);
        stage.initOwner(  ((Node)event.getSource()).getScene().getWindow() );
        stage.show();

    }
    
    @FXML
    private void handleButtonAction_btnAddBroker(ActionEvent event) throws IOException{
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
            MainController.class.getResource("Broker.fxml"));
            //MainController.class.getResource("../StockTradingClient.MessageBox/Info.fxml"));
        
        stage.setScene(new Scene(root));
        stage.setTitle("Brokers");
        stage.initModality(Modality.NONE);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.initOwner(  ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
    
    @FXML
    private void handleButtonAction_btnAddStock(ActionEvent event) throws IOException{
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
            MainController.class.getResource("Stock.fxml"));
 
        stage.setScene(new Scene(root));
        stage.setTitle("Stocks");
        stage.initModality(Modality.NONE);
        stage.initOwner(  ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
    
    @FXML
    private void handleButtonAction_btnAddCustomer(ActionEvent event) throws IOException{
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
            MainController.class.getResource("Customer.fxml"));
 
        stage.setScene(new Scene(root));
        stage.setTitle("Customers");
        stage.initModality(Modality.NONE);
        stage.initOwner(  ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
        
    @FXML
    private void handleButtonAction_btnAddSellOrder(ActionEvent event) throws IOException{
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
            MainController.class.getResource("SellOrder.fxml"));
 
        stage.setScene(new Scene(root));
        stage.setTitle("Selling Orders");
        stage.initModality(Modality.NONE);
        stage.initOwner(  ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
            
    @FXML
    private void handleButtonAction_btnAddBuyOrder(ActionEvent event) throws IOException{
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
            MainController.class.getResource("BuyOrder.fxml"));
 
        stage.setScene(new Scene(root));
        stage.setTitle("Buying Orders");
        stage.initModality(Modality.NONE);
        stage.initOwner(  ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
