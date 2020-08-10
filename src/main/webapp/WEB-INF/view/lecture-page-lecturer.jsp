<%@ page import="PPC.model.User" %>
<%@ page import="PPC.model.Lecture" %>
<%@ page import="PPC.database.PPCDatabase" %>
<%@ page import="PPC.database.PPCDatabaseManager" %>
<%@ page import="PPC.filesystem.FileManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/resources/style/css/lecturePageLecturerStyle.css" type="text/css" rel="stylesheet">
    <link href="/resources/image/logo.png" rel="shortcut icon" type="image/x-icon">
    <%
        PPCDatabase db = new PPCDatabase();
        PPCDatabaseManager dbManager = new PPCDatabaseManager(db.getConnection());
        Lecture lecture = dbManager.getLectureById(Integer.parseInt((String) request.getAttribute("lectureId")));
        String lectureName = lecture.getLectureName();
    %>
    <title><%=lectureName%>
    </title>
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

<div class="title"><%=lectureName%>
</div>

<div class="main">
    <div class="left-side">
        <% String url = lecture.getVideoUrl(); %>
        <form action="/edit-link" method="POST">
            <input class="vide-url" type="text" name="videoURL" value="<%=url%>">
            <input name="lectureId" type="hidden" value="${lectureId}">
            <button type="submit" class="button-link"> Edit Link</button>
        </form>

        <form action="/edit-quiz" method="GET">
            <input name="lectureId" type="hidden" value="${lectureId}">
            <button class="button-quiz" type="submit"> Edit Quiz</button>
        </form>
    </div>

    <%
        String lectureText = FileManager.readFile(Lecture.LECTURES_FILES_PATH, lecture.getFileName());
    %>

    <form action="/edit-text" method="POST">
        <textarea class="right-side" type="text" name="newText"><%=lectureText%></textarea>
        <input name="lectureId" type="hidden" value="${lectureId}">
        <button type="submit" class="button-text"> Edit Text</button>
    </form>

</div>

</body>
</html>