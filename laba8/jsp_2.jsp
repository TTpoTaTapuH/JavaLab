<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Финишная страница</title>
</head>
<body>
<%! int counter; %>
<jsp:useBean id="mybean" scope="session" class="jspappl.NameHandler" />
<form name="Back form" action="jsp_1.jsp">
<input type="submit" value="Back" name="button2" />
</form>
<table><tr><td>Cумма чётных и отрицательных:</td></tr>
    <tr><td>${mybean.sum[0]}</td></tr>
    <tr><td>Cумма нечётных и отрицательных:</td></tr>
    <tr><td>${mybean.sum[1]}</td></tr>
</table>
<h3>Счётчик <jsp:getProperty name="mybean" property="counter" /></h3>
<h3>Счётчик <%=++counter %></h3></body>
</html>