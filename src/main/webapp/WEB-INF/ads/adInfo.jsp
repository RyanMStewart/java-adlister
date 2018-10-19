<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 10/10/18
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="${param.title}" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
    <div class="container">
        <h1><c:out value="${ad.title}"/></h1>
        <h2>Category: <c:out value="${ad.category}"/></h2>
        <h2>Created by: <c:out value="${creator.getUsername()}"/></h2>
        <h2>Description:</h2>
        <p><c:out value="${ad.getDescription()}"/></p>
    </div>

</body>
</html>

