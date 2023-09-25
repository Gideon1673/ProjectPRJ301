<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entity.OrderDetail" %>

<%
    OrderDetail o = (OrderDetail)request.getAttribute("orderDetails");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <title>Update Order Information</title>
    </head>
    <body>
        <table border="1">
            <caption>Order details</caption>
            <tr>
                <th>OrderID</th>
                <th>UserID</th>
                <th>Order status</th>
                <th>Order date</th>
                <th>Total</th>
            </tr>
            <tr>
                <td><a href="AdminOrder?service=orderDetails&id=<%= o.getOrderID() %>"><%= o.getOrderID() %></a></td>
                <td><%= o.getUserID()%></td>
                <td><%= o.getOrderStatus() == 1 ? "Pending" : "Completed" %></td>
                <td><%= o.getOrderDate() %></td>
                <td> $<%= o.getTotal() %> </td>
            </tr>
        </table>
            
        <form class="form-horizontal" action="AdminOrder?service=update" method="POST">
            <fieldset>
                <input type="hidden" name="oID" value="<%= o.getOrderID() %>" readonly/>
                <input type="hidden" name="uID" value="<%= o.getUserID() %>" readonly>
                
<!--                <div class="form-group">
                    <label class="col-md-4 control-label" for="oStatus">Order status</label>  
                    
                    <div class="col-md-4">
                        <input id="oStatus" name="oStatus" placeholder="Order status" class="form-control input-md" required type="number" value="<%= o.getOrderStatus() %>">
                    </div>
                </div>-->
                    
                    <select id="oStatus" name="oStatus" >
                        <option value="1" <%= o.getOrderStatus() == 1 ? "selected" : "" %>>Pending</option>
                        <option value="2" <%= o.getOrderStatus() == 2 ? "selected" : "" %>>Completed</option>
                    </select>
                <input type="submit" value="submit" name="submit">
                <input type="reset">
            </fieldset>
        </form>
    </body>
</html>