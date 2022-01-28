package sample.сontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerLoginTeacher {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField log;

    @FXML
    private Button login;

    @FXML
    private TextField pass;
    static String Login;
    @FXML
    private Button back;
    @FXML
    void initialize() {
        back.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/sample.fxml"));
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
            stage.setTitle("Главное меню");
            stage.show();
        });
        System.out.println("dwd");

        login.setOnAction(actionEvent -> {
            System.out.println("нажатие");
            Connection con;
            PreparedStatement prst;
            ResultSet rs;
            if (log.getText().isEmpty() || pass.getText().isEmpty()) {

                System.out.println("пустя  стр");
            } else {
                try {
                    System.out.println("запрос бд");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = con.prepareStatement("SELECT * FROM ucheb_prackt.teacher where Login=? and Password=?");
                    prst.setString(1, log.getText());
                    prst.setString(2, pass.getText());


                    rs = prst.executeQuery();
                    System.out.println(log.getText());
                    System.out.println(pass.getText());
                    if (rs.next()) {
                        Login=log.getText();

                        //  JOptionPane.showMessageDialog(null, "Тип топ ");
                        System.out.println("ok");
                        log.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/view/MainTeacher.fxml"));

                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Учитель");
                        stage.show();
                        stage.setResizable(false);

                    } else {
                        // JOptionPane.showMessageDialog(null, "ты ебобо");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Упс");
                        alert.setHeaderText("Ошибка");
                        alert.setContentText("Такого пользователя нет");
                        alert.showAndWait();

                    }


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            });
        }
    }

