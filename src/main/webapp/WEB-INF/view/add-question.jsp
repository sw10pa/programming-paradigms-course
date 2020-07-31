<%@ page import="PPC.database.PPCDatabase" %>
<%@ page import="PPC.database.PPCDatabaseManager" %>
<%@ page import="PPC.model.Lecture" %>
<%@ page import="PPC.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Whiskeyjack
  Date: 31-Jul-20
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Question</title>
</head>
<html>
<head>
    <link href="/resources/style/css/addQuestionStyle.css" type="text/css" rel="stylesheet">
    <link href="/resources/image/logo.png" rel="shortcut icon" type="image/x-icon">
    <%
        PPCDatabase db = new PPCDatabase();
        PPCDatabaseManager dbManager = new PPCDatabaseManager(db.getConnection());
        Lecture lecture = dbManager.getLectureById(Integer.parseInt((String) request.getAttribute("lectureId")));
    %>
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

<div class = "title"><% out.write(lecture.getLectureName()); %> </div>

<div class = "main">
    <form class = "form" action = "edit-quiz" method = "POST" class = "TF">
        <p>True or False</p>
        <input name="lectureId" type = "hidden" value = "${lectureId}">
        <input name="questionType" type = "hidden" value = "TF">
        <input type = "text" name = "question" placeholder="Enter question"> <br>
        <input type ="radio" name = "answer" value = "True"> True <br>
        <input type ="radio" name = "answer" value = "False"> False <br>
        <button class = "button">Add Question</button>
    </form>
    <form class = "form" action = "edit-quiz" method = "POST" class = "MC">
        <p>Multiple Choice</p>
        <input name="lectureId" type = "hidden" value = "${lectureId}">
        <input name="questionType" type = "hidden" value = "MC">
        <input type = "text" name = "question" placeholder="Enter question"> <br>
        <input type ="text" name = "answer" placeholder="Enter correct answer"><br>
        <input type ="text" name = "wrongAnswer" placeholder="Enter wrong answer"><br>
        <input type ="text" name = "wrongAnswer" placeholder="Enter wrong answer"><br>
        <input type ="text" name = "wrongAnswer" placeholder="Enter wrong answer"><br>
        <button class = "button">Add Question</button>
    </form>

    <form class = "form" action = "edit-quiz" method = "POST" class = "QR">
        <p>Question Response</p>
        <input name="lectureId" type = "hidden" value = "${lectureId}">
        <input name="questionType" type = "hidden" value = "QR">
        <input type = "text" name = "question" placeholder = "Enter question"> <br>
        <input type = "text" name = "answer" placeholder = "Enter correct answer"> <br>
        <button class = "button">Add Question</button>
    </form>

</div>

</body>
</html>
