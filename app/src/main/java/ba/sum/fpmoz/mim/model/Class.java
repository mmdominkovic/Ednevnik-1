package ba.sum.fpmoz.mim.model;

public class Class {
    public String level;
    public String name;
    public String subject;
    public String classTeacher;
public Class(){}
    public Class(String level,String name,String subject, String classTeacher) {
        this.level = level;
        this.name = name;
        this.subject=subject;
        this.classTeacher = classTeacher;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

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
