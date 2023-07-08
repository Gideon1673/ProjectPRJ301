<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.Vector" %>
<%@page import="entity.ProductCategory" %>

<% 
    Vector<ProductCategory> categories = (Vector<ProductCategory>)request.getAttribute("categories");
    
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>ADD PRODUCTS - Bootsnipp.com</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>

<body>
<ul>
    <li><a href="AdminController?service=default">Admin home</a></li>
</ul>
<form class="form-horizontal" action="AdminProduct?service=add" method="POST">
<fieldset>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_id">PRODUCT ID</label>  
  <div class="col-md-4">
  <input id="product_id" name="product_id" placeholder="PRODUCT ID" class="form-control input-md" required="" type="number">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_name">PRODUCT NAME</label>  
  <div class="col-md-4">
  <input id="product_name" name="product_name" placeholder="PRODUCT NAME" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="manu_id">MANUFACTURER ID</label>  
  <div class="col-md-4">
  <input id="manu_id" name="manu_id" placeholder="MANUFACTURER ID" class="form-control input-md" required="" type="number">
    
  </div>
</div>

<!-- Search input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_categorie">PRODUCT CATEGORY</label>
  <div class="col-md-4">
    <select id="product_categorie" name="product_categorie" class="form-control">
        <% for(ProductCategory c : categories) { %>
        <option value="<%= c.getId() %>"><%= c.getName() %></option>
        <% } %>
    </select>
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="available_quantity">AVAILABLE QUANTITY</label>  
  <div class="col-md-4">
  <input id="available_quantity" name="available_quantity" placeholder="AVAILABLE QUANTITY" class="form-control input-md" required="" type="number">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="model_year">MODEL YEAR</label>  
  <div class="col-md-4">
  <input id="model_year" name="model_year" placeholder="MODEL YEAR" class="form-control input-md" required="" type="number">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="price">PRICE</label>  
  <div class="col-md-4">
  <input id="price" name="price" placeholder="PRICE" class="form-control input-md" required="" type="text">
    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="product_description">PRODUCT DESCRIPTION</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="product_description" name="product_description"></textarea>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="percentage_discount">DISCOUNT ID</label>  
  <div class="col-md-4">
  <input id="discount_id" name="discount_id" placeholder="PERCENTAGE DISCOUNT" class="form-control input-md" required="" type="text">
    
  </div>
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

<input type="submit" name="submit" value="Submit">

</fieldset>
</form>

<script type="text/javascript">

</script>
</body>
</html>
