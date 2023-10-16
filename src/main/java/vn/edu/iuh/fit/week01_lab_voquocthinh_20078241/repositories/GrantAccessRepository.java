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

public class GrantAccessRepository {

    public void insert(GrantAccess grantAccess) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="insert into grant_access values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, grantAccess.getRole().getId());
        ps.setString(2, grantAccess.getAccount().getId());
        ps.setString(3, grantAccess.getIsGrant().name());
        ps.setString(4, grantAccess.getNote());
        ps.executeUpdate();
    }


    public void update(GrantAccess grantAccess) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="update grant_access set is_grant=?, note=? where role_id=? and account_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, grantAccess.getIsGrant().name());
        ps.setString(2, grantAccess.getNote());
        ps.setString(3, grantAccess.getRole().getId());
        ps.setString(4, grantAccess.getAccount().getId());
        ps.executeUpdate();
    }

    public void delete(String roleID, String accountID) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="delete from grant_access where role_id=? and account_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, roleID);
        ps.setString(2, accountID);
        ps.executeUpdate();
    }

    public GrantAccess getByID(String roleID, String accountID) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select * from grant_access where role_id=? and account_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, roleID);
        ps.setString(2, accountID);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Role role = new RoleRepository().getByID(rs.getString(1));
            Account ac = new AccountRepository().getByID(rs.getString(2));
            GrantAccess grantAccess = new GrantAccess(role, ac, Grant.valueOf(rs.getString(3)), rs.getString(4));
            return grantAccess;
        }
        return null;
    }

    public List<GrantAccess> getAll() throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="SELECT gr.role_id, gr.account_id, gr.is_grant, gr.note from grant_access gr INNER JOIN role r ON gr.role_id = r.role_id INNER JOIN account ac ON gr.account_id = ac.account_id WHERE r.`status` != 'DELETED' AND ac.`status` != 'DELETED'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<GrantAccess>lst=new ArrayList<>();
        while(rs.next()){
            Role role = new RoleRepository().getByID(rs.getString(1));
            Account ac = new AccountRepository().getByID(rs.getString(2));
            GrantAccess grantAccess = new GrantAccess(role, ac, Grant.valueOf(rs.getString(3)), rs.getString(4));
            lst.add(grantAccess);
        }
        return lst;
    }

    public List<Role> getAllRoleByAccountID(String id) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="SELECT r.role_id, r.role_name, r.description, r.`status` from grant_access gr inner join role r on gr.role_id = r.role_id WHERE gr.account_id = ? and r.status != 'DELETED'";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        List<Role> lst = new ArrayList<>();
        while(rs.next()){
            Role r = new Role(rs.getString(1), rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)));
            lst.add(r);
        }
        return lst;
    }

    public List<Account> getAllAccountByRoleID(String id) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="SELECT ac.account_id, ac.full_name, ac.`password`, ac.email, ac.phone, ac.`status` from grant_access gr inner join account ac on gr.account_id = ac.account_id WHERE gr.role_id = ? and ac.status != 'DELETED'";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        List<Account> lst = new ArrayList<>();
        while(rs.next()){
            Account ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Status.valueOf(rs.getString(6)));
            lst.add(ac);
        }
        return lst;
    }
}
