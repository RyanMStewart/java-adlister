<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 10/12/18
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <form class="inline-form" method="POST" action="/profile/edit">
        <div class="form-group">
            <label for="email">Old Username:</label>
            <input id="oldUsername" name="OldUsername" class="form-control" type="text"/>
        </div>
        <div class="form-group">
            <label for="title">New Username:</label>
            <input id="title" name="newUsername" class="form-control" type="text">
            <% if (request.getSession().getAttribute("usernameDuplicate") == null) {
                request.setAttribute("usernameDuplicate", false);
            } else if ((boolean) request.getSession().getAttribute("usernameDuplicate")){ %>
            <p>Username is already taken, try again.</p>
            <% } %>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input id="email" name="email" class="form-control" type="email"/>
        </div>
        <div class="form-group">
            <label for="password">New Password:</label>
            <input id="password" name="password" class="form-control" type="password"/>

            <% if (request.getSession().getAttribute("passwordMatch") == null) {
                request.setAttribute("passwordMatch", false);
            } else if (!(boolean) request.getSession().getAttribute("passwordMatch")){ %>
            <p>Passwords do not match, try again.</p>
        <% } %>
        </div>
        <div class="form-group">
            <label for="rePassword">Re-Enter New Password:</label>
            <input id="rePassword" name="rePassword" class="form-control" type="password"/>
        </div>
        <%--<div class="form-group">--%>
            <%--<label for="description">Bio:</label>--%>
            <%--<textarea id="description" name="newDescription" class="form-control" type="text"></textarea>--%>
        <%--</div>--%>
        <input id="hiddenId" name="post" type="hidden" value="<%= request.getSession().getAttribute("updatedAdId")%>"/>

        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>

</body>
</html>
