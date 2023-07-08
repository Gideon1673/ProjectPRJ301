<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entity.User" %>
<%
    User user = (User)request.getAttribute("user");
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
        <title>Update User Information</title>
    </head>
    <body>
        <form class="form-horizontal" action="AdminUser?service=update" method="POST">
            <fieldset>
                <input type="hidden" name="uID" value="<%= user.getId() %>"/>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="user_name">USER NAME</label>  
                    <div class="col-md-4">
                        <input id="user_name" name="user_name" placeholder="USER NAME" class="form-control input-md" required type="text" value="<%= user.getUsername() %>">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="user_fname">FULL NAME</label>  
                    <div class="col-md-4">
                        <input id="user_fname" name="user_fname" placeholder="FULL NAME" class="form-control input-md" required type="text" value="<%= user.getFullname() %>">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="u_email">EMAIL</label>  
                    <div class="col-md-4">
                        <input id="u_email" name="u_email" placeholder="USER EMAIL" class="form-control input-md" required type="text" value="<%= user.getEmail() %>">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="phone">PHONE</label>  
                    <div class="col-md-4">
                        <input id="phone" name="phone" placeholder="PHONE NUMBER" class="form-control input-md" required type="text" value="<%= user.getPhone() %>">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="u_city">CITY</label>  
                    <div class="col-md-4">
                        <input id="u_city" name="u_city" placeholder="CITY" class="form-control input-md" required type="text" value="<%= user.getCity() %>">
                    </div>
                </div>
                <input type="submit" value="submit" name="submit">
                <input type="reset">
            </fieldset>
        </form>
    </body>
</html>