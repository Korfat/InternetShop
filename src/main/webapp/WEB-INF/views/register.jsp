<%--
  Created by IntelliJ IDEA.
  User: dima3
  Date: 17.09.2019
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style><%@include file="css/style.css"%></style>
</head>
<body>
Let's create a new user!

<form action="/InternetShop_war_exploded/registration" method="post">
    <div class="container">
        <%--@declare id="user_surname"--%><%--@declare id="user_name"--%><%--@declare id="psw-repeat"--%><%--@declare id="psw"--%><%--@declare id="login"--%>
            <h2>Register</h2>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <br>
        <input type="text" placeholder="Enter Login" name="login" required>
        <br>
        <label for="psw"><b>Password</b></label>
        <br>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <br>
        <label for="psw-repeat"><b>Repeat Password</b></label>
        <br>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
        <br>
        <label for="user_name"><b>Name</b></label>
        <br>
        <input type="text" placeholder="Enter name" name="user_name" required>
        <br>
        <label for="user_surname"><b>Surname</b></label>
        <br>
        <input type="text" placeholder="Enter surname" name="user_surname" required>
        <hr>

        <button type="submit" class="registerbtn">Register</button>
    </div>
</form>
</body>
</html>
