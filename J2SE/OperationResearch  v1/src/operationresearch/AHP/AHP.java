/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationresearch.AHP;

import insidefx.undecorator.UndecoratorScene;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.SwingUtilities;
import operationresearch.MainStage;

/**
 *
 * @author ahmed
 */
public class AHP {
    
    public UndecoratorScene DrawAHP(Stage stage) {
        BorderPane root = new BorderPane();
        SwingNode sn = new SwingNode();
        SwingUtilities.invokeLater(() -> {
            sn.setContent(new main_swing());
        });
        root.setCenter(sn);
        root.setPadding(new Insets(40, 25, 25, 25));
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
    
}
