/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DTS
 */
public class Product {
    private int productID;
    private String productName;    
    private int manuID;
    private int modelYear;
    private double price;
    private int quantity;
    private int categoryID;
    private boolean status;
    private int discountID;
    private String description;
    private String img_path;

    public Product(int productID, String productName, int manuID, int modelYear, double price, int quantity, int categoryID, boolean status, int discountID, String description, String img_path) {
        this.productID = productID;
        this.productName = productName;
        this.manuID = manuID;
        this.modelYear = modelYear;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.status = status;
        this.discountID = discountID;
        this.description = description;
        this.img_path = img_path;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getManuID() {
        return manuID;
    }

    public void setManuID(int manuID) {
        this.manuID = manuID;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", manuID=" + manuID + ", modelYear=" + modelYear + ", price=" + price + ", quantity=" + quantity + ", categoryID=" + categoryID + ", status=" + status + ", discountID=" + discountID + ", description=" + description + '}';
    }

    
}
