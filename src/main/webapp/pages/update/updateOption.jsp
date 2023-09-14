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
  <a href="<%=url%>/pages/update/selectAccount.jsp" class="btn">Cập nhật Account</a>
  <a href="<%=url%>/pages/update/selectRole.jsp" class="btn">Cập nhật Role</a>
  <a href="<%=url%>/pages/update/selectGrantAccess.jsp" class="btn">Cập nhật GrantAccess</a>
  <a href="<%=url%>/pages/update/selectLog.jsp" class="btn">Cập nhật Log</a>
</div>
</body>
</html>