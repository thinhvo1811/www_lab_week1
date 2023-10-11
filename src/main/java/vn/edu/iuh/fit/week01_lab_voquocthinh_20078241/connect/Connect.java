package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection con = null;
    public static Connect instance = new Connect();

    public static Connection getCon() {
        return con;
    }

    public static Connect getInstance() {
        return instance;
    }

    public void connect() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://localhost:3306/mydb2?createDatabaseIfNotExist=true";
        con = DriverManager.getConnection(url, "root", "");
    }

    public void disconnect() {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

