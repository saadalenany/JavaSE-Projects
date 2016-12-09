package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Demonstrator {
    String demonstrator_name , demonstrator_dep;

    public Demonstrator(String demonstrator_name, String demonstrator_dep) {
        this.demonstrator_name = demonstrator_name;
        this.demonstrator_dep = demonstrator_dep;
    }

    public String getDemonstrator_name() {
        return demonstrator_name;
    }

    public void setDemonstrator_name(String demonstrator_name) {
        this.demonstrator_name = demonstrator_name;
    }

    public String getDemonstrator_dep() {
        return demonstrator_dep;
    }

    public void setDemonstrator_dep(String demonstrator_dep) {
        this.demonstrator_dep = demonstrator_dep;
    }

    

}
