package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.repositories;

import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.connect.Connect;
import vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models.Logs;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        ps.setString(2, log.getLoginDate().toString());
        ps.setString(3, log.getLogoutDate().toString());
        ps.setString(4, log.getNote());
        ps.executeUpdate();
    }


    public void update(Logs log) throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="update log set account_id=?, login_time=?, logout_time=?, notes=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, log.getAccountID());
        ps.setString(2, log.getLoginDate().toString());
        ps.setString(3, log.getLogoutDate().toString());
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
            String loginDate = rs.getString(3);
            String logoutDate = rs.getString(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Logs l = new Logs(rs.getInt(1), rs.getString(2), LocalDateTime.parse(loginDate, formatter), LocalDateTime.parse(logoutDate, formatter), rs.getString(5));
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
            String loginDate = rs.getString(3);
            String logoutDate = rs.getString(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Logs l = new Logs(rs.getInt(1), rs.getString(2), LocalDateTime.parse(loginDate, formatter), LocalDateTime.parse(logoutDate, formatter), rs.getString(5));
            lst.add(l);
        }
        return lst;
    }

    public int getMaxID() throws Exception {
        Connect.getInstance().connect();
        Connection con = Connect.getCon();
        String sql="select max(id) from log";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        while(rs.next()){
            id = rs.getInt(1);
        }
        return id;
    }
}
