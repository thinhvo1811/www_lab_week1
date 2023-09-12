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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>
<body>
    <main class="ps-3">
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
        <p></p>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <form action="<%= url %>/services" method="POST">
            <input type="hidden" name="action" value="logout"/>
            <input type="submit" value="Logout" class="btn btn-primary"/>
        </form>
    </main>
</body>
</html>
