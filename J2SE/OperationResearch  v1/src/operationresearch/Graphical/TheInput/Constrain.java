/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.Graphical.TheInput;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 *
 * @author Amr
 */
public class Constrain extends HBox {

    private TextField xVal, yVal, rightVal;
    private double XDraw , YDraw;
    private String sign ;
    private ComboBox choice;
    private Circle XCircle , YCircle ;

    public Constrain(double PaneWidth) {
        xVal = new TextField();
        xVal.setPrefWidth(70);
        xVal.setPrefHeight(30);

        Label plus = new Label("+");
        plus.setStyle("-fx-font-size: 14pt;");

        yVal = new TextField();
        yVal.setPrefWidth(70);
        yVal.setPrefHeight(30);
     
        choice = new ComboBox();
        choice.setPrefWidth(80);
        choice.setPrefHeight(30);
        choice.getItems().addAll("<=", ">=");   

        rightVal = new TextField();
        rightVal.setPrefWidth(70);
        rightVal.setPrefHeight(30);  

        this.setSpacing(5);
        this.getChildren().addAll(xVal, plus, yVal, choice, rightVal);

    }

    public TextField getxVal() {
        return xVal;
    }

    public void setxVal(TextField xVal) {
        this.xVal = xVal;
    }

    public TextField getyVal() {
        return yVal;
    }

    public void setyVal(TextField yVal) {
        this.yVal = yVal;
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

    public double getXDraw() {
        return XDraw;
    }

    public void setXDraw(double XPane) {
        this.XDraw = XPane;
    }

    public double getYDraw() {
        return YDraw;
    }

    public void setYDraw(double YPane) {
        this.YDraw = YPane;
    }

 

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Circle getXCircle() {
        return XCircle;
    }

    public void setXCircle(Circle XCircle) {
        this.XCircle = XCircle;
    }

    public Circle getYCircle() {
        return YCircle;
    }

    public void setYCircle(Circle YCircle) {
        this.YCircle = YCircle;
    }
    
    

}
