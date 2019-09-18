<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="items" scope="request" type="java.util.List<internetshop.model.Item>"/>
<%--
  Created by IntelliJ IDEA.
  User: dima3
  Date: 17.09.2019
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Items</title>
</head>
<body>
<p>Items:</p>

<table border="2">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Add</th>
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
            <td>
                <a href="/InternetShop_war_exploded/addItemToBucket?item_id=${item.id}">ADD</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/InternetShop_war_exploded/addItem"><button type="submit" class="registerbtn">New Item</button></a>
<br>
<a href="/InternetShop_war_exploded/injectData"><button type="submit" class="registerbtn">Inject Data</button></a>
<br>
<a href="/InternetShop_war_exploded/bucket"><button type="submit" class="registerbtn">Bucket</button></a>

</body>
</html>
