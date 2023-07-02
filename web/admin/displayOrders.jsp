<%-- 
    Document   : displayOrders
    Created on : Jun 28, 2023, 4:57:20 PM
    Author     : DTS
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entity.OrderDetail, java.util.Vector"%>
<%
    Vector<OrderDetail> orders = (Vector<OrderDetail>)request.getAttribute("orders");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order details</title>
    </head>
    <body>

        <ul>
            <li><a href="AdminController?service=default">Admin home</a></li>
        </ul>

        <table border="1">
            <caption>All order details</caption>
            <tr>
                <th>OrderID</th>
                <th>UserID</th>
                <th>Order status</th>
                <th>Order date</th>
                <th>Total</th>
            </tr>
            <% for(OrderDetail o : orders) { %>
            <tr>
                <td><%= o.getOrderID() %></td>
                <td><%= o.getUserID()%></td>
                <td><%= o.getOrderStatus() %></td>
                <td><%= o.getOrderDate() %></td>
                <td><%= o.getTotal() %></td>
                <td><a href="#">Delete</a></td>
            </tr>
            <% } %>

        </table>
    </body>
</html>
