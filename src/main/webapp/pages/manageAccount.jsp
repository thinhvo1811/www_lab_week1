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
    <a href="<%=url%>/services?action=toGetAllAccountPage" class="btn">Danh sách tài khoản</a>
    <a href="<%=url%>/services?action=toAddAccountPage" class="btn">Thêm tài khoản</a>
    <a href="<%=url%>/services?action=toSelectAccountPage" class="btn">Cập nhật tài khoản</a>
    <a href="<%=url%>/services?action=toDeleteAccountPage" class="btn">Xóa tài khoản</a>
    <a href="<%=url%>/services?action=toAddGrantAccessPage" class="btn">Cấp quyền cho tài khoản</a>
</div>
</body>
</html>