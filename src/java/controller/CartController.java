/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
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
import java.util.Map;
import service.OrderService;
import service.ProductService;
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
            ProductService pService = new ProductService();

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

                // add item to cart. It will also check for the available quantity of product, if 
                // the amount we add to the cart exceed the available quantity, the max number will 
                // be the available quantity.
                case "add": {
                    // get item we want to add to the cart
                    int productID = Integer.valueOf(request.getParameter("id"));
                    Product product = pService.getProductByID(productID);
                    // Step 1: Get the number of item want to add
                    // specific handle for quantity from produtc_details
                    String quant = request.getParameter("quantity");
                    int addNumber; // number of item added
                    if (quant == null) { // add service sent from catergory
                        addNumber = 1;
                    } else {
                        addNumber = Integer.valueOf(quant);
                    }

                    // step 2: Get the number of item of pID already in the cart (if exist)
                    int quantity = 0;
                    // Get quantity of product if it already in cart
                    if (cart.get(productID) != null) {
                        quantity = cart.get(productID);
                    }

                    // Step 3: item alrealy in cart + item want to add
                    // checking for available number of item
                    int availableQuant = product.getQuantity();
                    // if the number of item we want to add exceed the available amount
                    if (quantity + addNumber > availableQuant) { // we set the amount to in cart to max = available item)
                        cart.put(productID, availableQuant);
                    } else {
                        cart.put(productID, quantity + addNumber);
                    }

                    response.sendRedirect("category");
                }
                break;

                case "remove": {
                    int pID = Integer.valueOf(request.getParameter("id"));
                    cart.remove(pID);
                    response.sendRedirect("cart?service=displayAll");
                }
                break;

                case "plusItem": { // handle logic when user clicks + icon on cart items
                    int pID = Integer.valueOf(request.getParameter("id"));
                    Product product = pService.getProductByID(pID);
                    // get old quantity
                    int oldQuantity = cart.get(pID);
                    // user can only increase number of item when it's not exceed the available items
                    if (oldQuantity < product.getQuantity()) {
                        cart.put(pID, oldQuantity + 1);
                    }

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

                    // ------- DEBUG ---------
                    printAllCarts(cart);
                    // -----------------------
                    // User do the checkout
                    orderService.checkout(cart, user);
                    response.sendRedirect("category?service=listAll");
                    break;
            }
        }
    }

    /**
     * FOR DEBUG ONLY
     */
    private void printAllCarts(HashMap<Integer, Integer> cart) {
        for (Map.Entry<Integer, Integer> cartItem : cart.entrySet()) {
            System.out.println("ProductID - Quantity: " + cartItem.getKey() + " - " + cartItem.getValue());
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
