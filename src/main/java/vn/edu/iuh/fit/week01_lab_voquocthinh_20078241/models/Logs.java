package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models;

import java.util.Date;

public class Logs {
    private int id;
    private String accountID;
    private Date loginDate;
    private Date logoutDate;
    private String note;

    public Logs() {
    }

    public Logs(int id, String accountID, Date loginDate, Date logoutDate, String note) {
        this.id = id;
        this.accountID = accountID;
        this.loginDate = loginDate;
        this.logoutDate = logoutDate;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", accountID='" + accountID + '\'' +
                ", loginDate=" + loginDate +
                ", logoutDate=" + logoutDate +
                ", note='" + note + '\'' +
                '}';
    }
}
