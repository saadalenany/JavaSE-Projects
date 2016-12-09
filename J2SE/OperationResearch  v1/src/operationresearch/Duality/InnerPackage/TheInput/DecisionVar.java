package operationresearch.Duality.InnerPackage.TheInput;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class DecisionVar extends HBox {

    private TextField var;
    Label plus;

    public DecisionVar() {

        var = new TextField();
        var.setPrefWidth(80);
        var.setMinWidth(50);
        var.setPrefHeight(45);
        var.setFont(new Font("Arial", 25));
        plus = new Label("+");
        plus.setStyle("-fx-font-size: 35px;");
        this.setSpacing(5);

        this.getChildren().addAll(var, plus);

    }

    public TextField getVar() {
        return var;
    }

    public void setVar(TextField var) {
        this.var = var;
    }

}
