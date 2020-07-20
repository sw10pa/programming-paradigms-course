<%@ page import="PPC.database.PPCDatabase" %>
<%@ page import="PPC.database.PPCDatabaseManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="PPC.model.Lecture" %><%--
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
            <a href="https://scontent.ftbs5-2.fna.fbcdn.net/v/t1.0-9/p960x960/69456674_2911355902224141_400024213043281920_o.jpg?_nc_cat=103&_nc_sid=85a577&_nc_ohc=a6GcwyPnicUAX_PIeNP&_nc_ht=scontent.ftbs5-2.fna&_nc_tp=6&oh=58786c76bc25c47acdc4733fd28333a4&oe=5F38A6A0">
                <img class = "icon" src="/resources/image/logo.png" /></a>
        </nav>
    </div>

    <div class="course-name">
        paradigms
    </div>
    <div class="log-out">
        logout
    </div>
</div>

<div class="main">
    <div class="lectures">
        <%
            PPCDatabase db = null;
            db = new PPCDatabase();
            PPCDatabaseManager dbManager = new PPCDatabaseManager(db.getConnection());
            ArrayList<Lecture> lectures = dbManager.getAllLectures();
            for (Lecture lec : lectures) {
                out.write("<a href = \"https://scontent.ftbs5-2.fna.fbcdn.net/v/t1.0-9/p960x960/69456674_2911355902224141_400024213043281920_o.jpg?_nc_cat=103&_nc_sid=85a577&_nc_ohc=a6GcwyPnicUAX_PIeNP&_nc_ht=scontent.ftbs5-2.fna&_nc_tp=6&oh=58786c76bc25c47acdc4733fd28333a4&oe=5F38A6A0\"> <div class = \"lecture\"> " +
                        "<div class = \"lectureName\">" + lec.getLectureName() + "</div> </div> </a>");
            }
        %>
    </div>
    <div class="leader-board">
        leaderboard
    </div>
</div>
</body>
</html>
