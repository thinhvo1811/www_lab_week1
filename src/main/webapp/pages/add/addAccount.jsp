<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
    <%
        String notification = request.getAttribute("notification")+"";
        notification = (notification.equals("null"))?"":notification;

        String textColor = request.getAttribute("textColor")+"";

        String id= request.getAttribute("id")+"";
        id = (id.equals("null"))?"":id;

        String fullName= request.getAttribute("fullName")+"";
        fullName = (fullName.equals("null"))?"":fullName;

        String password= request.getAttribute("password")+"";
        password = (password.equals("null"))?"":password;

        String email= request.getAttribute("email")+"";
        email = (email.equals("null"))?"":email;

        String phone= request.getAttribute("phone")+"";
        phone = (phone.equals("null"))?"":phone;

        String status= request.getAttribute("status")+"";
        status = (status.equals("null"))?"":status;
    %>
    <main class="form-signin w-100 m-auto">
        <form action="<%= url %>/services" method="POST">
            <input type="hidden" name="action" value="addAccount"/>

            <div class="mb-3">
                <label for="accountID" class="form-label">AccountID</label>
                <input type="text" name="accountID" class="form-control" id="accountID" value="<%=id%>" required>
            </div>
            <div class="mb-3">
                <label for="fullName" class="form-label">Fullname</label>
                <input type="text" name="fullName" class="form-control" id="fullName" value="<%=fullName%>" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" name="password" class="form-control" id="password" value="<%=password%>" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" name="email" class="form-control" id="email" value="<%=email%>" required>
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="tel" name="phone" class="form-control" id="phone" value="<%=phone%>" required>
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <select class="form-control" id="status" name="status">
                    <option value="ACTIVE" <%=(status.equals("ACTIVE"))?"selected='selected'":"" %> >ACTIVE</option>
                    <option value="DEACTIVE" <%=(status.equals("DEACTIVE"))?"selected='selected'":"" %> >DEACTIVE</option>
                    <option value="DELETED" <%=(status.equals("DELETED"))?"selected='selected'":"" %> >DELETED</option>
                </select>
            </div>
            <div class="form-text"><span style="color: <%= textColor%>"><%= notification%></span></div>
            <input type="submit" value="Add" class="btn btn-primary"/>
        </form>
    </main>
</body>
</html>