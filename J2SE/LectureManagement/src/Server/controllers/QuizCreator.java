package Server.controllers;

import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

public class QuizCreator extends BorderPane {

    VBox content;
    Button add, remove;
    RadioButton[] choices;
    Button create;
    ScrollPane scroller;
    int questionnumber;
    ArrayList<TextField> numberofchoicesarray, questiondataarray;

    public QuizCreator() {
        setPadding(new Insets(10, 20, 20, 10));

        questionnumber = 0;
        create = new Button("Create & Send");
        content = new VBox();

        add = new Button("+");
        remove = new Button("-");

        add.setStyle("-fx-font-size: 17px ;-fx-font-weight:bold;");
        remove.setStyle("-fx-font-size: 17px bold;-fx-font-weight:bold;");

        add.setPrefSize(150, 30);
        remove.setPrefSize(150, 30);

        numberofchoicesarray = new ArrayList<>();
        questiondataarray = new ArrayList<>();

        HBox buttonsbox = new HBox();
        buttonsbox.setPadding(new Insets(10, 30, 5, 30));
        buttonsbox.setSpacing(50);
        buttonsbox.setAlignment(Pos.CENTER);
        buttonsbox.getChildren().addAll(add, remove);

        setAlignment(remove, Pos.CENTER);
        setTop(buttonsbox);

        scroller = new ScrollPane();
        scroller.setPadding(new Insets(10, 10, 10, 10));
        setAlignment(scroller, Pos.CENTER);

        create.setPrefSize(150, 30);
        create.setAlignment(Pos.CENTER);
        setAlignment(create, Pos.BOTTOM_LEFT);
        setBottom(create);

        content.setPadding(new Insets(20, 20, 20, 20));
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER);

        add.setOnAction(e -> {
            questionnumber++;
            TextField numberofchoices = new TextField();
            numberofchoices.setPromptText("choices!");

            TextField question_data = new TextField();
            question_data.setText(questionnumber + ": " + question_data.getText());
            question_data.setPromptText("enter question!");

            numberofchoices.setPrefSize(70, 30);
            question_data.setPrefSize(150, 30);

            numberofchoicesarray.add(numberofchoices);
            questiondataarray.add(question_data);

            HBox hb = new HBox();
            hb.setSpacing(30);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(questiondataarray.get(questionnumber - 1), numberofchoicesarray.get(questionnumber - 1));

            content.getChildren().addAll(hb);

            new Validations().ValidateNumber(numberofchoicesarray.get(questionnumber-1));

            numberofchoicesarray.get(questionnumber - 1).setOnAction(a -> {
                int x = Integer.parseInt(numberofchoicesarray.get(questionnumber - 1).getText());
                choices = new RadioButton[x];

                VBox vb = new VBox();
                numberofchoicesarray.get(questionnumber - 1).setVisible(false);
                content.getChildren().remove(hb);

                ToggleGroup group = new ToggleGroup();

                VBox choicesButtons = new VBox();
                choicesButtons.setAlignment(Pos.CENTER_LEFT);

                vb.setPadding(new Insets(10, 0, 0, 10));
                vb.setSpacing(10);
                vb.setAlignment(Pos.CENTER);
                vb.getChildren().add(questiondataarray.get(questionnumber - 1));

                for (int i = 0; i <= x; i++) {
                    if (i == x) {
                        String str = JOptionPane.showInputDialog("Enter the right answer");
                        System.out.println("right answer! " + str);
                        break;
                    }
                    String str = JOptionPane.showInputDialog("Enter answer" + (i + 1) + " option");
                    choices[i] = new RadioButton(str);
                    choices[i].setToggleGroup(group);
                    choicesButtons.getChildren().add(choices[i]);
                    choicesButtons.setSpacing(5);
                }
                vb.getChildren().add(choicesButtons);
                content.getChildren().add(vb);
            });
        });

        remove.setOnAction(e -> {
            if (questionnumber > 0) {
                content.getChildren().remove(questionnumber - 1);
                questiondataarray.remove(questionnumber - 1);
                numberofchoicesarray.remove(questionnumber - 1);
            }
            questionnumber--;
            if (questionnumber < 0) {
                content.getChildren().clear();
                questiondataarray.clear();
                numberofchoicesarray.clear();
                questionnumber = 0;
            }
        });

        StackPane stackp = new StackPane();
        stackp.getChildren().add(content);

        scroller.setStyle("-fx-opacity:0.7");

        stackp.minWidthProperty().bind(Bindings.createDoubleBinding(()
        -> scroller.getViewportBounds().getWidth(), scroller.viewportBoundsProperty()));

        scroller.setContent(stackp);
        setCenter(scroller);
    }
}
