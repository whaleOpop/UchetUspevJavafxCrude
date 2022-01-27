package sample.model;

import javafx.scene.Group;
import javafx.scene.Node;

import java.util.Collection;

public class Groups  {

    private String NameGroup;

    public Groups(String nameGroup) {

        this.NameGroup=nameGroup;
    }




    public String getNameGroup() {
        return NameGroup;
    }

    public void setNameGroup(String nameGroup) {
        NameGroup = nameGroup;
    }
}