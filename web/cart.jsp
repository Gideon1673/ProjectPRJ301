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
                            int productID = product.getProductID();
                            // price of a item after discount
                            double priceAfterSaled = productService.getFinalPrice(productID);
                            // quantity of item in cart
                            int quantity = cart.get(productID);
                        %>
                            <tr>
                                <td><img src="https://dummyimage.com/50x50/55595c/fff" /> </td>
                              <!--Get product name by using productService-->
                                <td><%= product.getProductName() %></td>
                                <!--Stock-->
                                <% if(product.getQuantity() > 0) { %>
                                <td>In stock (available: <%= product.getQuantity() %>)</td>
                                <% } else { %>
                                <td><i>Out of orders</i></td>
                                <% } %>
                                
                                <td>
                                    <div class="input-group mb-3 cart-item" data-item-id="<%= productID %>">
                                        <div class="input-group-prepend">
                                            <!-- <a style="color: white" class="minus-btn btn btn-danger btn-number"  data-type="minus" data-field="" href="cart?service=minusItem&id=<%= productID %>">
                                                <i class="fa fa-minus"></i>
                                            </a> -->

                                            <button class="minus-btn btn btn-danger btn-number"  data-type="minus" data-field="">
                                                <i class="fa fa-minus"></i>
                                            </button>
                                        </div>
                                        <input type="number" class="form-control quantity" name="quantity" min="1" max="100" value="<%= quantity %>" readonly>
                                        <div class="input-group-append">
                                            <!-- <a style="color: white" class="plus-btn btn btn-success btn-number" data-type="plus" data-field="" href="cart?service=plusItem&id=<%= productID %>">
                                                <i class="fa fa-plus"></i>
                                            </a> -->

                                            <button class="plus-btn btn btn-success btn-number" data-type="plus" data-field="">
                                                <i class="fa fa-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                </td>
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
                            <!--<td><input form="cartForm" type="submit" value="Update"></td>-->
                            <td></td>
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
<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    $(document).ready(function () {
        // Attach click event handler to the plus button
        $(".plus-btn").on("click", function () {
            var itemDiv = $(this).closest(".cart-item");
            var itemId = itemDiv.data("item-id");

            // Get the current quantity displayed in the cart for this item
            var currentQuantity = parseInt(itemDiv.find(".quantity").val().trim());

            // Make an AJAX request to the server to fetch the available quantity
            $.ajax({
                url: "cart_api", // Server endpoint to fetch the available quantity
                type: "GET",
                data: { itemId: itemId }, // Send the item ID as a parameter in the request
                success: function (availableQuantity) {
                    // This function is called when the AJAX request is successful
                    // availableQuantity contains the response from the server, which is the available quantity for the item

                    // Check if the current quantity is less than the available quantity
                    if (!isNaN(currentQuantity) && currentQuantity < availableQuantity) {
                        // If the current quantity is less than available, update the displayed quantity by increasing it by 1
                        itemDiv.find(".quantity").val(currentQuantity + 1);
                    updateSessionDB(itemId, currentQuantity + 1);
                    } else {
                        // If the current quantity is equal to or exceeds the available quantity,
                        // you can handle this case here, for example, displaying an error message.
                        // Depending on your use case, you may take different actions, like disabling the plus button or showing a warning.
                    }
                },
                error: function () {
                    // This function is called if the AJAX request encounters an error
                    // You can handle the error response here, such as showing an error message to the user.
                }
            });
        });

        // Attach click event handler to the minus button
        $(".minus-btn").on("click", function () {
            var itemDiv = $(this).closest(".cart-item");
            var itemId = itemDiv.data("item-id");

            // Get the current quantity displayed in the cart for this item
            var currentQuantity = parseInt(itemDiv.find(".quantity").val().trim());
            // Check if the current quantity is greater than 1 (minimum allowed value)
            if (!isNaN(currentQuantity) && currentQuantity > 1) {
                // If the current quantity is greater than 1, update the displayed quantity by decreasing it by 1
                itemDiv.find(".quantity").val(currentQuantity - 1);
                updateSessionDB(itemId, currentQuantity - 1);
            } else {
                // If the current quantity is already 1 or less, you may handle this case if needed.
                // For example, showing an error message or disabling the minus button.
            }
        });
    });

    function updateSessionDB(productID, quantity) {
        // Make an AJAX request to update the server session with the new quantity
        $.ajax({
            url: "/updateQuantity", // Server endpoint to update the quantity in the session
            type: "POST",
            data: { itemId: productID, quantity: quantity }, // Send the item ID and updated quantity as parameters
            success: function () {
                // This function is called when the AJAX request is successful
                // You can update any UI elements or perform other actions after successfully updating the session.
                // For example, you can update the displayed quantity in the cart to the new value.
            },
            error: function () {
                // This function is called if the AJAX request encounters an error
                // You can handle the error response here, such as showing an error message to the user.
            }
        });
    }

</script>

</body>
</html>