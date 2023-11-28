<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <%
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    %>
    <link href="<%=url%>/css/index.css" rel="stylesheet" >

</head>
<body>
    <div class="wrap">
        <div class="header">
            <form action="<%= url %>/services" method="POST">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" value="Logout" class="btn btn-sm"/>
            </form>
        </div>
        <div class="container">
            <a href="<%=url%>/services?action=toManageAccountPage" class="btn">Quản lý tài khoản</a>
            <a href="<%=url%>/services?action=toShowRolePage" class="btn">Hiển thị các quyền của một tài khoản</a>
            <a href="<%=url%>/services?action=toShowAccountPage" class="btn">Hiển thị các tài khoản của một quyền</a>
        </div>
    </div>
</body>
</html>