<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= "some title" %></title>
    <%--<style><%@include file="partials/navbar.css"%></style>--%>
</head>
<body>
<%@include file="partials/navbar.jsp"%>

<%--<ul>--%>
    <%--<li><a href="/">Home</a></li>--%>
    <%--<li><a href="/login.jsp">Login</a></li>--%>
    <%--<li><a href="/profile.jsp">Profile</a></li>--%>
    <%--<li><a href="#">About</a></li>--%>
<%--</ul>--%>
    <c:if test="true">
        <h1>Variable names should be very descriptive</h1>
    </c:if>
    <c:if test="false">
        <h1>single letter variable names are good</h1>
    </c:if>
</body>
</html>
