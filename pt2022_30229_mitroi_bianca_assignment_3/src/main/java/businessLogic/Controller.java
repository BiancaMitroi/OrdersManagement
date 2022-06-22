package businessLogic;
import dataAccess.ClientDAO;
import dataAccess.OrderDAO;
import dataAccess.ProductDAO;
import dataAccess.TableDAO;
import model.Client;
import model.Order;
import model.Product;
import prezentation.View;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    protected View view = new View();

    public View getView(){
        return this.view;
    }

    public Controller(){
        view.clientRegisterListener(new ClientListener());
        view.productRegisterListener(new ProductListener());
        view.orderRegisterListener(new OrderListener());
        view.backFromClientListener(new FromClientListener());
        view.backFromOrderListener(new FromOrderListener());
        view.backFromProductListener(new FromProductListener());
        view.addClientListener(new AddClientListener());
        view.addProductListener(new AddProductListener());
        view.addOrderListener(new AddOrderListener());
        view.editClientListener(new EditClientListener());
        view.editProductListener(new EditProductListener());
        view.deleteClientListener(new DeleteClientListener());
        view.deleteProductListener(new DeleteProductListener());
        view.viewClientsListener(new ViewClientListener());
        view.viewProductsListener(new ViewProductListener());
        view.viewOrdersListener(new ViewOrdersListener());

    }

    public class ClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getTableSelectionFrame().setVisible(false);
            view.getClientFrame().setVisible(true);
        }
    }

    public class ProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getTableSelectionFrame().setVisible(false);
            view.getProductFrame().setVisible(true);
        }
    }

    public class OrderListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getTableSelectionFrame().setVisible(false);
            view.getOrderFrame().setVisible(true);
        }
    }

    public class FromClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getClientFrame().setVisible(false);
            view.getTableSelectionFrame().setVisible(true);
        }
    }

    public class FromProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getProductFrame().setVisible(false);
            view.getTableSelectionFrame().setVisible(true);
        }
    }

    public class FromOrderListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getOrderFrame().setVisible(false);
            view.getTableSelectionFrame().setVisible(true);
        }
    }

    public class AddClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int ID = -1;
            String name = "";
            String address = "";
            try{
                ID = Integer.parseInt(view.getClientIdTextField());
                if(ID < 0)
                    throw new NumberFormatException();
                name = view.getClientNameTextField();
                address = view.getClientAddressTextField();
                if(name.equals("") || address.equals(""))
                    throw new NullPointerException();
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "ID - ul este invalid, Incercati din nou!");
            }catch(NullPointerException nfe){
                JOptionPane.showMessageDialog(null, "Numele sau adresa sunt invalide, Incercati din nou!");
            }
            ClientDAO.addClient(ID, name, address);
        }
    }

    public class AddProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int ID = -1;
            String name = "";
            double price = 0;
            String description = "";
            int stock = 0;
            try{
                ID = Integer.parseInt(view.getProductIdTextField());
                price = Double.parseDouble(view.getProductPriceTextField());
                stock = Integer.parseInt(view.getProductStockTextField());
                if(ID < 0 || price == 0 || stock == 0)
                    throw new NumberFormatException();
                name = view.getProductNameTextField();
                description = view.getProductDescriptionTextField();
                if(name.equals("") || description.equals(""))
                    throw new NullPointerException();
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "ID - ul sau pretul sau stocul sunt invalide, Incercati din nou!");
            }catch(NullPointerException nfe){
                JOptionPane.showMessageDialog(null, "Numele sau descrierea sunt invalide, Incercati din nou!");
            }
            ProductDAO.addProduct(ID, name, price, description, stock);
        }
    }

    public class AddOrderListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(view.getOrderIdTextField());
                int clientId = Integer.parseInt(view.getOrderClient());
                int productId = Integer.parseInt(view.getOrderProduct());
                int quantity = Integer.parseInt(view.getOrderQuantityTextField());
                Product prod = ProductDAO.findProductById(productId);
                Client cl = ClientDAO.findClientById(clientId);
                FileWriter file = new FileWriter("fisier.txt", false);
                file.write("\nOrder id: " + id + cl.toString() + "\na cumparat " + quantity + "\ndin produsul " + prod.toString() + "\nla pretul de " + Double.parseDouble(prod.getPrice()) * quantity);
                file.close();
                if(Integer.parseInt(prod.getStock()) < quantity) {
                    JOptionPane.showMessageDialog(null, "Nu se poate adauga comanda deoarece nu sun suficiente produse pe stoc!");
                    return;
                }
                if(OrderDAO.addOrder(id, clientId, productId, Double.parseDouble(prod.getPrice()), quantity))
                    ProductDAO.editProduct(productId, prod.getName(), Double.parseDouble(prod.getPrice()), prod.getDescription(), Integer.parseInt(prod.getStock()) - quantity);
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Nu se poate adauga comanda deoarece date sunt invalide. Incercati din nou!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class EditClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int ID = -1;
            String name = "";
            String address = "";
            try{
                ID = Integer.parseInt(view.getClientIdTextField());
                if(ID < 0)
                    throw new NumberFormatException();
                name = view.getClientNameTextField();
                address = view.getClientAddressTextField();
                if(name.equals("") || address.equals(""))
                    throw new NullPointerException();
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "ID - ul este invalid, Incercati din nou!");
            }catch(NullPointerException nfe){
                JOptionPane.showMessageDialog(null, "Numele sau adresa sunt invalide, Incercati din nou!");
            }
            ClientDAO.editClient(ID, name, address);
        }
    }

    public class EditProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int ID = -1;
            String name = "";
            double price = 0;
            String description = "";
            int stock = 0;
            try{
                ID = Integer.parseInt(view.getProductIdTextField());
                price = Double.parseDouble(view.getProductPriceTextField());
                stock = Integer.parseInt(view.getProductStockTextField());
                if(ID < 0 || price == 0 || stock == 0)
                    throw new NumberFormatException();
                name = view.getProductNameTextField();
                description = view.getProductDescriptionTextField();
                if(name.equals("") || description.equals(""))
                    throw new NullPointerException();
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "ID - ul sau pretul sau stocul sunt invalide, Incercati din nou!");
            }catch(NullPointerException nfe){
                JOptionPane.showMessageDialog(null, "Numele sau descrierea sunt invalide, Incercati din nou!");
            }
            ProductDAO.editProduct(ID, name, price, description, stock);
        }
    }

    public class DeleteClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int ID = -1;
            try{
                ID = Integer.parseInt(view.getClientIdTextField());
                if(ID < 0)
                    throw new NumberFormatException();

            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "ID - ul este invalid, Incercati din nou!");
            }
            ClientDAO.deleteClient(ID);
        }
    }

    public class DeleteProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int ID = -1;
            try{
                ID = Integer.parseInt(view.getProductIdTextField());
                if(ID < 0)
                    throw new NumberFormatException();

            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "ID - ul este invalid, Incercati din nou!");
            }
            ProductDAO.deleteProduct(ID);
        }
    }

    public class ViewClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            TableDAO<Client> t = new ClientDAO();
            ArrayList<Client> clients = ClientDAO.findClient();
            TableModel tm = t.createTableModel(clients);
            view.setClientTable(tm);
        }
    }

    public class ViewProductListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Product> products = ProductDAO.findProduct();
            TableDAO t = new ProductDAO();
            TableModel tm = t.createTableModel(products);
            view.setClientTable(tm);
            view.setProductTable(tm);
        }
    }

    public class ViewOrdersListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Order> orders = OrderDAO.findOrder();
            TableDAO t = new OrderDAO();
            TableModel tm = t.createTableModel(orders);
            view.setClientTable(tm);
            view.setProductTable(tm);
            view.setOrderTable(tm);
        }
    }
}
