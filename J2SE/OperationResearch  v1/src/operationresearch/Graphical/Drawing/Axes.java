/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.Graphical.Drawing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Amr
 */
public class Axes extends Pane {

    public Line x, y;
    private Stage stage;
    private double LastWidthStage;
    private double LastHeightStage;
    private ArrayList<Text> YNumber, XNumber;
    private final Rectangle2D ScreenSize = Screen.getPrimary().getVisualBounds();
   
    private double xScale = 10;
    private double yScale = 10;
    private int NumPointX;
    private int NumPointY;

    public Parent Axes(Stage stage) {
        this.stage = stage;
        YNumber = new ArrayList();
        XNumber = new ArrayList();

        this.LastWidthStage = stage.getWidth();
        this.LastHeightStage = stage.getHeight();

        x = new Line();
        x.setStrokeWidth(4);

        y = new Line();
        y.setStrokeWidth(4);

        getChildren().addAll(YNumber);
        RefreshXYAxes();

        this.getChildren().addAll(x, y);

        this.setStyle(" -fx-background-color: #62976F ;");
        RefreshNumberXY();
        setXYNumber();

        return this;
    }

    private void RefreshXYAxes() {

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    // Refrsh X Location
                    x.setStartX(stage.getWidth() / 16);
                    x.setStartY(stage.getHeight() - 220);
                    x.setEndX(stage.getWidth() - (stage.getWidth() / 3) + stage.getWidth() / 52);
                    if (stage.getWidth() > 1200) {
                        x.setEndX(stage.getWidth() - (stage.getWidth() / 3) + stage.getWidth() / 27);
                    }
                    x.setEndY(stage.getHeight() - 220);
                    // Refrsh Y Location
                    y.setStartX(stage.getWidth() / 16);
                    y.setStartY(20);
                    y.setEndX(stage.getWidth() / 16);
                    y.setEndY(stage.getHeight() - 220);

                });
            }
        }, 100, 100);

    }

    private void setXYNumber() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    RefreshNumberXY();
                });
            }

        }, 50, 50);
    }

    private void RefreshNumberXY() {
        double increasingOfY = 0;
        double increasingOfX = 0;
        // if Stage Width becomes Width screen or Stage width
        if (LastWidthStage != stage.getWidth() || LastHeightStage != stage.getHeight() || ScreenSize.getWidth() == stage.getWidth()) {
            // RemoveAll Y Number
            this.getChildren().removeAll(YNumber);
            YNumber.clear();
            // RemoveAll X Number
            this.getChildren().removeAll(XNumber);
            XNumber.clear();
            // Add Numbers vertical (Y Axe)
            for (int i = (int) y.getEndY(); i > (int) y.getStartY(); i -= 20) {
                // The Number 
                increasingOfY += yScale;
                Double BigIncreasingOfY = new BigDecimal(increasingOfY)
                        .setScale(2, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
                
                Text number = new Text(BigIncreasingOfY + " -");
                number.setY(i);
                number.setX(y.getStartX()-20-(BigIncreasingOfY+"").length()*4 );
                number.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                YNumber.add(number);
            }
            // Add numbers Horizontal
            for (int i = (int) x.getStartX(); i < (int) x.getEndX(); i += 20) {
                increasingOfX += xScale;
                Double BigIncreasingOfX = new BigDecimal(increasingOfX)
                        .setScale(2, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
                Text number = new Text(BigIncreasingOfX + " -");
                number.setY(y.getEndY()+12+(BigIncreasingOfX+"").length()*2);
                number.setRotate(270);
                number.setX(i);
                number.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                XNumber.add(number);
            }
            // set Number Of Point 
            NumPointY = YNumber.size();
            NumPointX = XNumber.size();

            this.getChildren().addAll(YNumber);
            this.getChildren().addAll(XNumber);
            LastWidthStage = stage.getWidth();
            LastHeightStage = stage.getHeight();
        }
    }

    public Stage getStage() {
        return this.stage;
    }

    public double getxScale() {
        return xScale;
    }

    public void setxScale(double xScale) {
        this.xScale = xScale;
    }

    public double getyScale() {
        return yScale;
    }

    public void setyScale(double yScale) {
        this.yScale = yScale;
    }

    public int getNumPointX() {
        return NumPointX;
    }

    public void setNumPointX(int NumPointX) {
        this.NumPointX = NumPointX;
    }

    public int getNumPointY() {
        return NumPointY;
    }

    public void setNumPointY(int NumPointY) {
        this.NumPointY = NumPointY;
    }

}
