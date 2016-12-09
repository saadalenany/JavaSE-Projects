package operationresearch.Duality.InnerPackage.TheInput;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

public class DecisionVarInputPrimal extends BorderPane {

    Stack<DecisionVar> StackOfDecisionVar;
    private final FlowPane flowPane;
    private final ComboBox<String> cmbo;
    
    public DecisionVarInputPrimal() {
        StackOfDecisionVar = new Stack();
        flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 20));
        flowPane.setVgap(10);
        flowPane.setOrientation(Orientation.HORIZONTAL);
        //combobox that enable user to choose min or max//////////
        cmbo = new ComboBox();
        cmbo.getItems().addAll("MAX", "MIN");
        cmbo.setPrefHeight(45);
        cmbo.setPadding(new Insets(0, 10, 0, 10));
        cmbo.setValue("MAX");
        //label that carry z = ///
        Label z = new Label("Z = ");
        z.setFont(new Font("Tahoma", 25));
        z.setPadding(new Insets(0, 10, 0, 10));
      
        //adding textfield and combo box  to allow user enter decision variable
        DecisionVar init = new DecisionVar();
        StackOfDecisionVar.push(init);
        flowPane.getChildren().addAll(cmbo, z, init);

        this.setCenter(flowPane);

        DecisionVarControl();

    }

    private void DecisionVarControl() {

        Timer t2 = new Timer();
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                /// get last decicion variable in the stack /////////////
                DecisionVar last = StackOfDecisionVar.get(StackOfDecisionVar.size() - 1);
                last.getVar().setOnMouseClicked((MouseEvent e) -> {
                    if (last == StackOfDecisionVar.get(StackOfDecisionVar.size() - 1)) {
                        DecisionVar newConst = new DecisionVar();
                        StackOfDecisionVar.push(newConst);
                        flowPane.getChildren().add(newConst);
                    }
                    for (int i = 0; i < StackOfDecisionVar.size(); i++) {
                        System.out.println(StackOfDecisionVar.size());
                        if (StackOfDecisionVar.get(i).getVar().getText().equals("") && i != StackOfDecisionVar.size() - 2 && i != StackOfDecisionVar.size() - 1) {
                            flowPane.getChildren().remove(StackOfDecisionVar.get(i));
                            StackOfDecisionVar.remove(StackOfDecisionVar.get(i));
                        }
                    }
                    for (int i = 0; i < StackOfDecisionVar.size(); i++) {
                        StackOfDecisionVar.get(i).getVar().setPromptText("X" + (i + 1));
                    }

                });
            }
        };
        t2.scheduleAtFixedRate(task2, 100, 100);

    }

    public Stack<DecisionVar> getStackOfDecisionVar() {
        return StackOfDecisionVar;
    }

    public void setStackOfDecisionVar(Stack<DecisionVar> StackOfDecisionVar) {
        this.StackOfDecisionVar = StackOfDecisionVar;
    }

    public ComboBox<String> getCmbo() {
        return cmbo;
    }
    
}
