package ba.sum.fpmoz.mim.model;

public class Grade {
    public String uid;
    public String value;
    public String description;
    public String lecture;

    public Grade() {
    }

    public Grade(String uid, String value, String description, String lecture) {
        this.uid = uid;
        this.value = value;
        this.description = description;
        this.lecture = lecture;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }
}