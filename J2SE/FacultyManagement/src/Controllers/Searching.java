package Controllers;

import Model.Courses;
import Model.Demonstrator;
import Model.Demonstrator_Courses;
import Model.Departments;
import Model.Employees;
import Model.Roles;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Searching {

    public void searchInEmployee( TextField tf , FilteredList filterdata , TableView table , TextField filterfield , TextField gender , TextField job) {
        String str = tf.getPromptText();

        tf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Employees>() {
                @Override
                public boolean test(Employees employee) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(str.equalsIgnoreCase("employee_id")){
                        if (String.valueOf(employee.getEmployee_id()).toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches id.
                        }
                    }else if(str.equalsIgnoreCase("employee_name")){
                        if (employee.getEmployee_name().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches name.
                        }
                    }else if(str.equalsIgnoreCase("employee_email")){
                        if (employee.getEmployee_email().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches email.
                        }
                    }else if(str.equalsIgnoreCase("employee_phone")){
                        if (employee.getEmployee_phone().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches phone.
                        }
                    }else if(str.equalsIgnoreCase("employee_age")){
                        if (String.valueOf(employee.getEmployee_age()).toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches age.
                        }
                    }else if(str.equalsIgnoreCase("employee_salary")){
                        if (String.valueOf(employee.getEmployee_salary()).toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches salary.
                        }
                    }else if(str.equalsIgnoreCase("starting_date")){
                        if (employee.getStarting_date().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches date.
                        }
                    }
                    return false;
                }
            });
        });

        // Set the filter Predicate whenever the filter changes.
        filterfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Employees>() {
                @Override
                public boolean test(Employees employee) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(employee.getEmployee_id()).toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches id.
                    } else if (employee.getEmployee_name().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches name.
                    } else if (employee.getEmployee_email().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches employee email.
                    } else if (String.valueOf(employee.getEmployee_age()).toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches employee age.
                    } else if (employee.getEmployee_gender().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches employee gender.
                    } else if (employee.getEmployee_phone().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches employee phone.
                    } else if (String.valueOf(employee.getEmployee_salary()).toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches employee salary.
                    } else if (employee.getEmployee_role().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches role id.
                    }
                    return false; // Does not match.
                }
            });
        });

        gender.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Employees>() {
                @Override
                public boolean test(Employees employee) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    return employee.getEmployee_gender().toLowerCase().contains(lowerCaseFilter) ;
                };
            });
        });

        job.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Employees>() {
                @Override
                public boolean test(Employees employee) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    return employee.getEmployee_role().toLowerCase().contains(lowerCaseFilter) ;
                };
            });
        });

        SortedList<Employees> sortedlist = new SortedList<>(filterdata);
        sortedlist.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedlist);
    }

    public void searchInDepartments( TextField tf , FilteredList filterdata , TableView table , TextField filterfield) {
        String str = tf.getPromptText();

        tf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Departments>() {
                @Override
                public boolean test(Departments department) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(str.equalsIgnoreCase("department_id")){
                        if (String.valueOf(department.getDepartment_id()).toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches id.
                        }
                    }else if(str.equalsIgnoreCase("department_name")){
                        if (department.getDepartment_name().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches name.
                        }
                    }else if(str.equalsIgnoreCase("department_manager")){
                        if (department.getDepartment_manager().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches department manager.
                        }
                    }
                    return false;
                }
            });
        });

        // Set the filter Predicate whenever the filter changes.
        filterfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Departments>() {
                @Override
                public boolean test(Departments department) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(department.getDepartment_id()).toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches id.
                    } else if (department.getDepartment_name().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches name.
                    } else if (department.getDepartment_manager().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches department manager.
                    }
                    return false; // Does not match.
                }
            });
        });
        SortedList<Departments> sortedlist = new SortedList<>(filterdata);
        sortedlist.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedlist);
    }

    public void searchInDemonstrators(FilteredList filterdata, TableView table, TextField filterfield, TextField name, TextField dep) {

        name.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Demonstrator>() {
                @Override
                public boolean test(Demonstrator demonstrator) {
                    // If filter text is empty, display all demonstrators.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    return demonstrator.getDemonstrator_name().toLowerCase().contains(lowerCaseFilter);
                }
            });
        });

        dep.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Demonstrator>() {
                @Override
                public boolean test(Demonstrator demonstrator) {
                    // If filter text is empty, display all demonstrators.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    return demonstrator.getDemonstrator_dep().toLowerCase().contains(lowerCaseFilter);
                }
            });
        });

        // Set the filter Predicate whenever the filter changes.
        filterfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Demonstrator>() {
                @Override
                public boolean test(Demonstrator demonstrator) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (demonstrator.getDemonstrator_name().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches name.
                    } else if (demonstrator.getDemonstrator_dep().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches department.
                    }
                    return false; // Does not match.
                }
            });
        });
        SortedList<Demonstrator> sortedlist = new SortedList<>(filterdata);
        sortedlist.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedlist);
    }

    public void searchInCourses( TextField tf, FilteredList filterdata, TableView table, TextField filterfield, TextField dep, TextField year) {
        String str = tf.getPromptText();

        tf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Courses>() {
                @Override
                public boolean test(Courses course) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(str.equalsIgnoreCase("course_id")){
                        if (String.valueOf(course.getCourse_id()).toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches id.
                        }
                    }else if(str.equalsIgnoreCase("course_name")){
                        if (course.getCourse_name().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches name.
                        }
                    }
                    return false;
                }
            });
        });

        dep.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Courses>() {
                @Override
                public boolean test(Courses course) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    return course.getCourse_dep().toLowerCase().contains(lowerCaseFilter);
                }
            });
        });

        year.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Courses>() {
                @Override
                public boolean test(Courses course) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    return course.getAcademic_year().toLowerCase().contains(lowerCaseFilter);
                }
            });
        });

        // Set the filter Predicate whenever the filter changes.
        filterfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Courses>() {
                @Override
                public boolean test(Courses course) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(course.getCourse_id()).toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches id.
                    } else if (course.getCourse_name().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches name.
                    } else if (course.getCourse_dep().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches department.
                    } else if (course.getAcademic_year().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches academic year.
                    }
                    return false; // Does not match.
                }
            });
        });
        SortedList<Courses> sortedlist = new SortedList<>(filterdata);
        sortedlist.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedlist);
    }

    public void searchInDemonstratorCourses(FilteredList filterdata, TableView table, TextField filterfield, TextField name, TextField course) {
        name.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Demonstrator_Courses>() {
                @Override
                public boolean test(Demonstrator_Courses demonstrator) {
                    // If filter text is empty, display all demonstrators.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    return demonstrator.getDemonstrator_name().toLowerCase().contains(lowerCaseFilter);
                }
            });
        });

        course.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Demonstrator_Courses>() {
                @Override
                public boolean test(Demonstrator_Courses demonstrator) {
                    // If filter text is empty, display all demonstrators.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    return demonstrator.getDemonstrator_course().toLowerCase().contains(lowerCaseFilter);
                }
            });
        });

        // Set the filter Predicate whenever the filter changes.
        filterfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Demonstrator_Courses>() {
                @Override
                public boolean test(Demonstrator_Courses demonstrator) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (demonstrator.getDemonstrator_name().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches name.
                    } else if (demonstrator.getDemonstrator_course().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches course.
                    }
                    return false; // Does not match.
                }
            });
        });
        SortedList<Demonstrator_Courses> sortedlist = new SortedList<>(filterdata);
        sortedlist.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedlist);
    }

    public void searchInRoles( TextField tf , FilteredList filterdata , TableView table , TextField filterfield) {
        String str = tf.getPromptText();

        tf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Roles>() {
                @Override
                public boolean test(Roles roles) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(str.equalsIgnoreCase("role_id")){
                        if (String.valueOf(roles.getRole_id()).toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches id.
                        }
                    }else if(str.equalsIgnoreCase("role_name")){
                        if (roles.getRole_name().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches name.
                        }
                    }
                    return false;
                }
            });
        });

        // Set the filter Predicate whenever the filter changes.
        filterfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterdata.setPredicate(new Predicate<Roles>() {
                @Override
                public boolean test(Roles roles) {
                    // If filter text is empty, display all employees.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(roles.getRole_id()).toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches id.
                    } else if (roles.getRole_name().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches name.
                    }
                    return false; // Does not match.
                }
            });
        });
        SortedList<Roles> sortedlist = new SortedList<>(filterdata);
        sortedlist.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedlist);
    }

}
