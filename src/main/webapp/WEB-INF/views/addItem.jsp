<%--
  Created by IntelliJ IDEA.
  User: dima3
  Date: 18.09.2019
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@include file="css/style.css" %>
    </style>
</head>
<body>

<form action="/InternetShop_war_exploded/servlet/addItem" method="post">
    <div class="container">
        <%--@declare id="user_surname"--%><%--@declare id="user_name"--%><%--@declare id="psw-repeat"--%><%--@declare id="psw"--%><%--@declare id="login"--%><%--@declare id="price"--%><%--@declare id="model"--%><%--@declare id="item_name"--%>
        <h2>Create item</h2>
        <p>Please fill in this form to create an item.</p>
        <hr>
        <div class="form-wrap">
            <label for="item_name"><b>Name</b></label>
            <br>
            <input type="text" placeholder="Enter name" name="item_name" required>
            <br>

            <label for="model"><b>Model</b></label>
            <br>
            <input type="text" placeholder="Enter model" name="model" required>
            <br>

            <label for="price"><b>Price</b></label>
            <br>
            <input type="text" placeholder="Enter price" name="price" required>

            <button type="submit" class="registerbtn">Add Item</button>

        </div>
    </div>
</form>
</body>
</html>
