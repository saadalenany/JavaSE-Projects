package lecturemanagement;

import Client.view.Waiting;
import Server.view.LectureStage;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SignUp extends VBox {

    TextField user_id, user_name, user_email, user_phone;
    PasswordField user_password;
    ComboBox<String> user_gender, user_department;
    Button signup;
    ScrollPane sp ;
    public SignUp(VBox layout) {
        user_id = new TextField();
        user_name = new TextField();
        user_email = new TextField();
        user_phone = new TextField();
        user_password = new PasswordField();
        user_gender = new ComboBox(FXCollections.observableArrayList("male", "female"));
        user_department = new ComboBox(FXCollections.observableArrayList("General", "CS", "IS", "IT", "OR"));

        user_id.setPromptText("academic id");
        user_name.setPromptText("full name");
        user_email.setPromptText("E-mail");
        user_phone.setPromptText("phone number");
        user_password.setPromptText("password");
        user_gender.setPromptText("gender");
        user_department.setPromptText("department");

        user_id.setPrefSize(150, 30);
        user_name.setPrefSize(150, 30);
        user_email.setPrefSize(150, 30);
        user_phone.setPrefSize(150, 30);
        user_password.setPrefSize(150, 30);
        user_gender.setPrefSize(150, 30);
        user_department.setPrefSize(150, 30);

        signup = new Button("Sign Up");
        signup.setPrefSize(150, 30);

        signup.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().add(new Waiting(layout));
        });

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(30);

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(30);

        hb.getChildren().addAll(user_gender, user_department);
        vb.getChildren().addAll(user_id, user_name, user_email, user_phone, user_password, hb, signup);
        sp = new ScrollPane();

        StackPane stackp = new StackPane();
        stackp.getChildren().add(vb);

        stackp.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        sp.getViewportBounds().getWidth(), sp.viewportBoundsProperty()));

        sp.setContent(stackp);
        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(30);

        getChildren().addAll(vb);
    }
}
