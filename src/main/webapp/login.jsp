<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 10/5/18
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

<c:if test="${param.username == 'admin' && param.password == 'password'}">
    <c:redirect url="/profile.jsp"/>
</c:if>
    <%--<form method="POST" action="login.jsp">--%>
            <%--<label for="username" id="user_name">Username:</label>--%>
            <%--<input type="text" id="username" name="username"/>--%>
        <%--<br>--%>
            <%--<label for="password" id="pass_word">Password:</label>--%>
            <%--<input type="password" id="password" name="password">--%>
        <%--<br>--%>
            <%--<button type="submit" id="submitButton">Submit</button>--%>

    <%--</form>--%>
    <%@include file="partials/loginPage.jsp" %>
</body>
</html>
