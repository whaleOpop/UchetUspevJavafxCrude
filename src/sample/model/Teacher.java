package sample.model;

public class Teacher {
    private Integer id;
    private String Login;
    private String Name;
    private String Family;
    private String poBatiky;
    private String Password;

    public Teacher(Integer id, String login, String name, String family, String poBatiky, String password) {
        this.id = id;
        this.Login = login;
        this.Name = name;
        this.Family = family;
        this.poBatiky = poBatiky;
        this.Password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public String getPoBatiky() {
        return poBatiky;
    }

    public void setPoBatiky(String poBatiky) {
        this.poBatiky = poBatiky;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
