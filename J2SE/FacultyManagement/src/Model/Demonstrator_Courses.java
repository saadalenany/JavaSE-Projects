package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Demonstrator_Courses {
    String demonstrator_name , demonstrator_course ;

    public Demonstrator_Courses(String demonstrator_name, String course_name) {
        this.demonstrator_name = demonstrator_name;
        this.demonstrator_course = course_name;
    }

    public String getDemonstrator_name() {
        return demonstrator_name;
    }

    public void setDemonstrator_name(String demonstrator_name) {
        this.demonstrator_name = demonstrator_name;
    }

    public String getDemonstrator_course() {
        return demonstrator_course;
    }

    public void setDemonstrator_course(String course_name) {
        this.demonstrator_course = course_name;
    }

}
