package sample.сontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.db.DBconnector;

import sample.model.Groups;

import java.io.IOException;
import java.sql.*;


public class ControllerGroup {


    @FXML
    private TableView<Groups> TableDicpline;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<Groups, Integer> id;

    @FXML
    private TableColumn<Groups, String> name;

    @FXML
    private TextField namel;
    private String ids;

    @FXML
    void clickItem(MouseEvent event) {
        namel.setText(TableDicpline.getSelectionModel().getSelectedItem().getNameGroup().toString());
        ids=TableDicpline.getSelectionModel().getSelectedItem().getNameGroup().toString();
    }

    ObservableList<Groups> observableList = FXCollections.observableArrayList();
    int random_number1;

    @FXML
    void initialize() {
        updateTable();
        edit.setOnAction(event -> {

            Connection cons;
            PreparedStatement prst;
            if(namel.getText().trim().equals("")){
                System.out.println("error");
            }else {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("  UPDATE `ucheb_prackt`.`group` SET `NameGroup` = '"+namel.getText()+"' WHERE (`NameGroup` = '"+TableDicpline.getSelectionModel().getSelectedItem().getNameGroup()+"')");

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
        delete.setOnAction(event -> {

            Connection cons;
            PreparedStatement prst;
            if (namel == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Упс");
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните все поля");
                alert.showAndWait();
                System.out.println("error");
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement(" DELETE FROM `ucheb_prackt`.`group` WHERE (`NameGroup` = '" + namel.getText() + "')");
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
            add.setOnAction(event -> {
                if (namel.getText().trim().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Упс");
                    alert.setHeaderText("Ошибка");
                    alert.setContentText("Заполните все поля");
                    alert.showAndWait();
                    System.out.println("error");
                } else {
                    Connection cons;
                    PreparedStatement prst;
                    PreparedStatement prstUcheb;
                    PreparedStatement prstUspev;

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                        int a = 0;
                        int b = 9999;
                        prst = cons.prepareStatement("INSERT INTO `ucheb_prackt`.`group` (`NameGroup`) VALUES ('" + namel.getText() + "');");


                        prst.executeUpdate();
                        for (int i = 0; i < 25; i++) {
                            random_number1 = (a + (int) (Math.random() * b)) + i;
                            prstUcheb = cons.prepareStatement("INSERT INTO `ucheb_prackt`.`student` (`Login`, `numberZachetki`, `Name`, `Family`, `poBatike`, `NameGroup`, `YearPostupleny`, `FormObuch`, `Password`) VALUES ('" + namel.getText()+ random_number1 + "', 'Number', 'Name', 'Family', 'Otchestvo', '" + namel.getText() + "', '1', 'FormObuch', 'Password')");
                            prstUspev = cons.prepareStatement("INSERT INTO uspev (LoginStudent, Ocenka) VALUES ('"+namel.getText()+ random_number1+"','"+"0"+"')");

                            prstUcheb.executeUpdate();
                            prstUspev.executeUpdate();

                        }

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
            ResultSet rs = con.createStatement().executeQuery("SELECT NameGroup FROM ucheb_prackt.group");

            while (rs.next()) {
                observableList.add(new Groups(rs.getString("NameGroup")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        name.setCellValueFactory(new PropertyValueFactory<>("NameGroup"));

        TableDicpline.setItems(observableList);
    }
}
