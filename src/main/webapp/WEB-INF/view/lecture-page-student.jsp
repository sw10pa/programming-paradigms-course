<%@ page import="PPC.model.User" %>
<%@ page import="PPC.model.Lecture" %>
<%@ page import="PPC.database.PPCDatabase" %>
<%@ page import="PPC.database.PPCDatabaseManager" %>
<%@ page import="PPC.filesystem.FileManager" %><%--
  Created by IntelliJ IDEA.
  User: Whiskeyjack
  Date: 26-Jul-20
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/resources/style/css/lecturePageStudentStyle.css" type="text/css" rel="stylesheet">
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
    <div class = "left-side">
        <% String url = lecture.getVideoUrl(); %>
        <iframe class = "video-player"
                src="<%=url%>" frameborder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
        </iframe>
        <form action = "/quiz"  method = "GET">
            <input name="lectureId" type = "hidden" value = "${lectureId}">
            <button class = "button" type="submit"> Take Quiz </button>
        </form>
    </div>

    <div class = "right-side">
        <%
           String lectureText = FileManager.readFile(Lecture.LECTURES_FILES_PATH, lecture.getFileName());
           out.write(lectureText);
        %>

    </div>
</div>

</body>
</html>