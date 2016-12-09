/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.simplex.TheInput;

import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 *
 * @author Amr
 */
/**
 *
 * @author Amr
 */
public class Constrain extends HBox {

    private TextField rightVal;
    private ComboBox choice;
    private ArrayList<TextField> constrainVarText;
     private ArrayList<Float> dataConstrain ; 
    private int slack, surplus, artificial;

    public Constrain(int numberOfDecisionVar) {

        constrainVarText = new ArrayList<>();
        for (int i = 0; i < numberOfDecisionVar; i++) {
            TextField newVar = new TextField();
            newVar.setPromptText("X" + (i + 1));
            newVar.setPrefWidth(80);
            newVar.setPrefHeight(40);
            newVar.setFont(new Font("TAhoma", 20));
            constrainVarText.add(newVar);

            this.getChildren().add(newVar);
            if (i != numberOfDecisionVar - 1) {
                Label plus = new Label("+");
                plus.setStyle("-fx-font-size: 14pt;");
                this.getChildren().add(plus);
            }
        }

        choice = new ComboBox();
        choice.setPrefWidth(80);
        choice.setPrefHeight(40);
        choice.getItems().addAll("<=",">=","=");
        rightVal = new TextField();
        rightVal.setPrefWidth(80);
        rightVal.setPrefHeight(40);
        rightVal.setFont(new Font("TAhoma", 20));

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
    public int getSlack() {
        return slack;
    }

    public void setSlack(int slack) {
        this.slack = slack;
    }

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public int getArtifial() {
        return artificial;
    }

    public void setArtifial(int artifial) {
        this.artificial = artifial;
    }
    

    public ArrayList<Float> getDataConstrain() {
        return dataConstrain;
    }

    public void setDataConstrain(ArrayList<Float> dataConstrain) {
        this.dataConstrain = dataConstrain;
    }

    public ArrayList<TextField> getConstrainVarText() {
        return constrainVarText;
    }

    public void setConstrainVarText(ArrayList<TextField> constrainVarText) {
        this.constrainVarText = constrainVarText;
    }
    
}
