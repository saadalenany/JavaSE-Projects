/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.pert;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 *
 * @author Amr
 */
public class Arrow {

    public Arrow(double startx, double starty, double endx, double endy, Pane root) {

        double arrowAngle = Math.toRadians(45.0);
        double arrowLength = 10.0;

        double lineAngle = Math.atan2(starty - endy, startx - endx);
        System.out.println(lineAngle);
        double x1 = Math.cos(lineAngle + arrowAngle) * arrowLength + endx;
        double y1 = Math.sin(lineAngle + arrowAngle) * arrowLength + endy;

        double x2 = Math.cos(lineAngle - arrowAngle) * arrowLength + endx;
        double y2 = Math.sin(lineAngle - arrowAngle) * arrowLength + endy;

        Line line = new Line(startx, starty, endx, endy);
        Line arrowHead1 = new Line(endx, endy, x1, y1);
        Line arrowHead2 = new Line(endx, endy, x2, y2);

        line.setStrokeWidth(3);
        arrowHead1.setStrokeWidth(3);
        arrowHead2.setStrokeWidth(3);

        root.getChildren().addAll(line, arrowHead1, arrowHead2);

    }

}
