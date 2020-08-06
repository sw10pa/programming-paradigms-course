<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<head>
    <title>Change Password</title>
    <link href="/resources/style/css/styleCSS.css" type="text/css" rel="stylesheet">
</head>

<body>
<div class="login-page">
    <div class="form">
        <c:if test="${error != null}">
            <div class="error-box">
                <span class="error-text">${error}</span>
            </div>
        </c:if>
        <c:if test="${success != null}">
            <div class="success-box">
                <span class="success-text">${success}</span>
            </div>
        </c:if>
        <form action="/change-password" method="POST" class="login-form">
            <input type="password" placeholder="Current password" name="currentPass"/>
            <input type="password" placeholder="New password" name="newPass"/>
            <button type="submit">Change</button>
        </form>
    </div>
</div>

</body>

</html>