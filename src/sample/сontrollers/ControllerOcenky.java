package sample.сontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.db.DBconnector;
import sample.model.*;

public class ControllerOcenky {
    @FXML
    private TableColumn<Uspev, String> Dicpline;

    @FXML
    private TableColumn<Uspev, String> Group;

    @FXML
    private TableColumn<Uspev, Integer> Ocenka;

    @FXML
    private TableView<Uspev> TableDicpline;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button delete;
    @FXML
    private TextField loginl;
    @FXML
    private TextField dicplinel;
    @FXML
    private Button edit;
    @FXML
    private TableColumn<Uspev, Integer> id;
    @FXML
    private TableColumn<Uspev, String> family;
    @FXML
    private TableColumn<Uspev, String> Login;
    @FXML
    private TextField hourl;

    //@FXML
    //private TableColumn<?, ?> id;



    @FXML
    private TextField Otcenka;
    private String ids;
    ObservableList<Uspev> observableList = FXCollections.observableArrayList();
    @FXML
    void clickItem(MouseEvent event) {
        loginl.setText(TableDicpline.getSelectionModel().getSelectedItem().getLoginStudent().toString());
        dicplinel.setText(TableDicpline.getSelectionModel().getSelectedItem().getNameDicpline().toString());
        Otcenka.setText(TableDicpline.getSelectionModel().getSelectedItem().getOcenka().toString());
        ids=TableDicpline.getSelectionModel().getSelectedItem().getId().toString();
    }

    @FXML
    void initialize() {
        updateTable();
        back.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/MainTeacher.fxml"));
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
        delete.setOnAction(event -> {
            Connection cons;
            PreparedStatement prst;
            if(ids==null){
                System.out.println("error");
            }else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("  DELETE FROM `ucheb_prackt`.`uspev` WHERE (`id` = '"+TableDicpline.getSelectionModel().getSelectedItem().getId().toString()+"')");
                    prst.executeUpdate();

                    System.out.println(ids);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                TableDicpline.getItems().clear();
                updateTable();
            }

        });
        edit.setOnAction(event -> {
            Connection cons;
            PreparedStatement prst;
            if(loginl.getText().trim().equals("")||dicplinel.getText().trim().equals("")||Otcenka.getText().trim().equals("")){
                System.out.println("error");
            }else {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("UPDATE `ucheb_prackt`.`uspev` SET `LoginStudent` = '"+loginl.getText()+"', `nameDicpline` = '"+dicplinel.getText()+"', `Ocenka` = '"+Otcenka.getText()+"' WHERE (`id` = '"+TableDicpline.getSelectionModel().getSelectedItem().getId().toString()+"')");

                            prst.executeUpdate();
                    TableDicpline.getItems().clear();
                    updateTable();

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        add.setOnAction(event -> {
            if (loginl.getText().trim().equals("")||dicplinel.getText().trim().equals("")||Otcenka.getText().trim().equals("")) {
                System.out.println("пошел");
            } else {
                Connection cons;
                PreparedStatement prst;
                PreparedStatement prstUcheb;


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");

                    prst = cons.prepareStatement("INSERT INTO uspev (LoginStudent,nameDicpline, Ocenka) VALUES ('"+loginl.getText()+"','"+dicplinel.getText()+"','"+Otcenka.getText()+"')");


                    prst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                TableDicpline.getItems().clear();
                updateTable();
            }
        });

    }

    private void updateTable() {
        Connection con = DBconnector.ConnectDb();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT LoginStudent,NameGroup,Family,nameDicpline,Ocenka,id FROM ucheb_prackt.uspev inner join student on uspev.LoginStudent=student.Login ");

            while (rs.next()) {
                observableList.add(new Uspev(rs.getString("LoginStudent").toString(),rs.getString("NameGroup"),rs.getString("Family"), rs.getString("nameDicpline"),rs.getInt("Ocenka"),rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Group.setCellValueFactory(new PropertyValueFactory<>("NameGroup"));
        Ocenka.setCellValueFactory(new PropertyValueFactory<>("Ocenka"));
        Dicpline.setCellValueFactory(new PropertyValueFactory<>("nameDicpline"));
        family.setCellValueFactory(new PropertyValueFactory<>("Family"));
        Login.setCellValueFactory(new PropertyValueFactory<>("LoginStudent"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableDicpline.setItems(observableList);
    }
}
