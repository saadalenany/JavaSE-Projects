package Server.view;

import Server.controllers.QuizCreator;
import Server.controllers.ShowTables;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LectureStage extends VBox {

    Stage stage;
    int x = 0;
    public LectureStage(VBox layout, Stage primaryStage) {

        Button uploadSlide = new Button("Upload Slide");
        Button makeQuiz = new Button("Make Quiz");
        Button takeAbsence = new Button("Take Absence");
        Button tables = new Button("Tables");
        Pane slidecontainer = new Pane();
        FileChooser filechooser = new FileChooser();

        uploadSlide.setPrefSize(180, 40);
        makeQuiz.setPrefSize(180, 40);
        takeAbsence.setPrefSize(180, 40);
        tables.setPrefSize(180, 40);
        slidecontainer.setPrefSize(1200, 700);
        slidecontainer.setStyle("-fx-background-color: #83a1d1;-fx-effect: dropshadow(three-pass-box, rgba(131, 161, 209,0.8), 10, 0, 0, 0);-fx-padding: 10;");

        ImageView forwordimage = new ImageView(new Image(getClass().getResourceAsStream("forward.png")));
        forwordimage.setFitHeight(60);
        forwordimage.setFitWidth(60);

        ImageView backwordimage = new ImageView(new Image(getClass().getResourceAsStream("backword.png")));
        backwordimage.setFitHeight(60);
        backwordimage.setFitWidth(60);

        Button forword = new Button();
        Button backword = new Button();

        forword.setGraphic(forwordimage);
        backword.setGraphic(backwordimage);

        forword.setPrefSize(60,700);
        backword.setPrefSize(60,700);

        HBox slideshow = new HBox();
        slideshow.setSpacing(0);
        slideshow.setAlignment(Pos.CENTER);

        slideshow.getChildren().addAll(backword,slidecontainer,forword);

        HBox hb = new HBox();
        hb.setSpacing(30);

        hb.getChildren().addAll(uploadSlide, makeQuiz, takeAbsence, tables);
        hb.setAlignment(Pos.CENTER);

        setPadding(new Insets(30, 30, 30, 30));
        setSpacing(50);
        setAlignment(Pos.CENTER);

        getChildren().addAll(slideshow, hb);

        uploadSlide.setOnAction(e -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PPT files (*.ppt,*.pptx)", new String[]{"*.ppt", "*.pptx"});
            filechooser.getExtensionFilters().add(extFilter);
            File file = filechooser.showOpenDialog(primaryStage);
            System.out.println(file);
        });

        tables.setOnAction(e -> {
            layout.getChildren().clear();
            layout.getChildren().add(new ShowTables(layout));
        });

        makeQuiz.setOnAction(e -> {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            QuizCreator qc = new QuizCreator();
            qc.setId("root");
            Scene scene = new Scene(qc, 500, 600);
            scene.getStylesheets().add("/Server/view/style.css");
            stage.setScene(scene);
            stage.setTitle("Quiz Creator");
            stage.showAndWait();
        });

    }

}
