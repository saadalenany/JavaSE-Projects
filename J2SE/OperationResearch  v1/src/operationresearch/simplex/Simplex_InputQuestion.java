package operationresearch.simplex;

import insidefx.undecorator.UndecoratorScene;
import java.util.ArrayList;
import javafx.event.ActionEvent;
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
import operationresearch.MainStage;
import operationresearch.simplex.TheInput.Constrain;
import operationresearch.simplex.TheInput.ConstrainInput;
import operationresearch.simplex.TheInput.DecisionVarInput;
import operationresearch.simplex.makeSolution.Zwithout2Phase;

public class Simplex_InputQuestion {

    private DecisionVarInput DecisionVarInput;
    private ConstrainInput Constraininput;
    private HBox btnsPane;
    private StackPane titlePane;
    private VBox questionPane;
    private StackPane solvePane;
    private Button hold, solve;
    private Stage stage;
    private int decisionNumber;
    // data  storing variables//////
    private DecisionVars vars;
    private ArrayList<Constrain> Constrain;
    /////////////////////////////////

    public UndecoratorScene DrawSimplex(Stage stage) {
        this.stage = stage;
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(40, 10, 10, 10));
        questionPane = new VBox();
        questionPane.setSpacing(20);
        btnsPane = new HBox();
        btnsPane.setSpacing(20);
        hold = new Button("Hold");
        btnsPane.setAlignment(Pos.CENTER);
        btnsPane.getChildren().addAll(hold);

        DecisionVarInput = new DecisionVarInput();
        Label desionlbl = new Label("Decision variable");
        desionlbl.setFont(new Font("Tahoma", 20));
        desionlbl.setPadding(new Insets(5, 0, 5, 20));

        /////// title pane ///////////////
        titlePane = new StackPane();
        Label title = new Label("SIMPLEX");
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

        hold.setOnAction((ActionEvent event) -> {

            vars = new DecisionVars();
            vars.setType(DecisionVarInput.getCmbo().getValue());

            for (int i = 0; i < DecisionVarInput.getStackOfDecisionVar().size() - 1; i++) {
                vars.addVar(Float.parseFloat(DecisionVarInput.getStackOfDecisionVar().get(i).getVar().getText()));
            }

            /////////////////end of decision variables///////////////////
            decisionNumber = DecisionVarInput.getStackOfDecisionVar().size() - 1;
            Constraininput = new ConstrainInput(decisionNumber);
            questionPane.getChildren().addAll(Constraininput, solvePane);

            hold.setDisable(true);
        });
        solve.setOnAction((ActionEvent e) -> SolveAction());

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
        stage.setTitle("Simplex ");

