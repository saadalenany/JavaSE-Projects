package operationresearch.Duality;

import insidefx.undecorator.UndecoratorScene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import operationresearch.Duality.InnerPackage.TheInput.Duality_InputQuestion;
import operationresearch.Duality.InnerPackage.TheInput.Primality_InputQuestion;
import operationresearch.MainStage;

public class Duality_Stage {
    
    Button Dual , Primal ;
    
    public UndecoratorScene DrawDualStage(Stage stage){
        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(90, 0, 50, 10));
        GridPane child = new GridPane();
        
        Primal = new Button("Convert from Primal to Dual");
        Dual = new Button("Convert from Dual to Primal");
        
        root.setCenter(child);

        child.setPadding(new Insets(25, 25, 25, 25));
        child.setPadding(new Insets(100));
        child.setHgap(10);
        child.setVgap(10);
        child.setPrefSize(300, 300);
        
        Dual.setOnAction(e -> stage.setScene( new Duality_InputQuestion().DrawDuality(stage)));  // open Duality dicision variable scene
        Dual.setPrefSize(500, 100);
        
        Primal.setOnAction(e -> stage.setScene(new Primality_InputQuestion().DrawPrimality(stage)));   // open Primality decision variable scene
        Primal.setPrefSize(500, 100);
        
        child.add(Primal,5, 0);
        child.add(Dual , 5, 5);
        
        //the Undecorator as the Scene
        UndecoratorScene undecoratorScene = new UndecoratorScene(stage, root, true);
        undecoratorScene.getStylesheets().add(MainStage.class.getResource("StyleSheet.css").toExternalForm());
        
        stage.setOnCloseRequest((WindowEvent we) -> {
            we.consume();   // Do not hide yet
            undecoratorScene.setFadeOutTransition();
        });

        stage.toFront();
        stage.setTitle("Duality");

        return undecoratorScene;
    }
}