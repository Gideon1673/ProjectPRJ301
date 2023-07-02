/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CategoryDAO;
import entity.ProductCategory;
import java.util.Vector;

/**
 *
 * @author DTS
 */
public class CategoryService {
    private final static CategoryDAO cateDAO = new CategoryDAO();
    
    public Vector<ProductCategory> getAllCategories() {
        return cateDAO.getAllCategories();
    }
}
