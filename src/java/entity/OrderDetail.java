/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DTS
 */
public class OrderDetail {
    private int orderID;
    private int userID;
    private int orderStatus;
    private String orderDate;
    private double total;

    public OrderDetail(int orderID, int userID, int orderStatus, String orderDate, double total) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderID=" + orderID + ", userID=" + userID + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate + ", total=" + total + '}';
    }
    
    
}
