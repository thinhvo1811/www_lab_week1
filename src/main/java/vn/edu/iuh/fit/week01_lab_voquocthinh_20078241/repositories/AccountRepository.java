package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories;

import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.connect.Connect;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository{

    public void insert(Account account) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="insert into account values(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, account.getId());
        ps.setString(2, account.getFullName());
        ps.setString(3, account.getPassword());
        ps.setString(4, account.getEmail());
        ps.setString(5, account.getPhone());
        ps.setString(6, account.getStatus().name());
        ps.executeUpdate();
    }


    public void update(Account account) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="update account set full_name=?, password=?, email=?, phone=?, status=? where account_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, account.getFullName());
        ps.setString(2, account.getPassword());
        ps.setString(3, account.getEmail());
        ps.setString(4, account.getPhone());
        ps.setString(5, account.getStatus().name());
        ps.setString(6, account.getId());
        ps.executeUpdate();
    }

    public void delete(String id) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="delete from account where account_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public Account getByID(String id) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select * from account where account_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Account ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Status.valueOf(rs.getString(6)));
            return ac;
        }
        return null;
    }

    public List<Account> getAll() throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select * from account where status != 'DELETED'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Account>lst=new ArrayList<>();
        while(rs.next()){
            Account ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Status.valueOf(rs.getString(6)));
            lst.add(ac);
        }
        return lst;
    }

    public Account getByIDAndPassword(String id, String password) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select * from account where account_id=? and password=? and status != 'DELETED'";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Account ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Status.valueOf(rs.getString(6)));
            return ac;
        }
        return null;
    }

    public boolean isAdminAccount(String accountID) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select * from grant_access gr inner join role r on gr.role_id = r.role_id where r.role_name ='admin' and account_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, accountID);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return true;
        }
        return false;
    }
}
