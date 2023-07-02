/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ManufacturerDAO;
import entity.Manufacturer;
import java.util.Vector;

/**
 *
 * @author DTS
 */
public class ManufacturerService {
    private static final ManufacturerDAO dao = new ManufacturerDAO();
    
    public Vector<Manufacturer> getAllManufactuers() {
        return dao.getAll();
    }
}
