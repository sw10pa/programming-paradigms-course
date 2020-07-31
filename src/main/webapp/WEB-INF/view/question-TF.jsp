<%@ page import="PPC.model.Question" %><%--
  Created by IntelliJ IDEA.
  User: Niko
  Date: 7/31/2020
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>True or False</title>
</head>
<body>
<form action="/quiz" method = "post">

        <%
                Question question = (Question) request.getAttribute("question");
                if(question == null) System.out.println("yle");
                out.write("<p>" + question.getQuestionStructure().get(0) + "</p>");
        %>

        <input type = "radio" id = "true" name = "answer" />
        <label for = "true"> TRUE</label><br>
        <input type = "radio" id = "false" name="answer">
        <label for = "false"> FALSE</label><br>
        <button type = "submit" >SUBMIT</button>

</form>
</body>
</html>
