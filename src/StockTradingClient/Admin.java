/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StockTradingClient;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.annotation.Resource;

/**
 *
 * @author Sulochane
 */
public class Admin extends Application {
    
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
        
        // TODO Capture the proper user ids
        int userId = 44;
        Utility.setCurrentUser_BrokerID(userId);
        // get the brokerage firm id of the user (Admin will not have a brokerage firm, hence set to -1)
        StockTradingServer.User user = Utility.GetBrokerInfo(userId);
        if (user.getBrokerFirmId() > 0)
        {
            Utility.setCurrentUser_BrokerageFirmID(user.getBrokerFirmId());
        }
        else
        {
            Utility.setCurrentUser_BrokerageFirmID(-1);
        }

        stage = primaryStage;
        
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        //Resource resource = getClass().getResource("Broker.fxml");
        Scene scene = new Scene(root);
        stage.setTitle("Stock Trading Platform");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
