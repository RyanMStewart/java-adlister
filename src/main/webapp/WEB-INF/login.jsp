<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Please Log In</h1>
        <form action="/login" method="POST">
            <% if (request.getSession().getAttribute("stickyUsername") ==  null)  { %>
            <% request.getSession().setAttribute("stickyUsername", ""); %>
            <% } else if (request.getSession().getAttribute("stickyUsername") != null ) { %>
            <% String stickyUsername = (String) request.getSession().getAttribute("stickyUsername"); %>
            <% } %>

            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text" placeholder="${sessionScope.stickyUsername}">
            </div>
            <% if (request.getSession().getAttribute("failedLogin") ==  null)  {
                    request.getSession().setAttribute("failedLogin", false);
            } else if ( (boolean) request.getSession().getAttribute("failedLogin") ) { %>
                    <p>The password for this username did not match our records, please try again.</p>
            <% } %>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password" >
            </div>
            <input type="submit" class="btn btn-primary btn-block" value="Log In">
        </form>
    </div>
</body>
</html>
