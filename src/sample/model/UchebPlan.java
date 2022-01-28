package sample.model;

public class UchebPlan {
    private String id;
    private String NameSpec;
    private String nameDicpline;
    private Integer Semectr;

    public UchebPlan(String id, String nameSpec, String namedicpline, Integer semectr) {
        this.id = id;
        this.NameSpec = nameSpec;
        this.nameDicpline = namedicpline;
        this.Semectr = semectr;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameSpec() {
        return NameSpec;
    }

    public void setNameSpec(String nameSpec) {
        NameSpec = nameSpec;
    }

    public String getNameDicpline() {
        return nameDicpline;
    }

    public void setNameDicpline(String nameDicpline) {
        this.nameDicpline = nameDicpline;
    }

    public Integer getSemectr() {
        return Semectr;
    }

    public void setSemectr(Integer semectr) {
        Semectr = semectr;
    }
}
