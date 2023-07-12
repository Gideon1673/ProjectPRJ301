<%-- 
    Document   : displayProducts
    Created on : Jun 21, 2023, 1:48:27 PM
    Author     : DTS
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Product" %>

<%
    Vector<Product> products = (Vector<Product>)request.getAttribute("products");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
    </head>
    
<body>
    <ul>
        <li><a href="AdminController?service=default">Admin home</a></li>
    </ul>
    
        <table border="1">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product name</th>
                    <th>Manufac ID</th>
                    <th>Model year</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Discount ID</th>
                    <th>Description ID</th>
                </tr>
            </thead>
            <tbody>
                
            <% for(Product p : products ) { %>
            <tr>
                <td><%= p.getProductID() %></td>
                <td><%= p.getProductName() %></td>
                <td><%= p.getManuID() %></td>
                <td><%= p.getModelYear() %></td>
                <td><%= p.getPrice() %></td>
                <td><%= p.getQuantity() %></td>
                <td><%= p.getCategoryID() %></td>
                <td><%= p.isStatus() %></td>
                <td><%= p.getDiscountID() %></td>
                <td><%= p.getDescription() %></td>
                <td><a href="AdminProduct?service=update&id=<%= p.getProductID() %>">Update</a></td>
                <!--<td>Delete</td>-->
            </tr>
            <% } %>
            </tbody>
        </table>
</body>
</html>
