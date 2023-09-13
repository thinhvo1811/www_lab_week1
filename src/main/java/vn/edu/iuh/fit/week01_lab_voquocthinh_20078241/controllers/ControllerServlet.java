package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.*;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.AccountRepository;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.LogRepository;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.RoleRepository;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(name = "services",value = "/services")
public class ControllerServlet extends HttpServlet {
    public ControllerServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("showRole")){
            try {
                showRole(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("showAccount")) {
            try {
                showAccount(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("login")){
            try {
                login(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("grantRole")) {
            try {
                grantRoleToAccount(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("logout")) {
            try {
                logout(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addAccount")) {
            try {
                addAccount(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addRole")) {
            try {
                addRole(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("addGrantAccess")) {
            try {
                addGrantAccess(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("addLog")) {
            try {
                addLog(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("deleteAccount")) {
            try {
                deleteAccount(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("deleteRole")) {
            try {
                deleteRole(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("deleteGrantAccess")) {
            try {
                deleteGrantAccess(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("deleteLog")) {
            try {
                deleteLog(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName = request.getParameter("accountID");
        String password = request.getParameter("password");

        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.getByIDAndPassword(userName,password);
        String url = "";

        if(account != null){
            if(accountRepository.isAdminAccount(account.getId())){
                url = "/pages/successLoginWithAdminRole.jsp";
            }
            else {
                request.setAttribute("account", account);
                url = "/pages/successLogin.jsp";
            }
        }
        else {
            request.setAttribute("notification", "AccountID hoặc Password không chính xác!");
            url = "/pages/login.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();

            response.sendRedirect(url + "/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String accountID = request.getParameter("account");

        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.getByID(accountID);
        String url = "";

        if(account != null){
            GrantAccessRepository  grantAccessRepository = new GrantAccessRepository();
            ArrayList<Role> roles = (ArrayList<Role>) grantAccessRepository.getAllRoleByAccountID(accountID);
            request.setAttribute("roles", roles);
            url = "/pages/successShowRole.jsp";
        }
        else {
            url = "/pages/showRole.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void showAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleID = request.getParameter("role");

        RoleRepository roleRepository = new RoleRepository();
        Role role = roleRepository.getByID(roleID);
        String url = "";

        if(role != null){
            GrantAccessRepository  grantAccessRepository = new GrantAccessRepository();
            ArrayList<Account> accounts = (ArrayList<Account>) grantAccessRepository.getAllAccountByRoleID(roleID);
            request.setAttribute("accounts", accounts);
            url = "/pages/successShowAccount.jsp";
        }
        else {
            url = "/pages/showAccount.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void grantRoleToAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String accountID = request.getParameter("account");
        String roleID = request.getParameter("role");

        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        GrantAccess grantAccess = grantAccessRepository.getByID(roleID, accountID);

        if(grantAccess == null){
            grantAccessRepository.grantRoleToAccount(accountID, roleID);
            request.setAttribute("notification", "Đã cấp role cho account thành công!");
            request.setAttribute("textColor", "green");
        }
        else{
            request.setAttribute("notification", "Account đã tồn tại Role này");
            request.setAttribute("textColor", "red");
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/grantRoleToAccount.jsp");
        rd.forward(request, response);
    }

    private void addAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("accountID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");

        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.getByID(id);

        if(account == null){
            Account account2 = new Account(id, fullName, password, email, phone, Status.valueOf(status));
            accountRepository.insert(account2);
            request.setAttribute("notification", "Đã thêm Account thành công!");
            request.setAttribute("textColor", "green");
        }
        else{
            request.setAttribute("notification", "AccountID đã được sử dụng");
            request.setAttribute("textColor", "red");

            request.setAttribute("id", id);
            request.setAttribute("fullName", fullName);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("status", status);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/add/addAccount.jsp");
        rd.forward(request, response);
    }

    private void addRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("roleID");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        RoleRepository roleRepository = new RoleRepository();
        Role role = roleRepository.getByID(id);

        if(role == null){
            Role role2 = new Role(id, name, description, Status.valueOf(status));
            roleRepository.insert(role2);
            request.setAttribute("notification", "Đã thêm Role thành công!");
            request.setAttribute("textColor", "green");
        }
        else{
            request.setAttribute("notification", "RoleID đã được sử dụng");
            request.setAttribute("textColor", "red");

            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("status", status);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/add/addRole.jsp");
        rd.forward(request, response);
    }

    private void addGrantAccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleID = request.getParameter("role");
        String accountID = request.getParameter("account");
        String isGrant = request.getParameter("isGrant");
        String note = request.getParameter("note");

        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        GrantAccess grantAccess = grantAccessRepository.getByID(roleID, accountID);

        RoleRepository roleRepository = new RoleRepository();
        Role role = roleRepository.getByID(roleID);

        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.getByID(accountID);

        if(grantAccess == null){
            GrantAccess grantAccess2 = new GrantAccess(role, account, Grant.valueOf(isGrant), note);
            grantAccessRepository.insert(grantAccess2);
            request.setAttribute("notification", "Đã thêm GrantAccess thành công!");
            request.setAttribute("textColor", "green");
        }
        else{
            request.setAttribute("notification", "Account đã được cấp Role này");
            request.setAttribute("textColor", "red");

            request.setAttribute("roleID", roleID);
            request.setAttribute("accountID", accountID);
            request.setAttribute("isGrant", isGrant);
            request.setAttribute("note", note);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/add/addGrantAccess.jsp");
        rd.forward(request, response);
    }

    private void addLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String accountID = request.getParameter("accountID");

        String loginInput = request.getParameter("loginDate");
        Date loginDate =Date.valueOf(loginInput);

        String logoutInput = request.getParameter("logoutDate");
        Date logoutDate =Date.valueOf(logoutInput);

        String note = request.getParameter("note");

        LogRepository logRepository = new LogRepository();
        Logs log = new Logs(accountID, loginDate, logoutDate, note);
        logRepository.insert(log);
        request.setAttribute("notification", "Đã thêm log thành công!");
        request.setAttribute("textColor", "green");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/add/addLog.jsp");
        rd.forward(request, response);
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("accountID");

        AccountRepository accountRepository = new AccountRepository();
        accountRepository.delete(id);

        request.setAttribute("notification", "Đã xóa Account thành công!");
        request.setAttribute("textColor", "green");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/delete/deleteAccount.jsp");
        rd.forward(request, response);
    }

    private void deleteRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("roleID");

        RoleRepository roleRepository = new RoleRepository();
        roleRepository.delete(id);

        request.setAttribute("notification", "Đã xóa Role thành công!");
        request.setAttribute("textColor", "green");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/delete/deleteRole.jsp");
        rd.forward(request, response);
    }

    private void deleteGrantAccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleID = request.getParameter("roleID");
        String accountID = request.getParameter("accountID");

        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        GrantAccess grantAccess = grantAccessRepository.getByID(roleID, accountID);

        if(grantAccess != null){
            grantAccessRepository.delete(roleID, accountID);
            request.setAttribute("notification", "Đã xóa GrantAccess thành công!");
            request.setAttribute("textColor", "green");
        }
        else {
            request.setAttribute("notification", "Role này chưa được cấp cho Account này nên không thể xóa!");
            request.setAttribute("textColor", "red");
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/delete/deleteGrantAccess.jsp");
        rd.forward(request, response);
    }

    private void deleteLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("logID"));

        LogRepository logRepository = new LogRepository();
        logRepository.delete(id);

        request.setAttribute("notification", "Đã xóa Log thành công!");
        request.setAttribute("textColor", "green");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/delete/deleteLog.jsp");
        rd.forward(request, response);
    }
}
