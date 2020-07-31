<%@ page import="PPC.database.PPCDatabase" %>
<%@ page import="PPC.database.PPCDatabaseManager" %>
<%@ page import="PPC.model.Lecture" %>
<%@ page import="PPC.model.User" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.SQLException" %>
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
            <a href=""> <img class="icon" src="/resources/image/logo.png"/></a>
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
    <div class="lectures">
        <%
            PPCDatabase db = new PPCDatabase();
            final PPCDatabaseManager dbManager = new PPCDatabaseManager(db.getConnection());
            ArrayList<Lecture> lectures = dbManager.getAllLectures();
            for (Lecture lec : lectures) {
                out.write("<a href = \"/lecture?lectureId=" + lec.getLectureId() + "\"> <div class = \"lecture\"> " +
                        "<div class = \"lecture-name\">" + lec.getLectureName() + "</div> </div> </a>");
            }
        %>
    </div>
    <div class="leader-board">
        <div class="header"> Leader Board</div>
        <main class="profiles">
            <%
                ArrayList<User> users = dbManager.getAllUsers();
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        try {
                            return dbManager.getTotalScore(o2.getUserId()) -
                                    dbManager.getTotalScore(o1.getUserId());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        return 0;
                    }
                });
                for (User user : users) {
                    if (user.getStatus().equals(User.STUDENT)) {
                        try {
                            out.write("<article class=\"profile\">\n" +
                                    "<span class=\"name\">" +
                                    user.getFirstName() + " " + user.getLastName() +
                                    "</span>\n<span class=\"value\">" +
                                    dbManager.getTotalScore(user.getUserId()) +
                                    "</span>\n</article>");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            %>
        </main>
    </div>
</div>
</body>
</html>
