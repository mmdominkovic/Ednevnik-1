package ba.sum.fpmoz.imm.model;

public class Class {
    public String uid;
    public String name;

public Class(){}
    public Class(String uid,String name) {
        this.uid = uid;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }
}
