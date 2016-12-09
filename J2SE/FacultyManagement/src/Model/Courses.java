package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Courses {
    int course_id ;
    String course_name , course_dep , academic_year ;

    public Courses(int course_id, String course_name, String course_dep, String academic_year) {
        this.course_id = course_id;
        this.academic_year = academic_year;
        this.course_name = course_name;
        this.course_dep = course_dep;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_dep() {
        return course_dep;
    }

    public void setCourse_dep(String course_dep) {
        this.course_dep = course_dep;
    }

    

}