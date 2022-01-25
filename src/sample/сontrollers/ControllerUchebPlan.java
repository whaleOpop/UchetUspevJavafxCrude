package sample.сontrollers;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
        private TableColumn<UchebPlan,Integer> semestr;

        ObservableList<UchebPlan> observableList = FXCollections.observableArrayList();
        @FXML
        void clickItem(MouseEvent event) {

        }

        @FXML
        void initialize() {
            updateTable();




        }

    private void updateTable() {
        Connection con = DBconnector.ConnectDb();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT NameSpec,Semectr,uchebplan.id FROM ucheb_prackt.uchebplan inner join dicpline on uchebplan.idDicpline=dicpline.id where dicpline.LoginTeacher=" + "'" + ControllerLoginTeacher.Login + "'");

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
