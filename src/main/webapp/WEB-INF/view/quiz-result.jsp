<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Niko
  Date: 7/31/2020
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>stephen usaqmuri</title>
</head>
<body>
<form action = "/home">
    <%
        ArrayList<String> correctAnswers = (ArrayList<String>) request.getAttribute("correctAnswers");
        ArrayList<String> userAnswers = (ArrayList<String>) request.getAttribute("userAnswers");
    %>
    <div class = "result-board">
        <div class = "correct-answers">
            <%
                for(String answer : correctAnswers){
                    out.write(answer + "<br>");
                }
            %>
        </div>
        <div class = "user-answers">
            <%
                for(String answer : userAnswers){
                    out.write(answer + "<br>");
                }
            %>
        </div>
    </div>
    <button type = "submit">Return to home page</button>
</form>
</body>
</html>
