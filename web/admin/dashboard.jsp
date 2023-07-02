<%-- 
    Document   : dashboard
    Created on : Jun 19, 2023, 2:30:44 PM
    Author     : DTS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% String path = (String)request.getAttribute("path"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>
    </head>
    <body>
        <p>Welcome: <a href="AdminController?service=default"><b>${username}</b></a></p>
        <div class="container">
            <div class="sidebar-container">
                <a href="AdminController?service=product">Product Manager</a>
                <a href="AdminController?service=customer">Customer Manager</a>
                <a href="AdminController?service=order">Bill Manager</a>
            </div>
        </div>
        
        <% if(path != null ) { %>
            <jsp:include page="<%= path %>"></jsp:include>
        <% } %>
        
    </body>
</html>
