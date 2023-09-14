package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models;

import java.sql.Date;
import java.time.LocalDateTime;

public class Logs {
    private int id;
    private String accountID;
    private LocalDateTime loginDate;
    private LocalDateTime logoutDate;
    private String note;

    public Logs() {
    }

    public Logs(int id, String accountID, LocalDateTime loginDate, LocalDateTime logoutDate, String note) {
        this.id = id;
        this.accountID = accountID;
        this.loginDate = loginDate;
        this.logoutDate = logoutDate;
        this.note = note;
    }

    public Logs(String accountID, LocalDateTime loginDate, LocalDateTime logoutDate, String note) {
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

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public LocalDateTime getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(LocalDateTime logoutDate) {
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
