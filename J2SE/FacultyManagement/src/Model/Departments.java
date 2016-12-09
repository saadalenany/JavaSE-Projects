package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Departments {
    int department_id ;
    String department_name , department_manager ;

    public Departments(int department_id, String department_name, String department_manager) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.department_manager = department_manager;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDepartment_manager() {
        return department_manager;
    }

    public void setDepartment_manager(String department_manager) {
        this.department_manager = department_manager;
    }

}