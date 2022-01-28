package sample.model;

public class Uspev {
    private String LoginStudent;
    private String NameGroup;
    private String Family;
    private String nameDicpline;
    private Integer Ocenka;
    private Integer id;

    public Uspev(String loginStudent, String nameGroup, String family, String nameDicpline, Integer ocenka, Integer id) {
        this.LoginStudent = loginStudent;
        this.NameGroup = nameGroup;
        this.Family = family;
        this.nameDicpline = nameDicpline;
        this.Ocenka = ocenka;
        this.id = id;
    }
    public Uspev(String nameDicpline, Integer ocenka) {
        this.nameDicpline = nameDicpline;
        this.Ocenka = ocenka;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameGroup() {
        return NameGroup;
    }

    public void setNameGroup(String nameGroup) {
        NameGroup = nameGroup;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public String getLoginStudent() {
        return LoginStudent;
    }

    public void setLoginStudent(String loginStudent) {
        LoginStudent = loginStudent;
    }

    public String getNameDicpline() {
        return nameDicpline;
    }

    public void setNameDicpline(String nameDicpline) {
        this.nameDicpline = nameDicpline;
    }

    public Integer getOcenka() {
        return Ocenka;
    }

    public void setOcenka(Integer ocenka) {
        Ocenka = ocenka;
    }
}
