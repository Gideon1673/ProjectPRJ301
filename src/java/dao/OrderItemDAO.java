/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.OrderItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class OrderItemDAO extends DBConnect {

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
}
