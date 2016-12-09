package operationresearch.Duality.InnerPackage.FinalForms;

import insidefx.undecorator.UndecoratorScene;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import operationresearch.Duality.InnerPackage.TheInput.Constrain;
import operationresearch.Duality.InnerPackage.TheInput.ConstrainInput;
import operationresearch.Duality.InnerPackage.TheInput.DecisionVarInputPrimal;
import operationresearch.MainStage;

/**
 *
 * @author Saad
 */
public class FinalFormPrimality{
    
    ConstrainInput CI ;
    Constrain c ;
    StackPane titlePane ;
    VBox qPane ;
    DecisionVarInputPrimal DVI ;
    Label constrainlbl ;
    Label desionlbl ;
    Label MaxMin ;
    ArrayList<Label> operators ;
    ArrayList<Label> RHS ; 
    ArrayList<Label> ObjectiveF ;
    ArrayList<ArrayList> constran ;
    ArrayList<Label> conperline ;
            
    public UndecoratorScene DrawFinalPrimal(Stage stage) {
        
        MaxMin = new Label("MAX");
        operators = new ArrayList<>();
        RHS = new ArrayList<>();
        ObjectiveF = new ArrayList<>();
        constran = new ArrayList<>();
        conperline = new ArrayList<>();
        
        DVI = new DecisionVarInputPrimal();
        CI = new ConstrainInput(DVI.getStackOfDecisionVar().size() -1);
        c = new Constrain(DVI.getStackOfDecisionVar().size() -1);
                
        System.out.println("StackOfVariables size = "+DVI.getStackOfDecisionVar().size());
                
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(40, 10, 10, 10));
        desionlbl = new Label("Objective Function");
        desionlbl.setFont(new Font("Tahoma", 20));
        desionlbl.setPadding(new Insets(5, 0, 5, 20));
        
        /////// title pane ///////////////
        titlePane = new StackPane();
        Label title = new Label("Dual Form");
        title.setFont(new Font("Tahoma", 30));
        titlePane.setPadding(new Insets(0, 0, 10, 0));
        titlePane.getChildren().add(title);
        
        constrainlbl = new Label("Constrains");
        constrainlbl.setFont(new Font("Tahoma", 20));
        constrainlbl.setPadding(new Insets(20, 0, 20, 20));
        
            if(DVI.getCmbo().getValue().equals("MAX")){
                for (int i = DVI.getStackOfDecisionVar().size()-1 ; i >= 0  ; i--) {
                    RHS.add(new Label (DVI.getStackOfDecisionVar().get(i).getVar().getText()));
                }
                MaxMin = new Label("MIN");
                System.out.println(" ==> MIN");
            }else{
                for (int i = DVI.getStackOfDecisionVar().size()-1 ; i >= 0  ; i--) {
                    RHS.add(new Label (DVI.getStackOfDecisionVar().get(i).getVar().getText()));
                }
                MaxMin = new Label("MAX");
                System.out.println(" ==> MAX");
            }

