<%-- 
    Document   : displayOrderItems
    Created on : Jul 21, 2023, 12:11:04 AM
    Author     : DTS
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.OrderItem" %>
<%
    Vector<OrderItem> oItems = (Vector<OrderItem>) request.getAttribute("orderItems");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All order items</title>
    </head>
    <body>
        
        <ul>
            <li><a href="AdminController?service=default">Admin home</a></li>
        </ul>
        
        <table border="1">
            <caption>All order items</caption>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Order ID</th>
                    <th>Product ID</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <% for (OrderItem oI : oItems ) { %>
                <tr>
                    <td><%= oI.getId() %></td>
                    <td><%= oI.getOrderID() %></td>
                    <td><%= oI.getProductID() %></td>
                    <td><%= oI.getQuantity() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>

    </body>
</html>
