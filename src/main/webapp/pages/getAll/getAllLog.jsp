<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Role" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.AccountRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.LogRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Logs" %><%--
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
    <h2><%=notification%></h2>
    <p></p>
    <h3 style="text-align: center; margin-bottom: 15px">Danh sách các Log</h3>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Account ID</th>
            <th scope="col">Login Date</th>
            <th scope="col">Logout Date</th>
            <th scope="col">Note</th>
        </tr>
        </thead>
        <tbody>
            <%
                LogRepository logRepository = new LogRepository();
                ArrayList<Logs> logs = (ArrayList<Logs>) logRepository.getAll();
                for(Logs log : logs){
            %>
            <tr>
                <th scope="row"><%= log.getId()%></th>
                <td><%= log.getAccountID()%></td>
                <td><%= log.getLoginDate()%></td>
                <td><%= log.getLogoutDate()%></td>
                <td><%= log.getNote()%></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</main>
</body>
</html>
