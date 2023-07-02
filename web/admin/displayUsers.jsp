<%-- 
    Document   : displayUsers
    Created on : Jun 28, 2023, 9:03:49 AM
    Author     : DTS
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.User" %>
<%
    Vector<User> users = (Vector<User>)request.getAttribute("users");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail of Users</title>
    </head>
    <body>
        
        <ul>
            <li><a href="AdminController?service=default">Admin home</a></li>
        </ul>
        
        <table border="1">
            <caption>Detail of all users</caption>
            <tr>
                <th>ID</th>
                <th>USername</th>
                <th>Email</th>
                <th>Phone</th>
                <th>City</th>
            </tr>

            <% for(User u : users) { %>
                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= u.getUsername() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getPhone() %></td>
                    <td><%= u.getCity() %></td>
                    <td><a href="#">Update</a></td>
                </tr>
            <% } %>
            
        </table>
    </body>
</html>
