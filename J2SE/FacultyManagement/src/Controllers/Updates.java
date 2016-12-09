package Controllers;

import Database.JDBCDriver;
import Model.Courses;
import Model.Demonstrator_Courses;
import Model.Departments;
import Model.Employees;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;

public class Updates {

    Connection con ;
    Statement stat ;

    public Updates(){
        con = new JDBCDriver().getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Updates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEmployee(TableColumn[] colarray, TableView table){
        colarray[1].setCellFactory(TextFieldTableCell.forTableColumn());
        colarray[1].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> t) {
                ((Employees) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setEmployee_name(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateEmp(t.getNewValue(), "employee_name", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        colarray[2].setCellFactory(TextFieldTableCell.forTableColumn());
        colarray[2].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> t) {
                ((Employees) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmployee_email(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateEmp(t.getNewValue(), "employee_email", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        colarray[3].setCellFactory(TextFieldTableCell.forTableColumn());
        colarray[3].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> t) {
                ((Employees) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmployee_phone(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateEmp(t.getNewValue(), "employee_phone", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        colarray[4].setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        colarray[4].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, Long> t) {
                ((Employees) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmployee_age(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateEmp(String.valueOf(t.getNewValue()), "employee_age", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        colarray[5].setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList("male", "female")));
        colarray[5].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> t) {
                ((Employees) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmployee_gender(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateEmp(t.getNewValue(), "employee_gender", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        colarray[6].setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        colarray[6].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, Long> t) {
                ((Employees) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmployee_salary(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateEmp(String.valueOf(t.getNewValue()), "employee_salary", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        colarray[7].setCellFactory(TextFieldTableCell.forTableColumn());
        colarray[7].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> t) {
                ((Employees) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setStarting_date(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateEmp(t.getNewValue(), "starting_date", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        try {
            colarray[8].setCellFactory(ComboBoxTableCell.forTableColumn(new Dataclass().roles));
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        colarray[8].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employees, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employees, String> t) {
                ((Employees) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmployee_role(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateEmp(t.getNewValue(), "employee_role", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });
    }

    public void updateCourse(TableColumn[] colarray , TableView table){
        colarray[1].setCellFactory(TextFieldTableCell.forTableColumn());
        colarray[1].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Courses, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Courses, String> t) {
                ((Courses) t.getTableView()
                        .getItems().get(
                                t.getTablePosition().getRow())).setCourse_name(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updatecourse(t.getNewValue(), "course_name", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        try {
            colarray[2].setCellFactory(ComboBoxTableCell.forTableColumn(new Dataclass().deps));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        colarray[2].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Courses, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Courses, String> t) {
                ((Courses) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setCourse_dep(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updatecourse(t.getNewValue(), "course_dep", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        colarray[3].setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList("1st year","2nd year","3rd year","4th year")));
        colarray[3].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Courses, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Courses, String> t) {
                ((Courses) t.getTableView()
                        .getItems().get(
                                t.getTablePosition().getRow())).setAcademic_year(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updatecourse(t.getNewValue(), "academic_year", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

    }

    public void updateDepartment(TableColumn[] colarray , TableView table){
        colarray[1].setCellFactory(TextFieldTableCell.forTableColumn());
        colarray[1].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Departments, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Departments, String> t) {
                ((Departments) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setDepartment_name(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateDep(t.getNewValue(), "department_name", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

        try {
            colarray[2].setCellFactory(ComboBoxTableCell.forTableColumn(new Dataclass().managers));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        colarray[2].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Departments, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Departments, String> t) {
                ((Departments) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setDepartment_manager(t.getNewValue());
                int row = table.getSelectionModel().getSelectedIndex();
                int col =(int) colarray[0].getCellObservableValue(table.getItems().get(row)).getValue();
                try {
                    updateDep(t.getNewValue(), "department_manager", col);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println(t.getTablePosition().getRow());
                System.out.println(t.getNewValue());
            }
        });

    }

    public void updateEmp(String str, String colname, int id) throws SQLException {
        stat.executeUpdate("UPDATE `employees` SET `" + colname + "` = '" + str + "' WHERE employee_id = " + id);
        System.out.println("Updated!");
    }

    public void updateCourseByDemo(String str, String colname, String input) throws SQLException {
        stat.executeUpdate("UPDATE `demostrator_courses` SET `" + colname + "` = '" + str + "' WHERE demonstrator_name = " + input);
        System.out.println("Updated!");
    }


    public void updatecourse(String str, String colname, int id) throws SQLException {
        stat.executeUpdate("UPDATE `courses` SET `" + colname + "` = '" + str + "' WHERE course_id = " + id);
        System.out.println("Updated!");
    }

    public void updateDep(String str, String colname, int id) throws SQLException {
        stat.executeUpdate("UPDATE `departments` SET `" + colname + "` = '" + str + "' WHERE department_id = " + id);
        System.out.println("Updated!");
    }

}
