package ba.sum.fpmoz.imm.model;

public class Student {
    public String uid;
    public String name;
    public String email;
    public String grade;

    public Student() {
    }

    public Student(String uid, String email, String name, String grade) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}