package sample.сontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class ControllerMainTeacher {

    @FXML
    private Label Login;

    @FXML
    private Label family;

    @FXML
    private Label name;

    @FXML
    private Label otchestv;

    @FXML
    private Button Dicpline;

    @FXML
    private Button Otcenky;



    @FXML
    private Button Uchebplan;

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
            prst = con.prepareStatement("Select Name,Family,poBatiky from teacher where Login=?");
            prst.setString(1, ControllerLoginTeacher.Login);
            rs = prst.executeQuery();
            if(rs.next()) {
                String Name = rs.getString("Name");
                String Family = rs.getString("Family");
                String poBatiky = rs.getString("poBatiky");
                System.out.println(Name + " " + Family + " " + poBatiky);
                Login.setText(ControllerLoginTeacher.Login);
                family.setText(Family);
                name.setText(Name);
                otchestv.setText(poBatiky);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Dicpline.setOnAction(event -> {
            Dicpline.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/DicplineTeacher.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Дисциплина");
            stage.show();
            stage.setResizable(false);
        });
        Uchebplan.setOnAction(event -> {
            Uchebplan.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/UchebPlan.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Студент");
            stage.show();
            stage.setResizable(false);
        });


    }
}
