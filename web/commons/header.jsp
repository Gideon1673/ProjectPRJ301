<%-- 
    Document   : header
    Created on : Jun 25, 2023, 10:17:53 AM
    Author     : DTS
--%>

<%@page import="java.util.HashMap" %>

<%
    HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>)session.getAttribute("cart");
    if(cart == null) {
        cart = new HashMap<>();
        session.setAttribute("cart", cart);
    }
    
//    String uri = request.getRequestURI();
//    String[] parts = uri.split("/");
//    String controller = parts[parts.length - 1];
//    int dotIndex = controller.lastIndexOf(".");
//    if (dotIndex != -1) {
//        controller = controller.substring(0, dotIndex);
//    }
//    System.out.println(controller); // DEBUG
//    String searchQuery = controller + "?service=search&id=";

%>




<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="trang-chu">Simple Ecommerce</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto header-navbar">
                <li class="nav-item">
                    <a class="nav-link" href="trang-chu">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="category">Categories <span class="sr-only">(current)</span></a>
                </li>
<!--                <li class="nav-item">
                    <a class="nav-link" href="product.html">Product</a>
                </li>-->
                <li class="nav-item">
                    <a class="nav-link" href="cart?service=displayAll">Cart</a>
                </li>
<!--                <li class="nav-item">
                    <a class="nav-link" href="contact.html">Contact</a>
                </li>-->
            </ul>

            <form class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <form method="POST" action="abc" id="search-field">
                        <input name="search" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                        <div class="input-group-append">
                            <button type="button" class="btn btn-secondary btn-number" form="search-field">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </form>                    
                </div>
                
                <a class="btn btn-success btn-sm ml-3" href="cart?service=displayAll">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <span class="badge badge-light"><%= cart.size() %></span>
                </a>
            </form>
        </div>
    </div>
</nav>

<script>
    // FOR FUN ONLY

    function getSearchQuery() {
        let searchQuery = document.querySelectorAll('.search-input-field');
        return searchQuery[0].value;
    }

    // Use JS to send POST request to the server -- for experiment too
    function sendSearchPOST(searchParam) {
        console.log("Search string is " + searchParam);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", window.location.href, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({
            service: "search",
            id: searchParam
        }));
    }

    function search() {
        let searchText = getSearchQuery();
        sendSearchPOST(searchText);
    }

    // Active the <li> item of <ul> navbar-nav based on the URL
    function activeListItem() {
        let path = window.location.pathname;
        let list = document.querySelector(".header-navbar");

        if(path === '/Project-Final/category') {
            
        }

    }

    
</script>