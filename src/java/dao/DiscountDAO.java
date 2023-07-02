/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Discount;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class DiscountDAO extends DBConnect {
    public Discount getDiscountByID(int discountID) {
        String sqlStatement = "SELECT * FROM Discount WHERE id = " + discountID +";";
        ResultSet rs = getData(sqlStatement);
        Discount dis = null;
        try {
            if(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                double percent = rs.getDouble(4);
                boolean active = rs.getBoolean(5);
                
                dis = new Discount(id, name, description, percent, active);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dis;
    }
}
