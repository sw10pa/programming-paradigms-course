<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>

<script>
    function swapForm() {
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    }
</script>
<head>
    <title>Paradigms Course</title>
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
        <form action="/signup" method="POST" class="register-form">
            <input type="text" placeholder="First Name" name="firstName"/>
            <input type="text" placeholder="Last Name" name="lastName"/>
            <input type="email" placeholder="Email" name="username"/>
            <button type="submit">create</button>
            <p class="message">Already registered? <a href="#" onclick=swapForm()>Sign In</a></p>
        </form>
        <form action="/" method="POST" class="login-form">
            <input type="email" placeholder="Email" name="username"/>
            <input type="password" placeholder="Password" name="password"/>
            <button type="submit">login</button>
            <p class="message">Not registered? <a href="#" onclick=swapForm()>Create an account</a></p>
            <p class="message">Forgot password? <a href="/reset-password">Reset password</a></p>
        </form>
        <c:if test="${type != null}">
            <script>
                swapForm();
            </script>
        </c:if>
    </div>
</div>

</body>

</html>