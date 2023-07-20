/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import service.OrderService;
import service.UserService;

/**
 *
 * @author DTS
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

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
            HttpSession session = request.getSession();
            OrderService orderService = new OrderService();
            UserService userService = new UserService();

            // Get cart K-V == ProductID - Quantity
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            // TEST CODE. REMOVE WHEN DONE
            // -------------
            if (service == null) {
                service = "displayAll";
            }

            switch (service) {
                case "displayAll":
                    request.setAttribute("activePage", "cart");
                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                    break;
                case "add": // add item to cart
                    int productID = Integer.valueOf(request.getParameter("id"));
                    // specific handle for quantity from produtc_details
                    String quant = request.getParameter("quantity");
                    int addNumber;
                    if (quant == null) { // add service sent from catergory
                        addNumber = 1;
                    } else {
                        addNumber = Integer.valueOf(quant);
                    }

                    int quantity = 0;
                    // Get quantity of product if it already in cart
                    if (cart.get(productID) != null) {
                        quantity = cart.get(productID);
                    }
                    cart.put(productID, quantity + addNumber);
                    response.sendRedirect("category");
                    break;
                case "remove": {
                    int pID = Integer.valueOf(request.getParameter("id"));
                    cart.remove(pID);
                    response.sendRedirect("cart?service=displayAll");
                }
                break;

                case "plusItem": { // handle logic when user clicks + icon on cart items
                    int pID = Integer.valueOf(request.getParameter("id"));
                    // get old quantity
                    int oldQuantity = cart.get(pID);
                    cart.put(pID, oldQuantity + 1);
                    response.sendRedirect("cart?service=displayAll");
                }
                break;

                case "minusItem": { // handle logic when user clicks - icon on cart items
                    int pID = Integer.valueOf(request.getParameter("id"));
                    // get old quantity
                    int oldQuantity = cart.get(pID);
                    if (oldQuantity > 1) {
                        cart.put(pID, oldQuantity - 1);
                    }
                    response.sendRedirect("cart?service=displayAll");
                }
                break;

                case "update": // update the cart (quantity)
                    int pId = Integer.valueOf(request.getParameter("pId"));
                    int quan = Integer.valueOf((request.getParameter("quantity")));
                    System.out.println(pId);
                    System.out.println(quan);
                    cart.put(pId, quan);
                    response.sendRedirect("cart?service=displayAll");
                    break;

                case "checkout":
                    // Check user login
                    String usrName = (String) session.getAttribute("username");
                    if (usrName == null) { // If user not yet logged it
                        response.sendRedirect("login");
                        return;
                    }
                    // Get user object that are currently logged in
                    User user = userService.getUserByUsername((String) session.getAttribute("username"));
                    // User do the checkout
                    orderService.checkout(cart, user);
                    response.sendRedirect("category?service=listAll");
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
