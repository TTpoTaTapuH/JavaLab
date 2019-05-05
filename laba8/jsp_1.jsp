<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%-- Объявление библиотек тэгов JSF --%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%!
    public int[] compute(String arg){
    int list1=0;
        int list2=0;
        String[] args = arg.split(",");
        for(String x: args){
            if(Integer.parseInt(x)%2 == 0 || Integer.parseInt(x)<0){
                list1+=Integer.parseInt(x);
            }
            if(Integer.parseInt(x)%2 == 1 || Integer.parseInt(x)<0){
                list2+=Integer.parseInt(x);
            }
        }
        int[] sum=new int[2];
        sum[0]=list1;
        sum[1]=list2;
        return sum;
        //System.out.println("сумма нечётных и отрицательных: "+list2);
    }
%>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Главная страница</title>
</head>
<body>
<%! int counter; %>
<jsp:useBean id="mybean" scope="session" class="jspappl.NameHandler" />
<h3>Введите ваше имя</h3>
<form action="jsp_1.jsp">
    <input type="text" name="number"/>
    <input type="submit" value="OK" name="button1"/>
<%
    String a=request.getParameter("number");
    if(a==null)
    mybean.setSum(compute("1,2,3,4,5"));
    else mybean.setSum(compute(a));
%>
<%mybean.addCounter(1);%>
</form>
<h1><a href="jsp_2.jsp">Переход</a></h1>
<h3>Счётчик <%=++counter %></h3>
</body>
</html>