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
<div class="container">
    <a href="<%=url%>/pages/delete/deleteAccount.jsp" class="btn">Xóa Account</a>
    <a href="<%=url%>/pages/delete/deleteRole.jsp" class="btn">Xóa Role</a>
    <a href="<%=url%>/pages/delete/deleteGrantAccess.jsp" class="btn">Xóa GrantAccess</a>
    <a href="<%=url%>/pages/delete/deleteLog.jsp" class="btn">Xóa Log</a>
</div>
</body>
</html>