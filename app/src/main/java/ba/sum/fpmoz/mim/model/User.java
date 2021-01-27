package ba.sum.fpmoz.mim.model;

public class User {
    public String role;
    public User() {}

    public User(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
