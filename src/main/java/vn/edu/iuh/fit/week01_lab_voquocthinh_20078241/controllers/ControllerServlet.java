package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Account;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.GrantAccess;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Role;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Status;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.AccountRepository;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories.RoleRepository;

import java.io.IOException;
import java.sql.SQLException;
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
            request.setAttribute("notification", "Account không tồn tại");
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
            request.setAttribute("notification", "Role không tồn tại");
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
}
