package Model;

public class Employees {

    int employee_id ;
    long employee_age , employee_salary ;
    String employee_name , employee_email , employee_phone , employee_gender , starting_date , employee_role ;

    public Employees(int employee_id, String employee_name, String employee_email, String employee_phone, long employee_age,  String employee_gender,long employee_salary, String starting_date, String employee_role) {
        this.employee_id = employee_id;
        this.employee_age = employee_age;
        this.employee_salary = employee_salary;
        this.employee_name = employee_name;
        this.employee_email = employee_email;
        this.employee_phone = employee_phone;
        this.employee_gender = employee_gender;
        this.starting_date = starting_date;
        this.employee_role = employee_role;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public long getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(long employee_age) {
        this.employee_age = employee_age;
    }

    public long getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(long employee_salary) {
        this.employee_salary = employee_salary;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public String getEmployee_phone() {
        return employee_phone;
    }

    public void setEmployee_phone(String employee_phone) {
        this.employee_phone = employee_phone;
    }

    public String getEmployee_gender() {
        return employee_gender;
    }

    public void setEmployee_gender(String employee_gender) {
        this.employee_gender = employee_gender;
    }

    public String getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(String starting_date) {
        this.starting_date = starting_date;
    }

    public String getEmployee_role() {
        return employee_role;
    }

    public void setEmployee_role(String employee_role) {
        this.employee_role = employee_role;
    }

    

}