<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.AccountRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.RoleRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Role" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.LogRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Logs" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Show Role</title>
    <%
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    %>
    <link href="<%=url%>/css/login.css" rel="stylesheet" >
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>
<body>
<main class="form-signin w-100 m-auto">
    <form action="<%= url %>/services" method="POST">
        <input type="hidden" name="action" value="deleteLog"/>

        <div class="mb-3">
            <label for="logID" class="form-label">Select Log</label>
            <select class="form-control" id="logID" name="logID">
                <%
                    LogRepository logRepository = new LogRepository();
                    ArrayList<Logs> logs = (ArrayList<Logs>) logRepository.getAll();
                    for (Logs log : logs) {
                %>
                <option value="<%=log.getId()%>"><%=log.getId()%></option>
                <%
                    }
                %>
            </select>
        </div>
        <%
            String notification = request.getAttribute("notification")+"";
            if(notification.equals("null")){
                notification = "";
            }
            String textColor = request.getAttribute("textColor")+"";
        %>
        <div class="form-text"><span style="color: <%= textColor%>"><%= notification%></span></div>
        <input type="submit" value="Delete Log" class="btn btn-primary"/>
    </form>
</main>
</body>
</html>