/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Manufacturer;
import entity.Product;
import entity.ProductCategory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import service.CategoryService;
import service.ManufacturerService;
import service.ProductService;

/**
 *
 * @author DTS
 */
@WebServlet(name = "AdminProduct", urlPatterns = {"/AdminProduct"})
public class AdminProduct extends HttpServlet {

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

            ProductService pService = new ProductService();

            String service = request.getParameter("service");
            if (service == null) {
                response.sendRedirect("AdminProduct?service=displayAll");
                return;
            }

            switch (service) {
                case "add": // Add product
                    if (request.getParameter("submit") != null) { // user clicked to submit insert form

                        // get form data
                        int productID = Integer.valueOf(request.getParameter("product_id"));
                        String productName = request.getParameter("product_name");
                        int manuID = Integer.valueOf(request.getParameter("manu_id"));
                        int modelYear = Integer.valueOf(request.getParameter("model_year"));
                        double price = Double.valueOf(request.getParameter("price"));
                        int quantity = Integer.valueOf(request.getParameter("available_quantity"));
                        int cat_id = Integer.valueOf(request.getParameter("product_categorie"));
                        boolean status = Boolean.valueOf(request.getParameter("status"));
                        int discountID = Integer.valueOf(request.getParameter("discount_id")); // FIX PARAM NAME PLEASE
                        String desc = request.getParameter("product_description");
                        String img_path = request.getParameter("img_path");
                        if(img_path == null || img_path.isEmpty()) {
                            img_path = "https://dummyimage.com/600x400/55595c/fff";
                        }

                        Product product = new Product(productID, productName, manuID, modelYear, price, quantity, cat_id, status, discountID, desc, img_path);

                        // DEBUG
//                        System.out.println(product);
                        pService.insertProduct(product);

                        response.sendRedirect("AdminProduct?service=displayAll");
                    } else {
                        CategoryService cateService = new CategoryService();
                        Vector<ProductCategory> categories = cateService.getAllCategories();
                        request.setAttribute("categories", categories);

                        request.getRequestDispatcher("/admin/add_products.jsp").forward(request, response);
                    }

                    break;

                case "displayAll": // Display all products
                    Vector<Product> products = pService.getAllProducts();
                    request.setAttribute("products", products);
                    request.getRequestDispatcher("/admin/displayProducts.jsp").forward(request, response);
                    break;

                case "update": // update product
                    if (request.getParameter("submit") != null) { // user submitted update form
                        int pID = Integer.valueOf(request.getParameter("product_id"));
                        String productName = request.getParameter("product_name");
                        int manuID = Integer.valueOf(request.getParameter("manu_id"));
                        int catID = Integer.valueOf(request.getParameter("product_categorie"));
                        int quantity = Integer.valueOf(request.getParameter("available_quantity"));
                        int year = Integer.valueOf(request.getParameter("model_year"));
                        double price = Double.valueOf(request.getParameter("price"));
                        String desc = request.getParameter("product_description");
                        int disID = Integer.valueOf(request.getParameter("discount_id"));
                        boolean status = Boolean.valueOf(request.getParameter("status"));
                        String img_path = request.getParameter("img_path");

                        Product p = new Product(pID, productName, manuID, year, price, quantity, catID, status, disID, desc, img_path);

                        pService.updateProduct(p);

                        response.sendRedirect("AdminProduct?service=displayAll");

                    } else { // user navigate to update form
                        // get productID user want to update
                        int pID = Integer.valueOf(request.getParameter("id"));

                        Product product = pService.getProductByID(pID);
                        request.setAttribute("product", product);

                        CategoryService cateService = new CategoryService();
                        Vector<ProductCategory> categories = cateService.getAllCategories();
                        request.setAttribute("categories", categories);

                        request.getRequestDispatcher("/admin/update_product.jsp").forward(request, response);
                    }
                    break;

                case "displayManufs":
                    ManufacturerService manuService = new ManufacturerService();
                    Vector<Manufacturer> manus = manuService.getAllManufactuers();
                    request.setAttribute("manus", manus);

                    request.getRequestDispatcher("/admin/displayManus.jsp").forward(request, response);
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
