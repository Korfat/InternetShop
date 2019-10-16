<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="users" scope="request" type="java.util.List<internetshop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@include file="css/style.css" %>
    </style>
</head>
<body>

<div class="container">
    <h2>Users</h2>
    <table class="table-item">
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>
                    <c:out value="${user.surname}"/>
                </td>
                <td>
                    <a href="/InternetShop_war_exploded/servlet/deleteUser?user_id=${user.id}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="button-wrap">
        <a href="/InternetShop_war_exploded/servlet/addItem">
            <button type="submit" class="registerbtn">Add Items</button>
        </a>
        <br>
        <a href="/InternetShop_war_exploded/injectData">
            <button type="submit" class="registerbtn">Inject Data</button>
        </a>
        <br>
        <a href="/InternetShop_war_exploded/servlet/allItems">
            <button type="submit" class="registerbtn">All Items</button>
        </a>
    </div>
</div>
</body>
</html>
