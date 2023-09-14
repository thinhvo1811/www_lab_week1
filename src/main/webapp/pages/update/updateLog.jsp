<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Logs" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.AccountRepository" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
<%
    String notification = request.getAttribute("notification")+"";
    notification = (notification.equals("null"))?"":notification;

    String textColor = request.getAttribute("textColor")+"";
    textColor = (textColor.equals("null"))?"white":textColor;

    Logs log = (Logs) request.getAttribute("log");
%>
<main class="form-signin w-100 m-auto">
    <form action="<%= url %>/services" method="POST">
        <input type="hidden" name="action" value="updateLog"/>
        <input type="hidden" name="id" value="<%=log.getId()%>"/>

        <div class="mb-3">
            <label for="accountID" class="form-label">Select Account</label>
            <select class="form-control" id="accountID" name="accountID">
                <%
                    AccountRepository accountRepository = new AccountRepository();
                    ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.getAll();
                    for (Account account : accounts) {
                %>
                <option value="<%=account.getId()%>" <%=(log.getAccountID().equals(account.getId()))?"selected='selected'":"" %> ><%=account.getId()%></option>
                <%
                    }
                %>
            </select>
        </div>
        <div class="mb-3">
            <label for="loginDate" class="form-label">Login Date</label>
            <input type="date" name="loginDate" class="form-control" value="<%=log.getLoginDate()%>" id="loginDate" required>
        </div>
        <div class="mb-3">
            <label for="logoutDate" class="form-label">Logout Date</label>
            <input type="date" name="logoutDate" class="form-control" value="<%=log.getLogoutDate()%>" id="logoutDate" required>
        </div>
        <div class="mb-3">
            <label for="note" class="form-label">Note</label>
            <input type="text" name="note" class="form-control" value="<%=log.getNote()%>" id="note" required>
        </div>

        <div class="form-text"><span style="color: <%= textColor%>"><%= notification%></span></div>
        <input type="submit" value="Update Log" class="btn btn-primary"/>
    </form>
</main>
</body>
</html>