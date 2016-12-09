package lecturemanagement;

import Server.view.LectureStage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OpeningStage extends Application {

    Button controlButton;
    TextField namefield, passfield;
    HBox buttonscontainer;
    VBox logincontainer, layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.autosize();

        logincontainer = new VBox();
        logincontainer.setSpacing(40);
        logincontainer.setPadding(new Insets(10, 20, 20, 20));

        namefield = new TextField();
        namefield.setPromptText("enter username");
        namefield.setPrefSize(170, 30);

        passfield = new PasswordField();
        passfield.setPromptText("enter password");
        passfield.setPrefSize(170, 30);

        buttonscontainer = new HBox();
        buttonscontainer.setPadding(new Insets(25, 25, 25, 25));
        buttonscontainer.setPadding(new Insets(100));
        buttonscontainer.setSpacing(50);

        Button login = new Button("Login");
        login.setPrefSize(150, 30);

        Button exit = new Button("Exit");
        exit.setPrefSize(150, 30);

        buttonscontainer.getChildren().addAll(login, exit);
        buttonscontainer.setAlignment(Pos.CENTER);

        logincontainer.getChildren().addAll(namefield, passfield, buttonscontainer);
        logincontainer.setAlignment(Pos.CENTER);

        SideBar sidebar = new SideBar();
        sidebar.getChildren().add(logincontainer);
        VBox.setVgrow(logincontainer, Priority.ALWAYS);
        VBox.setVgrow(sidebar, Priority.ALWAYS);
        sidebar.setMinWidth(250);
        sidebar.setAlignment(Pos.CENTER);

        //this VBox layout will be passed throughout all classes in order to change the VBox only
        layout = new VBox();
        layout.getChildren().addAll(controlButton, sidebar);
        sidebar.setStyle("-fx-background-color:rgba(0,0,0,0);");

        login.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().add(new LectureStage(layout, primaryStage));
            layout.setAlignment(Pos.CENTER);
        });

        exit.setOnAction(e -> {
            Platform.exit();
        });

        layout.setAlignment(Pos.CENTER);
        new header().setMenuBar(root, primaryStage);
        root.setCenter(layout);
        root.setStyle("-fx-background-image:url('/Server/view/colored.jpg');"
                + "-fx-background-repeat: stretch;"
                + "-fx-background-position: center;"
                + "-fx-background-size: cover;"
                + "-fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0);");

        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add("/Server/view/style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lecture Management");
        primaryStage.show();
    }

    class SideBar extends VBox {

        /**
         * creates a sidebar containing a vertical alignment of the given nodes
         */
        SideBar(Node... nodes) {
            // create a bar to hide and show.
            setAlignment(Pos.CENTER);
            setStyle("-fx-padding: 10; -fx-background-color: linear-gradient(to bottom, lavenderblush, mistyrose); -fx-border-color: derive(mistyrose, -10%); -fx-border-width: 3;");

            // create a button to hide and show the sidebar.
            controlButton = new Button("Sign Up!");
            controlButton.setMaxWidth(Double.MAX_VALUE);
            controlButton.setTooltip(new Tooltip("Play sidebar login or sign up!"));

            // apply the animations when the button is pressed.
            controlButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (controlButton.getText().equalsIgnoreCase("Login!")) {
                        getChildren().clear();
                        getChildren().add(logincontainer);
                        controlButton.setText("Sign Up!");
                    } else if (controlButton.getText().equalsIgnoreCase("Sign Up!")) {
                        getChildren().clear();
                        getChildren().add(new SignUp(layout));
                        controlButton.setText("Login!");
                    }
                }
            });
        }
    }

}
