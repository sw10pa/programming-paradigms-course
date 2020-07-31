<%@ page import="PPC.model.Question" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Niko
  Date: 7/31/2020
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Multiple Choice</title>
</head>
<body>
<form action="/quiz" method = "post">
    <%
        Question question = (Question) request.getAttribute("question");
        ArrayList<String> data = question.getQuestionStructure();
        out.write("<p>" + data.get(0) + "</p>");

        for (int i = 1; i < data.size(); i++) {
            String choice = data.get(i);
            out.write("<input type = \"radio\" id =\"" + i + "\" name = \"answer\"/>");
            out.write("<label for=\"" + i + "\">" + choice + "</label> <br><br>");
        }
    %>

    <button type = "submit" >SUBMIT</button>

</form>
</body>
</html>
