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
            <h3>User ID: ${user.id}</h3>
            <h3>Username: ${user.username}</h3>
            <h3>Email: ${user.email}</h3>
        </div>
        <div>
            <h2 style="text-decoration:underline">${user.username}'s Ad's</h2>
            <c:forEach var="ad" items="${usersAds}">
                <h1>${ad.title}</h1>
                <p>${ad.description}</p>
            </c:forEach>
        </div>

    </div>




</body>
</html>
