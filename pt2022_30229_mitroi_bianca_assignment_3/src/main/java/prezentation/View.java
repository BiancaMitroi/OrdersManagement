package prezentation;

import dataAccess.ClientDAO;
import dataAccess.ProductDAO;
import model.Client;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

public class View {

    private JFrame tableSelection;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    private JFrame clientFrame;
    private JTable clientTable;
    private JTextField clientIdTextField;
    private JTextField clientAddressTextField;
    private JTextField clientNameTextField;
    private JButton insertClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton viewClientsButton;
    private JFrame productFrame;
    private JTextField productIdTextField;
    private JTextField productNameTextField;
    private JTextField priceProductTextField;
    private JTextField productDescriptionTextField;
    private JButton addProductButton;
    private JButton deleteProductButton;
    private JButton viewProductsButton;
    private JButton editProductButton;
    private JTable productTable;
    private JFrame orderFrame;
    private JTextField quantityTextField;
    private JTable orderTable;
    private JButton addOrderButton;
    private JComboBox<String> productComboBox;
    private JComboBox<String> clientComboBox;
    private JButton backFromClientsButton;
    private JButton backFromProductButton;
    private JButton backFromOrderButton;
    private JTextField productStockTextField;
    private JTextField orderIdTextField;
    private JButton viewOrdersButton;

