package Client.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LectureStage extends VBox {

    Stage stage;

    public LectureStage(VBox layout) {

        Button downloadSlide = new Button("Download Slide");
        Button askQuestion = new Button("Ask Question");
        Button addNote = new Button("Add Note");
        Pane slidecontainer = new Pane();

        downloadSlide.setPrefSize(200, 40);
        askQuestion.setPrefSize(200, 40);
        addNote.setPrefSize(200, 40);
        slidecontainer.setPrefSize(1200, 700);
        slidecontainer.setStyle("-fx-background-color: #83a1d1;-fx-effect: dropshadow(three-pass-box, rgba(131, 161, 209,0.8), 10, 0, 0, 0);-fx-padding: 10;");

        ImageView forwordimage = new ImageView(new Image(getClass().getResourceAsStream("/Server/view/forward.png")));
        forwordimage.setFitHeight(60);
        forwordimage.setFitWidth(60);

        ImageView backwordimage = new ImageView(new Image(getClass().getResourceAsStream("/Server/view/backword.png")));
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
        hb.setSpacing(40);

        hb.getChildren().addAll(downloadSlide, askQuestion,addNote);
        hb.setAlignment(Pos.CENTER);

        setPadding(new Insets(30, 30, 30, 30));
        setSpacing(50);
        setAlignment(Pos.CENTER);

        getChildren().addAll(slideshow, hb);

    }

}
