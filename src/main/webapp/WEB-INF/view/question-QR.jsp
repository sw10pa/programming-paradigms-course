<%@ page import="PPC.model.Question" %><%--
  Created by IntelliJ IDEA.
  User: Niko
  Date: 7/31/2020
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question Response</title>
</head>
<body>
<form action="/quiz" method = "post">

    <%
        Question question = (Question) request.getAttribute("question");
        out.write("<p>" + question.getQuestionStructure().get(0) + "</p>");
    %>

    <input type = "text" name = "answer" placeholder = "enter your answer"/> <br>
    <button type = "submit" >SUBMIT</button>

</form>
</body>
</html>
