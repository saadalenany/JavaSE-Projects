package operationresearch.simplex.makeSolution;

import insidefx.undecorator.UndecoratorScene;
import java.util.ArrayList;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.SwingUtilities;
import operationresearch.MainStage;
import operationresearch.simplex.Table;

/**
 *
 * @author Amr
 */
public class Zwithout2Phase {

    private Button next;
    private String FirstRow[];
    private String data[][];
    private String type;
    private BorderPane root;
    private HBox indicator ; 
    Label FinalTable ;
    Stage stage ; 

    public UndecoratorScene Zwithout2Phase(String FirstRow[], String data[][], String type, Stage stage) {
        this.FirstRow = FirstRow;
        this.type = type;
        this.data = data;
        this.stage= stage ;
        //----------------------------------
        next = new Button("Next  ->");
        next.setOnAction(e -> nextAction());

        indicator = new HBox();
        indicator.setSpacing(100);
        indicator.getChildren().addAll(next);
        //---------------------------------------------
        root = new BorderPane();

        root.setPadding(new Insets(50, 10, 10, 10));
        SwingNode sn = new SwingNode();
        SwingUtilities.invokeLater(() -> {
            sn.setContent(new Table(FirstRow, data));
        });

        FinalTable  =  new Label ("Optimum solution : "); 
        FinalTable.setFont(new Font("Tahoma", 20));

          indicator.getChildren().add(FinalTable);
          
        root.setCenter(sn);
        root.setBottom(indicator);

        BorderPane.setMargin(sn, new Insets(0, 0, 10, 0));
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

    boolean isFinalTable() {

        if (type.equals("MAX")) {
            for (int i = 1; i < data[0].length - 2; i++) {
                if (Float.parseFloat(data[0][i]) > 0) {
                    return false;
                }
            }
            return true;
        }

        for (int i = 1; i < data[0].length - 2; i++) {
            if (Float.parseFloat(data[0][i]) < 0) {
                return false;
            }
        }
        return true;

    }

    int selectMinOrMaxValue() {

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

    int createRatio(int minColIndex) {
        int minRatio = 1;
        boolean isFoundValue = false;   // not zero

        if (data[minRatio][minColIndex].equals("0") || data[minRatio][minColIndex].equals("0.0")) {  //  if minRatio equal zero 
            for (int i = 1; i < data.length; i++) {
                if (!(data[i][minColIndex].equals("0") || data[i][minColIndex].equals("0.0"))) {
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

    private void nextAction() { 
        
          String Opt = data[0][data[0].length-2];
            FinalTable.setText("Optimum solution : "+Opt) ;   
            
        if (isFinalTable()){ 
          return ;
        }
        ArrayList<Float> MinOrMaxRow = new  ArrayList<>();
        MinOrMaxRow.add(-100f);
        int minOrMaxColIndex = selectMinOrMaxValue();
        int minOrMaxRatioIndex = createRatio(minOrMaxColIndex);

        //---------------------------------------------------
        // Divide the min column and the min row with intersection
        float inter = Float.parseFloat(data[minOrMaxRatioIndex][minOrMaxColIndex]);  // intersection

        // set new Value for min row
        for (int i = 1; i < data[0].length - 1; i++) {
            float newVal = Float.parseFloat(data[minOrMaxRatioIndex][i]) / inter;
            MinOrMaxRow.add(Float.parseFloat(data[minOrMaxRatioIndex][i]));
            data[minOrMaxRatioIndex][i] = newVal + "";
        }

        // set new Value for min colmun 
        for (int i = 0; i < data.length; i++) {
            float newVal = Float.parseFloat(data[i][minOrMaxColIndex]) / inter;
            data[i][minOrMaxColIndex] = newVal + "";
        }

        // set intersection value to be 1 
        data[minOrMaxRatioIndex][minOrMaxColIndex] = (inter / inter) + "";

        //-------------------------------
        // Make Z Opeartion 
        for (int i = 1; i < data[0].length - 1; i++) {
            if (i == minOrMaxColIndex) {
                continue;
            }
            for (int j = 0; j < data.length; j++) {
                if (j == minOrMaxRatioIndex) {
                    continue;
                }

                float ChangingVal = Float.parseFloat(data[j][i]);
                float FVal = Float.parseFloat(data[j][minOrMaxColIndex]);
                float SFal = MinOrMaxRow.get(i);
                
                System.out.println("ChangingVal " + ChangingVal + " " + j + " " + i);
                System.out.println("FVal " + FVal + " " + j + " " + minOrMaxColIndex);
                System.out.println("SFal " + SFal + " " + minOrMaxColIndex + " " + i);

                float NewVal = (1 * ChangingVal) - (FVal * SFal);
                data[j][i] = NewVal + "";
            }
            System.out.println("-----------------------");
        }

        //  set inComing
        data[minOrMaxRatioIndex][0] = FirstRow[minOrMaxColIndex];
        //------------------------------------------------------------------

       // set new value of incoming Variable 
       for (int i = 0; i < data.length; i++) {
           if (i == minOrMaxRatioIndex) {
               data[i][minOrMaxColIndex] = "1";
           } else {
               data[i][minOrMaxColIndex] = "0";
           }
       }
      
       stage.setWidth(stage.getWidth()-.1);
     
    }

}
