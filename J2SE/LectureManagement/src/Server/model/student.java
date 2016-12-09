package Server.model;

public class student {

    int student_id , academic_year ;

    public student(int student_id, int academic_year) {
        this.student_id = student_id;
        this.academic_year = academic_year;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(int academic_year) {
        this.academic_year = academic_year;
    }

}
