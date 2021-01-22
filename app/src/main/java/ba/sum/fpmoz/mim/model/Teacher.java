package ba.sum.fpmoz.mim.model;

public class Teacher {
    public String name;
    public String surname;
    public String course;
    public String email;
    public String password;

    public Teacher () {}

    public Teacher(String name, String surname, String course,  String email,String password) {
        this.name = name;
        this.surname = surname;
        this.course = course;
        this.email = email;
        this.password = password;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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
}
