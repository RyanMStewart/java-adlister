<%--
  Created by IntelliJ IDEA.
  User: ryan
  Date: 10/11/18
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="${param.title}" />
    </jsp:include>
</head>
<body>
<%--<c:var  id = "${param.post}" %>--%>
<%--<% request.--%>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <form class="inline-form" method="POST" action="/update">
        <div class="form-group">
            <label for="title">New Title:</label>
            <input id="title" name="newTitle" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="newDescription" class="form-control" type="text"></textarea>
        </div>
        <input id="hiddenId" name="post" type="hidden" value="<%= request.getSession().getAttribute("updatedAdId")%>"/>

        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>

</body>
</html>
