<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"> </script>
<script>
    function swapForm(){
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    }
</script>
<head>
    <title>PPC</title>
    <link href="/resources/style/css/styleCSS.css" type="text/css" rel="stylesheet" >
</head>

<body>
<div class="login-page">
    <div class="form">
        <form action="/signup" method="POST" class="register-form">
            <input type="text" placeholder="First Name" name="firstName"/>
            <input type="text" placeholder="Last Name" name="lastName"/>
            <input type="password" placeholder="Password" name="password"/>
            <input type="text" placeholder="Email" name="username"/>
            <button type = "submit">create</button>
            <p class="message">Already registered? <a href="#" onclick=swapForm()>Sign In</a></p>
        </form>
        <form action="/login" method="POST" class="login-form">
            <input type="text" placeholder="Email" name="username"/>
            <input type="password" placeholder="Password" name="password"/>
            <button type = "submit">login</button>
            <p class="message">Not registered? <a href="#" onclick=swapForm()>Create an account</a></p>
        </form>
    </div>
</div>

</body>
</html>