package dataAccess;

import connection.ConnectionFactory;
import model.Client;
import model.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO extends TableDAO<Product>{

    //queries

    private final static String findProduct = "SELECT * FROM tema3.product";
    private final static String findProductById = "SELECT * FROM tema3.product WHERE productId = ?";
    private final static String addProduct = "INSERT INTO tema3.product(productId, name, price, description, stock) " + " VALUES(?, ?, ?, ?, ?) ";
    private final static String deleteProduct = "DELETE FROM tema3.product " + " WHERE productId = ?";
    private final static String editProduct = " UPDATE tema3.product " + " SET name = ?, price = ?, description = ?, stock = ? " + " WHERE productId = ?";

    public static ArrayList<Product> findProduct(){
        ArrayList<Product> resultedProducts = new ArrayList<Product>();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet result = null;
        try{
            findStatement = connection.prepareStatement(findProduct);
            result = findStatement.executeQuery();
            while(result.next()) {
                int productId = result.getInt("productId");
                String name = result.getString("name");
                double price = result.getDouble("price");
                String description = result.getString("description");
                int stock = result.getInt("stock");
                resultedProducts.add(new Product(productId, name, price, description, stock));
            }
        }catch(SQLException sql){
            sql.getStackTrace();
        }
        return resultedProducts;
    }

    public static Product findProductById(int id) {
        Product resultedProduct = null;
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet result = null;
        try {
            findStatement = connection.prepareStatement(findProductById);
            findStatement.setInt(1, id);
            result = findStatement.executeQuery();
            if(result.next()) {
                int productId = result.getInt("productId");
                String name = result.getString("name");
                double price = result.getDouble("price");
                String description = result.getString("description");
                int stock = result.getInt("stock");
                resultedProduct = new Product(productId, name, price, description, stock);
            }
            connection.close();
        } catch (SQLException sql) {
            sql.getStackTrace();
        }
        return resultedProduct;
    }

    public static void addProduct(int ID, String name, double price, String description, int stock){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement addStatement = null;
        try{
            addStatement = connection.prepareStatement(addProduct);
            addStatement.setInt(1, ID);
            addStatement.setString(2, name);
            addStatement.setDouble(3, price);
            addStatement.setString(4, description);
            addStatement.setInt(5, stock);
            addStatement.execute();
        }catch(SQLException sql){
            //JOptionPane.showMessageDialog(null, "Produsul nu se poate adauga in baza de date!");
            sql.printStackTrace();
        }
    }

    public static void deleteProduct(int ID){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try{
            deleteStatement = connection.prepareStatement(deleteProduct);
            deleteStatement.setInt(1, ID);
            deleteStatement.execute();
        }catch(SQLException sql){
            //JOptionPane.showMessageDialog(null, "Produsul nu se poate sterge din baza de date!");
            sql.printStackTrace();
        }
    }

    public static void editProduct(int ID, String name, double price, String description, int stock){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement editStatement = null;
        try{
            editStatement = connection.prepareStatement(editProduct);
            editStatement.setInt(5, ID);
            editStatement.setString(1, name);
            editStatement.setDouble(2, price);
            editStatement.setString(3, description);
            editStatement.setInt(4, stock);
            editStatement.execute();
            connection.close();
        }catch(SQLException sql){
            //JOptionPane.showMessageDialog(null, "Clientul nu se poate adauga in baza de date!");
            sql.printStackTrace();
        }
    }

    public static void setProductComboBox(JComboBox<String> productComboBox){
        ArrayList<Product> products = ProductDAO.findProduct();
        for(int i = 0; i < products.size(); i++){
            productComboBox.addItem(products.get(i).getProductId());
        }
    }
}