            if(MaxMin.getText().equals("MIN")){
                for(int i = CI.getStackOfConstrain().size()-1 ; i >= 0  ; i-- ){
                    if(CI.getStackOfConstrain().get(i).getChoice().getValue().equals(">=")){
                        for(int j=0 ; j < c.getconstrainVarValue().size() ; j++){
                            conperline.add(new Label((double)(c.getConstrainstore().get(j).get(i))*-1+""));
                        }
                        ObjectiveF.add(new Label((Double.parseDouble(c.getRHSs().get(i).getText()))*-1+""));
                        operators.add(new Label(">="));
                        constran.add(conperline);
                    }else if(CI.getStackOfConstrain().get(i).getChoice().getValue().equals("<=")){
                        for(int j=0 ; j < c.getconstrainVarValue().size() ; j++){
                            conperline.add(new Label((double)(c.getConstrainstore().get(j).get(i))+""));
                        }
                        ObjectiveF.add(new Label((Double.parseDouble(c.getRHSs().get(i).getText()))+""));
                        operators.add(new Label(">="));
                        constran.add(conperline);
                    }else {
                        for(int j=0 ; j < c.getconstrainVarValue().size() ; j++){
                            conperline.add(new Label((double)(c.getConstrainstore().get(j).get(i))+""));
                            conperline.add(new Label((double)(c.getConstrainstore().get(j).get(i))*-1+""));
                        }
                        ObjectiveF.add(new Label((Double.parseDouble(c.getRHSs().get(i).getText()))+""));
                        ObjectiveF.add(new Label((Double.parseDouble(c.getRHSs().get(i).getText()))*-1+""));
                        operators.add(new Label(">="));
                        constran.add(conperline);
                    }
                }
            }else{
                for(int i = CI.getStackOfConstrain().size()-1 ; i >= 0  ; i-- ){
                    if(CI.getStackOfConstrain().get(i).getChoice().getValue().equals("<=")){
                        for(int j=0 ; j < c.getconstrainVarValue().size() ; j++){
                            conperline.add(new Label((double)(c.getConstrainstore().get(j).get(i))*-1+""));
                        }
                        ObjectiveF.add(new Label((Double.parseDouble(c.getRHSs().get(i).getText()))*-1+""));
                        operators.add(new Label("<="));
                        constran.add(conperline);
                    }else if(CI.getStackOfConstrain().get(i).getChoice().getValue().equals(">=")){
                        for(int j=0 ; j < c.getconstrainVarValue().size() ; j++){
                            conperline.add(new Label((double)(c.getConstrainstore().get(j).get(i))+""));
                        }
                        ObjectiveF.add(new Label((Double.parseDouble(c.getRHSs().get(i).getText()))+""));
                        operators.add(new Label("<="));
                        constran.add(conperline);
                    }else {
                        for(int j=0 ; j < c.getconstrainVarValue().size() ; j++){
                            conperline.add(new Label((double)(c.getConstrainstore().get(j).get(i))+""));
                            conperline.add(new Label((double)(c.getConstrainstore().get(j).get(i))*-1+""));
                        }
                        ObjectiveF.add(new Label((Double.parseDouble(c.getRHSs().get(i).getText()))+""));
                        ObjectiveF.add(new Label((Double.parseDouble(c.getRHSs().get(i).getText()))*-1+""));
                        operators.add(new Label("<="));
                        constran.add(conperline);
                    }
                }
            }
        
        qPane.getChildren().add(desionlbl);

        HBox hb1 = new HBox() ;
        for(int i=0 ; i<ObjectiveF.size() ; i++){
            if(i==ObjectiveF.size()-1){
                hb1.getChildren().addAll(ObjectiveF.get(i),new Label("X"+i));
            }else{
                hb1.getChildren().addAll(ObjectiveF.get(i),new Label("X"+i) , new Label("+"));
            }
        }
        HBox contain = new HBox();
        contain.getChildren().addAll(MaxMin , new Label("Z") , hb1);
        qPane.getChildren().add(contain);
        qPane.getChildren().add(constrainlbl);
        
        ArrayList<HBox> hbcon = new ArrayList() ;
        for(int i=0 ; i<constran.size() ; i++){
            for(int j=0 ; j<conperline.size() ; j++){
                if(i==constran.size()-1){
                    HBox hh = new HBox();
                    hh.getChildren().addAll(new Label(constran.get(i).get(j)+"") , new Label("X"+j));
                    hbcon.add(hh);
                }else{
                    HBox hh = new HBox();
                    hh.getChildren().addAll(new Label(constran.get(i).get(j)+"") , new Label("X"+j) , new Label("+"));
                    hbcon.add(hh);
                }
            }
        }

        ArrayList<HBox> hbcombo = new ArrayList();
        for(int i=0 ; i<operators.size() ; i++){
            if(i==ObjectiveF.size()-1){
                HBox hh = new HBox();
                hh.getChildren().addAll(operators.get(i),new Label("X"+i));
                hbcombo.add(hh);
            }else{
                HBox hh = new HBox();
                hh.getChildren().addAll(operators.get(i),new Label("X"+i) , new Label("+"));
                hbcombo.add(hh);
            }
        }
        
        ArrayList<HBox> hbRHS = new ArrayList();
        for(int i=0 ; i<RHS.size() ; i++){
            if(i==RHS.size()-1){
                HBox hh = new HBox();
                hh.getChildren().addAll(RHS.get(i),new Label("X"+i));
                hbRHS.add(hh);
            }else{
                HBox hh = new HBox();
                hh.getChildren().addAll(RHS.get(i),new Label("X"+i) , new Label("+"));
                hbRHS.add(hh);
            }
        }
        
        for(int i=0 ; i<hbcon.size() ; i++){
            qPane.getChildren().addAll(hbcon.get(i) , hbcombo.get(i) , hbRHS.get(i));
        }
        
        root.setTop(titlePane);
        root.setCenter(qPane);
        
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
        stage.setTitle("Primal Form");

        return undecoratorScene;
    }
        
}
