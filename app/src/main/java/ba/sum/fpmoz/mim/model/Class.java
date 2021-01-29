package ba.sum.fpmoz.mim.model;

public class Class {
    public String uid;
    public String name;
    public String subject;
    public String classTeacher;

public Class(){}
    public Class(String uid,String name,String subject, String classTeacher) {
        this.uid = uid;
        this.name = name;
        this.subject=subject;
        this.classTeacher = classTeacher;
    }

    public String getLevel() {
        return uid;
    }

    public void setLevel(String level) {
        this.uid = uid;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }
}
