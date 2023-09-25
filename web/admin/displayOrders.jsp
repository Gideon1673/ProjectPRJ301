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
                <td><a href="AdminOrder?service=orderDetails&id=<%= o.getOrderID() %>"><%= o.getOrderID() %></a></td>
                <td><%= o.getUserID()%></td>
                <td><%= o.getOrderStatus() == 1 ? "Pending" : "Completed" %></td>
                <td><%= o.getOrderDate() %></td>
                <td> $<%= o.getTotal() %> </td>
                <td><a href="AdminOrder?service=delete&oID=<%= o.getOrderID() %>">Delete</a></td>
                <td><a href="AdminOrder?service=update&oID=<%= o.getOrderID() %>">Update order info</a></td>
            </tr>
            <% } %>

        </table>
    </body>
</html>
