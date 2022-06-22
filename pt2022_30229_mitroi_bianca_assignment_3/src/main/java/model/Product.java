package model;

public class Product {
    private int productId;
    private String name;
    private double price;
    private String description;
    private int stock;

    public Product(int productId, String name, double price, String decription, int stock){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = decription;
        this.stock = stock;
    }

    public String getProductId() {
        return "" + productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return "" + price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return "\nProduct name: " + name + "\nProduct price: " + price;
    }

    public String getStock() {
        return "" + stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