        return undecoratorScene;
    }

    private void SolveAction() {

        Constrain = new ArrayList<>();
        Constrain = Constraininput.getStackOfConstrain();  // get data Constrain 

        // Convert Decision Variable to constrain  
        Constrain dec = new Constrain(decisionNumber);

        for (int i = 0; i < vars.getDecisionVars().size(); i++) {
            TextField newText = new TextField(vars.getDecisionVars().get(i) + "");
            dec.getConstrainVarText().add(newText);
        }
        //------------------------------------------

        // Construct first Row 
        int NofSlack = 0;
        int additional = 4;   // for RHS , ratio , Z , and first cell

        // if  variable have Slack  sign it by 1
        for (int i = 0; i < Constrain.size(); i++) {
            if (Constrain.get(i).getChoice().getValue().equals("<=")) {
                Constrain.get(i).setSlack(1);
                NofSlack++;
            }
        }
        //-------------------------------------------
        //construct first Row  
        String firstRow[] = new String[NofSlack + additional + decisionNumber];   // create first Row 
        firstRow[0] = "Basic Vars";
        firstRow[1] = "Z";
        for (int i = 2; i <= (decisionNumber + 1); i++) {
            firstRow[i] = ("X" + (i - 1));
        }
        for (int i = (decisionNumber + 1) + 1; i <= NofSlack + (decisionNumber + 1); i++) {
            firstRow[i] = ("S" + (i - (decisionNumber + 1)));
        }
        firstRow[firstRow.length - 1] = "ratio";
        firstRow[firstRow.length - 2] = "RHS";

        //*------------------------------------------------------------
        //construct All Data  without (second row ) and (first and second column )
        String Data[][] = new String[1 + NofSlack][NofSlack + additional + decisionNumber];

        for (int i = 0; i < Constrain.size(); i++) {
            int j;
            for (j = 0; j < Constrain.get(0).getConstrainVarText().size(); j++) {
                Data[i + 1][j + 2] = Constrain.get(i).getConstrainVarText().get(j).getText(); // set data from TextField (constrain )
            }
            int t = j;
            int countSlack = 0;           // for slack become 1 0            
            //                                                0 1
            for (; j < NofSlack + t; j++) {
                Data[i + 1][j + 2] = "0";
                if (Constrain.get(j - t).getSlack() == 1 && countSlack == 0) {
                    Data[i + 1][j + 2] = "1";
                    Constrain.get(j - t).setSlack(0);
                    countSlack++;
                }
            }
            Data[i + 1][j + 2] = Constrain.get(i).getRightVal().getText();       // Set RHS 
            Data[i + 1][j + 3] = "0";                 // set ratio
        }

        //------------------------------------------------------------------
        // construct First Column  
        Data[0][0] = "Z";
        for (int j = 1; j <= NofSlack; j++) {
            Data[j][0] = "S" + j;
        }
        // construct Second  Column
        Data[0][1] = "1";
        for (int j = 1; j <= NofSlack; j++) {
            Data[j][1] = "0";
        }
        // construct second row   ( Z row Decision variable ) 
        int k = 2;
        for (; k < vars.getDecisionVars().size() + 2; k++) {
            Data[0][k] = (vars.getDecisionVars().get(k - 2) * -1) + "";
        }
        int t = k;
        for (int i = k; i < t + NofSlack; i++) {
            Data[0][i] = "0";
        }
        Data[0][Data[0].length - 2] = "0";  //    Set RHS 
        Data[0][Data[0].length - 1] = "0";   //  set ratio
        //------------------------------------------------------------
        System.out.println(Data.length + " " + Data[0].length);
        for (int i = 0; i < Data.length; i++) {
            for (int j = 0; j < Data[i].length; j++) {
                System.out.print(Data[i][j] + " ");
            }
            System.out.println();
        }
        //-------------------------------------------------------------
      
        Zwithout2Phase init = new Zwithout2Phase();
        //--------------------------------------------------------------
        String type = vars.getType();                   // max Or min 
       
     //   int maxOrMin = selectMinOrMaxValue(Data, type);
      //  createRatio(maxOrMin, Data);
        
        stage.setScene(init.Zwithout2Phase(firstRow, Data, type, stage));
    }

    int createRatio(int minColIndex, String data[][]) {
        int minRatio = 1;
        boolean isFoundValue = false;   // not zero

        if (data[minRatio][minColIndex].equals("0")) {  //  if minRatio equal zero 
            for (int i = 1; i < data.length; i++) {
                if (!data[i][minColIndex].equals("0")) {
                    isFoundValue = true;
                    minRatio = i;
                    break;
                }
            }
            if (isFoundValue == false) {
                return -1;
            }

        }

        for (int i = 1; i < data.length; i++) {
            float RHS = Float.parseFloat(data[i][data[0].length - 2]);
            float MinCol = Float.parseFloat(data[i][minColIndex]);
            if (MinCol != 0.0f) {
                data[i][data[0].length - 1] = (RHS / MinCol) + "";
            } else {
                data[i][data[0].length - 1] = "E";

                continue;
            }
            float minVal = Float.parseFloat(data[minRatio][data[0].length - 1]);
            if ((RHS / MinCol) < minVal) {
                minRatio = i;
            }
        }

        return minRatio;
    }

    int selectMinOrMaxValue(String data[][], String type) {

        if (type.equals("MAX")) {
            int min = 1;
            for (int i = 1; i < data[0].length - 2; i++) {
                float tVal = Float.parseFloat(data[0][i]);
                float TminVal = Float.parseFloat(data[0][min]);
                if (TminVal > tVal) {
                    TminVal = tVal;
                    min = i;
                }
            }
            return min;

        }
        int max = 1;

        for (int i = 1; i < data[0].length - 2; i++) {
            float tVal = Float.parseFloat(data[0][i]);
            float TmaxVal = Float.parseFloat(data[0][max]);
            if (TmaxVal < tVal) {
                TmaxVal = tVal;
                max = i;
            }
        }
        return max;
    }

}
