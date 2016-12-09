package facultymanagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainStage{

    Stage primaryStage ;
    
    public MainStage(Stage stage){
        this.primaryStage = stage;
    }

    public Scene start() {
        StackPane pane = new StackPane();

        Image demoimage = new Image(getClass().getResourceAsStream("/View/demo.png"));
        Image employeeiamge = new Image(getClass().getResourceAsStream("/View/employee.png"));
        Image courseimage = new Image(getClass().getResourceAsStream("/View/course.png"));
        Image departmentsimage = new Image(getClass().getResourceAsStream("/View/dep.png"));
        Image jobsimage = new Image(getClass().getResourceAsStream("/View/job.png"));
        Image democourseimage = new Image(getClass().getResourceAsStream("/View/democourse.png"));

        Button Demonstrator = new Button("D e m o n s t r a t o r s");
        Button Employees = new Button("      E m p l o y e e s   ");
        Button Courses = new Button("          C o u r s e s    ");
        Button Departments = new Button("  D e p a r t m e n t s  ");
        Button Jobs = new Button("          J o b s             ");
        Button Demonstrator_courses = new Button("D e m o n s t r a t o r  C o u r s e s");

        ImageView i1 = new ImageView(demoimage);
        i1.setFitHeight(30);
        i1.setFitWidth(30);
        
        ImageView i2 = new ImageView(employeeiamge);
        i2.setFitHeight(30);
        i2.setFitWidth(30);

        ImageView i3 = new ImageView(courseimage);
        i3.setFitHeight(30);
        i3.setFitWidth(30);

        ImageView i4 = new ImageView(departmentsimage);
        i4.setFitHeight(30);
        i4.setFitWidth(30);

        ImageView i5 = new ImageView(jobsimage);
        i5.setFitHeight(30);
        i5.setFitWidth(30);

        ImageView i6 = new ImageView(democourseimage);
        i6.setFitHeight(30);
        i6.setFitWidth(30);

        Button[] buttonarr = new Button[]{Demonstrator,Employees,Courses,Departments,Jobs,Demonstrator_courses};
        String[] tablenames = new String[]{"demonstrator","employees","courses","departments","roles","demostrator_courses"};
        ImageView[] imagearr = new ImageView[]{i1,i2,i3,i4,i5,i6};

        for(int i=0 ; i< buttonarr.length ; i++){
            buttonarr[i].setGraphic(imagearr[i]);
            buttonarr[i].setPrefSize(400, 30);
            buttonarr[i].setStyle("-fx-effect: dropshadow(three-pass-box, white, 30, 0.5, 0, 0); ");
            String tb = tablenames[i];
            buttonarr[i].setOnAction( e -> {
                try {
                    primaryStage.setScene(new Controllers.Processes(primaryStage).display(tb));
                    primaryStage.setTitle(tb);
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }

        VBox child = new VBox();

        child.setPadding(new Insets(25, 25, 25, 25));
        child.setPadding(new Insets(100));
        child.setSpacing(30);
        child.setPrefSize(300, 300);

        child.getChildren().addAll(Demonstrator,Employees,Courses,Departments,Jobs,Demonstrator_courses);

        child.setAlignment(Pos.CENTER);
        pane.getChildren().add(child);
        StackPane.setAlignment(child , Pos.CENTER);

        pane.setStyle("-fx-background-image: url('View/background.jpg'); "
                + "-fx-background-position: center center; "
                + "-fx-background-size: cover;"
                + "-fx-background-repeat: stretch;"
                + "-fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");

        Scene scene = new Scene(pane,700,600);

        scene.getStylesheets().add("/View/style.css");

        return scene;
    }

}
