<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="items" scope="request" type="java.util.List<internetshop.model.Item>"/>
<%--
  Created by IntelliJ IDEA.
  User: dima3
  Date: 18.09.2019
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complete Order</title>
</head>
<body>
<p>Items in order:</p>

<table border="2">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.id}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/InternetShop_war_exploded/allOrders"><button type="submit" class="registerbtn">All Orders</button></a>
<br>
<a href="/InternetShop_war_exploded/allItems"><button type="submit" class="registerbtn">All Items</button></a>

</body>
</html>
