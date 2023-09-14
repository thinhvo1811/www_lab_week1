package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.controllers;

import jakarta.ejb.Local;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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
        } else if (action.equals("selectAccountForUpdating")) {
            try {
                selectAccountForUpdating(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("updateAccount")) {
            try {
                updateAccount(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("selectRoleForUpdating")) {
            try {
                selectRoleForUpdating(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("updateRole")) {
            try {
                updateRole(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("selectGrantAccessForUpdating")) {
            try {
                selectGrantAccessForUpdating(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("updateGrantAccess")) {
            try {
                updateGrantAccess(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("selectLogForUpdating")) {
            try {
                selectLogForUpdating(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("updateLog")) {
            try {
                updateLog(req,resp);
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
            HttpSession session = request.getSession();

            LogRepository logRepository = new LogRepository();
            Logs log = new Logs(account.getId(), LocalDateTime.now(), LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0 ,0), "logged");
            logRepository.insert(log);
            session.setAttribute("log", log);

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
            HttpSession session = request.getSession();
            Logs log = (Logs) session.getAttribute("log");

            LogRepository logRepository = new LogRepository();
            log.setId(logRepository.getMaxID());
            log.setLogoutDate(LocalDateTime.now());
            log.setNote("logged out");

            logRepository.update(log);

            // Huy bo session
            session.invalidate();

            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();

            response.sendRedirect(url + "/index.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        String url = "";

        if(grantAccess == null){
            grantAccessRepository.grantRoleToAccount(accountID, roleID);
            request.setAttribute("notification", "Đã cấp role cho account thành công!");
            url = "/pages/getAll/getAllGrantAccess.jsp";
        }
        else{
            request.setAttribute("notification", "Account đã tồn tại Role này");
            request.setAttribute("textColor", "red");
            url = "/pages/grantRoleToAccount.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
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
        String url = "";

        if(account == null){
            Account account2 = new Account(id, fullName, password, email, phone, Status.valueOf(status));
            accountRepository.insert(account2);
            request.setAttribute("notification", "Đã thêm Account thành công!");
            url = "/pages/getAll/getAllAccount.jsp";
        }
        else{
            request.setAttribute("notification", "AccountID đã được sử dụng");
            request.setAttribute("textColor", "red");
            url = "/pages/add/addAccount.jsp";

            request.setAttribute("id", id);
            request.setAttribute("fullName", fullName);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("status", status);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void addRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("roleID");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        RoleRepository roleRepository = new RoleRepository();
        Role role = roleRepository.getByID(id);
        String url = "";

        if(role == null){
            Role role2 = new Role(id, name, description, Status.valueOf(status));
            roleRepository.insert(role2);
            request.setAttribute("notification", "Đã thêm Role thành công!");
            url = "/pages/getAll/getAllRole.jsp";
        }
        else{
            request.setAttribute("notification", "RoleID đã được sử dụng");
            request.setAttribute("textColor", "red");
            url = "/pages/add/addRole.jsp";

            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("status", status);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
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

        String url = "";

        if(grantAccess == null){
            GrantAccess grantAccess2 = new GrantAccess(role, account, Grant.valueOf(isGrant), note);
            grantAccessRepository.insert(grantAccess2);
            request.setAttribute("notification", "Đã thêm GrantAccess thành công!");
            url = "/pages/getAll/getAllGrantAccess.jsp";
        }
        else{
            request.setAttribute("notification", "Account đã được cấp Role này");
            request.setAttribute("textColor", "red");
            url = "/pages/add/addGrantAccess.jsp";

            request.setAttribute("roleID", roleID);
            request.setAttribute("accountID", accountID);
            request.setAttribute("isGrant", isGrant);
            request.setAttribute("note", note);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void addLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String accountID = request.getParameter("accountID");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String loginInput = request.getParameter("loginDate")+" 00:00:00";
        LocalDateTime loginDate =LocalDateTime.parse(loginInput, formatter);

        String logoutInput = request.getParameter("logoutDate")+" 00:00:00";
        LocalDateTime logoutDate =LocalDateTime.parse(logoutInput, formatter);

        String note = request.getParameter("note");

        LogRepository logRepository = new LogRepository();
        Logs log = new Logs(accountID, loginDate, logoutDate, note);
        logRepository.insert(log);
        request.setAttribute("notification", "Đã thêm log thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/getAll/getAllLog.jsp");
        rd.forward(request, response);
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("accountID");

        AccountRepository accountRepository = new AccountRepository();
        accountRepository.delete(id);

        request.setAttribute("notification", "Đã xóa Account thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/getAll/getAllAccount.jsp");
        rd.forward(request, response);
    }

    private void deleteRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("roleID");

        RoleRepository roleRepository = new RoleRepository();
        roleRepository.delete(id);

        request.setAttribute("notification", "Đã xóa Role thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/getAll/getAllRole.jsp");
        rd.forward(request, response);
    }

    private void deleteGrantAccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleID = request.getParameter("roleID");
        String accountID = request.getParameter("accountID");

        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        GrantAccess grantAccess = grantAccessRepository.getByID(roleID, accountID);
        String url = "";

        if(grantAccess != null){
            grantAccessRepository.delete(roleID, accountID);
            request.setAttribute("notification", "Đã xóa GrantAccess thành công!");
            url = "/pages/getAll/getAllGrantAccess.jsp";
        }
        else {
            request.setAttribute("notification", "Role này chưa được cấp cho Account này nên không thể xóa!");
            request.setAttribute("textColor", "red");
            url = "/pages/delete/deleteGrantAccess.jsp";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void deleteLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("logID"));

        LogRepository logRepository = new LogRepository();
        logRepository.delete(id);

        request.setAttribute("notification", "Đã xóa Log thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/getAll/getAllLog.jsp");
        rd.forward(request, response);
    }

    private void selectAccountForUpdating(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("accountID");

        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.getByID(id);

        request.setAttribute("account", account);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/update/updateAccount.jsp");
        rd.forward(request, response);
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("accountID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");


        AccountRepository accountRepository = new AccountRepository();
        Account account = new Account(id, fullName, password, email, phone, Status.valueOf(status));
        accountRepository.update(account);

        request.setAttribute("notification", "Đã cập nhật Account thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/getAll/getAllAccount.jsp");
        rd.forward(request, response);
    }

    private void selectRoleForUpdating(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("roleID");

        RoleRepository roleRepository = new RoleRepository();
        Role role = roleRepository.getByID(id);

        request.setAttribute("role", role);;

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/update/updateRole.jsp");
        rd.forward(request, response);
    }

    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("roleID");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        RoleRepository roleRepository = new RoleRepository();
        Role role = new Role(id, name, description, Status.valueOf(status));
        roleRepository.update(role);

        request.setAttribute("notification", "Đã cập nhật Role thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/getAll/getAllRole.jsp");
        rd.forward(request, response);
    }

    private void selectGrantAccessForUpdating(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleID = request.getParameter("roleID");
        String accountID = request.getParameter("accountID");

        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        GrantAccess grantAccess = grantAccessRepository.getByID(roleID, accountID);
        String url = "";

        if(grantAccess != null){
            request.setAttribute("grantAccess", grantAccess);
            url = "/pages/update/updateGrantAccess.jsp";
        }
        else {
            request.setAttribute("notification", "Role này chưa được cấp cho Account này nên không thể cập nhật!");
            request.setAttribute("textColor", "red");
            url = "/pages/update/selectGrantAccess.jsp";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void updateGrantAccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleID = request.getParameter("roleID");
        String accountID = request.getParameter("accountID");
        String isGrant = request.getParameter("isGrant");
        String note = request.getParameter("note");

        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();

        RoleRepository roleRepository = new RoleRepository();
        Role role = roleRepository.getByID(roleID);

        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.getByID(accountID);

        GrantAccess grantAccess = new GrantAccess(role, account, Grant.valueOf(isGrant), note);

        grantAccessRepository.update(grantAccess);


        request.setAttribute("notification", "Đã cập nhật GrantAccess thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/getAll/getAllGrantAccess.jsp");
        rd.forward(request, response);
    }

    private void selectLogForUpdating(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("logID"));

        LogRepository logRepository = new LogRepository();
        Logs log = logRepository.getByID(id);

        request.setAttribute("log", log);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/update/updateLog.jsp");
        rd.forward(request, response);
    }

    private void updateLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String accountID = request.getParameter("accountID");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String loginInput = request.getParameter("loginDate")+" 00:00:00";
        LocalDateTime loginDate =LocalDateTime.parse(loginInput, formatter);

        String logoutInput = request.getParameter("logoutDate")+" 00:00:00";
        LocalDateTime logoutDate =LocalDateTime.parse(logoutInput, formatter);

        String note = request.getParameter("note");

        LogRepository logRepository = new LogRepository();
        Logs log = new Logs(id, accountID, loginDate, logoutDate, note);
        logRepository.update(log);

        request.setAttribute("notification", "Đã cập nhật Log thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/getAll/getAllLog.jsp");
        rd.forward(request, response);
    }
}
