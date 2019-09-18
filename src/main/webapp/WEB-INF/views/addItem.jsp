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
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: black;
        }

        * {
            box-sizing: border-box;
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
            background-color: white;
        }

        /* Full-width input fields */
        input[type=text], input[type=password] {
            width: 25%;
            padding: 6px;
            margin: 11px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Overwrite default styles of hr */
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 15px;
        }

        /* Set a style for the submit button */
        .registerbtn {
            background-color: #4CAF50;
            color: white;
            padding: 11px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 120px;
            opacity: 0.9;
        }

        .registerbtn:hover {
            opacity: 1;
        }

        /* Add a blue text color to links */
        a {
            color: dodgerblue;
        }

        /* Set a grey background color and center the text of the "sign in" section */
        .signin {
            background-color: #f1f1f1;
            text-align: center;
        }
    </style>
</head>
<body>
Let's create a new user!

<form action="/InternetShop_war_exploded/addItem" method="post">
    <div class="container">
        <%--@declare id="user_surname"--%><%--@declare id="user_name"--%><%--@declare id="psw-repeat"--%><%--@declare id="psw"--%><%--@declare id="login"--%><%--@declare id="price"--%><%--@declare id="model"--%><%--@declare id="item_name"--%>
            <h2>Create item</h2>
        <p>Please fill in this form to create an item.</p>
        <hr>

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
        <hr>

        <button type="submit" class="registerbtn">Add Item</button>
    </div>
</form>
</body>
</html>
