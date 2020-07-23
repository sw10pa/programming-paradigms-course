<%@ page import="PPC.database.PPCDatabase" %>
<%@ page import="PPC.database.PPCDatabaseManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="PPC.model.Lecture" %>
<%@ page import="PPC.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Niko
  Date: 7/20/2020
  Time: 12:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/resources/style/css/userPageStyle.css" type="text/css" rel="stylesheet">
    <link href="/resources/image/logo.png" rel="shortcut icon" type="image/x-icon">

</head>
<body class="body">
<div class="info">
    <div class="logo">
        <nav>
            <a href=""> <img class = "icon" src="/resources/image/logo.png" /></a>
        </nav>
    </div>

    <div class="course-name">
        <nav>
            <img class = "course-name-text" src="/resources/image/v2.png"/>
        </nav>
    </div>
    <div class="log-out">
        <form>
        <button type= "submit" class = "log-out-button">
            <span>
                <%
                    User user = (User) request.getSession().getAttribute("user");
                    String firstName = user.getFirstName();
                    String lastName = user.getLastName();
                    out.write(firstName + " " + lastName);
                %>
            </span>
        </button>
    </div>
</div>

<div class="main">
    <div class="lectures">
        <%
            PPCDatabase db = new PPCDatabase();
            PPCDatabaseManager dbManager = new PPCDatabaseManager(db.getConnection());
            ArrayList<Lecture> lectures = dbManager.getAllLectures();
            for (Lecture lec : lectures) {
                out.write("<a href = \"https://facebook.com\"> <div class = \"lecture\"> " +
                        "<div class = \"lectureName\">" + lec.getLectureName() + "</div> </div> </a>");
            }
        %>
    </div>
    <div class="leader-board">
        <div class = "header"> Leader Board </div>
        <main class="profiles">
        <%
            for (Lecture lec : lectures) {
                out.write("<article class=\"profile\">\n" +
                        "<span class=\"name\">" + lec.getLectureName() + "</span>\n" +
                        "<span class=\"value\">2.1</span>\n" +
                        "</article>");
            }
        %>
        </main>
    </div>
</div>
</body>
</html>
