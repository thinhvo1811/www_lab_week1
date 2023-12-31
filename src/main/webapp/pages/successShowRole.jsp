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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>
<body>
    <main class="ps-3">
        <h2 style="text-align: center; margin-bottom: 15px">Các role của account</h2>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Status</th>
            </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Role> roles = (ArrayList<Role>) request.getAttribute("roles");
                    for(Role role : roles){
                %>
                    <tr>
                        <th scope="row"><%= role.getId()%></th>
                        <td><%= role.getName()%></td>
                        <td><%= role.getDescription()%></td>
                        <td><%= role.getStatus()%></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </main>
</body>
</html>
