package sample.сontrollers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerRegTeacher {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private TextField family;

    @FXML
    private TextField log;

    @FXML
    private Button login;

    @FXML
    private TextField name;

    @FXML
    private TextField pass;

    @FXML
    private TextField pass1;

    @FXML
    private TextField pobatyke;

    @FXML
    void initialize() {
        back.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/LoginTeacher.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            back.getScene().getWindow().hide();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root));

            stage.show();
        });
        login.setOnAction(event -> {
            System.out.println("kekws");

        if (log.getText().trim().equals("") || pass.getText().trim().equals("") || pass1.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Упс");
            alert.setHeaderText("Ошибка");
            alert.setContentText("Заполните все поля");
            alert.showAndWait();
            System.out.println("error");
        } else {
                System.out.println("kekw");
                Connection cons;
                PreparedStatement prst;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");

                    prst = cons.prepareStatement("INSERT INTO `ucheb_prackt`.`teacher` (`Login`, `Name`, `Family`, `poBatiky`, `Password`) VALUES ('" + log.getText() + "', '" + name.getText() + "', '" + family.getText() + "', '" + pobatyke.getText() + "', '" + pass.getText() + "')");
                    login.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/view/LoginTeacher.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    stage.setResizable(false);

                    prst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }

        });
    }
}
