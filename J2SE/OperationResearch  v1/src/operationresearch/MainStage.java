package operationresearch;

import insidefx.undecorator.UndecoratorScene;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import operationresearch.AHP.AHP;
import operationresearch.Duality.Duality_Stage;
import operationresearch.Graphical.Graphical;
import operationresearch.pert.pert;
import operationresearch.simplex.Simplex_InputQuestion;

public class MainStage {

    private final int STAGE_HEIGHT = 700;
    private final int STAGE_WIDTH = 900;

    public void showMainStage() throws IOException {
        BuildStage();
    }

    public void BuildStage() {

        Stage stage = new Stage();
        stage.setHeight(STAGE_HEIGHT);
        stage.setWidth(STAGE_WIDTH);

        GridPane child = new GridPane();
        BorderPane root = new BorderPane(child);       // Main Pane

        Menu file = new Menu(" ");
     

        MenuBar bar = new MenuBar(file);

        //select Posision 
        root.setTop(bar);
        root.setCenter(child);
        root.setPadding(new Insets(25, 0, 0, 0));
        child.setPadding(new Insets(25, 25, 25, 25));
        child.setPadding(new Insets(100));
        child.setHgap(10);
        child.setVgap(10);
        child.setPrefSize(300, 300);
        Button graphical = new Button("graphical");
        graphical.setOnAction(e -> stage.setScene(new Graphical().DrawGraphical(stage)));  // open Gpraphical scene
        graphical.setPrefSize(100, 100);

        Button Simplex = new Button("Simplex");
        Simplex_InputQuestion DrawDecisionVar = new Simplex_InputQuestion();
        Simplex.setOnAction(e -> stage.setScene(DrawDecisionVar.DrawSimplex(stage)));  // open Simplex dicision variable scene
        Simplex.setPrefSize(100, 100);  // set Size 

        Button pertBut = new Button("Pert");
        pert Pert = new pert();
        pertBut.setOnAction(e -> stage.setScene(Pert.DrawPert(stage)));  // open Simplex dicision variable scene
        pertBut.setPrefSize(100, 100);  // set Size 

        Button Duality = new Button("Duality");
        Duality.setOnAction(e -> stage.setScene(new Duality_Stage().DrawDualStage(stage))); // open Duality Scene
        Duality.setPrefSize(100, 100);  // set Size

        
         Button AHP = new Button("AHP");
        AHP.setOnAction(e -> stage.setScene(new AHP().DrawAHP(stage))); // open Duality Scene
        AHP.setPrefSize(100, 100);  // set Size
        
        
        child.add(AHP, 20, 5);
       child.add(graphical, 0, 0);     // Add button to BorderPane (0,0)
        child.add(Simplex, 40, 0);
        child.add(pertBut, 0, 10);
        child.add(Duality, 40, 10);
        

        BorderPane.setMargin(bar, new Insets(20, 0, 0, 30));  // move my bar to down and right

        // The Undecorator as a Scene
        UndecoratorScene undecoratorScene = new UndecoratorScene(stage, root, false);
        //  set Style 
        undecoratorScene.getStylesheets().add(MainStage.class.getResource("StyleSheet.css").toExternalForm());

        undecoratorScene.setFadeInTransition();
        /*
         * Fade out transition on window closing request
         */
        stage.setOnCloseRequest((WindowEvent we) -> {
            we.consume();   // Do not hide yet
            undecoratorScene.setFadeOutTransition();
        });

        stage.setScene(undecoratorScene);
        stage.toFront();
        stage.show();
        stage.setTitle("Main");
    }

}
