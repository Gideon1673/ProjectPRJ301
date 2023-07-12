/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DiscountDAO;
import dao.ProductDAO;
import entity.Discount;
import entity.Product;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author DTS
 */
public class ProductService {

    private static final ProductDAO productDao = new ProductDAO();
    private static final DiscountDAO discountDao = new DiscountDAO();

    public Vector<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Vector<Product> getProductsByManu(int manufacturerID) {
        return productDao.getProductsByManu(manufacturerID);
    }

    public Product getProductByID(int id) {
        return productDao.getProductByID(id);
    }

    public boolean insertProduct(Product p) {
        return productDao.insertProduct(p);
    }
    
//    public Vector<Product> searchByProductName(String pName) {
//        return productDao.searchByProductName(pName);
//    }

    /**
     * Get final price (after taxes, discount, etc. ) of an product
     *
     * @param productID
     * @return
     */
    public double getFinalPrice(int productID) {
        Product p = getProductByID(productID);
        double oriPrice = p.getPrice();
        double percent = discountDao.getDiscountByID(p.getDiscountID()).getDiscount_percent();
        double finalPrice = oriPrice - oriPrice * percent;
        return finalPrice;
    }
    
    public Vector<Product> findProductByName(String name) {
        return productDao.getProductsByName(name);
    }
    
    /**
     * Get Discount object corresponding to Product
     * @param p
     * @return 
     */
    public Discount getProductDiscount(Product p) {
        return discountDao.getDiscountByID(p.getDiscountID());
    }
    
    public int updateProduct(Product product) {
        int n = productDao.updateProduct(product);
        return n;
    }
    

//    public Vector<Product> getProductsOnPage(int pageNumber) {
//        
//    }
}
