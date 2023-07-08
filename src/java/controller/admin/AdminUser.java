/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

/**
 *
 * @author DTS
 */
@WebServlet(name = "AdminUser", urlPatterns = {"/AdminUser"})
public class AdminUser extends HttpServlet {

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

        UserService userService = new UserService();

        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                response.sendRedirect("AdminUser?displayUsers");
                return;
            }

            switch (service) {
                case "displayUsers":
                    request.setAttribute("users", userService.getAllUSers());
                    request.getRequestDispatcher("/admin/displayUsers.jsp").forward(request, response);
                    break;
                case "update":
                    if (request.getParameter("submit") == null) { // user navigate to update form
                        int uID = Integer.valueOf(request.getParameter("uID"));
                        User user = userService.getUserByID(uID);

                        // Check for valid parameter
                        if (user == null) {
                            request.setAttribute("msg", "Error: User does not exist");
                            request.getRequestDispatcher("/error-page.jsp").forward(request, response);
                        } else { // update the user information
                            request.setAttribute("user", user);
                            request.getRequestDispatcher("/admin/updateUser.jsp").forward(request, response);
                        }
                    } else { // user submit update form
                        int uID = Integer.valueOf(request.getParameter("uID"));
                        String usrname = request.getParameter("user_name");
                        String fullname = request.getParameter("user_fname");
                        String email = request.getParameter("u_email");
                        String phone = request.getParameter("phone");
                        String city = request.getParameter("u_city");
                        
                        // create a temp object contains informations needed update
                        User tmpUser = new User(uID, fullname, usrname, email, phone, null, null, city);
                        userService.updateUser(tmpUser);
                        response.sendRedirect("AdminUser?service=displayUsers");
                    }

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
