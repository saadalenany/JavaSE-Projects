package Controllers;

import Database.JDBCDriver;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dataclass {

    ObservableList<String> roles , deps , demo , managers , corses , emp_id , cours_id , dc_demo , dc_course ;
    public Dataclass() throws SQLException {

        Connection con = new JDBCDriver().getConnection();

        ResultSet rs1 = con.createStatement().executeQuery("SELECT `role_name` FROM `roles`");
        roles = FXCollections.observableArrayList();
        while (rs1.next()) {
            roles.add(rs1.getString("role_name"));
        }

        ResultSet rs2 = con.createStatement().executeQuery("SELECT `department_name` FROM `departments`");
        deps = FXCollections.observableArrayList();
        while (rs2.next()) {
            deps.add(rs2.getString("department_name"));
        }

        ResultSet rs3 = con.createStatement().executeQuery("SELECT `employee_id`, `employee_name` FROM `employees`");
        demo = FXCollections.observableArrayList();
        emp_id = FXCollections.observableArrayList();
        while (rs3.next()) {
            demo.add(rs3.getString("employee_name"));
            emp_id.add(rs3.getString("employee_id"));
        }

        ResultSet rs4 = con.createStatement().executeQuery("SELECT `demonstrator_name` FROM `demonstrator`");
        managers = FXCollections.observableArrayList();
        while (rs4.next()) {
            managers.add(rs4.getString("demonstrator_name"));
        }

        ResultSet rs6 = con.createStatement().executeQuery("SELECT `demonstrator_name` , `demonstrator_course` FROM `demostrator_courses`");
        dc_demo = FXCollections.observableArrayList();
        dc_course = FXCollections.observableArrayList();
        while (rs6.next()) {
            dc_demo.add(rs6.getString("demonstrator_name"));
            dc_course.add(rs6.getString("demonstrator_course"));
        }

        ResultSet rs5 = con.createStatement().executeQuery("SELECT `course_id` , `course_name` FROM `courses`");
        corses = FXCollections.observableArrayList();
        cours_id = FXCollections.observableArrayList();
        while (rs5.next()) {
            corses.add(rs5.getString("course_name"));
            cours_id.add(rs5.getString("course_id"));
        }

        for(int i=0 ; i<demo.size() ; i++){
            System.out.print(demo.get(i)+"\t");
        }
        System.out.println();
        for(int i=0 ; i<deps.size() ; i++){
            System.out.print(deps.get(i)+"\t");
        }
        System.out.println();
        for(int i=0 ; i<roles.size() ; i++){
            System.out.print(roles.get(i)+"\t");
        }
        System.out.println();
        for(int i=0 ; i<corses.size() ; i++){
            System.out.print(corses.get(i)+"\t");
        }
        System.out.println();
        for(int i=0 ; i<managers.size() ; i++){
            System.out.print(managers.get(i)+"\t");
        }

    }

}
