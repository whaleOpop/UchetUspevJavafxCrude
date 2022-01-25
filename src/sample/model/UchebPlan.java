package sample.model;

public class UchebPlan {
    private String id;
    private String NameSpec;
    private Integer idDicpline;
    private Integer Semectr;

    public UchebPlan(String id, String nameSpec, Integer idDicpline, Integer semectr) {
        this.id = id;
        this.NameSpec = nameSpec;
        this.idDicpline = idDicpline;
        this.Semectr = semectr;
    }

    public UchebPlan(String nameSpec, int semectr, String id) {
        this.id = id;
        this.NameSpec = nameSpec;

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

    public Integer getIdDicpline() {
        return idDicpline;
    }

    public void setIdDicpline(Integer idDicpline) {
        this.idDicpline = idDicpline;
    }

    public Integer getSemectr() {
        return Semectr;
    }

    public void setSemectr(Integer semectr) {
        Semectr = semectr;
    }
}
