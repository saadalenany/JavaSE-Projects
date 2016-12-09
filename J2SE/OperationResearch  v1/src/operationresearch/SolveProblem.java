package operationresearch;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import operationresearch.Graphical.Drawing.Axes;
import operationresearch.Graphical.TheInput.Constrain;
import operationresearch.Graphical.TheInput.QInput;

public class SolveProblem {

    private Stack<Constrain> StackOfConstrain;
    private double MaxXDraw;
    private double MaxYDraw;
    private final Axes axes;
    private QInput input;

    public SolveProblem(Axes axes, QInput input) {
        StackOfConstrain = new Stack();
        this.axes = axes;
        this.input = input;
    }

    public void SolveButtonAction() {
        MakeXYPoint();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    //Scale X
                    Double scaleX = MaxXDraw / axes.getNumPointX();
                    axes.setxScale(scaleX);
                    System.out.println("X: " + scaleX + "  " + MaxXDraw + " " + axes.getNumPointX());
                    //------------------------------------------
                    //Scale Y
                    Double scaleY = MaxYDraw / axes.getNumPointY();
                    axes.setyScale(scaleY);
                    System.out.println("Y: " + scaleY + "  " + MaxYDraw + " " + axes.getNumPointY());
                });
            }
        }, 100, 100);
    }

    private void MakeXYPoint() {
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
