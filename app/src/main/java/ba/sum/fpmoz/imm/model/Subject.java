package ba.sum.fpmoz.imm.model;

public class Subject {
    public String uid;
    public String name;
    public String nastavnik;
    public String razred;


    public Subject() {
    }
    public Subject(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public Subject(String uid, String name, String nastavnik, String razred) {
        this.uid = uid;
        this.name = name;
        this.nastavnik = nastavnik;
        this.razred = razred;
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

    public String getRazred() {
        return razred;
    }

    public void setRazred(String razred) {
        this.razred = razred;
    }

    public String getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(String nastavnik) {
        this.nastavnik = nastavnik;
    }
}
