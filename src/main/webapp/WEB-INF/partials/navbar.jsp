<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default justify-content-between">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/ads">Adlister</a>
        </div>

        <ul class="nav navbar-nav navbar-right">

        <%--CHECK IF A PERSON IS LOGGED IN    --%>
            <% if (request.getSession().getAttribute("user") == null) { %>
            <li><a href="/login">Login</a></li>
            <li><a href="/register">Register</a></li>
            <%--<% String contextPath = pageContext.contextPath(); %>--%>
            <%--<% System.out.println(contextPath); %>--%>
            <%--<li>  <%= contextPath %></li>--%>
            <%--<li><c:out =  ${pageContext.request.contextPath}/ ></li>--%>
        <%--IF PERSON IS LOGGED IN DISPLAY USERS LINKS        --%>
                <% } else if (request.getSession().getAttribute("user") != null) { %>
            <li><a href="/logout">Logout</a></li>
            <li><a href="/ads/create">Create Ad</a></li>
            <li><a href="/profile">Profile</a></li>
            <%--<li><% Object test = request.getRequestURI(); %> <%= test.toString() %></li>--%>
        <%--IF PERSON IS ON AD-VIEW PAGE AND THEY ARE THE CREATOR ALLOW EDITING    --%>
                <% } %>
            <% if (request.getSession().getAttribute("loggedInCreator") == null) { %>
            <% request.getSession().setAttribute("loggedInCreator", false); } %>
            <% if (request.getRequestURI().toString().equals("/WEB-INF/ads/adInfo.jsp") && (boolean) request.getSession().getAttribute("loggedInCreator")) { %>
            <li><a href="/delete?post=${ad.id}">Delete</a></li>
            <li><a href="/update?post=${ad.id}">Update</a></li>
                <% } %>
        </ul>
        <form style="margin-top:1em" class="form-inline align-bottom" method="GET" action="/search">
            <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>


    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->

</nav>

