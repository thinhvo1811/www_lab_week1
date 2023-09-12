<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.AccountRepository" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Show Role</title>
  <link href="../css/login.css" rel="stylesheet" >
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>
<body>
<main class="form-signin w-100 m-auto">

  <%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();
  %>

  <form action="<%= url %>/services" method="GET">
    <input type="hidden" name="action" value="showRole"/>

    <div class="mb-3">
      <label for="account" class="form-label">Select Account</label>
      <select class="form-control" id="account" name="account">
        <%
          AccountRepository accountRepository = new AccountRepository();
          ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.getAll();
          for (Account account : accounts) {
        %>
        <option value="<%=account.getId()%>"><%=account.getId()%></option>
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
    <input type="submit" value="Show Role" class="btn btn-primary"/>
  </form>
</main>
</body>
</html>