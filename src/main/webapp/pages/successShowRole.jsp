<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Role" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 11/09/2023
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Các role của account</h1>
    <%
        ArrayList<Role> roles = (ArrayList<Role>) request.getAttribute("roles");
        for(Role role : roles){
    %>
            <p>ID: <%= role.getId()%></p>
            <p>Name: <%= role.getName()%></p>
            <p>Description: <%= role.getDescription()%></p>
            <p>Status: <%= role.getStatus()%></p>
    <%
        }
    %>
</body>
</html>
