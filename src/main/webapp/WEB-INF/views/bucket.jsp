<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="itemsInBucket" scope="request" type="java.util.List<internetshop.model.Item>"/>
<%--
  Created by IntelliJ IDEA.
  User: dima3
  Date: 18.09.2019
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@include file="css/style.css" %>
    </style>
</head>
<body>
<div class="container">
    <h2>Items in bucket</h2>
    <table class="table-item">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Model</th>
            <th>Price</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="item" items="${itemsInBucket}">
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
                <td>
                    <a href="/InternetShop_war_exploded/servlet/deleteFromBucket?item_id=${item.id}">DEL</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="button-wrap">
    <a href="/InternetShop_war_exploded/servlet/completeOrder">
        <button type="submit" class="registerbtn">Complete Order</button>
    </a>
    <br>
    <a href="/InternetShop_war_exploded/servlet/allItems">
        <button type="submit" class="registerbtn">All Items</button>
    </a>
    </div>
</div>
</body>
</html>
