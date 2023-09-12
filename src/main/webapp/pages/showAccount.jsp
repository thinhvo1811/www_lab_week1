<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.RoleRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Role" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Show Account</title>
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
<main class="form-signin w-100 m-auto">
  <form action="<%= url %>/services" method="GET">
    <input type="hidden" name="action" value="showAccount"/>

    <div class="mb-3">
      <label for="role" class="form-label">Select Role</label>
      <select class="form-control" id="role" name="role">
        <%
          RoleRepository roleRepository = new RoleRepository();
          ArrayList<Role> roles = (ArrayList<Role>) roleRepository.getAll();
          for (Role role : roles) {
        %>
        <option value="<%=role.getId()%>"><%=role.getId()%></option>
        <%
          }
        %>
      </select>
    </div>
    <%
      String notification = request.getAttribute("notification")+"";
      if(notification.equals("null")){
        notification = "";
      }
    %>
    <div id="notification" class="form-text"><%= notification%></div>
    <input type="submit" value="Show Account" class="btn btn-primary"/>
  </form>
</main>
</body>
</html>