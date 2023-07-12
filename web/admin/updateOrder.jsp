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
        <form class="form-horizontal" action="AdminOrder?service=update" method="POST">
            <fieldset>
                <input type="hidden" name="oID" value="<%= o.getOrderID() %>"/>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="oStatus">Order status</label>  
                    <div class="col-md-4">
                        <input id="oStatus" name="oStatus" placeholder="Order status" class="form-control input-md" required type="number" value="<%= o.getOrderStatus() %>">
                    </div>
                </div>
                <input type="submit" value="submit" name="submit">
                <input type="reset">
            </fieldset>
        </form>
    </body>
</html>