package sample.сontrollers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import sample.model.Dicpline;
import sample.model.UchebPlan;


public class ControllerUchebPlan {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<UchebPlan> TableDicpline;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<UchebPlan, Integer> id;

    @FXML
    private TableColumn<UchebPlan, String> name;

    @FXML
    private TextField namel;

    @FXML
    private TextField otchetl;

    @FXML
    private TableColumn<UchebPlan, Integer> semestr;

    private String ids;

    ObservableList<UchebPlan> observableList = FXCollections.observableArrayList();

    @FXML
    void clickItem(MouseEvent event) {
        if (event.getClickCount() == 2) //Checking double click
        {
            namel.setText(TableDicpline.getSelectionModel().getSelectedItem().getNameSpec().toString());
            otchetl.setText(TableDicpline.getSelectionModel().getSelectedItem().getSemectr().toString());
            ids = TableDicpline.getSelectionModel().getSelectedItem().getId().toString();
        }
    }

    @FXML
    void initialize() {
        updateTable();
        delete.setOnAction(event -> {
            Connection cons;
            PreparedStatement prst;
            if(ids==null){
                System.out.println("error");
            }else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("DELETE FROM dicpline WHERE (id = (Select idDicpline from uchebplan where id ="+ids+" ))");
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
            if(namel.equals("")||otchetl.equals("")){
                System.out.println("error");
            }else {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    cons = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC", "root", "77322850nN%");
                    prst = cons.prepareStatement("UPDATE `ucheb_prackt`.`uchebplan` SET `NameSpec` = '"+namel.getText()+"', `Semectr` = '"+otchetl.getText()+"' WHERE (`id` = '"+ids+"')");

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

    private void updateTable() {
        Connection con = DBconnector.ConnectDb();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT NameSpec,Semectr,uchebplan.id FROM ucheb_prackt.uchebplan inner join dicpline on uchebplan.nameDicpline=dicpline.nameDicpline where dicpline.LoginTeacher=" + "'" + ControllerLoginTeacher.Login + "'");

            while (rs.next()) {
                observableList.add(new UchebPlan(rs.getString("NameSpec"), rs.getInt("Semectr"), rs.getString("id").toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        semestr.setCellValueFactory(new PropertyValueFactory<>("Semectr"));
        name.setCellValueFactory(new PropertyValueFactory<>("NameSpec"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableDicpline.setItems(observableList);
    }
}
