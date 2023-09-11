<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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

        <form action="<%= url %>/services" method="POST">
            <input type="hidden" name="action" value="login"/>

            <div class="mb-3">
                <label for="userName" class="form-label">Username</label>
                <input type="text" name="userName" class="form-control" id="userName">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" name="password" class="form-control" id="password">
            </div>
            <%
                String notification = request.getAttribute("notification")+"";
                if(notification.equals("null")){
                    notification = "";
                }
            %>
            <div id="notification" class="form-text"><%= notification%></div>
            <input type="submit" value="Login" class="btn btn-primary"/>
        </form>
    </main>
</body>
</html>