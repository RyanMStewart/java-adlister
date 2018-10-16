<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
    <%--<jsp:include page="/WEB-INF/partials/registerPasswordCss.jsp"/>--%>
    <%--<link href="partials/passwordVerificationCSS.css" type="text/css" rel="stylesheet" >--%>
    <style><%@include file="/WEB-INF/css/passwordVerificationCSS.css"%></style>
</head>
<body>
    <%--<% boolean dup = (boolean) request.getAttribute("duplicate"); %>--%>
    <%--<% if(dup == null) dup = false; %>--%>
    <jsp:include page="partials/navbar.jsp" />
    <div class="container">
        <h1>Please fill in your information.</h1>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text">
                    <% if (request.getSession().getAttribute("duplicate") == null) {
                        request.setAttribute("duplicate", false);
                    } else if ((boolean) request.getSession().getAttribute("duplicate")){ %>
                            <p>Username is already taken, try again.</p>
                    <% } %>
                <%--<c:if test = "${dup == true}">--%>
                    <%--<p>Username is already taken, try again.</p>--%>
                <%--</c:if>--%>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input id="email" name="email" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password">
                <% if (request.getSession().getAttribute("passwordIsValid") == null) {
                    request.setAttribute("passwordIsValid", false);
                } else if (!(boolean) request.getSession().getAttribute("passwordIsValid")){ %>
                <p>Passwords does not meet the minimum requirements, try again.</p>
                <% } %>
                <div id="message" style="display:none">
                    <h4>Password must contain the following:</h4>
                    <p id="number" class="invalid">At least one <b>number</b></p>
                    <p id="symbol" class="invalid">At least one <b>symbol</b></p>
                    <p id="length" class="invalid">Minimum of <b>8 characters</b></p>
                </div>
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input id="confirm_password" name="confirm_password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block">
        </form>
    </div>
    <script><%@include file="/WEB-INF/js/passwordCheckLogic.js"%></script>
</body>
<%--<script>--%>
    <%--var msg = document.getElementById('message');--%>
    <%--var pwd = document.getElementById('password');--%>

    <%--var number = document.getElementById('number');--%>
    <%--var length = document.getElementById('length');--%>
    <%--var symbol = document.getElementById('symbol');--%>

    <%--// pwd.addEventListener(, function(e) {--%>
    <%--//     document.getElementById('message').setAttribute("style", "display:none");--%>
    <%--// });--%>
    <%--pwd.addEventListener("blur", function(e) {--%>
        <%--document.getElementById('message').setAttribute("style", "display:none");--%>
    <%--});--%>

    <%--pwd.addEventListener("focus", function(e) {--%>
        <%--// msg.style.display = "none";--%>
        <%--document.getElementById('message').setAttribute("style", "display:block");--%>
    <%--});--%>

    <%--// pwd.onfocus(function(e) {--%>
    <%--//     // msg.style.display = "block";--%>
    <%--//     document.getElementById('message').setAttribute("style", "display:block");--%>
    <%--// });--%>

    <%--pwd.addEventListener('keyup', function() {--%>
        <%--// Validate numbers--%>
        <%--var numbers = /[0-9]/g;--%>
        <%--if(pwd.value.match(numbers)) {--%>
            <%--number.classList.remove("invalid");--%>
            <%--number.classList.add("valid");--%>
        <%--} else {--%>
            <%--number.classList.remove("valid");--%>
            <%--number.classList.add("invalid");--%>
        <%--}--%>

        <%--// Validate symbols--%>
        <%--// var symbols = /!|@|#|\$|%|\^|&/g;--%>
        <%--var symbols = /[$-/:-?{-~!"^_`\[\]]/g;--%>
        <%--if(pwd.value.match(symbols)) {--%>
            <%--symbol.classList.remove("invalid");--%>
            <%--symbol.classList.add("valid");--%>
        <%--} else {--%>
            <%--symbol.classList.remove("valid");--%>
            <%--symbol.classList.add("invalid");--%>
        <%--}--%>
        <%--// Validate length--%>
        <%--if(pwd.value.length >= 8) {--%>
            <%--length.classList.remove("invalid");--%>
            <%--length.classList.add("valid");--%>
        <%--} else {--%>
            <%--length.classList.add("invalid");--%>
            <%--length.classList.remove("valid");--%>
        <%--}--%>
    <%--});--%>
<%--</script>--%>
</html>
