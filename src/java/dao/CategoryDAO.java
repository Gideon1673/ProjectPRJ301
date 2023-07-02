/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ProductCategory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class CategoryDAO extends DBConnect {

    /**
     * Get all category objects in the DB.
     *
     * @return vector contains all category objects, vector will have size = 0
     * if there is no data.
     */
    public Vector<ProductCategory> getAllCategories() {
        String sqlStatement = "SELECT * FROM product_category;";
        ResultSet rs = getData(sqlStatement);
        Vector<ProductCategory> categories = new Vector<>();

        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String desc = rs.getString(3);

                ProductCategory cat = new ProductCategory(id, name, desc);
                categories.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categories;
    }
}
