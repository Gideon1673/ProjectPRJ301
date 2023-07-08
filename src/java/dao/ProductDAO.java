/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Product;
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
public class ProductDAO extends DBConnect {

    /**
     * Get a vector contains all products in the DB.
     *
     * @return
     */
    public Vector<Product> getAllProducts() {
        String sqlStatement = "SELECT * FROM Product;";
        Vector<Product> products = new Vector<>();

        ResultSet rs = getData(sqlStatement);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String pName = rs.getString(2);
                int manuID = rs.getInt(3);
                int modelYear = rs.getInt(4);
                double price = rs.getInt(5);
                int quantity = rs.getInt(6);
                int categoryID = rs.getInt(7);
                boolean status = rs.getBoolean(8);
                int discountID = rs.getInt(9);
                String desc = rs.getString(10);
                String img_path = rs.getString(11);

                Product prod = new Product(id, pName, manuID, modelYear, price, quantity, categoryID, status, discountID, desc, img_path);
                products.add(prod);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public Vector<Product> getProductsByManu(int manufacturerID) {
        String sqlStatement = "SELECT * FROM Product WHERE [manufacturer_id] = " + manufacturerID + ";";
        Vector<Product> products = new Vector<>();

        ResultSet rs = getData(sqlStatement);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String pName = rs.getString(2);
                int manuID = rs.getInt(3);
                int modelYear = rs.getInt(4);
                double price = rs.getInt(5);
                int quantity = rs.getInt(6);
                int categoryID = rs.getInt(7);
                boolean status = rs.getBoolean(8);
                int discountID = rs.getInt(9);
                String desc = rs.getString(10);
                String img_path = rs.getString(11);

                Product prod = new Product(id, pName, manuID, modelYear, price, quantity, categoryID, status, discountID, desc, img_path);
                products.add(prod);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    /**
     * Get product corresponding to ID
     *
     * @param id
     * @return Product object corresponding to ID, or null if there's no product
     */
    public Product getProductByID(int id) {
        String sqlStatement = "SELECT * FROM Product WHERE [product_id] = " + id + ";";
        Product prod = null;

        ResultSet rs = getData(sqlStatement);
        try {
            while (rs.next()) {
                String pName = rs.getString(2);
                int manuID = rs.getInt(3);
                int modelYear = rs.getInt(4);
                double price = rs.getInt(5);
                int quantity = rs.getInt(6);
                int categoryID = rs.getInt(7);
                boolean status = rs.getBoolean(8);
                int discountID = rs.getInt(9);
                String desc = rs.getString(10);
                String img_path = rs.getString(11);

                prod = new Product(id, pName, manuID, modelYear, price, quantity, categoryID, status, discountID, desc, img_path);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;
    }

    public boolean insertProduct(Product p) {
        String sqlStatement = "INSERT INTO Product(product_id, product_name, manufacturer_id, model_year, price, quantity, category, status, discount_id, description) VALUES\n"
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        System.out.println(p);

        try {
            PreparedStatement pre = connect.prepareStatement(sqlStatement);

            pre.setInt(1, p.getProductID());
            pre.setString(2, p.getProductName());
            pre.setInt(3, p.getManuID());
            pre.setInt(4, p.getModelYear());
            pre.setDouble(5, p.getPrice());
            pre.setInt(6, p.getQuantity());
            pre.setInt(7, p.getCategoryID());
            pre.setBoolean(8, p.isStatus());
            pre.setInt(9, p.getDiscountID());
            pre.setString(10, p.getDescription());

            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public Vector<Product> getProductsByName(String name) {
        Vector<Product> products = new Vector<>();
        String sqlStatement = "SELECT * FROM Product WHERE product_name LIKE '" + name + "';";
        ResultSet rs = getData(sqlStatement);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String pName = rs.getString(2);
                int manuID = rs.getInt(3);
                int modelYear = rs.getInt(4);
                double price = rs.getInt(5);
                int quantity = rs.getInt(6);
                int categoryID = rs.getInt(7);
                boolean status = rs.getBoolean(8);
                int discountID = rs.getInt(9);
                String desc = rs.getString(10);
                String img_path = rs.getString(11);

                products.add(new Product(id, pName, manuID, modelYear, price, quantity, categoryID, status, discountID, desc, img_path));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public int updateProduct(Product p) {
        int updatedRows = 0;
        String sqlStatement = "UPDATE [Product]\n"
                + "   SET [product_name] = ?\n"
                + "      ,[manufacturer_id] = ?\n"
                + "      ,[model_year] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[category] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[discount_id] = ?\n"
                + "      ,[description] = ?\n"
                + "      , img_path = ?"
                + " WHERE product_id = ?;";

        PreparedStatement pre;
        try {
            pre = connect.prepareStatement(sqlStatement);
            pre.setString(1, p.getProductName());
            pre.setInt(2, p.getManuID());
            pre.setInt(3, p.getModelYear());
            pre.setDouble(4, p.getPrice());
            pre.setInt(5, p.getQuantity());
            pre.setInt(6, p.getCategoryID());
            pre.setBoolean(7, p.isStatus());
            pre.setInt(8, p.getDiscountID());
            pre.setString(9, p.getDescription());
            pre.setString(10, p.getImg_path());
            pre.setInt(11, p.getProductID());

            updatedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return updatedRows;
    }
}
