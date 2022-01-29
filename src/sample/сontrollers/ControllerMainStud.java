package sample.сontrollers;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.db.DBconnector;
import sample.model.Uspev;

public class ControllerMainStud {

    @FXML
    private TableView<Uspev> Table;
    @FXML
    private TableColumn<Uspev, ?> id;
    @FXML
    private TableColumn<Uspev, String> Name;
    @FXML
    private TableColumn<Uspev, String> ocenka;
    @FXML
    private Label Familys;

    @FXML
    private Label Namel;

    @FXML
    private Label Ochetstvol;

    @FXML
    private Label Tipl;
    ObservableList<Uspev> observableList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        updateTable();
        System.out.println("kekw "+ControllerLoginTeacher.Login);
        Connection con;
        PreparedStatement prst;

        ResultSet rs;
        System.out.println("запрос бд");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
            prst = con.prepareStatement("SELECT Name,Family,poBatike,NameGroup FROM ucheb_prackt.student where Login=?");
            prst.setString(1, ControllerLoginStud.Login);
            rs = prst.executeQuery();
            if(rs.next()) {
                String Name = rs.getString("Name");
                String Family = rs.getString("Family");
                String poBatiky = rs.getString("poBatike");
                System.out.println(Name + " " + Family + " " + poBatiky);
                Familys.setText(Family);
                Namel.setText(Name);
                Ochetstvol.setText(poBatiky);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    private void updateTable() {
        Connection con = DBconnector.ConnectDb();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT nameDicpline,Ocenka FROM ucheb_prackt.uspev where LoginStudent='"+ControllerLoginStud.Login+"'");
            while (rs.next()) {
                observableList.add(new Uspev(rs.getString("nameDicpline"),rs.getInt("Ocenka")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Name.setCellValueFactory(new PropertyValueFactory<>("nameDicpline"));
        ocenka.setCellValueFactory(new PropertyValueFactory<>("Ocenka"));
        Table.setItems(observableList);
    }
}