<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 10/10/18
  Time: 4:59 PM
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
    <h1>Ads that match your search term: </h1>
    <h2 style="text-decoration:underline">Matches by Title</h2>
    <c:forEach var="ad" items="${searchTitleAds}">
        <div class="col-md-6">
            <h2><a href="/view?id=${ad.getId()}&user=${ad.getUserId()}">${ad.title}</a></h2>
            <p>${ad.description}</p>
        </div>
    </c:forEach>
    <h2 style="text-decoration:underline">Matches by Description</h2>
    <c:forEach var="ad" items="${searchDescAds}">
        <div class="col-md-6">
            <h2><a href="/view?id=${ad.getId()}&user=${ad.getUserId()}">${ad.title}</a></h2>
            <p>${ad.description}</p>
        </div>
    </c:forEach>
</body>
</html>
