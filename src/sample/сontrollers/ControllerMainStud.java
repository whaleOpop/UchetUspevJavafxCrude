package sample.сontrollers;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerMainStud {

    @FXML
    private TableView<?> Table;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> Name;
    @FXML
    private TableColumn<?, ?> ocenka;
    @FXML
    private Label Familys;

    @FXML
    private Label Namel;

    @FXML
    private Label Ochetstvol;

    @FXML
    private Label Tipl;


    @FXML
    void initialize() {

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
}