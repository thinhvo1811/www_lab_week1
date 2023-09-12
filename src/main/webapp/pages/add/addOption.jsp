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
    <a href="<%=url%>/pages/add/addAccount.jsp" class="btn">Thêm Account</a>
    <a href="<%=url%>/pages/login.jsp" class="btn">Thêm Role</a>
    <a href="<%=url%>/pages/login.jsp" class="btn">Thêm GrantAccess</a>
    <a href="<%=url%>/pages/showRole.jsp" class="btn">Thêm Log</a>
</div>
</body>
</html>