/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package StockTradingClient;

import StockTradingCommon.Enumeration;
import StockTradingServer.*;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.value.ObservableValueBase;

import javafx.scene.control.*;

/**
 * @date    :   Oct 21, 2013
 * @author  :   Hirosh Wickramasuriya
 */
public class  Utility 
{
    private static DatabaseConnector dbConnector = new DatabaseConnector();
    private static int currentUser_BrokerageFirmID = -1;     // when a broker logs in his Brokerage Firm id should be here
    private static int currentUser_BrokerID = -1;            // when a broker logs in his id should be here

    public static int getCurrentUser_BrokerID() {
        return currentUser_BrokerID;
    }

    public static void setCurrentUser_BrokerID(int brokerID) {
        Utility.currentUser_BrokerID = brokerID;
    }
    public static int getCurrentUser_BrokerageFirmID() {
        return currentUser_BrokerageFirmID;
    }

    public static void setCurrentUser_BrokerageFirmID(int brokerageFirmID) {
        Utility.currentUser_BrokerageFirmID = brokerageFirmID;
    }
    
    public  Utility()
    {
        //dbConnector = new StockTradingServer.DatabaseConnector();
    }
    
    public static boolean isValidDate(String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        boolean isValid = false;
        Date testDate = null;
        try
        {
            testDate = simpleDateFormat.parse(date);
            if (!simpleDateFormat.format(testDate).equals(date)) 
            {
                isValid = false;
            }
            else 
            {
                isValid = true;
            }            
        }
        catch (ParseException ex)
        {
             isValid = false;
        }
        finally
        {
            return isValid;
        } 
    }    
    public static String FormatNumber(String number)
    {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(number);
    }
    public static String FormatNumber(int number)
    {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(number);
    }
    public static String FormatNumber(double number)
    {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(number);
    }
        
