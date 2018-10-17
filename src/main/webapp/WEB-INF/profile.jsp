<%@ page import="com.codeup.adlister.models.Ad" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codeup.adlister.dao.DaoFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>

        <div>
            <h2 style="text-decoration:underline">User Stats</h2>
            <h3>User ID: <c:out value="${user.id}"/></h3>
            <h3>Username: <c:out value="${user.username}"/></h3>
            <h3>Email: <c:out value="${user.email}"/></h3>
            <input type="button" value="Edit Profile" onclick="window.location.href='/profile/edit'" />
        </div>
        <%--<% List<Ad> usersAds = DaoFactory.getAdsDao().getAdsByUser(); %>--%>
        <div>
            <h2 style="text-decoration:underline"><c:out value="${user.username}"/>'s Ad's</h2>
            <c:forEach var="ad" items="${usersAds}">
                <h1><a href="/view?id=${ad.id}&user=${ad.getUserId()}"><c:out value="${ad.title}"/></a></h1>
                <p><c:out value="${ad.description}"/></p>
            </c:forEach>
        </div>

    </div>




</body>
</html>
