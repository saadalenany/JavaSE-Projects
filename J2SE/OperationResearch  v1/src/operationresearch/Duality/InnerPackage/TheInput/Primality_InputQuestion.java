package operationresearch.Duality.InnerPackage.TheInput;

import insidefx.undecorator.UndecoratorScene;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import operationresearch.Duality.InnerPackage.DecisionVars;
import operationresearch.Duality.InnerPackage.Constraint;
import operationresearch.Duality.InnerPackage.FinalForms.FinalFormDuality;
import operationresearch.MainStage;

public class Primality_InputQuestion {
    
    DecisionVarInputPrimal DecisionVarInput;
    ConstrainInput Constraininput;
    operationresearch.Duality.InnerPackage.TheInput.Constrain Constraint;
    HBox btnsPane;
    StackPane titlePane;
    VBox questionPane;
    StackPane solvePane;
    Button showConstrain;
    Button release; 
    Button solve;

    // data  storing variables//////
    DecisionVars vars;
    Constraint[] con;
    /////////////////////////////////
    
    public UndecoratorScene DrawPrimality(Stage stage) {

        DecisionVarInput = new DecisionVarInputPrimal();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(40, 10, 10, 10));
        questionPane = new VBox();
        questionPane.setSpacing(20);
        btnsPane = new HBox();
        btnsPane.setSpacing(20);
        showConstrain = new Button("submit");
        release = new Button("release");

        btnsPane.setAlignment(Pos.CENTER);
        btnsPane.getChildren().addAll(showConstrain, release);

        Label desionlbl = new Label("Decision variable");
        desionlbl.setFont(new Font("Tahoma", 20));
        desionlbl.setPadding(new Insets(5, 0, 5, 20));

        /////// title pane ///////////////
        titlePane = new StackPane();
        Label title = new Label("Primal to Dual");
        title.setFont(new Font("Tahoma", 30));
        titlePane.setPadding(new Insets(0, 0, 10, 0));
        titlePane.getChildren().add(title);

        Label constrainlbl = new Label("Constrains");
        constrainlbl.setFont(new Font("Tahoma", 20));
        constrainlbl.setPadding(new Insets(20, 0, 20, 20));

        solve = new Button("Solve");

        solvePane = new StackPane();
        solvePane.getChildren().add(solve);
        VBox.setMargin(solve, new Insets(-20, 0, 0, 0));

        questionPane.getChildren().addAll(desionlbl, DecisionVarInput, btnsPane, constrainlbl);

        showConstrain.setOnAction((ActionEvent event) -> {
            /////// store decion variables////////////////////////////
            vars = new DecisionVars();
            vars.setType(DecisionVarInput.getCmbo().getValue());
            System.out.print(vars.getType() + " Z ");
            
            for (int i = 0 ; i < DecisionVarInput.getStackOfDecisionVar().size() - 1 ; i++) {
                vars.addVar(Float.parseFloat(DecisionVarInput.getStackOfDecisionVar().get(i).getVar().getText()));
                System.out.print(vars.getDecisionVars().get(i) + " + ");
            }
            /////////////////end of decision variables///////////////////
            int decisionNumber = DecisionVarInput.getStackOfDecisionVar().size() - 1;
            Constraininput = new ConstrainInput(decisionNumber);
            Constraint = new operationresearch.Duality.InnerPackage.TheInput.Constrain(decisionNumber);
            questionPane.getChildren().addAll(Constraininput, solvePane);
            showConstrain.setDisable(true);
        });

        solve.setOnAction(e-> stage.setScene(new FinalFormDuality().DrawFinalDual(stage)) );

        root.setTop(titlePane);
        root.setCenter(questionPane);
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
        stage.setTitle("Duality ");

        return undecoratorScene;
    }
    
}
