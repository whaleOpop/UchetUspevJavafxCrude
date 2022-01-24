package sample.model;

public class Dicpline {
    private Integer id;
    private String nameDicpline;
    private Integer hours;
    private String formaOtchensti;
    private String LoginTeacher;

    public Dicpline(Integer id, String nameDicpline, Integer hours, String formaOtchensti, String loginTeacher) {
        this.id = id;
        this.nameDicpline = nameDicpline;
        this.hours = hours;
        this.formaOtchensti = formaOtchensti;
        this.LoginTeacher = loginTeacher;
    }

    public Dicpline(String nameDicpline, Integer hours, String formaOtchensti) {
        this.nameDicpline = nameDicpline;
        this.hours = hours;
        this.formaOtchensti = formaOtchensti;
    }

    public Dicpline(String nameDicpline, int hours, String formaOtchensti, int id) {
        this.nameDicpline = nameDicpline;
        this.hours = hours;
        this.formaOtchensti = formaOtchensti;
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameDicpline() {
        return nameDicpline;
    }

    public void setNameDicpline(String nameDicpline) {
        this.nameDicpline = nameDicpline;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getFormaOtchensti() {
        return formaOtchensti;
    }

    public void setFormaOtchensti(String formaOtchensti) {
        this.formaOtchensti = formaOtchensti;
    }

    public String getLoginTeacher() {
        return LoginTeacher;
    }

    public void setLoginTeacher(String loginTeacher) {
        LoginTeacher = loginTeacher;
    }
}
