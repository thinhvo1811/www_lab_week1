<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Role" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.AccountRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.RoleRepository" %><%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>
<body>
<main class="ps-3">
    <%
        String notification = request.getAttribute("notification")+"";
        notification = (notification.equals("null"))?"":notification;
    %>
    <h1><%=notification%></h1>
    <p></p>
    <h3>Danh sách các Role</h3>
    <%
        RoleRepository roleRepository = new RoleRepository();
        ArrayList<Role> roles = (ArrayList<Role>) roleRepository.getAll();
        for(Role role : roles){
    %>
    <p>ID: <%= role.getId()%></p>
    <p>Name: <%= role.getName()%></p>
    <p>Description: <%= role.getDescription()%></p>
    <p>Status: <%= role.getStatus()%></p>
    <p>-------------------------------</p>
    <%
        }
    %>
</main>
</body>
</html>
