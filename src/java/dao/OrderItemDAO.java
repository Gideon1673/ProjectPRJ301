/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.OrderItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class OrderItemDAO extends DBConnect {

    public Vector<OrderItem> getAllOrderItems() {
        String sql = "SELECT * FROM order_items;";
        ResultSet rs = getData(sql);
        Vector<OrderItem> orderItems = new Vector<>();
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                int oID = rs.getInt(2);
                int pID = rs.getInt(3);
                int quantity = rs.getInt(4);

                OrderItem oI = new OrderItem(id, oID, pID, quantity);
                orderItems.add(oI);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderItems;
    }

    public Vector<OrderItem> getOItemByoID(int oID) {
        String sql = "SELECT * FROM order_items WHERE order_id = ?;";
        Vector<OrderItem> orderItems = new Vector<>();
        try ( PreparedStatement pre = connect.prepareStatement(sql)) {

            pre.setInt(1, oID);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                int pID = rs.getInt(3);
                int quantity = rs.getInt(4);

                OrderItem oI = new OrderItem(id, oID, pID, quantity);
                orderItems.add(oI);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderItems;
    }

    /**
     *
     * @param o
     * @return
     */
    public int addOrderItem(OrderItem o) {
        String sqlStatement = "INSERT INTO [order_items]\n"
                + "           ([id]\n"
                + "           ,[order_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES (?, ?, ?, ?)";

        int n = 0;

        PreparedStatement pre;
        try {
            pre = connect.prepareStatement(sqlStatement);
            pre.setInt(1, o.getId());
            pre.setInt(2, o.getOrderID());
            pre.setInt(3, o.getProductID());
            pre.setInt(4, o.getQuantity());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getLastID() {
        int n = 0;
        String sqlStatement = "SELECT TOP(1) id FROM order_items ORDER BY id DESC;";

        ResultSet rs = getData(sqlStatement);
        try {
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    /**
     * Delete all order items with orderID
     *
     * @param orderID orderID of order item
     * @return number of order item deleted
     */
    public int deleteOrderItems(int orderID) {
        String sql = "DELETE FROM order_items\n"
                + "      WHERE order_id = ?;";
        int n = 0;

        try ( PreparedStatement pre = connect.prepareStatement(sql)) {
            pre.setInt(1, orderID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Delete order_item error, exception message: " + ex.getMessage());
            Logger.getLogger(OrderItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
