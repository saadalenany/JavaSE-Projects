package operationresearch.Duality.InnerPackage.TheInput;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class ConstrainInput extends BorderPane {

    Stack<Constrain> StackOfConstrain;
    private FlowPane flowPane;
    private int numberOfDisVar;

    public ConstrainInput(int numberOfDisVar) {
        this.numberOfDisVar = numberOfDisVar;
        StackOfConstrain = new Stack();
        flowPane = new FlowPane();
        flowPane.setPadding(new Insets(0, 10, 0, 20));
        flowPane.setVgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);
        
        Constrain init = new Constrain(numberOfDisVar);
        
        StackOfConstrain.push(init);

        flowPane.getChildren().addAll(init);
        this.setCenter(flowPane);

        DecisionVarControl();
    }

    private void DecisionVarControl() {

        Timer t2 = new Timer();
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                Constrain last = StackOfConstrain.get(StackOfConstrain.size() - 1);
                last.getRightVal().setOnMouseClicked((MouseEvent e) -> {
                    if (last == StackOfConstrain.get(StackOfConstrain.size() - 1)) {
                        Constrain newConst = new Constrain(numberOfDisVar);
                        StackOfConstrain.push(newConst);
                        flowPane.getChildren().add(newConst);
                    }
                    for (int i = 0; i < StackOfConstrain.size(); i++) {
                        System.out.println(StackOfConstrain.size());
                        if (StackOfConstrain.get(i).getRightVal().getText().equals("") && i != StackOfConstrain.size() - 2 && i != StackOfConstrain.size() - 1) {
                            flowPane.getChildren().remove(StackOfConstrain.get(i));
                            StackOfConstrain.remove(StackOfConstrain.get(i));
                        } }
                });
            }
        };
       t2.scheduleAtFixedRate(task2, 100, 100);
    }

    public Stack<Constrain> getStackOfConstrain() {
        return StackOfConstrain;
    }

    public void setStackOfConstrain(Stack<Constrain> StackOfConstrain) {
        this.StackOfConstrain = StackOfConstrain;
    }

    public int getNumberOfDisVar() {
        return numberOfDisVar;
    }

    public void setNumberOfDisVar(int numberOfDisVar) {
        this.numberOfDisVar = numberOfDisVar;
    }

}
