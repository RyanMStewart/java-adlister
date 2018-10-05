<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 10/5/18
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style><%@include file="loginFormStyle.css"%></style>
</head>
<body>
    <form class="login" method="POST" action="/login.jsp">
        <h1 class="login-title">Login Here</h1>
        <input type="text" class="login-input" id="username" name="username" placeholder="Email Address" autofocus>
        <input type="password" class="login-input" id="password" name="password" placeholder="Password">
        <input type="submit" value="Lets Go" class="login-button">
        <p class="login-lost"><a href="">Forgot Password?</a></p>
    </form>
</body>
</html>
