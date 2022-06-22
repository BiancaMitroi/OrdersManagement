package dataAccess;

import connection.ConnectionFactory;
import model.Order;
import model.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO extends TableDAO<Order>{

    //queries
    private final static String findOrder = "SELECT * FROM tema3.order";
    private final static String addOrder = "INSERT INTO tema3.order(orderId, clientId, productId, price, quantity) " + " VALUES(?, ?, ?, ?, ?);";

    public static ArrayList<Order> findOrder(){
        ArrayList<Order> resultedOrders = new ArrayList<Order>();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet result = null;
        try{
            findStatement = connection.prepareStatement(findOrder);
            result = findStatement.executeQuery();
            while(result.next()) {
                int orderId = result.getInt("orderId");
                int clientId = result.getInt("clientId");
                int productId = result.getInt("productId");
                double price = result.getDouble("price");
                int quantity = result.getInt("quantity");
                //System.out.println(price);
                resultedOrders.add(new Order(orderId, clientId, productId, price, quantity));
            }
            connection.close();
        }catch(SQLException sql){
            sql.getStackTrace();
        }
        return resultedOrders;
    }

    public static boolean addOrder(int orderId, int clientId, int productId, double price, int quantity){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement addStatement = null;
        double finalPrice = price * quantity;
        try{
            addStatement = connection.prepareStatement(addOrder);
            addStatement.setInt(1, orderId);
            addStatement.setInt(2, clientId);
            addStatement.setInt(3, productId);
            addStatement.setDouble(4, finalPrice);
            addStatement.setInt(5, quantity);
            addStatement.execute();
        }catch(SQLException sql){
            sql.getStackTrace();
            return false;
        }
        return true;
    }

}