    public View() {
        tableSelection = new JFrame();
        tableSelection.setBounds(100, 100, 198, 184);
        tableSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableSelection.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Please select a register");
        titleLabel.setBounds(10, 11, 164, 14);
        tableSelection.getContentPane().add(titleLabel);

        clientButton = new JButton("Client");
        clientButton.setBounds(10, 36, 89, 23);
        tableSelection.getContentPane().add(clientButton);

        productButton = new JButton("Product");
        productButton.setBounds(10, 70, 89, 23);
        tableSelection.getContentPane().add(productButton);

        orderButton = new JButton("Order");
        orderButton.setBounds(10, 104, 89, 23);
        tableSelection.getContentPane().add(orderButton);

        clientFrame = new JFrame();
        clientFrame.setBounds(100, 100, 450, 300);
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.getContentPane().setLayout(null);

        JScrollPane clientScrollPane = new JScrollPane();
        clientScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        clientScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        clientScrollPane.setBounds(10, 86, 416, 166);
        clientFrame.getContentPane().add(clientScrollPane);

        clientTable = new JTable();
        clientTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        clientScrollPane.setViewportView(clientTable);

        JLabel clientId = new JLabel("ID:");
        clientId.setBounds(10, 11, 25, 14);
        clientFrame.getContentPane().add(clientId);

        clientIdTextField = new JTextField();
        clientIdTextField.setBounds(62, 8, 49, 20);
        clientFrame.getContentPane().add(clientIdTextField);
        clientIdTextField.setColumns(10);

        JLabel clientName = new JLabel("Name:");
        clientName.setBounds(10, 36, 49, 14);
        clientFrame.getContentPane().add(clientName);

        JLabel clientAddress = new JLabel("Address:");
        clientAddress.setBounds(10, 61, 65, 14);
        clientFrame.getContentPane().add(clientAddress);

        clientAddressTextField = new JTextField();
        clientAddressTextField.setBounds(62, 58, 96, 20);
        clientFrame.getContentPane().add(clientAddressTextField);
        clientAddressTextField.setColumns(10);

        clientNameTextField = new JTextField();
        clientNameTextField.setBounds(62, 33, 96, 20);
        clientFrame.getContentPane().add(clientNameTextField);
        clientNameTextField.setColumns(10);

        insertClientButton = new JButton("Add client");
        insertClientButton.setBounds(168, 11, 121, 23);
        clientFrame.getContentPane().add(insertClientButton);

        editClientButton = new JButton("Edit client");
        editClientButton.setBounds(168, 36, 121, 23);
        clientFrame.getContentPane().add(editClientButton);

        deleteClientButton = new JButton("Delete client");
        deleteClientButton.setBounds(299, 11, 121, 23);
        clientFrame.getContentPane().add(deleteClientButton);

        viewClientsButton = new JButton("View clients");
        viewClientsButton.setBounds(299, 36, 121, 23);
        clientFrame.getContentPane().add(viewClientsButton);

        productFrame = new JFrame();
        productFrame.setBounds(100, 100, 450, 300);
        productFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productFrame.getContentPane().setLayout(null);

        JLabel productId = new JLabel("ID:");
        productId.setBounds(10, 11, 25, 14);
        productFrame.getContentPane().add(productId);

        productIdTextField = new JTextField();
        productIdTextField.setBounds(55, 8, 49, 20);
        productFrame.getContentPane().add(productIdTextField);
        productIdTextField.setColumns(10);

        JLabel productName = new JLabel("Name:");
        productName.setBounds(114, 11, 49, 14);
        productFrame.getContentPane().add(productName);

        productNameTextField = new JTextField();
        productNameTextField.setBounds(173, 8, 96, 20);
        productFrame.getContentPane().add(productNameTextField);
        productNameTextField.setColumns(10);

        JLabel productPrice = new JLabel("Price:");
        productPrice.setBounds(10, 36, 49, 14);
        productFrame.getContentPane().add(productPrice);

        priceProductTextField = new JTextField();
        priceProductTextField.setBounds(55, 33, 49, 20);
        productFrame.getContentPane().add(priceProductTextField);
        priceProductTextField.setColumns(10);

        JLabel productDescription = new JLabel("Description:");
        productDescription.setBounds(114, 36, 70, 14);
        productFrame.getContentPane().add(productDescription);

        productDescriptionTextField = new JTextField();
        productDescriptionTextField.setBounds(173, 33, 96, 20);
        productFrame.getContentPane().add(productDescriptionTextField);
        productDescriptionTextField.setColumns(10);

        addProductButton = new JButton("Add");
        addProductButton.setBounds(279, 7, 70, 23);
        productFrame.getContentPane().add(addProductButton);

        deleteProductButton = new JButton("Delete");
        deleteProductButton.setBounds(279, 32, 70, 23);
        productFrame.getContentPane().add(deleteProductButton);

        viewProductsButton = new JButton("View");
        viewProductsButton.setBounds(356, 7, 70, 23);
        productFrame.getContentPane().add(viewProductsButton);

        editProductButton = new JButton("Edit");
        editProductButton.setBounds(356, 32, 70, 23);
        productFrame.getContentPane().add(editProductButton);

        JScrollPane productScrollPane = new JScrollPane();
        productScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        productScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        productScrollPane.setBounds(10, 82, 416, 170);
        productFrame.getContentPane().add(productScrollPane);


        productTable = new JTable();
        productTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        productScrollPane.setViewportView(productTable);

        orderFrame = new JFrame();
        orderFrame.setBounds(100, 100, 450, 300);
        orderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderFrame.getContentPane().setLayout(null);

        JLabel orderClient = new JLabel("Client");
        orderClient.setBounds(10, 11, 99, 14);
        orderFrame.getContentPane().add(orderClient);

        clientComboBox = new JComboBox<String>();
        clientComboBox.setBounds(57, 7, 50, 22);
        orderFrame.getContentPane().add(clientComboBox);

        JLabel orderProduct = new JLabel("Product");
        orderProduct.setBounds(10, 40, 114, 14);
        orderFrame.getContentPane().add(orderProduct);

        productComboBox = new JComboBox<String>();
        productComboBox.setBounds(57, 36, 50, 22);
        orderFrame.getContentPane().add(productComboBox);

        JLabel orderQuantity = new JLabel("Quantity");
        orderQuantity.setBounds(117, 11, 114, 14);
        orderFrame.getContentPane().add(orderQuantity);

        quantityTextField = new JTextField();
        quantityTextField.setBounds(181, 8, 50, 20);
        orderFrame.getContentPane().add(quantityTextField);
        quantityTextField.setColumns(10);

        addOrderButton = new JButton("Add order");
        addOrderButton.setBounds(241, 36, 114, 23);
        orderFrame.getContentPane().add(addOrderButton);


        viewOrdersButton = new JButton("View orders");
        viewOrdersButton.setBounds(241, 7, 114, 23);
        orderFrame.getContentPane().add(viewOrdersButton);

        JScrollPane orderScrollPane = new JScrollPane();
        orderScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        orderScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        orderScrollPane.setBounds(10, 69, 416, 183);
        orderFrame.getContentPane().add(orderScrollPane);

        orderTable = new JTable();
        orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        orderScrollPane.setViewportView(orderTable);

        backFromOrderButton = new JButton("<-");
        backFromOrderButton.setBounds(376, 36, 50, 23);
        orderFrame.getContentPane().add(backFromOrderButton);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setBounds(117, 40, 49, 14);
        orderFrame.getContentPane().add(lblNewLabel);

        orderIdTextField = new JTextField();
        orderIdTextField.setBounds(181, 37, 50, 20);
        orderFrame.getContentPane().add(orderIdTextField);
        orderIdTextField.setColumns(10);

        backFromClientsButton = new JButton("<-");
        backFromClientsButton.setBounds(371, 61, 49, 23);
        clientFrame.getContentPane().add(backFromClientsButton);

        backFromProductButton = new JButton("<-");
        backFromProductButton.setBounds(377, 58, 49, 23);
        productFrame.getContentPane().add(backFromProductButton);

        JLabel lblNewLabel_4 = new JLabel("Stock");
        lblNewLabel_4.setBounds(10, 57, 49, 14);
        productFrame.getContentPane().add(lblNewLabel_4);

        productStockTextField = new JTextField();
        productStockTextField.setBounds(55, 59, 49, 20);
        productFrame.getContentPane().add(productStockTextField);
        productStockTextField.setColumns(10);

        this.tableSelection.setVisible(true);
        ClientDAO.setClientComboBox(clientComboBox);
        ProductDAO.setProductComboBox(productComboBox);
    }

