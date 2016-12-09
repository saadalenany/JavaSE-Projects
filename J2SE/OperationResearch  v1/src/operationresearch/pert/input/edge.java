/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.pert.input;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Amr
 */
public class edge extends HBox {

    private ArrayList<TextField> predecessor;
    private Timer t2;
    private TextField duration, activity;
    private Button AddRemove;

    public edge(boolean haveTime) {
        predecessor = new ArrayList<>();
        activity = new TextField();
        activity.setPrefSize(80, 25);
        activity.setPromptText("Activity");
        if (haveTime) {
            duration = new TextField();
            duration.setPrefSize(85, 25);
            duration.setPromptText("Duration");
        }
        Label depend = new Label("Depend on ");
        depend.setEffect(new Bloom());
        TextField init = new TextField();
        init.setPrefSize(40, 20);
        predecessor.add(init);
// AddRemove 

        AddRemove = new Button("Add/Remove");
        this.getChildren().add(AddRemove);

        this.setSpacing(5);
        if (haveTime) {
            this.getChildren().addAll(activity, duration, depend, init);
        } else {
            this.getChildren().addAll(activity, depend, init);
        }
        setPadding(new Insets(0, 30, 0, 0));
        predecessorControl();
    }
   private TextField last;
    private void predecessorControl() {

         last = predecessor.get(predecessor.size() - 1);
        AddRemove.setOnMouseClicked((MouseEvent e) -> {
            if (last == predecessor.get(predecessor.size() - 1)) {
                TextField newText = new TextField();
                newText.setPrefSize(50, 20);
                predecessor.add(newText);
                getChildren().add(newText);
            }
            for (int i = 0; i < predecessor.size(); i++) {
                System.out.println(predecessor.size());
                if (predecessor.get(i).getText().equals("") && i != predecessor.size() - 2 && i != predecessor.size() - 1) {
                    getChildren().remove(predecessor.get(i));
                    predecessor.remove(predecessor.get(i));
                }
            }
        });
          last = predecessor.get(predecessor.size() - 1);
    }

    public ArrayList<TextField> getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(ArrayList<TextField> predecessor) {
        this.predecessor = predecessor;
    }

    public TextField getDuration() {
        return duration;
    }

    public void setDuration(TextField duration) {
        this.duration = duration;
    }

    public TextField getActivity() {
        return activity;
    }

    public void setActivity(TextField activity) {
        this.activity = activity;
    }

    public Timer getT2() {
        return t2;
    }

    public void setT2(Timer t2) {
        this.t2 = t2;
    }

}
