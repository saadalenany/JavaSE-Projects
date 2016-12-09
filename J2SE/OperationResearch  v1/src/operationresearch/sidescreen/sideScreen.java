/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.sidescreen;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/**
 *
 * @author Amr
 */
public class sideScreen extends BorderPane {

    private double StageWidth;
    private double Speed = 10;
    private int DirOROppositeDir = 1;
    private int initPositionX = 15;
    private int initPositionY = 15;
    private int SideDivide = 4;

    private double StageHeight;
    private int mode;
    private Arc arc;
    private Stage stage;
    private Color HoverColor, FocusColor;
    private int Dir, oppositeDir;

    private ObservableList<Node> children;
    private ArrayList<Node> ZeroNodesSize = new ArrayList<>();
    private ArrayList<NodeSize> ActualNodesSize = new ArrayList<>();

    /**
     *
     * @param stage
     * @param mode __ 1 -> put in left , 2 -> put in Right , 3 -> put in Up , 4
     * -> put in Down
     *
     *
     */
    public sideScreen(Stage stage, int mode) {
        HoverColor = Color.rgb(255, 183, 157);
        FocusColor = Color.CORAL;
        this.mode = mode;
        this.stage = stage;
        StageHeight = stage.getHeight();
        StageWidth = stage.getWidth();
        setStyle("-fx-background-color:#fcc6c2;");
        DrawArc();

        switch (mode) {
            case 1:   // left
                Dir = 90;
                oppositeDir = 270;
                this.setRight(new Pane(arc));
                break;
            case 2:   //Right
                Dir = 270;
                oppositeDir = 90;
                this.setLeft(new Pane(arc));
                break;
            case 3:  //Up
                Dir = 180;
                oppositeDir = 360;
                this.setBottom(new Pane(arc));
                break;
            case 4:  //Down
                Dir = 360;
                oppositeDir = 180;
                this.setTop(new Pane(arc));
                break;
            default:
                break;
        }
       Button  unVisible= new Button();
       unVisible.setPrefWidth(this.getWidth());
       unVisible.setVisible(false);
       this.setBottom(unVisible);
        // Mouse Hover 
        arc.setOnMouseEntered(e -> {
            arc.setStroke(HoverColor);
        });
        arc.setOnMouseExited(e -> {
            arc.setStroke(FocusColor);
        });
        //mouse Klick
        arc.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent me) -> MouseKlickToArc());
        // reSize Arc to The Center Of Height or Width 
        ReSizeArcToCenter();
    }

    private void ReSizeArcToCenter() {
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                StageHeight = stage.getHeight();
                StageWidth = stage.getWidth();
                switch (mode) {
                    case 1:   // left
                        arc.setCenterY(StageHeight / 2);
                        break;
                    case 2:   //Right
                        arc.setCenterY(StageHeight / 2);
                        break;
                    case 3:  //Up
                        arc.setCenterX(StageWidth / 2);
                        break;
                    case 4:  //Down
                        arc.setCenterX(StageWidth / 2);
                        break;
                    default:
                        break;
                }

            }
        };
        t.scheduleAtFixedRate(task, 0, 50);
    }

    private void DrawArc() {
        arc = new Arc(15, StageHeight / 2, 30, 30, 0, 180);
        arc.setType(ArcType.OPEN);
        arc.setStrokeWidth(17);
        arc.setStroke(Color.CORAL);
        arc.setStrokeType(StrokeType.INSIDE);
        arc.setFill(null);
        arc.setRotate(Dir); // side 
    }

    private void HideComponent() {
        for (int i = 0; i < ZeroNodesSize.size(); i++) {
            SetNodeSize(ZeroNodesSize.get(i), 0, 0);
        }
    }

    private void ShowComponent() {
        for (int i = 0; i < ActualNodesSize.size(); i++) {
            double w = ActualNodesSize.get(i).getWidth();
            double h = ActualNodesSize.get(i).getHeight();
            //  System.out.println(w + " " + h);
            SetNodeSize(ZeroNodesSize.get(i), w, h);
        }
    }

    private void MouseKlickToArc() {
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                StageWidth = stage.getWidth();
                StageHeight = stage.getHeight();
                if (mode == 1 || mode == 2) {     // Move left to right 
                    if (initPositionX < StageWidth / SideDivide && DirOROppositeDir == 1) {// start
                        arc.setCenterX(initPositionX);
                        initPositionX += Speed;
                        HideComponent();
                    } else if (initPositionX > 15 && DirOROppositeDir == 0) {       // end
                        arc.setCenterX(initPositionX);
                        initPositionX -= Speed;
                        HideComponent();
                    } else {
                        if (DirOROppositeDir == 0) {
                            arc.setRotate(Dir);
                        } else {
                            ShowComponent();
                            arc.setRotate(oppositeDir);
                        }

                        DirOROppositeDir = 1 - DirOROppositeDir;
                        t.cancel();
                    }
                } else if (mode == 3 || mode == 4) {// Move Up to down
                    if (initPositionY < StageHeight / SideDivide && DirOROppositeDir == 1) {// start
                        arc.setCenterY(initPositionY);
                        System.out.println(initPositionY);
                        initPositionY += Speed;
                    } else if (initPositionY > 15 && DirOROppositeDir == 0) {       // end
                        arc.setCenterY(initPositionY);
                        System.out.println(initPositionY);
                        initPositionY -= Speed;
                    } else {
                        if (DirOROppositeDir == 0) {
                            arc.setRotate(Dir);
                        } else {
                            arc.setRotate(oppositeDir);
                        }
                        DirOROppositeDir = 1 - DirOROppositeDir;
                        t.cancel();
                    }
                }

            }
        };
        t.scheduleAtFixedRate(task, 0, 50);
    }

    /**
     * @param node
     * @param w node width
     * @param h node Height
     */
    private void SetNodeSize(Node node, double w, double h) {
        NodeSize size = new NodeSize();
        if (node instanceof Button) {
            Button b = (Button) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
            b.setPrefSize(w, h);
        } else if (node instanceof TextField) {
            TextField b = (TextField) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
            System.out.println(b.getWidth() + " " + b.getHeight());
            b.setPrefSize(w, h);
        } else if (node instanceof ComboBox) {
            ComboBox b = (ComboBox) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
            b.setPrefSize(w, h);
        } else if (node instanceof Label) {
            Label b = (Label) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
            b.setPrefSize(w, h);
        } else if (node instanceof RadioButton) {
            RadioButton b = (RadioButton) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
            b.setPrefSize(w, h);
        } else if (node instanceof PasswordField) {
            PasswordField b = (PasswordField) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
            b.setPrefSize(w, h);
        } else if (node instanceof ListView) {
            ListView b = (ListView) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
            b.setPrefSize(w, h);
        }

    }

    /**
     * @param node
     * @param w node width
     * @param h node Height
     */
    private NodeSize getNodeSize(Node node) {
        NodeSize size = new NodeSize();
        if (node instanceof Button) {
            Button b = (Button) node;
            size.setWidth(b.getPrefWidth());
            size.setHeight(b.getPrefHeight());
        } else if (node instanceof TextField) {
            TextField b = (TextField) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
            System.out.println(b.getWidth() + " " + b.getHeight());
        } else if (node instanceof ComboBox) {
            ComboBox b = (ComboBox) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
        } else if (node instanceof Label) {
            Label b = (Label) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
        } else if (node instanceof RadioButton) {
            RadioButton b = (RadioButton) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
        } else if (node instanceof PasswordField) {
            PasswordField b = (PasswordField) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
        } else if (node instanceof ListView) {
            ListView b = (ListView) node;
            size.setWidth(b.getWidth());
            size.setHeight(b.getHeight());
        }
        return size;
    }

    private void addAllNodeFromPane(Node node) {

        if (node instanceof Pane) {
            Pane p = (Pane) node;
            children = p.getChildren();
        } else {
            ZeroNodesSize.add(node);
            ActualNodesSize.add(getNodeSize(node));

            return;
        }
        for (int i = 0; i < children.size(); i++) {
            if (node instanceof Pane) {
                addAllNodeFromPane(children.get(i));
            }
        }
    }

    public void setToCenter(Node node) {
        centerProperty().set(node);
        if (node instanceof Pane) {
            addAllNodeFromPane(node);
        } else {
            ZeroNodesSize.add(node);
            ActualNodesSize.add(getNodeSize(node));
        }

    }

    public void setToRight(Node node) {
        rightProperty().set(node);
        if (node instanceof Pane) {
            addAllNodeFromPane(node);
        } else {
            ZeroNodesSize.add(node);
            ActualNodesSize.add(getNodeSize(node));
        }
    }

    public void setToLeft(Node node) {
        leftProperty().set(node);
        if (node instanceof Pane) {
            addAllNodeFromPane(node);
        } else {
            ZeroNodesSize.add(node);
            ActualNodesSize.add(getNodeSize(node));
        }
    }

    public void setToTop(Node node) {
        topProperty().set(node);
        if (node instanceof Pane) {
            addAllNodeFromPane(node);
        } else {
            ZeroNodesSize.add(node);
            ActualNodesSize.add(getNodeSize(node));
        }
    }

    public void setToBottom(Node node) {
        bottomProperty().set(node);
        if (node instanceof Pane) {
            addAllNodeFromPane(node);
        } else {
            ZeroNodesSize.add(node);
            ActualNodesSize.add(getNodeSize(node));
        }
    }

    public double getSpeed() {
        return Speed;
    }

    public void setSpeed(double Speed) {
        this.Speed = Speed;
    }

    public int getSideDivide() {
        return SideDivide;
    }

    public void setSideDivide(int SideDivide) {
        this.SideDivide = SideDivide;
    }

    public Color getHoverColor() {
        return HoverColor;
    }

    public void setHoverColor(Color HoverColor) {
        this.HoverColor = HoverColor;
    }

    public Color getFocusColor() {
        return FocusColor;
    }

    public void setFocusColor(Color FocusColor) {
        this.FocusColor = FocusColor;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
