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

public class ClientDAO extends TableDAO<Client>{

    //queries
    private final static String findClient = "SELECT * FROM tema3.client";
    private final static String findClientById = "SELECT * FROM tema3.client WHERE clientId = ?";

    private final static String addClient = "INSERT INTO tema3.client(clientId, clientName, clientAdress) " + " VALUES(?, ?, ?) ";
    private final static String deleteClient = "DELETE FROM tema3.client " + " WHERE clientId = ?";
    private final static String editClient = " UPDATE tema3.client " + " SET clientName = ?, clientAdress = ? " + " WHERE clientId = ?";

    //functions
    public static ArrayList<Client> findClient(){
        ArrayList<Client> resultedClients = new ArrayList<Client>();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet result = null;
        try{
            findStatement = connection.prepareStatement(findClient);
            result = findStatement.executeQuery();
            while(result.next()) {
                int clientId = result.getInt("clientId");
                String clientName = result.getString("clientName");
                String clientAdress = result.getString("clientAdress");
                resultedClients.add(new Client(clientId, clientName, clientAdress));
            }
        }catch(SQLException sql){
            sql.getStackTrace();
        }
        return resultedClients;
    }

    public static void addClient(int ID, String name, String address){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement addStatement = null;
        try{
            addStatement = connection.prepareStatement(addClient);
            addStatement.setInt(1, ID);
            addStatement.setString(2, name);
            addStatement.setString(3, address);
            addStatement.execute();
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null, "Clientul nu se poate adauga in baza de date!");
            //sql.printStackTrace();
        }
    }

    public static void editClient(int ID, String name, String address){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement editStatement = null;
        try{
            editStatement = connection.prepareStatement(editClient);
            editStatement.setInt(3, ID);
            editStatement.setString(1, name);
            editStatement.setString(2, address);
            editStatement.execute();
        }catch(SQLException sql){
            //JOptionPane.showMessageDialog(null, "Clientul nu se poate adauga in baza de date!");
            sql.printStackTrace();
        }
    }

    public static void deleteClient(int ID){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try{
            deleteStatement = connection.prepareStatement(deleteClient);
            deleteStatement.setInt(1, ID);
            deleteStatement.execute();
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null, "Clientul nu se poate sterge din baza de date!");
            //sql.printStackTrace();
        }
    }

    public static void setClientComboBox(JComboBox<String> clientComboBox){
        ArrayList<Client> clients = ClientDAO.findClient();
        for(int i = 0; i < clients.size(); i++){
            clientComboBox.addItem(clients.get(i).getClientId());
        }
    }

    public static Client findClientById(int id) {
        Client resultedClient = null;
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet result = null;
        try {
            findStatement = connection.prepareStatement(findClientById);
            findStatement.setInt(1, id);
            result = findStatement.executeQuery();
            if(result.next()) {
                int clientId = result.getInt("clientId");
                String clientName = result.getString("clientName");
                String clientAdress = result.getString("clientAdress");
                resultedClient = new Client(clientId, clientName, clientAdress);
            }
            connection.close();
        } catch (SQLException sql) {
            sql.getStackTrace();
        }
        return resultedClient;
    }
}
