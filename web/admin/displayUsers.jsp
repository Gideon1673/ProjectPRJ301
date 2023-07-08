<%-- 
    Document   : displayUsers
    Created on : Jun 28, 2023, 9:03:49 AM
    Author     : DTS
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.User, service.UserService" %>
<%
    Vector<User> users = (Vector<User>)request.getAttribute("users");
    UserService uService = new UserService();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail of Users</title>

        <style>
            table {
                text-align: center;
            }
        </style>
    </head>
    <body>
        
        <ul>
            <li><a href="AdminController?service=default">Admin home</a></li>
        </ul>
        
        <table border="1">
            <caption>Detail of all users</caption>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Full name</th>
                <th>Role</th>
                <th>Email</th>
                <th>Phone</th>
                <th>City</th>
                <th>Update user info</th>
                <th>Delete user</th>
            </tr>

            <% for(User u : users) { %>
                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= u.getUsername() %></td>
                    <td><%= u.getFullname() %></td>
                    <td><%= uService.getUserRole(u.getUsername()) %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getPhone() %></td>
                    <td><%= u.getCity() %></td>
                    <td><a href="AdminUser?service=update&uID=<%= u.getId() %>">Update</a></td>
                </tr>
            <% } %>
            
        </table>
    </body>
</html>
