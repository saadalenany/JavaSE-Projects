package Controllers;

import Model.Courses;
import Model.Demonstrator;
import Model.Demonstrator_Courses;
import Model.Employees;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class Insertions {

    public void insert(String tablename , int cols , ArrayList inputs , Connection con , TableView table , ObservableList database ){
        boolean b = false;

        for(int i=0 ; i<cols ; i++){
            if(inputs.get(i) instanceof TextField){
                TextField tf = (TextField) inputs.get(i);
                if(tf.getText().equals("")){
                    b = false ;
                    JOptionPane.showMessageDialog(null, "Please fill the fields!", "Alert", JOptionPane.ERROR_MESSAGE);
                    break;
                }else{b = true ;System.out.println(tf.getText());}
            }else if(inputs.get(i) instanceof ComboBox){
                ComboBox cb = (ComboBox) inputs.get(i);
                if(cb.getSelectionModel().isEmpty()){
                    b = false ;
                    JOptionPane.showMessageDialog(null, "Please fill the fields!", "Alert", JOptionPane.ERROR_MESSAGE);
                    break;
                }else{b = true ;System.out.println(cb.getValue().toString());}
            }else if(inputs.get(i) instanceof DatePicker){
                DatePicker dp = (DatePicker) inputs.get(i);
                if(dp.getValue() == null){
                    b = false ;
                    JOptionPane.showMessageDialog(null, "Please fill the fields!", "Alert", JOptionPane.ERROR_MESSAGE);
                    break;
                }else{b = true;System.out.println(dp.getValue().toString());}
            }
        }
        if(tablename.equalsIgnoreCase("employees")){
            if(b == true){
                TextField tf = (TextField) inputs.get(0);
                try {
                    for(int i=0 ; i<new Dataclass().emp_id.size() ; i++){
                        if(tf.getText().equalsIgnoreCase(new Dataclass().emp_id.get(i))){
                            b = false ;
                            JOptionPane.showMessageDialog(null,"Employee already exists!");
                            break;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }else if(tablename.equalsIgnoreCase("demonstrator")){
            if(b == true){
                ComboBox tf = (ComboBox) inputs.get(0);
                try {
                    for(int i=0 ; i<new Dataclass().managers.size() ; i++){
                        if(tf.getValue().toString().equalsIgnoreCase(new Dataclass().managers.get(i))){
                            b = false ;
                            JOptionPane.showMessageDialog(null,"Demonstrator already exists!");
                            break;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }else if(tablename.equalsIgnoreCase("courses")){
            if(b == true){
                TextField tf = (TextField) inputs.get(0);
                TextField tf2 = (TextField) inputs.get(1);
                try {
                    for(int i=0 ; i<new Dataclass().cours_id.size() ; i++){
                        if(tf.getText().equalsIgnoreCase(new Dataclass().cours_id.get(i)) || tf2.getText().equalsIgnoreCase(new Dataclass().corses.get(i))){
                            b = false ;
                            JOptionPane.showMessageDialog(null,"Course already exists!");
                            break;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }else if(tablename.equalsIgnoreCase("demostrator_courses")){
            if(b == true){
                ComboBox tf = (ComboBox) inputs.get(0);
                ComboBox tf2 = (ComboBox) inputs.get(1);
                try {
                    for(int i=0 ; i<new Dataclass().dc_demo.size() ; i++){
                        if(tf.getValue().toString().equalsIgnoreCase(new Dataclass().dc_demo.get(i)) && tf2.getValue().toString().equalsIgnoreCase(new Dataclass().dc_course.get(i))){
                            b = false ;
                            JOptionPane.showMessageDialog(null,"Demonstrator & his course already exist!");
                            break;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
        if(tablename.equalsIgnoreCase("employees")){
            if(b == true){
                try{
                    PreparedStatement ps = con.prepareStatement("INSERT INTO `employees` (`employee_id`, `employee_name`, `employee_email`, `employee_phone`, `employee_age`, `employee_gender`, `employee_salary`, `starting_date`, `employee_role`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    for(int i=0 ,x=1 ; i<cols ; i++,x++ ){
                        if(inputs.get(i) instanceof TextField){
                            TextField tf = (TextField) inputs.get(i);
                            ps.setString(x, tf.getText());
                        }else if(inputs.get(i) instanceof ComboBox){
                            ComboBox cb = (ComboBox) inputs.get(i);
                            ps.setString(x, cb.getValue().toString());
                        }else if(inputs.get(i) instanceof DatePicker){
                            DatePicker dp = (DatePicker) inputs.get(i);
                            ps.setString(x, dp.getValue().toString());
                        }
                    }
                    ps.executeUpdate();

                    database.clear();

                    ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `employees`");

                    while (rst.next()) {
                        database.add(new Employees(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),
                                rst.getLong(5),rst.getString(6),rst.getLong(7),rst.getString(8),rst.getString(9)));
                    }

                    table.setItems(database);

                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }else if(tablename.equalsIgnoreCase("demonstrator")){
            if(b == true){
                try{
                    PreparedStatement ps = con.prepareStatement("INSERT INTO `demonstrator` (`demonstrator_name`, `demonstrator_dep`) VALUES (?, ?)");
                    for(int i=0 ,x=1 ; i<cols ; i++,x++ ){
                        if(inputs.get(i) instanceof TextField){
                            TextField tf = (TextField) inputs.get(i);
                            ps.setString(x, tf.getText());
                        }else if(inputs.get(i) instanceof ComboBox){
                            ComboBox cb = (ComboBox) inputs.get(i);
                            ps.setString(x, cb.getValue().toString());
                        }else if(inputs.get(i) instanceof DatePicker){
                            DatePicker dp = (DatePicker) inputs.get(i);
                            ps.setString(x, dp.getValue().toString());
                        }
                    }
                    ps.executeUpdate();

                    database.clear();

                    ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `demonstrator`");

                    while (rst.next()) {
                        database.add(new Demonstrator(rst.getString(1),rst.getString(2)));
                    }

                    table.setItems(database);

                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }else if(tablename.equalsIgnoreCase("courses")){
            if(b == true){
                try{
                    PreparedStatement ps = con.prepareStatement("INSERT INTO `courses` (`course_id`, `course_name`, `course_dep`, `academic_year`) VALUES (?, ?, ?, ?)");
                    for(int i=0 ,x=1 ; i<cols ; i++,x++ ){
                        if(inputs.get(i) instanceof TextField){
                            TextField tf = (TextField) inputs.get(i);
                            System.out.println(tf.getText()+"\t"+x);
                            ps.setString(x, tf.getText());
                        }else if(inputs.get(i) instanceof ComboBox){
                            ComboBox cb = (ComboBox) inputs.get(i);
                            System.out.println(cb.getValue().toString()+"\t"+x);
                            ps.setString(x, cb.getValue().toString());
                        }else if(inputs.get(i) instanceof DatePicker){
                            DatePicker dp = (DatePicker) inputs.get(i);
                            System.out.println(dp.getValue().toString()+"\t"+x);
                            ps.setString(x, dp.getValue().toString());
                        }
                    }
                    
                    ps.executeUpdate();

                    database.clear();

                    ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `courses`");

                    while (rst.next()) {
                        database.add(new Courses(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4)));
                    }

                    table.setItems(database);
                    
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }else if(tablename.equalsIgnoreCase("demostrator_courses")){
            if(b == true){
                try{
                    PreparedStatement ps = con.prepareStatement("INSERT INTO `demostrator_courses` (`demonstrator_name`, `demonstrator_course`) VALUES (?, ?)");
                    for(int i=0 ,x=1 ; i<cols ; i++,x++ ){
                        if(inputs.get(i) instanceof TextField){
                            TextField tf = (TextField) inputs.get(i);
                            ps.setString(x, tf.getText());
                        }else if(inputs.get(i) instanceof ComboBox){
                            ComboBox cb = (ComboBox) inputs.get(i);
                            ps.setString(x, cb.getValue().toString());
                        }else if(inputs.get(i) instanceof DatePicker){
                            DatePicker dp = (DatePicker) inputs.get(i);
                            ps.setString(x, dp.getValue().toString());
                        }
                    }
                    ps.executeUpdate();
                    
                    
                    database.clear();

                    ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `demostrator_courses`");

                    while (rst.next()) {
                        database.add(new Demonstrator_Courses(rst.getString(1),rst.getString(2)));
                    }

                    table.setItems(database);

                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

}
