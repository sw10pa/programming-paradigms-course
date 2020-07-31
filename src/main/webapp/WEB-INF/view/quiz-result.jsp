<%@ page import="java.util.ArrayList" %>
<%@ page import="PPC.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Niko
  Date: 7/31/2020
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/resources/style/css/resultStyle.css" type="text/css" rel="stylesheet">
    <link href="/resources/image/logo.png" rel="shortcut icon" type="image/x-icon">
    <title>stephen usaqmuri</title>
</head>
<body class="body">
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
<form action = "/home">
    <%
        ArrayList<String> correctAnswers = (ArrayList<String>) request.getAttribute("correctAnswers");
        ArrayList<String> userAnswers = (ArrayList<String>) request.getAttribute("userAnswers");
        int maxScore = correctAnswers.size();
    %>
    <div class = "result-board">
        <div class = "correct-answers">
            <%
                out.write("Correct Answers:" + "<br>");
                for(String answer : correctAnswers){
                    out.write(answer + "<br>");
                }
            %>
            <br>
            <div class = "score">
            You scored ${correctAnswerCount} / <%=maxScore%>
        </div>
        </div>
        <div class = "user-answers">
            <%
                out.write("Your Answers:" + "<br>");
                for(String answer : userAnswers){
                    out.write(answer + "<br>");
                }
            %>
            <br>
            <div><button class="goHome" type = "submit">Return to home page</button></div>
        </div>
    </div>


</div>
</form>
</body>
</html>
