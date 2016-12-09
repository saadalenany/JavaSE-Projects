/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.pert;

import insidefx.undecorator.UndecoratorScene;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import operationresearch.MainStage;
import operationresearch.pert.input.input;

/**
 *
 * @author Amr
 */
public class pert {

    CheckBox haveTime;
    BorderPane root;
    input inputWithTime;

    public UndecoratorScene DrawPert(Stage stage) {
        root = new BorderPane();
        root.setPadding(new Insets(90, 0, 50, 50));

        haveTime = new CheckBox(" Add Duration ");
        haveTime.setFont(Font.font(null, FontWeight.BOLD, 18));
        root.setTop(haveTime);
        BorderPane.setMargin(haveTime, new Insets(0, 0, 50, 0));

        inputWithTime = new input(false);
        root.setLeft(inputWithTime);
        Button Solve = new Button("Solve");
        Solve.setPrefSize(120, 30);
        BorderPane.setMargin(Solve, new Insets(0, 0, 10, 20));

        Solve.setOnAction(e -> {
         
            
            System.gc();
            System.runFinalization();
         //   root.getChildren().remove(inputWithTime);
            

        });
        root.setBottom(Solve);

        // The Undecorator as a Scene
        UndecoratorScene undecoratorScene = new UndecoratorScene(stage, root, true);
        undecoratorScene.getStylesheets().add(MainStage.class.getResource("StyleSheet.css").toExternalForm());
        /*
         * Fade out transition on window closing request
         */
        stage.setOnCloseRequest((WindowEvent we) -> {
            we.consume();   // Do not hide yet
            undecoratorScene.setFadeOutTransition();
        });
        stage.toFront();
        stage.setTitle("Pert");

        checkDuration();
        return undecoratorScene;
    }

    public void checkDuration() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    haveTime.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                        root.getChildren().remove(inputWithTime);
                        if (haveTime.isSelected()) {
                            inputWithTime = new input(true);
                            root.setLeft(inputWithTime);
                        } else {
                            inputWithTime = new input(false);
                            root.setLeft(inputWithTime);
                        }
                        inputWithTime = null;
                    });
                });
            }
        }, 100, 100);

    }

}
