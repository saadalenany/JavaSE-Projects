package operationresearch.Duality.InnerPackage.TheInput;

import insidefx.undecorator.UndecoratorScene;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import operationresearch.Duality.InnerPackage.Constraint;
import operationresearch.Duality.InnerPackage.DecisionVars;
import operationresearch.Duality.InnerPackage.FinalForms.FinalFormPrimality;
import operationresearch.MainStage;

public class Duality_InputQuestion {

    DecisionVarInputDual DecisionVarInput;
    ConstrainInput Constraininput;
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
    
    public UndecoratorScene DrawDuality(Stage stage) {
        
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

        DecisionVarInput = new DecisionVarInputDual();
        Label desionlbl = new Label("Decision variable");
        desionlbl.setFont(new Font("Tahoma", 20));
        desionlbl.setPadding(new Insets(5, 0, 5, 20));

        /////// title pane ///////////////
        titlePane = new StackPane();
        Label title = new Label("Dual to Primal");
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

        showConstrain.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                /////// store decion variables////////////////////////////
                vars = new DecisionVars();
                vars.setType(DecisionVarInput.getCmbo().getValue());
                System.out.print(vars.getType() + " W ");

                for (int i = 0 ; i < DecisionVarInput.getStackOfDecisionVar().size() - 1 ; i++) {
                    vars.addVar(Float.parseFloat(DecisionVarInput.getStackOfDecisionVar().get(i).getVar().getText()));
                    System.out.print(vars.getDecisionVars().get(i) + " + ");
                }
                /////////////////end of decision variables///////////////////
                int decisionNumber = DecisionVarInput.getStackOfDecisionVar().size() - 1;
                Constraininput = new ConstrainInput(decisionNumber);
                questionPane.getChildren().addAll(Constraininput, solvePane);
                showConstrain.setDisable(true);
            }
        });

//        solve.setOnAction(new EventHandler() {
//
//            @Override
//            public void handle(Event event) {
//                
//                con = new Constraint[Constraininput.getStackOfConstrain().size() - 1];
//                System.out.println("the number of constarins = " + (Constraininput.getStackOfConstrain().size() - 1));
//                for (int i = 0; i < Constraininput.getStackOfConstrain().size() - 1; i++) {
//                    con[i] = new Constraint(DecisionVarInput.getStackOfDecisionVar().size() - 1);
//                    ArrayList<TextField> constarnValues = Constraininput.getStackOfConstrain().get(i).getconstrainVarValue();
//
//                    con[i].setConstarinValues(constarnValues);
//                    con[i].setOperator(Constraininput.getStackOfConstrain().get(i).getChoice().getValue()+"");
//                    con[i].setRightHand(Float.parseFloat(Constraininput.getStackOfConstrain().get(i).getRightVal().getText()));
//                    con[i].showElemnts();
//
//                }
//            }
//        });

        solve.setOnAction( e -> stage.setScene(new FinalFormPrimality().DrawFinalPrimal(stage)) );

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
