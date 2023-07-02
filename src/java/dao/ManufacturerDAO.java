/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Manufacturer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class ManufacturerDAO extends DBConnect {

    /**
     * Get all Manufacturer objects from DB.
     *
     * @return vector contains Manufacturers, or vector of size 0 if there is no
     * Manufacturer in the DB
     */
    public Vector<Manufacturer> getAll() {
        String sqlStatement = "SELECT * FROM Manufacturer";
        Vector<Manufacturer> manus = new Vector<>();
        ResultSet rs = getData(sqlStatement);

        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String address = rs.getString(5);

                Manufacturer man = new Manufacturer(id, name, phone, email, address);
                manus.add(man);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manus;
    }
}
