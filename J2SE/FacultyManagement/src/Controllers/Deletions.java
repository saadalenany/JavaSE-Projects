package Controllers;

import Model.Courses;
import Model.Demonstrator;
import Model.Demonstrator_Courses;
import Model.Employees;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;


public class Deletions {

    public void delete(String tablename , TableView table , TableColumn[] colarray , Connection con , ObservableList database) throws SQLException {
        if( tablename.equals("employees")){
            ObservableList<Employees> selectedrow;
            selectedrow = table.getSelectionModel().getSelectedItems();

            int row = table.getSelectionModel().getSelectedIndex();
            System.out.println(row);
            String col;

            if(row == -1){
                JOptionPane.showMessageDialog(null, "please choose a record to delete!");
                return;
            }

            col = (String) colarray[1].getCellObservableValue(table.getItems().get(row)).getValue();
            System.out.println(col);

            //delete row that is connected to demonstrator_courses table through foreign key
            ResultSet rs1 = con.createStatement().executeQuery("SELECT `demonstrator_name` FROM `demostrator_courses`");
            rs1.first();
            while (rs1.next()) {
                if (rs1.getString("demonstrator_name").equalsIgnoreCase(col)) {
                    con.createStatement().executeUpdate("DELETE FROM `demostrator_courses` WHERE `demonstrator_name` = '" + col + "'");
                    System.out.println("deleted from demostrator_courses!");
                }
            }

            //request to update row that is connected to departments table through foreign key
            ResultSet rs2 = con.createStatement().executeQuery("SELECT `department_manager` FROM `departments`");
            rs2.first();
            while (rs2.next()) {
                if (rs2.getString("department_manager").equalsIgnoreCase(col)) {
                    JOptionPane.showMessageDialog(null, "Employee is a Department manager\nplease replace him from deprtment managers first!", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }

            //delete row that is connected to demonstrator table through foreign key
            ResultSet rs3 = con.createStatement().executeQuery("SELECT `demonstrator_name` FROM `demonstrator`");
            rs3.first();
            while (rs3.next()) {
                if (rs3.getString("demonstrator_name").equalsIgnoreCase(col)) {
                    con.createStatement().executeUpdate("DELETE FROM `demonstrator` WHERE `demonstrator_name` = '" + col + "'");
                    System.out.println("deleted from demonstrator!");
                }
            }

            //delete row from the current table
            con.createStatement().executeUpdate("DELETE FROM `employees` WHERE `employee_name` = '" + col + "'");
            System.out.println("deleted from employees !");

            //delete from tableview
            selectedrow.forEach(database::remove);
        }else if(tablename.equals("demonstrator")){
            ObservableList<Demonstrator> selectedrow;
            selectedrow = table.getSelectionModel().getSelectedItems();

            int row = table.getSelectionModel().getSelectedIndex();
            System.out.println(row);
            String col;

            if(row == -1){
                JOptionPane.showMessageDialog(null, "please choose a record to delete!");
                return;
            }

            col = (String) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
            System.out.println(col);

            //delete row that is connected to demonstrator_courses table through foreign key
            ResultSet rs1 = con.createStatement().executeQuery("SELECT `demonstrator_name` FROM `demostrator_courses`");
            rs1.first();
            while (rs1.next()) {
                if (rs1.getString("demonstrator_name").equalsIgnoreCase(col)) {
                    con.createStatement().executeUpdate("DELETE FROM `demostrator_courses` WHERE `demonstrator_name` = '" + col + "'");
                    System.out.println("deleted from demostrator_courses!");
                }
            }

            //request to update row that is connected to departments table through foreign key
            ResultSet rs2 = con.createStatement().executeQuery("SELECT `department_manager` FROM `departments`");
            rs2.first();
            while (rs2.next()) {
                if (rs2.getString("department_manager").equalsIgnoreCase(col)) {
                    JOptionPane.showMessageDialog(null, "Demonstrator is a Department manager\nplease replace him from deprtment managers first!", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            }

            //delete row in the demonstrator table
            con.createStatement().executeUpdate("DELETE FROM `demonstrator` WHERE `demonstrator_name` = '" + col + "'");
            System.out.println("deleted from demonstrator!");

            //delete from tableview
            selectedrow.forEach(database::remove);
        }else if(tablename.equals("demostrator_courses")){
            ObservableList<Demonstrator_Courses> selectedrow;
            selectedrow = table.getSelectionModel().getSelectedItems();

            int row = table.getSelectionModel().getSelectedIndex();
            System.out.println(row);
            String col;

            if(row == -1){
                JOptionPane.showMessageDialog(null, "please choose a record to delete!");
                return;
            }

            col = (String) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
            System.out.println(col);

            //delete row in the demonstrator_courses table
            con.createStatement().executeUpdate("DELETE FROM `demostrator_courses` WHERE `demonstrator_name` = '" + col + "'");
            System.out.println("deleted from demostrator_courses!");

            //delete from tableview
            selectedrow.forEach(database::remove);
        }else if(tablename.equals("courses")){
            ObservableList<Courses> selectedrow;
            selectedrow = table.getSelectionModel().getSelectedItems();

            int row = table.getSelectionModel().getSelectedIndex();
            System.out.println(row);
            String col;

            if(row == -1){
                JOptionPane.showMessageDialog(null, "please choose a record to delete!");
                return;
            }

            col = (String) colarray[1].getCellObservableValue(table.getItems().get(row)).getValue();
            System.out.println(col);

            //delete row that is connected to demonstrator_courses table through foreign key
            ResultSet rs1 = con.createStatement().executeQuery("SELECT `course_name` FROM `demostrator_courses`");
            while (rs1.next()) {
                if (rs1.getString("course_name").equalsIgnoreCase(col)) {
                    con.createStatement().executeUpdate("DELETE FROM `demostrator_courses` WHERE `course_name` = '" + col + "'");
                    System.out.println("deleted from demostrator_courses!");
                }
            }

            //delete row in the courses table
            con.createStatement().executeUpdate("DELETE FROM `courses` WHERE `courses_name` = '" + col + "'");
            System.out.println("deleted from courses!");

            //delete from tableview
            selectedrow.forEach(database::remove);
        }
    }

}