    public void setProductTable(TableModel tm){
        productTable.setModel(tm);
    }

    public void setClientTable(TableModel tm){
        clientTable.setModel(tm);
    }

    public JTable getClientTable(){
        return clientTable;
    }

    public void setOrderTable(TableModel tm){
        orderTable.setModel(tm);
    }

    public JFrame getTableSelectionFrame(){
        return this.tableSelection;
    }

    public JFrame getClientFrame(){
        return this.clientFrame;
    }

    public JFrame getProductFrame(){
        return this.productFrame;
    }

    public JFrame getOrderFrame(){
        return this.orderFrame;
    }

    public String getClientIdTextField(){
        return this.clientIdTextField.getText();
    }

    public String getClientNameTextField(){
        return this.clientNameTextField.getText();
    }

    public String getClientAddressTextField(){
        return this.clientAddressTextField.getText();
    }

    public String getProductIdTextField(){
        return this.productIdTextField.getText();
    }

    public String getProductStockTextField(){
        return this.productStockTextField.getText();
    }

    public String getProductNameTextField(){
        return this.productNameTextField.getText();
    }

    public String getProductPriceTextField(){
        return this.priceProductTextField.getText();
    }

    public String getProductDescriptionTextField(){
        return this.productDescriptionTextField.getText();
    }

    public String getOrderQuantityTextField(){
        return this.quantityTextField.getText();
    }

    public String getOrderClient(){
        return this.clientComboBox.getSelectedItem().toString();
    }

    public String getOrderProduct(){
        return this.productComboBox.getSelectedItem().toString();
    }

    public String getOrderIdTextField(){
        return this.orderIdTextField.getText();
    }

    public void clientRegisterListener(ActionListener listener){
        clientButton.addActionListener(listener);
    }

    public void productRegisterListener(ActionListener listener){
        productButton.addActionListener(listener);
    }

    public void orderRegisterListener(ActionListener listener){
        orderButton.addActionListener(listener);
    }

    public void addClientListener(ActionListener listener){
        insertClientButton.addActionListener(listener);
    }

    public void editClientListener(ActionListener listener){
        editClientButton.addActionListener(listener);
    }

    public void deleteClientListener(ActionListener listener){
        deleteClientButton.addActionListener(listener);
    }

    public void viewClientsListener(ActionListener listener){
        viewClientsButton.addActionListener(listener);
    }

    public void addProductListener(ActionListener listener){
        addProductButton.addActionListener(listener);
    }

    public void editProductListener(ActionListener listener){
        editProductButton.addActionListener(listener);
    }

    public void deleteProductListener(ActionListener listener){
        deleteProductButton.addActionListener(listener);
    }

    public void viewProductsListener(ActionListener listener){
        viewProductsButton.addActionListener(listener);
    }

    public void addOrderListener(ActionListener listener){
        addOrderButton.addActionListener(listener);
    }

    public void viewOrdersListener(ActionListener listener){
        viewOrdersButton.addActionListener(listener);
    }

    public void backFromClientListener(ActionListener listener){
        backFromClientsButton.addActionListener(listener);
    }

    public void backFromProductListener(ActionListener listener){
        backFromProductButton.addActionListener(listener);
    }

    public void backFromOrderListener(ActionListener listener){
        backFromOrderButton.addActionListener(listener);
    }
}

