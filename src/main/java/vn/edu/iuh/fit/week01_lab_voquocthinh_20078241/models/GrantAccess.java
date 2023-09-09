package vn.edu.iuh.fit.week01_lab_voquocthinh_20078241.models;

public class GrantAccess {
    private Account account;
    private Role role;
    private Grant isGrant;
    private String note;

    public GrantAccess() {
    }

    public GrantAccess(Account account, Role role, Grant isGrant, String note) {
        this.account = account;
        this.role = role;
        this.isGrant = isGrant;
        this.note = note;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Grant getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Grant isGrant) {
        this.isGrant = isGrant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "account=" + account +
                ", role=" + role +
                ", isGrant=" + isGrant +
                ", note='" + note + '\'' +
                '}';
    }
}
