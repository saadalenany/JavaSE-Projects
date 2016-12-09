package operationresearch.Duality.InnerPackage.TheInput;

import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Constrain extends HBox {

    private TextField rightVal;
   
    private ComboBox choice;
    ArrayList<TextField> constrainVarValue;
    //datastores
    ArrayList<ArrayList> constrainstore ;
    ArrayList<ComboBox> combos ;
    ArrayList<TextField> RHSs ;

    public Constrain(int numberOfDecisionVar) {
        
        constrainstore = new ArrayList<>();
        combos = new ArrayList<>();
        RHSs = new ArrayList<>();
        constrainVarValue = new ArrayList<>();
        
        for (int i = 0; i < numberOfDecisionVar; i++) {

            TextField newVar = new TextField();
            newVar.setPromptText("X" + (i + 1));
            newVar.setPrefWidth(80);
            newVar.setPrefHeight(40);
            newVar.setFont(new Font("Tahoma", 20));

            constrainVarValue.add(newVar);
            
            this.getChildren().add(newVar);
            if (i != numberOfDecisionVar - 1) {
                Label plus = new Label("+");
                plus.setStyle("-fx-font-size: 14pt;");
                this.getChildren().add(plus);
            }
        }
        
        constrainstore.add(constrainVarValue);
        
        choice = new ComboBox();
        choice.setPrefWidth(80);
        choice.setPrefHeight(40);
        choice.getItems().addAll("<=", ">=", "=");
        
        combos.add(choice);
        
        rightVal = new TextField();
        rightVal.setPromptText("RHS");
        rightVal.setPrefWidth(80);
        rightVal.setPrefHeight(40);
        rightVal.setFont(new Font("Tahoma", 20));

        RHSs.add(rightVal);
        
        this.setSpacing(5);
        getChildren().addAll(choice, rightVal);

    }

    public TextField getRightVal() {
        return rightVal;
    }

    public void setRightVal(TextField rightVal) {
        this.rightVal = rightVal;
    }

    public ComboBox getChoice() {
        return choice;
    }

    public void setChoice(ComboBox choice) {
        this.choice = choice;
    }

    public ArrayList<TextField> getconstrainVarValue() {
        return constrainVarValue;
    }

    public void setconstrainVarValue(ArrayList<TextField> constrainVarValue) {
        this.constrainVarValue = constrainVarValue;
    }

    public ArrayList<ArrayList> getConstrainstore() {
        return constrainstore;
    }

    public ArrayList<ComboBox> getCombos() {
        return combos;
    }

    public ArrayList<TextField> getRHSs() {
        return RHSs;
    }
    
}
