/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.Graphical.TheInput;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QInput extends BorderPane {

    TextField xField, yField;
    private Stack<Constrain> StackOfConstrain;
    VBox box;
    Stage stage;

    public void QInput(Stage stage) {
        StackOfConstrain = new Stack();
        this.stage = stage;
        box = new VBox();
        box.setPadding(new Insets(0, 10, 0, 0));
        box.setSpacing(20);
        this.setCenter(box);

        xField = new TextField();
        xField.setPromptText("Set x variable");
        yField = new TextField();
        yField.setPromptText("Set y variable");
        yField.setPrefWidth(box.getMaxWidth());

        Constrain init = new Constrain(box.getPrefWidth());
        StackOfConstrain.push(init);

        box.getChildren().addAll(xField, yField, init);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        this.setRight(separator);

        ConstarinNumberControl();
        WidthConstrainControl();

    }

    
    private void ConstarinNumberControl() {

        Timer t2 = new Timer();
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                Constrain last = StackOfConstrain.get(StackOfConstrain.size() - 1);
                last.getxVal().setOnMouseClicked((MouseEvent e) -> {
                    if (last == StackOfConstrain.get(StackOfConstrain.size() - 1)) {
                        Constrain newConst = new Constrain(box.getPrefWidth());
                        StackOfConstrain.push(newConst);
                        box.getChildren().add(newConst);
                    }
                    for (int i = 0; i < StackOfConstrain.size(); i++) {
                        System.out.println(StackOfConstrain.size());
                        if (StackOfConstrain.get(i).getxVal().getText().equals("") && i != StackOfConstrain.size() - 2 && i != StackOfConstrain.size() - 1) {
                            box.getChildren().remove(StackOfConstrain.get(i));
                            StackOfConstrain.remove(StackOfConstrain.get(i));
                        }
                    }
                });
            }
        };

        t2.scheduleAtFixedRate(task2, 100, 100);

    }

    private void WidthConstrainControl() {

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    box.setPrefWidth(stage.getWidth() / 4);
                });
            }

        }, 100, 100);
    }

    public Stack<Constrain> getStackOfConstrain() {
        return StackOfConstrain;
    }

    public void setStackOfConstrain(Stack<Constrain> StackOfConstrain) {
        this.StackOfConstrain = StackOfConstrain;
    }

}
