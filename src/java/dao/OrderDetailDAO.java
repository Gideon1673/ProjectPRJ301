/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.OrderDetail;
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
public class OrderDetailDAO extends DBConnect {

    public int getLastOrderID() {
        String sqlStatement = "SELECT TOP(1) order_id FROM order_details ORDER BY order_id DESC;";
        int lastID = 0;
        ResultSet rs = getData(sqlStatement);
        try {
            if (rs.next()) { // nếu có bản ghi
                lastID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastID;
    }

    public int addOrder(OrderDetail order) {
        int n = 0;
        String sqlStatement = "INSERT INTO [order_details]\n"
                + "           ([order_id]\n"
                + "           ,[user_id]\n"
                + "           ,[order_status]\n"
                + "           ,[order_date]\n"
                + "           ,[total])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connect.prepareStatement(sqlStatement);
            
            pre.setInt(1, order.getOrderID());
            pre.setInt(2, order.getUserID());
            pre.setInt(3, order.getOrderStatus());
            pre.setString(4, order.getOrderDate());
            pre.setDouble(5, order.getTotal());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<OrderDetail> getAllOrders() {
        String sqlStatement = "SELECT * FROM order_details;";
        ResultSet rs = getData(sqlStatement);
        Vector<OrderDetail> orders = new Vector<>();
        try {
            while(rs.next()){
                int oID = rs.getInt(1);
                int uID = rs.getInt(2);
                int status = rs.getInt(3);
                String date = rs.getString(4);
                double total = rs.getDouble(5);
                
                OrderDetail o = new OrderDetail(oID, uID, status, date, total);
                orders.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
}
