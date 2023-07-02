<%-- 
    Document   : displayManus
    Created on : Jun 27, 2023, 6:39:29 PM
    Author     : DTS
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entity.Manufacturer, java.util.Vector" %>

<%
    Vector<Manufacturer> manus = (Vector<Manufacturer>)request.getAttribute("manus");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
            <li><a href="AdminController?service=default">Admin home</a></li>
        </ul>
        
        <table border="1">
            <caption>Detail of all manufacturers</caption>
            <tr>
                <th>ManuID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Address</th>
            </tr>

            <% for(Manufacturer m : manus ) { %>
            <tr>
                <td><%= m.getId() %></td>
                <td><%= m.getName() %></td>
                <td><%= m.getPhone() %></td>
                <td><%= m.getEmail() %></td>
                <td><%= m.getAddress() %></td>
                <td><a href="#">Update</a></td>
            </tr>
            <% } %>

        </table>
    </body>
</html>
