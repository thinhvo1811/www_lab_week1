<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 11/09/2023
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Account account = (Account) request.getAttribute("account");
    %>
    <h1>Thông tin tài khoản</h1>
    <p>ID: <%= account.getId()%></p>
    <p>Fullname: <%= account.getFullName()%></p>
    <p>Password: <%= account.getPassword()%></p>
    <p>Email: <%= account.getEmail()%></p>
    <p>Phone: <%= account.getPhone()%></p>
    <p>Status: <%= account.getStatus()%></p>
</body>
</html>
