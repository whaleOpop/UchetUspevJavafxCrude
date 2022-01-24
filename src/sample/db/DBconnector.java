package sample.db;

import javax.swing.*;
import java.sql.*;

public class DBconnector {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ucheb_prackt?serverTimezone=UTC","root","77322850nN%");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }




//    public static ObservableList<Teacher> getDatausers(){
//        Connection conn = ConnectDb();
//        ObservableList<Teacher> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("select * from users");
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()){
//                list.add(new Teacher(Integer.parseInt(rs.getString("id")), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("type")));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }



}
