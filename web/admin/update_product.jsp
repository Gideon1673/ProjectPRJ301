<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.Vector" %>
<%@page import="entity.ProductCategory, entity.Product, service.ProductService" %>

<% 
    Vector<ProductCategory> categories = (Vector<ProductCategory>)request.getAttribute("categories");
    Product product = (Product)request.getAttribute("product");
    ProductService pService = new ProductService();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>UPDATE PRODUCTS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
    <h1>Update <%= product.getProductName() %></h1>
    
<form class="form-horizontal" action="AdminProduct?service=update" method="POST">
<fieldset>
    
<!-- Text input-->
<div class="form-group" hidden>
  <label class="col-md-4 control-label" for="product_id">PRODUCT ID</label>  
  <div class="col-md-4">
  <input id="product_id" name="product_id" placeholder="PRODUCT ID" class="form-control input-md" required type="number" value="<%= product.getProductID() %>">
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_name">PRODUCT NAME</label>  
  <div class="col-md-4">
  <input id="product_name" name="product_name" placeholder="PRODUCT NAME" class="form-control input-md" required type="text" value="<%= product.getProductName() %>">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="manu_id">MANUFACTURER ID</label>  
  <div class="col-md-4">
  <input id="manu_id" name="manu_id" placeholder="MANUFACTURER ID" class="form-control input-md" required type="number" value="<%= product.getManuID() %>">
    
  </div>
</div>

<!-- Search input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_categorie">PRODUCT CATEGORY</label>
  <div class="col-md-4">
    <select id="product_categorie" name="product_categorie" class="form-control">
        <% for(ProductCategory c : categories) { %>
            <% if(product.getCategoryID() == c.getId()) { %>
                <option selected value="<%= c.getId() %>"><%= c.getName() %></option>
            <% } else { %>
                <option value="<%= c.getId() %>"><%= c.getName() %></option>
            <% } %>
        <% } %>
    </select>
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="available_quantity">AVAILABLE QUANTITY</label>  
  <div class="col-md-4">
  <input id="available_quantity" name="available_quantity" placeholder="AVAILABLE QUANTITY" class="form-control input-md" required type="number" value="<%= product.getQuantity() %>">
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="model_year">MODEL YEAR</label>  
  <div class="col-md-4">
  <input id="model_year" name="model_year" placeholder="MODEL YEAR" class="form-control input-md" required type="number" value="<%= product.getModelYear() %>">
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="price">PRICE</label>  
  <div class="col-md-4">
  <input id="price" name="price" placeholder="PRICE" class="form-control input-md" required= type="text" value="<%= product.getPrice() %>">
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_description">PRODUCT DESCRIPTION</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="product_description" name="product_description"><%= product.getDescription() %></textarea>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="percentage_discount">DISCOUNT ID</label>  
  <div class="col-md-4">
  <input id="discount_id" name="discount_id" placeholder="DISCOUNT ID" class="form-control input-md" required type="text" value="<%= product.getDiscountID() %>">
  </div>
</div>
  
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="img_path">Image Path</label>  
  <div class="col-md-4">
  <input id="img_path" name="img_path" placeholder="Image path" class="form-control input-md" required type="text" value="<%= product.getImg_path() %>">
  </div>
</div>

<!--Hidden div for JS read Java variables-->
<div id="javaValues" style="display: none;">
    <div id="prodStatus"><%= product.isStatus() %></div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="status">STATUS</label>
  <div class="col-md-4">
    <select id="status" name="status" class="form-control">
      <option value="1">True</option>
      <option value="0">False</option>
    </select>
  </div>
</div>

<script>
    // I use JS here for select correct initial value (of course there are many ways, but I want to test)
    let prod = $("#prodStatus").html().trim();
    let status = document.querySelector("#status");
    
    console.log(prod);
    console.log(status);
    if(prod === 'true') {
        console.log('come here');
        status.selectedIndex = 0;
    } else {
        console.log('and here');
        status.selectedIndex = 1;
    }
</script>

<input type="submit" name="submit" value="Submit">

</fieldset>
</form>

<script type="text/javascript">

</script>
</body>
</html>
