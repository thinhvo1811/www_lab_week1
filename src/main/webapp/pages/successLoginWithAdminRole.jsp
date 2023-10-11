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
            <a href="<%=url%>/pages/add/addOption.jsp" class="btn">Thêm</a>
            <a href="<%=url%>/pages/update/updateOption.jsp" class="btn">Cập nhật</a>
            <a href="<%=url%>/pages/delete/deleteOption.jsp" class="btn">Xóa</a>
            <a href="<%=url%>/pages/showRole.jsp" class="btn">Hiển thị các role của một account</a>
            <a href="<%=url%>/pages/showAccount.jsp" class="btn">Hiển thị các account của một role</a>
            <a href="<%=url%>/pages/grantRoleToAccount.jsp" class="btn">Cấp role cho một account</a>
        </div>
    </div>
</body>
</html>