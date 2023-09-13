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

    String name= request.getAttribute("name")+"";
    name = (name.equals("null"))?"":name;

    String description= request.getAttribute("description")+"";
    description = (description.equals("null"))?"":description;


    String status= request.getAttribute("status")+"";
    status = (status.equals("null"))?"":status;
%>
<main class="form-signin w-100 m-auto">
    <form action="<%= url %>/services" method="POST">
        <input type="hidden" name="action" value="addRole"/>

        <div class="mb-3">
            <label for="roleID" class="form-label">Role ID</label>
            <input type="text" name="roleID" class="form-control" id="roleID" value="<%=id%>" required>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" name="name" class="form-control" id="name" value="<%=name%>" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" name="description" class="form-control" id="description" value="<%=description%>" required>
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
        <input type="submit" value="Add Role" class="btn btn-primary"/>
    </form>
</main>
</body>
</html>