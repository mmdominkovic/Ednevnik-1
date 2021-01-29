package ba.sum.fpmoz.mim.model;

public class Subject {
    public String uid;
    public String name;
    public String idnastavnika;

    public Subject() {
    }
    public Subject(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public Subject(String uid, String name, String idnastavnika) {
        this.uid = uid;
        this.name = name;
        this.idnastavnika = idnastavnika;
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

    public String getIdnastavnika() {
        return idnastavnika;
    }

    public void setIdnastavnika(String idnastavnika) {
        this.idnastavnika = idnastavnika;
    }
}
