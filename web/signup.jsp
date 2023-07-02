<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Registration or Sign Up form in HTML CSS | CodingLab </title> 
    <link rel="stylesheet" href="./css/signup-style.css">
   </head>
<body>
  <div class="wrapper">
    <h2>Registration</h2>

    <form action="login?register=true" method="POST">
      <div class="input-box">
        <input type="text" placeholder="Enter your name" required name="username">
      </div>
      <div class="input-box">
        <input type="email" placeholder="Enter your email" required name="email">
      </div>
      <div class="input-box">
        <input type="password" placeholder="Create password" required name="password">
      </div>
      <div class="input-box">
        <input type="password" placeholder="Confirm password" required name="retype_password">
      </div>
      <div class="policy">
        <input type="checkbox" required>
        <h3>I accept all terms & condition</h3>
      </div>
      <div class="input-box button">
        <input name="submit" type="Submit" value="Register Now">
      </div>
      <div class="text">
        <h3>Already have an account? <a href="login">Login now</a></h3>
      </div>
    </form>
  </div>
</body>
</html>