    public static void PopulateStatus(ChoiceBox choiceBox)
    {
        StockTradingServer.DatabaseConnector dbConnector = new StockTradingServer.DatabaseConnector();     
        
        ArrayList<StatusesOptions> statuses = dbConnector.selectAllStatuses();        
        choiceBox.getItems().clear();
        choiceBox.getItems().add(new KeyValuePair("-1", "Select Status"));

        for(StatusesOptions s : statuses)
        {
            choiceBox.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getName() ));
        }
        choiceBox.getSelectionModel().selectFirst();
    }
    
    public static void SelectKey(ComboBox<KeyValuePair> comboBox, String key)
    {
        for (KeyValuePair item : comboBox.getItems()) 
        {
            if (item.getKey() != null)
            {
                if (item.getKey().equals(key))
                {
                    //comboBox.getSelectionModel().select(item);
                    comboBox.setValue(item);
                    break;
                }
            }
        }
    }

    public static void SelectKey(ComboBox<KeyValuePair> comboBox, int key)
    {
        SelectKey(comboBox, Integer.toString(key));
    }
    
    // Stock
    public static Validator AddStock(Stock stock)
    {
        return dbConnector.insertNewStock(stock);        
    }
    public static Validator UpdateStock(Stock stock)
    {
        return dbConnector.updateStock(stock.getId(), stock);        
    }
    public static void PopulateStocks(ComboBox comboBox)
    {
        comboBox.getItems().clear();
        
        ArrayList<Stock> records = dbConnector.selectStockAll();        
        
        comboBox.getItems().add(new KeyValuePair("-1", "[Select Stock]"));

        for(Stock s : records)
        {
            comboBox.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getName() ));
        }
        comboBox.getSelectionModel().selectFirst();
    }
    
    public static void PopulateStocks(ListView listView)
    {
        listView.getItems().clear();
        ArrayList<Stock> records = dbConnector.selectStockAll();
        for(Stock s : records)
        {
            listView.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getName() ));
        }
        listView.getSelectionModel().selectFirst();
    }
    public static Stock GetStockInfo(int stockId)
    {
        return dbConnector.selectStock(stockId);
    }
    
    // Broker
    public static Validator AddBroker(User broker)
    {
        return dbConnector.insertNewBroker(broker);
    }
    public static Validator UpdateBroker(User broker)
    {
        return dbConnector.updateBroker(broker.getId(), broker);        
    }
    public static void PopulateBrokers(int brokerageFirm, ListView listView)
    {        
        listView.getItems().clear();
        ArrayList<User> records = null;
        if (brokerageFirm ==0)
        {
            int statusID = 0;
            records = dbConnector.selectBrokersAll(statusID)  ;   
        }
        else
        {
            records = dbConnector.selectBrokersAllbyFirm(brokerageFirm);
        }
        for (User s : records)
        {
            listView.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getFirstName() + " " + s.getLastName()));
        }
    } 
    public static User GetBrokerInfo(int brokerId)
    {
        return dbConnector.selectBrokerUser(brokerId);
    }
    
    // Brokerage Firm
    public static BrokerageFirm GetBrokerageFirmInfo(int brokerageFirmId)
    {
        return dbConnector.selectBrokerageFirm(brokerageFirmId);
    }
    public static Validator AddBrokerageFirm(BrokerageFirm brokerageFirm)
    {
        return dbConnector.insertNewBrokerageFirm(brokerageFirm);
        /*Validator validator = brokerageFirm.validate();
        if (validator.isVerified())
        {
            boolean status = dbConnector.insertNewBrokerageFirm(brokerageFirm);   
            validator.setVerified(status);
            if (status)
            {
                validator.setStatus(Enumeration.Database.DB_INSERT_SUCCESS);
            }
            else
            {
                validator.setStatus(Enumeration.Database.DB_INSERT_FAILURE);
            }
        }
        return validator;*/
    }
    public static Validator UpdateBrokerageFirm(BrokerageFirm brokerageFirm)
    {
        return dbConnector.updateBrokerageFirm(brokerageFirm.getId(), brokerageFirm);        
    }    
    public static void PopulateBrokerageFirms(ComboBox comboBox)
    {
        comboBox.getItems().clear();
        ArrayList<BrokerageFirm> records = dbConnector.selectBrokerageFirmsAll();        
        
        comboBox.getItems().add(new KeyValuePair(null, "[All Brokerage Firms]"));

        for(BrokerageFirm s : records)
        {
            comboBox.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getName() ));
        }
        comboBox.getSelectionModel().selectFirst();
    }    
    public static void PopulateBrokerageFirms(ListView listView)
    {
        listView.getItems().clear();
        ArrayList<BrokerageFirm> records = dbConnector.selectBrokerageFirmsAll();        
        for(BrokerageFirm s : records)
        {
            listView.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getName() ));
        }
    }
    
    // Customer
    
    public static CustomerInfo GetCustomerInfo(int customerId)
    {
        return dbConnector.selectCustomerInfo(customerId);
    }    
    public static Validator AddCustomer(CustomerInfo customer)
    {
        customer.setFirmId(getCurrentUser_BrokerageFirmID());
        return dbConnector.insertNewCustomerInfo(customer);
    }
    public static Validator UpdateCustomer(CustomerInfo customer)
    {
        customer.setFirmId(getCurrentUser_BrokerageFirmID());
        return dbConnector.updateCustomerInfo(customer.getId(), customer);        
    }
    public static void PopulateCustomers(ComboBox comboBox)
    {
        ArrayList<CustomerInfo> statuses = dbConnector.selectCustomerInfoAll();        
        comboBox.getItems().clear();
        comboBox.getItems().add(new KeyValuePair("-1", "[Select Customer]"));

        for(CustomerInfo s : statuses)
        {
            comboBox.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getFirstName() + " " + s.getLastName()));
        }
        comboBox.getSelectionModel().selectFirst();
    }
    
    public static void PopulateCustomersOfFirm(ComboBox comboBox)
    {
        ArrayList<CustomerInfo> statuses = dbConnector.selectCustomersByFirm(getCurrentUser_BrokerageFirmID());        
        comboBox.getItems().clear();
        comboBox.getItems().add(new KeyValuePair(null, "[Select Customer]"));
        
        for(CustomerInfo s : statuses)
        {
            comboBox.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getFirstName() + " " + s.getLastName()));
        }
        comboBox.getSelectionModel().selectFirst();
    }
    public static void PopulateCustomers(ListView listView, int brokerageFirmID)
    {
        ArrayList<CustomerInfo> statuses = dbConnector.selectCustomersByFirm(brokerageFirmID);        
        listView.getItems().clear();
        
        for(CustomerInfo s : statuses)
        {
            listView.getItems().add(new KeyValuePair(Integer.toString(s.getId()), s.getFirstName() + " " + s.getLastName()));
        }
    }    
    // Buying Order
    public static Validator AddBuyingOrder(Order order)
    {
        order.setTypeId(Enumeration.OrderType.BUYING_ORDER);
        order.setBrokerId(getCurrentUser_BrokerID());
        return dbConnector.insertNewOrder(order);
    }
    public static Validator UpdateBuyingOrder(Order order)
    {
        order.setTypeId(Enumeration.OrderType.BUYING_ORDER);
        order.setBrokerId(getCurrentUser_BrokerID());
        return dbConnector.updateOrder(order.getOrderId(), order);
    }
    public static Order GetBuyingOrder(int orderID)
    {
        return dbConnector.selectOrder(orderID);
    }
    public static void PopulateBuyingOrdersByBrokerageFirm(ListView listView)
    {
        listView.getItems().clear();
        ArrayList<Order> records = dbConnector.selectOrdersByFirmByType(
                                                    getCurrentUser_BrokerageFirmID()
                                                    , Enumeration.OrderType.BUYING_ORDER);            
        for(Order s : records)
        {
            listView.getItems().add(    new KeyValuePair(
                                                    Integer.toString(s.getOrderId())
                                                    , s.getDisplaySummary()  )
                                    );
        }
    }
    public static void PopulateBuyingOrders(ListView listView)
    {
        listView.getItems().clear();
        //TODO:
//        ArrayList<Order> records = dbConnector.selectBuyingOrdersActiveOnly();        
//        for(Order s : records)
//        {
//            listView.getItems().add(new KeyValuePair(Integer.toString(s.getOrderId()),Integer.toString(s.getOrderId()) ));
//        }
    }
        
    // Selling Order
    public static Validator AddSellingOrder(Order order)
    {
        order.setTypeId(Enumeration.OrderType.SELLING_ORDER);
        order.setBrokerId(getCurrentUser_BrokerID());
        return dbConnector.insertNewOrder(order);
    }
    public static Validator UpdateSellingOrder(Order order)
    {
        order.setTypeId(Enumeration.OrderType.SELLING_ORDER);
        order.setBrokerId(getCurrentUser_BrokerID());
        return dbConnector.updateOrder(order.getOrderId(), order);
    }
    public static Order GetSellingOrder(int orderID)
    {
        return dbConnector.selectOrder(orderID);
    }
    public static void PopulateSellingOrdersByBrokerageFirm(ListView listView)
    {
        listView.getItems().clear();
        ArrayList<Order> records = dbConnector.selectOrdersByFirmByType(
                                                    getCurrentUser_BrokerageFirmID()
                                                    , Enumeration.OrderType.SELLING_ORDER);        
        for(Order s : records)
        {
            listView.getItems().add(    new KeyValuePair(
                                         Integer.toString(s.getOrderId())
                                         , s.getDisplaySummary()  )
                                    );
        }
    }
    public static void PopulateSellingOrders(ListView listView)
    {
        //TODO
//        listView.getItems().clear();
//        ArrayList<Order> records = dbConnector.selectSellingOrdersActiveOnly();        
//        for(Order s : records)
//        {
//            listView.getItems().add(new KeyValuePair(Integer.toString(s.getOrderId()),Integer.toString(s.getOrderId()) ));
//        }
    }
}
