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
    <a href="<%=url%>/pages/account/getAllAccount.jsp" class="btn">Danh sách tài khoản</a>
    <a href="<%=url%>/pages/account/addAccount.jsp" class="btn">Thêm tài khoản</a>
    <a href="<%=url%>/pages/account/selectAccount.jsp" class="btn">Cập nhật tài khoản</a>
    <a href="<%=url%>/pages/account/deleteAccount.jsp" class="btn">Xóa tài khoản</a>
    <a href="<%=url%>/pages/account/addGrantAccess.jsp" class="btn">Cấp quyền cho tài khoản</a>
</div>
</body>
</html>