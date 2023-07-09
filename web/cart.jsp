<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap, java.util.Map" %>
<%@page import="service.ProductService, entity.Product" %>

<%
    HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>)session.getAttribute("cart");
    ProductService productService = new ProductService();
    double total = 0; // store total price of all items in cart
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Shopping Cart</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        /*
** Style Simple Ecommerce Theme for Bootstrap 4
** Created by T-PHP https://t-php.fr/43-theme-ecommerce-bootstrap-4.html
*/
.bloc_left_price {
    color: #c01508;
    text-align: center;
    font-weight: bold;
    font-size: 150%;
}
.category_block li:hover {
    background-color: #007bff;
}
.category_block li:hover a {
    color: #ffffff;
}
.category_block li a {
    color: #343a40;
}
.add_to_cart_block .price {
    color: #c01508;
    text-align: center;
    font-weight: bold;
    font-size: 200%;
    margin-bottom: 0;
}
.add_to_cart_block .price_discounted {
    color: #343a40;
    text-align: center;
    text-decoration: line-through;
    font-size: 140%;
}
.product_rassurance {
    padding: 10px;
    margin-top: 15px;
    background: #ffffff;
    border: 1px solid #6c757d;
    color: #6c757d;
}
.product_rassurance .list-inline {
    margin-bottom: 0;
    text-transform: uppercase;
    text-align: center;
}
.product_rassurance .list-inline li:hover {
    color: #343a40;
}
.reviews_product .fa-star {
    color: gold;
}
.pagination {
    margin-top: 20px;
}
footer {
    background: #343a40;
    padding: 40px;
}
footer a {
    color: #f8f9fa!important
}

    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<jsp:include page="/commons/header.jsp" />

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">E-COMMERCE CART</h1>
     </div>
</section>

<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <!--<form id="cartForm" method="POST" action="cart">-->
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"> </th>
                            <th scope="col">Product</th>
                            <th scope="col">Available</th>
                            <th scope="col" class="text-center">Quantity</th>
                            <th scope="col" class="text-right">Price</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Map.Entry<Integer, Integer> cartItem : cart.entrySet()) { 
                            Product product = productService.getProductByID(cartItem.getKey());
                            // price of a item after discount
                            double priceAfterSaled = productService.getFinalPrice(product.getProductID());
                            // quantity of item in cart
                            int quantity = cart.get(product.getProductID());
                        %>
                            <tr>
                                <td><img src="https://dummyimage.com/50x50/55595c/fff" /> </td>
                              <!--Get product name by using productService-->
                                <td><%= product.getProductName() %></td>
                                <!--Future feature-->
                                <td>In stock</td>
                                <!--<input type="hidden" name="pId" value="<%= product.getProductID() %>" />-->
                                <td><input name="quantity" class="form-control" type="text" value="<%= quantity %>" /></td>
                                <%
                                    
                                %>
                                <td class="text-right"><%= priceAfterSaled %> €</td>
                                <%
                                    // Calculate total price
                                    total += priceAfterSaled * quantity;
                                %>
                                <td class="text-right"><a href="cart?service=remove&id=<%= product.getProductID() %>" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></a> </td>
                                <!-- <button onclick="location.href='cart?service=remove&id=<%= product.getProductID() %>'" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></button> -->
                            </tr>

                        <% } %>
                        
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Sub-Total</td>
                            <td class="text-right"><%= total %> €</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Shipping</td>
                            <td class="text-right">0,00 €</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input form="cartForm" type="submit" value="Update"></td>
                            <td></td>
                            <td></td>
                            <td><strong>Total</strong></td>
                            <td class="text-right"><strong><%= total %></strong></td>
                        </tr>
                    </tbody>
                </table>
                <!--</form>-->
                
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                    <button onclick="location.href='category?service=listAll'" type="button" class="btn btn-block btn-light">Continue Shopping</button>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <button onclick="location.href='cart?service=checkout'" class="btn btn-lg btn-block btn-success text-uppercase">Checkout</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="commons/footer.jsp" />
<script type="text/javascript">

</script>
</body>
</html>