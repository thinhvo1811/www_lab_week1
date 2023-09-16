package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories;

import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.connect.Connect;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Role;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository {

    public void insert(Role role) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="insert into role values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, role.getId());
        ps.setString(2, role.getName());
        ps.setString(3, role.getDescription());
        ps.setString(4, role.getStatus().name());
        ps.executeUpdate();
    }


    public void update(Role role) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="update role set role_name=?, description=?, status=? where role_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, role.getName());
        ps.setString(2, role.getDescription());
        ps.setString(3, role.getStatus().name());
        ps.setString(4, role.getId());
        ps.executeUpdate();
    }

    public void delete(String id) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="delete from role where role_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public Role getByID(String id) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select * from role where role_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Role r = new Role(rs.getString(1), rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)));
            return r;
        }
        return null;
    }

    public List<Role> getAll() throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select * from role where status != 'DELETED'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Role>lst=new ArrayList<>();
        while(rs.next()){
            Role r = new Role(rs.getString(1), rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)));
            lst.add(r);
        }
        return lst;
    }
}
