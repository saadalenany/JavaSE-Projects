package operationresearch.Graphical;

import operationresearch.MainStage;
import operationresearch.Graphical.Drawing.Axes;
import operationresearch.Graphical.TheInput.QInput;
import insidefx.undecorator.UndecoratorScene;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import operationresearch.Graphical.TheInput.Constrain;

public class Graphical {

    private Stack<Constrain> StackOfConstrain;
    private QInput input;
    private Stage stage;
    private double MaxXDraw;
    private double MaxYDraw;
    private double LastWidthStage;
    private double LastHeightStage;
    private Axes axes;

    public UndecoratorScene DrawGraphical(Stage stage) {
        this.stage = stage;
        StackOfConstrain = new Stack();
        LastWidthStage = stage.getWidth();
        LastHeightStage = stage.getHeight();
        BorderPane root = new BorderPane();
        input = new QInput();
        input.QInput(stage);

        Button Solve = new Button("Solve");
        Solve.setPrefWidth(150);

        root.setPadding(new Insets(90, 0, 50, 10));

        input.setBottom(new StackPane(Solve));
        root.setLeft(input);

        axes = new Axes();
        root.setCenter(axes.Axes(stage));
        Solve.setOnAction(e -> SolveButtonAction());
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
        stage.setTitle("Graphical");
        return undecoratorScene;
    }

    private void SolveButtonAction() {
        MakeXYPoint();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    
                    //Right Scale 
                     Double RscaleY = MaxXDraw / (axes.getNumPointY());
                    //Scale X
                    Double scaleX = MaxXDraw / (axes.getNumPointX() - 2);
                    axes.setxScale(scaleX);
                    //System.out.println("X: " + scaleX + "  " + MaxXDraw + " " + axes.getNumPointX());
                    //------------------------------------------
                    //Scale Y
                    Double scaleY = MaxYDraw / (axes.getNumPointY() - 2);
                    axes.setyScale(scaleY);
                    // System.out.println("Y: " + scaleY + "  " + MaxYDraw + " " + axes.getNumPointY());
                    //------------------------------------------
                    // Draw Lines
                    if (LastWidthStage != stage.getWidth() || LastHeightStage != stage.getHeight()) {
                        // Remove Lines and Circles 
                        for (int index = 0; index < StackOfConstrain.size(); index++) {
                          axes.getChildren().remove(StackOfConstrain.get(index).getYCircle());
                        }
                        // insert Lines and Circles 
                        for (int index = 0; index < StackOfConstrain.size(); index++) {
                            Constrain thisConst = StackOfConstrain.get(index);
                            double pos = 0;
                            try {
                                pos = axes.y.getEndY()-(((thisConst.getYDraw() / scaleY) / (axes.getNumPointY()+2))) * axes.y.getEndY();
                            } catch (Exception e) {
                            }
                            System.out.println(pos + " " + axes.getNumPointY() + " " + axes.y.getEndX() + " " + (index + 1));
                            StackOfConstrain.get(index).setYCircle(new Circle(axes.y.getEndX(), pos, 2));
                            Circle cir = StackOfConstrain.get(index).getYCircle();
                            cir.setFill(Color.CORAL);
                            axes.getChildren().add(cir);

                            LastWidthStage = stage.getWidth();
                            LastHeightStage = stage.getHeight();
                        }
                    }

                });
            }
        }, 50, 50);
    }

    public void MakeXYPoint() {
        StackOfConstrain = input.getStackOfConstrain();
        double rightVal, xVal, yVal;
        for (int i = 0; i < StackOfConstrain.size(); i++) {
            Constrain thisConst = StackOfConstrain.get(i);

            if ((thisConst.getxVal().getText().equals("")
                    || thisConst.getyVal().getText().equals("")
                    || thisConst.getRightVal().getText().equals(""))) {
                continue;
            }

            rightVal = Integer.parseInt(thisConst.getRightVal().getText());
            xVal = Double.parseDouble(thisConst.getxVal().getText());
            yVal = Double.parseDouble(thisConst.getyVal().getText());

            thisConst.setXDraw(rightVal / xVal);
            MaxXDraw = Math.max(MaxXDraw, thisConst.getXDraw());

            thisConst.setYDraw(rightVal / yVal);
            MaxYDraw = Math.max(MaxYDraw, thisConst.getYDraw());
        }
    }

    public double getMaxXDraw() {
        return MaxXDraw;
    }

    public void setMaxXDraw(double MaxXDraw) {
        this.MaxXDraw = MaxXDraw;
    }

    public double getMaxYDraw() {
        return MaxYDraw;
    }

    public void setMaxYDraw(double MaxYDraw) {
        this.MaxYDraw = MaxYDraw;
    }

}
