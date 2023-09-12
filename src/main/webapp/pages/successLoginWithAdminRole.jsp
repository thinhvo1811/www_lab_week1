<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="./css/index.css" rel="stylesheet" >

</head>
<body>
    <div class="wrap">
        <div class="header">
            <%
                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                        + request.getContextPath();
            %>
            <form action="<%= url %>/services" method="POST">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" value="Logout" class="btn btn-sm"/>
            </form>
        </div>
        <div class="container">
            <a href="./pages/login.jsp" class="btn">Thêm</a>
            <a href="./pages/login.jsp" class="btn">Cập nhật</a>
            <a href="./pages/login.jsp" class="btn">Xóa</a>
            <a href="./pages/showRole.jsp" class="btn">Hiển thị các role của một account</a>
            <a href="./pages/showAccount.jsp" class="btn">Hiển thị các account của một role</a>
            <a href="./pages/grantRoleToAccount.jsp" class="btn">Cấp quyền cho một account</a>
        </div>
    </div>
</body>
</html>