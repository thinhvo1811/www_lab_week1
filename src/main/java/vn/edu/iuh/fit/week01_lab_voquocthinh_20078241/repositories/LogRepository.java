package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories;

import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.connect.Connect;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogRepository {

    public void insert(Logs log) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="insert into log values(NULL,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, log.getAccountID());
        ps.setDate(2, log.getLoginDate());
        ps.setDate(3, log.getLogoutDate());
        ps.setString(4, log.getNote());
        ps.executeUpdate();
    }


    public void update(Logs log) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="update log set account_id=?, login_time=?, logout_time=?, notes=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, log.getAccountID());
        ps.setDate(2, log.getLoginDate());
        ps.setDate(3, log.getLogoutDate());
        ps.setString(4, log.getNote());
        ps.setInt(5, log.getId());
        ps.executeUpdate();
    }

    public void delete(int id) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="delete from log where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public Logs getByID(int id) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select * from log where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Logs l = new Logs(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5));
            return l;
        }
        return null;
    }

    public List<Logs> getAll() throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select * from log ";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Logs>lst=new ArrayList<>();
        while(rs.next()){
            Logs l = new Logs(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5));
            lst.add(l);
        }
        return lst;
    }
}
