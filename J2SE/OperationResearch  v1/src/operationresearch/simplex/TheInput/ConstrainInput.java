package operationresearch.simplex.TheInput;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Amr
 */
public class ConstrainInput extends BorderPane {

    private ArrayList<Constrain> StackOfConstrain;
    
    private FlowPane flowPane;
    private int numberOfDisVar;
    Constrain last;

    public ConstrainInput(int numberOfDisVar) {
        this.numberOfDisVar = numberOfDisVar;
        StackOfConstrain = new ArrayList();
        flowPane = new FlowPane();
        flowPane.setPadding(new Insets(0, 10, 0, 20));
        flowPane.setVgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);
        Button add = new Button("add / Delete ");
        BorderPane.setMargin(add, new Insets(0, 0, 20, 20));
        Constrain init = new Constrain(numberOfDisVar);
        StackOfConstrain.add(init);
        last = StackOfConstrain.get(StackOfConstrain.size() - 1);
        add.setOnAction(e -> {
            ConstrianControl();
        });

        this.setTop(add);
        flowPane.getChildren().addAll(init);
        this.setCenter(flowPane);

    }

    private void ConstrianControl() {

        if (last == StackOfConstrain.get(StackOfConstrain.size() - 1)) {
            Constrain newConst = new Constrain(numberOfDisVar);
            StackOfConstrain.add(newConst);
            flowPane.getChildren().add(newConst);
        }
        for (int i = 0; i < StackOfConstrain.size(); i++) {
            if (StackOfConstrain.get(i).getRightVal().getText().equals("") &&  i != StackOfConstrain.size() - 1 ) {
                flowPane.getChildren().remove(StackOfConstrain.get(i));
                StackOfConstrain.remove(StackOfConstrain.get(i));
            }
        }
        last = StackOfConstrain.get(StackOfConstrain.size() - 1);

    }

    public ArrayList<Constrain> getStackOfConstrain() {
        return StackOfConstrain;
    }

    public void setStackOfConstrain(ArrayList<Constrain> StackOfConstrain) {
        this.StackOfConstrain = StackOfConstrain;
    }

    public int getNumberOfDisVar() {
        return numberOfDisVar;
    }

    public void setNumberOfDisVar(int numberOfDisVar) {
        this.numberOfDisVar = numberOfDisVar;
    }

}
