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
import sample.model.Dicpline;

import java.io.IOException;
import java.sql.*;

public class ControllerDicpline {
    @FXML
    private TableView<Dicpline> TableDicpline;
    @FXML
    private TableColumn<Dicpline, String> formotchet;

    @FXML
    private TableColumn<Dicpline, Integer> hours;
    @FXML
    private Button add;

    @FXML
    private TableColumn<Dicpline, Integer> id;
    @FXML
    private Button back;
    @FXML
    private TableColumn<Dicpline, String> name;
    ObservableList<Dicpline> observableList = FXCollections.observableArrayList();
     @FXML
    private TextField namel;
    @FXML
    private TextField hourl;
    @FXML
    private TextField otchetl;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    private String ids;
    @FXML
    void initialize() {
        updateTable();
        delete.setOnAction(event -> {

            Connection cons;
            PreparedStatement prst;
            if(namel.getText()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Упс");
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните все поля");
                alert.showAndWait();
                System.out.println("error");
            }else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("  DELETE FROM dicpline WHERE (`nameDicpline` = '"+namel.getText()+"')");
                    prst.executeUpdate();
                    TableDicpline.getItems().clear();
                    updateTable();
                    System.out.println(ids);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }


        });


        edit.setOnAction(event ->{
            Connection cons;
            PreparedStatement prst;
            if(namel.equals("")||hourl.equals("")||otchetl.equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Упс");
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните все поля");
                alert.showAndWait();

            }else {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                prst = cons.prepareStatement(" UPDATE dicpline SET nameDicpline = '"+namel.getText()+"', `hours` = '"+hourl.getText()+"', `formaOtchensti` = '"+otchetl.getText()+"' WHERE (`nameDicpline` = '"+TableDicpline.getSelectionModel().getSelectedItem().getNameDicpline().toString()+"')");
                prst.executeUpdate();
                TableDicpline.getItems().clear();
                updateTable();
                System.out.println(ids);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            }


        });




        add.setOnAction(event -> {
            if(namel.getText()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Упс");
                alert.setHeaderText("Ошибка");
                alert.setContentText("Заполните все поля");
                alert.showAndWait();
            }else {


            Connection cons;
            PreparedStatement prst;
            PreparedStatement prstUcheb;


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");

                prst = cons.prepareStatement("INSERT INTO dicpline (nameDicpline, hours," +
                        " formaOtchensti, LoginTeacher) VALUES (  ?, ?, ?, " + "'" + ControllerLoginTeacher.Login + "'" + ")");
                prstUcheb=cons.prepareStatement("INSERT INTO `ucheb_prackt`.`uchebplan` (`NameSpec`, `nameDicpline`, `Semectr`) VALUES ('"+"Название"+"', '"+namel.getText()+"', '1')");



                prst.setString(1,   namel.getText() );
                prst.setString(2, hourl.getText());
                prst.setString(3, otchetl.getText());
                prst.executeUpdate();
                prstUcheb.executeUpdate();

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


    }


    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            namel.setText(TableDicpline.getSelectionModel().getSelectedItem().getNameDicpline().toString());
            hourl.setText(TableDicpline.getSelectionModel().getSelectedItem().getHours().toString());
            otchetl.setText(TableDicpline.getSelectionModel().getSelectedItem().getFormaOtchensti().toString());

        }
    }
    private void updateTable() {
        Connection con = DBconnector.ConnectDb();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT nameDicpline,hours,formaOtchensti FROM dicpline where LoginTeacher=" + "'" + ControllerLoginTeacher.Login + "'");

            while (rs.next()) {
                observableList.add(new Dicpline(rs.getString("nameDicpline"), rs.getInt("hours"), rs.getString("formaOtchensti")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        formotchet.setCellValueFactory(new PropertyValueFactory<>("formaOtchensti"));
        hours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        name.setCellValueFactory(new PropertyValueFactory<>("nameDicpline"));

        TableDicpline.setItems(observableList);
    }


}
