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
        } else if (action.equals("toManageAccountPage")) {
            resp.sendRedirect("pages/manageAccount.jsp");
        } else if (action.equals("toShowRolePage")) {
            resp.sendRedirect("pages/showRole.jsp");
        } else if (action.equals("toShowAccountPage")) {
            resp.sendRedirect("pages/showAccount.jsp");
        } else if (action.equals("toGetAllAccountPage")) {
            resp.sendRedirect("pages/account/getAllAccount.jsp");
        } else if (action.equals("toAddAccountPage")) {
            resp.sendRedirect("pages/account/addAccount.jsp");
        } else if (action.equals("toSelectAccountPage")) {
            resp.sendRedirect("pages/account/selectAccount.jsp");
        } else if (action.equals("toDeleteAccountPage")) {
            resp.sendRedirect("pages/account/deleteAccount.jsp");
        } else if (action.equals("toAddGrantAccessPage")) {
            resp.sendRedirect("pages/account/addGrantAccess.jsp");
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
        }else if (action.equals("addGrantAccess")) {
            try {
                addGrantAccess(req,resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("deleteAccount")) {
            try {
                deleteAccount(req,resp);
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

    private void addAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("accountID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String status = String.valueOf(Status.ACTIVE);

        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.getByID(id);
        String url = "";

        if(account == null){
            Account account2 = new Account(id, fullName, password, email, phone, Status.valueOf(status));
            accountRepository.insert(account2);
            request.setAttribute("notification", "Đã thêm Account thành công!");
            url = "/pages/account/getAllAccount.jsp";
        }
        else{
            request.setAttribute("notification", "AccountID đã được sử dụng");
            request.setAttribute("textColor", "red");
            url = "/pages/account/addAccount.jsp";

            request.setAttribute("id", id);
            request.setAttribute("fullName", fullName);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void addGrantAccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleID = request.getParameter("role");
        String accountID = request.getParameter("account");
        String isGrant = String.valueOf(Grant.ENABLE);
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
            request.setAttribute("notification", "Đã cấp quyền cho tài khoản thành công!");
            url = "/pages/account/getAllGrantAccess.jsp";
        }
        else{
            request.setAttribute("notification", "Tài khoản đã tồn tại quyền này");
            request.setAttribute("textColor", "red");
            url = "/pages/account/addGrantAccess.jsp";

            request.setAttribute("roleID", roleID);
            request.setAttribute("accountID", accountID);
            request.setAttribute("note", note);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("accountID");

        AccountRepository accountRepository = new AccountRepository();
        accountRepository.delete(id);

        request.setAttribute("notification", "Đã xóa tài khoản thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/account/getAllAccount.jsp");
        rd.forward(request, response);
    }

    private void selectAccountForUpdating(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("accountID");

        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.getByID(id);

        request.setAttribute("account", account);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/account/updateAccount.jsp");
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

        request.setAttribute("notification", "Đã cập nhật tài khoản thành công!");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/account/getAllAccount.jsp");
        rd.forward(request, response);
    }
}
