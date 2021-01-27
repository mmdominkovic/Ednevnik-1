package ba.sum.fpmoz.mim.model;

public class Admin {
    public String name;
    public String surname;
    public String email;
    public String password;
    public String role;

    public Admin() {
    }

    public Admin(String name, String surname, String email, String password, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
