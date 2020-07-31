<%@ page import="PPC.model.Question" %>
<%@ page import="PPC.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Niko
  Date: 7/31/2020
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/resources/style/css/questionStyle.css" type="text/css" rel="stylesheet">
    <link href="/resources/image/logo.png" rel="shortcut icon" type="image/x-icon">
    <title>True or False</title>
</head>
<body class = "body">
<div class="info">
        <div class="logo">
                <nav>
                        <a href="/home"> <img class="icon" src="/resources/image/logo.png"/></a>
                </nav>
        </div>

        <div class="course-name">
                <nav>
                        <img class="course-name-text" src="/resources/image/v2.png"/>
                </nav>
        </div>
        <div class="log-out">
                <form action="/logout">
                        <button type="submit" class="log-out-button">
            <span>
                <%
                        User currUser = (User) request.getSession().getAttribute("user");
                        String firstName = currUser.getFirstName();
                        String lastName = currUser.getLastName();
                        out.write(firstName + " " + lastName);
                %>
            </span>
                        </button>
                </form>
        </div>
</div>
<div class="main">
<form class ="TF" action="/quiz" method = "post">

        <%
                Question question = (Question) request.getAttribute("question");
                String quest = question.getQuestionStructure().get(0);
                out.write("<div class = \"p\">" + quest + "</div>");
        %>

        <div class="answer-container">
            <input type = "radio" id = "true" name = "answer" />
            <label for = "true"> TRUE</label><br>
        </div>
        <div class="answer-container">
            <input type = "radio" id = "false" name="answer">
            <label for = "false"> FALSE</label><br>
        </div>
        <button type = "submit" >SUBMIT</button>
</div>
</form>
</body>
</html>
