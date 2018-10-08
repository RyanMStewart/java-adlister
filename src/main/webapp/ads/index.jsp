<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 10/8/18
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="/partials/navbar.jsp"%>
    <h1>Products Listing</h1>
    <c:forEach var="product" items="${products}">
        <div class="product">
            <h2>${product.name}</h2>
            <p>Price: $ ${product.price}</p>
        </div>
    </c:forEach>

</body>
</html>
