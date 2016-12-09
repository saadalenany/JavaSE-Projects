package facultymanagement;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FacultyManagement extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();
        BorderPane root1 = new BorderPane();
        root1.setId("pane");

        TextField username = new TextField();
        PasswordField password =  new PasswordField();

        username.setPromptText("Username");
        password.setPromptText("Password");

        username.setId("username");
        password.setId("password");

        Button login = new Button("Login");
        Button exit = new Button("Exit");

        login.setId("login");
        exit.setId("exit");

        HBox hori = new HBox();
        hori.setPadding(new Insets(25, 25, 25, 25));
        hori.setPadding(new Insets(100));
        hori.setSpacing(30);

        username.setPrefSize(400, 80);
        password.setPrefSize(400, 80);

        login.setPrefSize(400, 50);
        exit.setPrefSize(400, 50);

        hori.getChildren().addAll(login,exit);

        exit.setOnAction(e -> Platform.exit());

        VBox verti = new VBox();
        verti.setPadding(new Insets(25, 25, 25, 25));
        verti.setPadding(new Insets(100));
        verti.setSpacing(30);
        verti.setPrefSize(300, 300);

        verti.getChildren().addAll(username,password,hori);

        root1.setCenter(verti);
        pane.getChildren().add(root1);

        Scene scene1 = new Scene(pane,700,600);

        scene1.getStylesheets().add("/View/style.css");

        root1.setStyle("-fx-background-image: url('View/background.jpg'); "
            + "-fx-background-position: center center; "
            + "-fx-background-size: cover;"
            + "-fx-background-repeat: stretch;"
            + "-fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");

        login.setOnAction(e ->{
            if(username.getText().equalsIgnoreCase("superuser") && password.getText().equalsIgnoreCase("superuser")){
                primaryStage.setScene(new MainStage(primaryStage).start());
                primaryStage.setTitle("Main Stage");
            }else{
                JOptionPane.showMessageDialog(null,"User doesn't exist!");
            }
        });

        primaryStage.setTitle("Faculty Management System");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
