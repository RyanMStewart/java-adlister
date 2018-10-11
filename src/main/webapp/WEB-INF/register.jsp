<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
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
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input id="confirm_password" name="confirm_password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block">
        </form>
    </div>
</body>
</html>
