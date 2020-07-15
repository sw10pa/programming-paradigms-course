<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"> </script>
<script>
    function register(){
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
        <form class="register-form">
            <input type="text" placeholder="Name"/>
            <input type="password" placeholder="Password"/>
            <input type="text" placeholder="Email"/>
            <button>create</button>
            <p class="message">Already registered? <a href="#">Sign In</a></p>
        </form>
        <form class="login-form">
            <input type="text" placeholder="Email"/>
            <input type="password" placeholder="Password"/>
            <button>login</button>
            <p class="message">Not registered? <a href="#" onclick="register()">Create an account</a></p>
        </form>
    </div>
</div>

</body>
</html>