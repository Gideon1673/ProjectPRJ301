/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.UserService;

/**
 * This servlet handle all login/logout and register function
 *
 * @author DTS
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
            HttpSession session = request.getSession();

            // Register logic
            if (request.getParameter("register") != null) {
                // If user just clicked from login form to register
                if (request.getParameter("submit") == null) {
                    request.getRequestDispatcher("/signup.jsp").forward(request, response);
                } else { // If user just submitted signup form
                    String username = request.getParameter("username");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String retype_password = request.getParameter("retype_password");

                    // Register
                    UserService service = new UserService();
                    try {
                        service.userRegister(username, email, password, retype_password);
                    } catch (Exception ex) {
                        System.out.println(ex.getCause());
                        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    response.sendRedirect("login");
                }
                
                return;
            }
            
            if (request.getParameter("logout") != null) { // if user choose to logout
                session.invalidate();
                response.sendRedirect("trang-chu");
                return;
            }
            
            if (session.getAttribute("username") != null) { // if user alrealy logged before
                response.sendRedirect("trang-chu");
            } else {
                if (request.getParameter("submit") == null) { // user not submitted login form yet
                    request.setAttribute("loginFailed", false);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else { // user clicked on login button form --> handle login logic
                    UserService service = new UserService();
                    
                    String username = request.getParameter("email");
                    String password = request.getParameter("password");
                    
                    boolean status = service.userLogin(username, password);
                    if (status) { // Username and password are correct
                        session.setAttribute("username", username);
//                        System.out.println("Current logged in user is: " + username);
                        int roleID = service.getUserRoleID(username);
                        if (roleID == 2) {
                            session.setAttribute("isAdmin", true);
                        } else {
                            session.setAttribute("isAdmin", false);
                        }
                        request.changeSessionId();
//                        request.getRequestDispatcher("trang-chu").forward(request, response);
                        response.sendRedirect("trang-chu");
                    } else { // username or password is wrong
                        request.setAttribute("loginFailed", true);
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
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
