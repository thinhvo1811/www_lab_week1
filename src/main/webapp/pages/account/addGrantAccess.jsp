<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.RoleRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Role" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.AccountRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %>
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

    String roleID= request.getAttribute("roleID")+"";
    roleID = (roleID.equals("null"))?"":roleID;

    String accountID= request.getAttribute("accountID")+"";
    accountID = (accountID.equals("null"))?"":accountID;

    String note= request.getAttribute("note")+"";
    note = (note.equals("null"))?"":note;
%>
<main class="form-signin w-100 m-auto">
    <form action="<%= url %>/services" method="POST">
        <input type="hidden" name="action" value="addGrantAccess"/>

        <div class="mb-3">
            <label for="role" class="form-label">Select Role</label>
            <select class="form-control" id="role" name="role">
                <%
                    RoleRepository roleRepository = new RoleRepository();
                    ArrayList<Role> roles = (ArrayList<Role>) roleRepository.getAll();
                    for (Role role : roles) {
                %>
                <option value="<%=role.getId()%>" <%=(roleID.equals(role.getId()))?"selected='selected'":"" %> ><%=role.getId()%></option>
                <%
                    }
                %>
            </select>
        </div>
        <div class="mb-3">
            <label for="account" class="form-label">Select Account</label>
            <select class="form-control" id="account" name="account">
                <%
                    AccountRepository accountRepository = new AccountRepository();
                    ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.getAll();
                    for (Account account : accounts) {
                %>
                <option value="<%=account.getId()%>" <%=(accountID.equals(account.getId()))?"selected='selected'":"" %> ><%=account.getId()%></option>
                <%
                    }
                %>
            </select>
        </div>
        <div class="mb-3">
            <label for="note" class="form-label">Note</label>
            <input type="text" name="note" class="form-control" id="note" value="<%=note%>" required>
        </div>
        <div class="form-text"><span style="color: <%= textColor%>"><%= notification%></span></div>
        <input type="submit" value="Add GrantAccess" class="btn btn-primary"/>
    </form>
</main>
</body>
</html>