<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@include file="css/style.css" %>
    </style>
</head>
<body>

<form action="/InternetShop_war_exploded/login" method="post">
    <div class="container">
        <%--@declare id="user_surname"--%><%--@declare id="user_name"--%><%--@declare id="psw-repeat"--%><%--@declare id="psw"--%><%--@declare id="login"--%>
        <h2>Login</h2>
        <p class="text">Please fill in this form to sign into account.</p>
        <div class="form-wrap">
            <p>${errorMessage}</p>
            <label for="login"><b>Login</b></label>
            <br>
            <input type="text" placeholder="Enter Login" name="login" required>
            <br>
            <label for="psw"><b>Password</b></label>
            <br>
            <input type="password" placeholder="Enter Password" name="psw" required>
            <button type="submit" class="registerbtn">Login</button>
            <br>
            <p class="text">If you don't have account:
                <a href="/InternetShop_war_exploded/registration">Registration</a></p>
        </div>
    </div>
</form>
</body>
</html>
