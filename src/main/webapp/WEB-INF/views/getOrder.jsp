<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="itemsInOrder" scope="request" type="java.util.List<internetshop.model.Item>"/>
<%--
  Created by IntelliJ IDEA.
  User: dima3
  Date: 18.09.2019
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Order</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@include file="css/style.css" %>
    </style>
</head>
<body>
<div class="container">
    <h2>Items in order</h2>
    <table class="table-item">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Model</th>
            <th>Price</th>
        </tr>
        <c:forEach var="item" items="${itemsInOrder}">
            <tr>
                <td>
                    <c:out value="${item.id}"/>
                </td>
                <td>
                    <c:out value="${item.name}"/>
                </td>
                <td>
                    <c:out value="${item.model}"/>
                </td>
                <td>
                    <c:out value="${item.price}"/>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="button-wrap">
        <a href="/InternetShop_war_exploded/servlet/allOrders">
            <button type="submit" class="registerbtn">All Orders</button>
        </a>
        <br>
        <a href="/InternetShop_war_exploded/servlet/allItems">
            <button type="submit" class="registerbtn">All Items</button>
        </a>
    </div>
</div>
</body>
</html>
