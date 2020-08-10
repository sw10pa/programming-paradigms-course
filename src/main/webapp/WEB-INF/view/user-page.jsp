<%@ page import="PPC.database.PPCDatabase" %>
<%@ page import="PPC.database.PPCDatabaseManager" %>
<%@ page import="PPC.model.Lecture" %>
<%@ page import="PPC.model.User" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="/resources/style/css/userPageStyle.css" type="text/css" rel="stylesheet">
    <link href="/resources/image/logo.png" rel="shortcut icon" type="image/x-icon">
    <title>Home Page</title>
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
        <button class="log-out-button">
        <span>
            <%
                User currUser = (User) request.getSession().getAttribute("user");
                String firstName = currUser.getFirstName();
                String lastName = currUser.getLastName();
                out.write(firstName + " " + lastName);
            %>
        </span>
        </button>
        <div class="dropdown-content">
            <a href="/change-password"
               class="log-out-button">Change Password</a>
            <form action="/logout">
                <button type="submit" class="log-out-button">Log Out</button>
            </form>
        </div>
    </div>
</div>

<div class="main">
    <div class="lectures">
        <%
            PPCDatabase db = new PPCDatabase();
            final PPCDatabaseManager dbManager = new PPCDatabaseManager(db.getConnection());
            ArrayList<Lecture> lectures = dbManager.getAllLectures();
            int maxScore = 0;
            for (Lecture lec : lectures) {
                maxScore += dbManager.getQuestionsByLectureId(lec.getLectureId()).size();
                if (currUser.getStatus().equals(User.STUDENT)) {
                    out.write("<a href = \"/lecture?lectureId=" + lec.getLectureId() + "\"> <div class = \"lecture\"> "
                            + "<div class = \"lecture-name\">" + lec.getLectureName() + "  (" +
                            dbManager.getLectureScore(currUser.getUserId(), lec.getLectureId()) + "/" +
                            dbManager.getQuestionsByLectureId(lec.getLectureId()).size() + ") " +
                            "</div>" + "</div></a>");
                } else {
                    out.write("<a href = \"/lecture?lectureId=" + lec.getLectureId() + "\"> <div class = \"lecture\"> "
                            + "<div class = \"lecture-name\">" + lec.getLectureName() + "</div>" + "</div></a>");
                }
            }
        %>
    </div>

    <div class="leader-board">
        <%
            if (currUser.getStatus().equals(User.STUDENT)) {
                double progress = dbManager.getTotalScore(currUser.getUserId());
                out.write("<div class = \"progress-text\"> Your Progress "
                        + (int) progress + "/" + maxScore + "</div>");
                progress = progress * 100 / maxScore;
                out.write("<div class=\"p-bar\">" + "<div class=\"progress\" " +
                        "style = \"max-width:" + progress + "%\"></div></div>");
            }
        %>
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
                            if (currUser.getStatus().equals(User.ADMINISTRATOR)) {
                                out.write("<form action = \"change-status\" Method = \"POST\">" +
                                        "<input name=\"email\" type = \"hidden\" value = \"" + user.getEmail() + "\">"
                                        + "<button type = \"submit\" class = \"make-lecturer\"> Grant "
                                        + user.getFirstName() + " Lecturer Status </button></form>");
                            }
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
