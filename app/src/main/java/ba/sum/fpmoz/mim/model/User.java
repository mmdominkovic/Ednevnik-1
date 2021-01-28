package ba.sum.fpmoz.mim.model;

public class User {
    public String uid;
    public String role;
    public String displayName;
    public String email;

    public User() {}

    public User(String uid, String email, String displayName, String role) {
        this.uid = uid;
        this.role = role;
        this.displayName = displayName;
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
