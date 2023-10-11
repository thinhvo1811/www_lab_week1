<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 11/09/2023
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <h2 style="text-align: center; margin-bottom: 15px">Thông tin tài khoản</h2>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Fullname</th>
                <th scope="col">Password</th>
                <th scope="col">Email</th>
                <th scope="col">Phone</th>
                <th scope="col">Status</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row"><%= account.getId()%></th>
                    <td><%= account.getFullName()%></td>
                    <td><%= account.getPassword()%></td>
                    <td><%= account.getEmail()%></td>
                    <td><%= account.getPhone()%></td>
                    <td><%= account.getStatus()%></td>
                </tr>
            </tbody>
        </table>
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
