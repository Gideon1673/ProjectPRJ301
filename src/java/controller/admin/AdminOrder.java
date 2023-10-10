/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.OrderDetail;
import entity.OrderItem;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import service.OrderService;

/**
 *
 * @author DTS
 */
@WebServlet(name = "AdminOrder", urlPatterns = {"/AdminOrder"})
public class AdminOrder extends HttpServlet {

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
        OrderService oService = new OrderService();

        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "displayAll";
            }

            switch (service) {
                case "displayAll":
                    request.setAttribute("orders", oService.getAllOrders());
                    request.getRequestDispatcher("/admin/displayOrders.jsp").forward(request, response);
                    break;
                case "delete":
                    int oID = Integer.parseInt(request.getParameter("oID"));
                    OrderDetail o = oService.getOrderByID(oID);
                    if (o == null) { // there is no order with oID
                        request.setAttribute("msg", "Error when deleting order with id " + oID + ". There is no oID in DB");
                        request.getRequestDispatcher("/error-page.jsp").forward(request, response);
                    } else { // Delete order logic
                        oService.deleteOrder(oID);
                        response.sendRedirect("AdminOrder?service=displayAll");
                    }
                    break;
                case "update":
                    int orderID = Integer.valueOf(request.getParameter("oID"));
                    OrderDetail orD = oService.getOrderByID(orderID);
                    if (request.getParameter("submit") != null) { // user submit update form
                        int status = Integer.valueOf(request.getParameter("oStatus"));
                        orD.setOrderStatus(status);
                        oService.updateOrderDetails(orD);
                        response.sendRedirect("AdminOrder?service=displayAll");
                    } else {

                        request.setAttribute("orderDetails", orD);
                        request.getRequestDispatcher("/admin/updateOrder.jsp").forward(request, response);
                        return;
                    }
                    break;
                case "displayOrderItems": {
                    Vector<OrderItem> oItems = oService.getAllOrderItems();
                    request.setAttribute("orderItems", oItems);
                    request.getRequestDispatcher("/admin/displayOrderItems.jsp").forward(request, response);
                }

                break;

                case "orderDetails": { // display all order_items corresponding to an orderID
                    int orID = Integer.valueOf(request.getParameter("id"));
                    Vector<OrderItem> oItems = oService.getAllItemsByoID(orID);
                    request.setAttribute("orderItems", oItems);
                    request.getRequestDispatcher("/admin/displayOrderItems.jsp").forward(request, response);
                }

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
