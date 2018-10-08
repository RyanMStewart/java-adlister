<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <jsp:include page="/partials/head.jsp">
        <jsp:param name="title" value="Welcome to Ad Lister!" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/partials/navbar.jsp" />
    <h1>Ads Listing</h1>
    <c:forEach var="ad" items="${ads}">
        <div class="ad">
            <h2>${ad.getTitle()}</h2>
            <p>Description: ${ad.getDescription()}</p>
        </div>
    </c:forEach>

</body>
</html>
