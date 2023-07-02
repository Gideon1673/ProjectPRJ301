/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import service.ManufacturerService;
import service.ProductService;

/**
 *
 * @author DTS
 */
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");

            if (service == null) {
                service = "listAll";
            }

            ManufacturerService manuService = new ManufacturerService();
            ProductService productService = new ProductService();

            request.setAttribute("categories", manuService.getAllManufactuers());

            switch(service) {
                case "listAll": // default: display all products 
                    request.setAttribute("products", productService.getAllProducts());
                    request.getRequestDispatcher("/category.jsp").forward(request, response);
                    break;
                    
                case "manFilter":
                    int manuID = Integer.valueOf(request.getParameter("manID"));
                    request.setAttribute("products", productService.getProductsByManu(manuID));
                    request.getRequestDispatcher("/category.jsp").forward(request, response);
                    break;
                    
                case "productDetail":
                    int productID = Integer.valueOf(request.getParameter("id"));
                    Product product = productService.getProductByID(productID);
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/product-details.jsp").forward(request, response);
                    break;
                    
                case "search":
                    String searchText = request.getParameter("id");
                    Vector<Product> result = productService.findProductByName(searchText);
                    System.out.println(searchText);
                    request.setAttribute("products", result);
                    request.getRequestDispatcher("/category.jsp").forward(request, response);
                    break;
            }            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
