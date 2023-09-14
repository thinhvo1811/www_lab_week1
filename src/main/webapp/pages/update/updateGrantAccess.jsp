<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.GrantAccess" %>
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

    GrantAccess grantAccess = (GrantAccess) request.getAttribute("grantAccess");
%>
<main class="form-signin w-100 m-auto">
    <form action="<%= url %>/services" method="POST">
        <input type="hidden" name="action" value="updateGrantAccess"/>
        <input type="hidden" name="roleID" value="<%=grantAccess.getRole().getId()%>"/>
        <input type="hidden" name="accountID" value="<%=grantAccess.getAccount().getId()%>"/>

        <div class="mb-3">
            <label for="isGrant" class="form-label">Is Grant</label>
            <select class="form-control" id="isGrant" name="isGrant">
                <option value="DIASABLE" <%=(grantAccess.getIsGrant().name().equals("DIASABLE"))?"selected='selected'":"" %> >DIASABLE</option>
                <option value="ENABLE" <%=(grantAccess.getIsGrant().name().equals("ENABLE"))?"selected='selected'":"" %> >ENABLE</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="note" class="form-label">Note</label>
            <input type="text" name="note" class="form-control" id="note" value="<%=grantAccess.getNote()%>" required>
        </div>
        <div class="form-text"><span style="color: <%= textColor%>"><%= notification%></span></div>
        <input type="submit" value="Update GrantAccess" class="btn btn-primary"/>
    </form>
</main>
</body>
</html>