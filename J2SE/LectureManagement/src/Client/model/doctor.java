package Client.model;

public class doctor {

    int doctor_id , numberofquizes;
    String doctor_course ;

    public doctor(int doctor_id, String doctor_course) {
        this.doctor_id = doctor_id;
        this.doctor_course = doctor_course;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_course() {
        return doctor_course;
    }

    public void setDoctor_course(String doctor_course) {
        this.doctor_course = doctor_course;
    }

    public int getNumberofquizes() {
        return numberofquizes;
    }

    
}
