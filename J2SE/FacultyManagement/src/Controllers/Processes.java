package Controllers;

import Database.JDBCDriver;
import Model.Courses;
import Model.Demonstrator;
import Model.Demonstrator_Courses;
import Model.Departments;
import Model.Employees;
import Model.Roles;
import facultymanagement.MainStage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Processes {

    ObservableList database ;
    TextField filterfield ;
    ArrayList<Control> inputs ;
    Connection con = null;
    TableView table ;
    TableColumn[] colarray ;
    public String n ;
    Scene scene ;
    Stage primaryStage;

    public Processes(Stage stage){
        this.primaryStage = stage;
    }

    public Scene display(String tablename) throws SQLException{

        Statement stat = null;
        ResultSet rs = null;

        Button save, delete;
        table = new TableView();

        con = new JDBCDriver().getConnection();

        stat = con.createStatement();

        rs = stat.executeQuery("SELECT * FROM `" + tablename + "`");
        rs.last();
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        System.out.println("Cols: " + cols);

        colarray = new TableColumn[cols];
        String[] columns = new String[cols];
        for(int i=0 , x=1 ; i<cols ; i++ , x++){
            columns[i] = rsmd.getColumnName(x);
            System.out.print(columns[i]+"\t");
            colarray[i] = new TableColumn(columns[i]);
            colarray[i].setPrefWidth(150);
            colarray[i].setCellValueFactory(new PropertyValueFactory(columns[i]));
        }

        //inputs of the tables
        inputs = new ArrayList();
        for(int i=0 ; i<cols ; i++){
            if(columns[i].contains("_role")){
                ComboBox cb = new ComboBox(new Dataclass().roles);
                cb.setPromptText(columns[i]);
                cb.setPrefSize(150, 25);
                inputs.add(cb);
                System.out.println("\nrole input added");
            }else if(columns[i].contains("_gender")){
                ComboBox cb = new ComboBox(FXCollections.observableArrayList("male","female")) ;
                cb.setPromptText(columns[i]);
                cb.setPrefSize(150, 25);
                inputs.add(cb);
                System.out.println("\ngender input added");
            }else if(columns[i].contains("_dep")){
                ComboBox cb = new ComboBox(new Dataclass().deps);
                cb.setPromptText(columns[i]);
                cb.setPrefSize(150, 25);
                inputs.add(cb);
                System.out.println("\ndepartment input added");
            }else if(columns[i].contains("_year")){
                ComboBox cb = new ComboBox(FXCollections.observableArrayList("1st year","2nd year","3rd year","4th year")) ;
                cb.setPromptText(columns[i]);
                cb.setPrefSize(150, 25);
                inputs.add(cb);
                System.out.println("\nyear input added");
            }else if(columns[i].contains("_course")){
                ComboBox cb = new ComboBox(new Dataclass().corses);
                cb.setPromptText(columns[i]);
                cb.setPrefSize(150, 25);
                inputs.add(cb);
                System.out.println("\ncourse_name input added");
            }else if(columns[i].contains("rator_name")){
                if(tablename.equalsIgnoreCase("demonstrator")){
                    ComboBox cb = new ComboBox(new Dataclass().demo);
                    cb.setPromptText(columns[i]);
                    cb.setPrefSize(150, 25);
                    inputs.add(cb);
                    System.out.println("\ndemonstrator_name input added");
                }else if(tablename.equalsIgnoreCase("demostrator_courses")){
                    ComboBox cb = new ComboBox(new Dataclass().managers);
                    cb.setPromptText(columns[i]);
                    cb.setPrefSize(150, 25);
                    inputs.add(cb);
                    System.out.println("\ndemonstrator_name input added");
                }
            }else if(columns[i].contains("_date")){
                DatePicker cb = new DatePicker();
                cb.setPromptText(columns[i]);
                cb.setPrefSize(150, 25);
                inputs.add(cb);
                System.out.println("\ndate input added");
            }else{
                TextField cb = new TextField();
                cb.setPromptText(columns[i]);
                cb.setPrefSize(150, 25);
                // if input was salary or age "number" validate it so the user enters only a number
                if(columns[i].contains("_salary") || columns[i].contains("_age") || columns[i].contains("id")){
                    new Validations().ValidateNumber(cb);
                }
                if(columns[i].contains("_name") || columns[i].contains("_manager")){
                    new Validations().ValidateName(cb);
                }
                if(columns[i].contains("phone")){
                    new Validations().ValidatePhone(cb);
                }
                if(columns[i].contains("_email")){
                    new Validations().ValidateEmail(cb);
                }
                inputs.add(cb);
                System.out.println("\nregular textfield input added");
            }
        }

        ImageView backimage = new ImageView(new Image(getClass().getResourceAsStream("/View/back.png")));
        backimage.setFitHeight(20);
        backimage.setFitWidth(20);

        Button back = new Button();
        back.setPrefSize(100, 30);
        back.setStyle("-fx-background-color: rgba(0, 0, 0, 0.0);");
        back.setGraphic(backimage);

        back.setOnAction(e -> {
            primaryStage.setScene(new MainStage(primaryStage).start());
            primaryStage.setTitle("Main Stage");
            System.out.println("returned to Main Stage!");
        });

        delete = new Button("Delete");

        ImageView delimage = new ImageView(new Image(getClass().getResourceAsStream("/View/delete3.png")));
        delimage.setFitHeight(20);
        delimage.setFitWidth(20);

        delete.setPrefSize(100, 25);
        delete.setGraphic(delimage);

        delete.setOnAction(e -> {
            try {
                new Deletions().delete(tablename, table , colarray , con , database);
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }

        });

        ImageView im = new ImageView(new Image(getClass().getResourceAsStream("/View/add2.png")));
        im.setFitHeight(20);
        im.setFitWidth(20);

        save = new Button("Insert");
        save.setPrefSize(100, 25);
        save.setGraphic(im);

        save.setOnAction(e -> {new Insertions().insert(tablename, cols , inputs , con , table , database);});

        HBox horizon = new HBox();
        horizon.setSpacing(70);
        horizon.setPadding(new Insets(10, 20, 20, 20));

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 20, 20, 20));

        table.setStyle("-fx-opacity:0.9");

        StackPane root = new StackPane();

        table.setEditable(true);

        HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(10, 0, 0, 10));
        hbox.getChildren().addAll(inputs);

        HBox searchBox = new HBox();
        searchBox.setSpacing(5);
        searchBox.setAlignment(Pos.TOP_RIGHT);

        filterfield = new TextField();
        filterfield.setPromptText("Filter Table");
        filterfield.setPrefSize(table.getPrefWidth(), 25);

        if(tablename.equals("employees")){
            ObservableList<Employees> data = FXCollections.observableArrayList();

            System.out.println("\nemployees table\n");
            stat = con.createStatement();
            rs = stat.executeQuery("SELECT * FROM `employees`");

            while (rs.next()) {
                data.add(new Employees(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getLong(5),rs.getString(6),rs.getLong(7),rs.getString(8),rs.getString(9)));
            }

            table.setItems(data);
            for(int i=0 ; i<colarray.length ; i++){
                table.getColumns().add(colarray[i]);
            }

            TextField employee_gender = new TextField();
            employee_gender.setPromptText("employee_gender");
            employee_gender.setPrefSize(150, 30);

            TextField employee_role = new TextField();
            employee_role.setPromptText("employee_role");
            employee_role.setPrefSize(150, 30);

            searchBox.getChildren().addAll(employee_gender , employee_role);

            database = data ;
            FilteredList<Employees> filterdata = new FilteredList<>(data , p -> true) ;
            for(int i=0 ; i<inputs.size() ; i++){
                if(inputs.get(i)instanceof TextField){
                   new Searching().searchInEmployee((TextField) inputs.get(i) , filterdata , table , filterfield, employee_gender, employee_role);
                }
            }

            new Updates().updateEmployee(colarray , table);

            horizon.getChildren().addAll(save, delete , searchBox);
            vbox.getChildren().addAll(back, filterfield, table, hbox, horizon);
            root.getChildren().add(vbox);

        }else if(tablename.equals("departments")){
            ObservableList<Departments> data = FXCollections.observableArrayList();

            System.out.println("\ndepartments table\n");
            stat = con.createStatement();
            rs = stat.executeQuery("SELECT * FROM `departments`");

            while (rs.next()) {
                data.add(new Departments(rs.getInt(1), rs.getString(2),
                        rs.getString(3)));
            }

            table.setItems(data);
            for(int i=0 ; i<colarray.length ; i++){
                table.getColumns().add(colarray[i]);
            }

            database = data ;
            FilteredList<Departments> filterdata = new FilteredList<>(data , p -> true);
            for(int i=0 ; i<inputs.size() ; i++){
                if(inputs.get(i)instanceof TextField){
                   new Searching().searchInDepartments((TextField) inputs.get(i) , filterdata , table , filterfield);
                }
            }

            new Updates().updateDepartment(colarray , table);

            vbox.getChildren().addAll(back, filterfield, table, hbox);
            root.getChildren().add(vbox);

        }else if(tablename.equals("demonstrator")){
            ObservableList<Demonstrator> data = FXCollections.observableArrayList();

            System.out.println("\ndemonstrator table\n");
            stat = con.createStatement();
            rs = stat.executeQuery("SELECT * FROM `demonstrator`");

            while (rs.next()) {
                data.add(new Demonstrator(rs.getString(1),rs.getString(2)));
            }

            table.setItems(data);
            for(int i=0 ; i<colarray.length ; i++){
                table.getColumns().add(colarray[i]);
            }

            TextField demonstrator_name = new TextField();
            demonstrator_name.setPromptText("demonstrator_name");
            demonstrator_name.setPrefSize(160, 30);

            TextField demonstrator_dep = new TextField();
            demonstrator_dep.setPromptText("demonstrator_department");
            demonstrator_dep.setPrefSize(170, 30);

            searchBox.getChildren().addAll(demonstrator_name , demonstrator_dep);

            database = data ;
            FilteredList<Demonstrator> filterdata = new FilteredList<>(data , p -> true);

            new Searching().searchInDemonstrators(filterdata, table, filterfield, demonstrator_name, demonstrator_dep);

            horizon.getChildren().addAll(save, delete, searchBox);
            vbox.getChildren().addAll(back, filterfield, table, hbox, horizon);
            root.getChildren().add(vbox);

        }else if(tablename.equals("courses")){
            ObservableList<Courses> data = FXCollections.observableArrayList();

            System.out.println("\ncourses table\n");
            ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `courses`");

            while (rst.next()) {
                data.add(new Courses(rst.getInt(1), rst.getString(2),
                        rst.getString(3), rst.getString(4)));
            }

            table.setItems(data);
            for(int i=0 ; i<colarray.length ; i++){
                table.getColumns().add(colarray[i]);
            }

            TextField course_dep = new TextField();
            course_dep.setPromptText("course_department");
            course_dep.setPrefSize(160, 30);

            TextField academic_year = new TextField();
            academic_year.setPromptText("academic_year");
            academic_year.setPrefSize(150, 30);

            searchBox.getChildren().addAll(course_dep , academic_year);

            database = data ;
            FilteredList<Courses> filterdata = new FilteredList<>(data , p -> true);
            for(int i=0 ; i<inputs.size() ; i++){
                if(inputs.get(i)instanceof TextField){
                   new Searching().searchInCourses((TextField) inputs.get(i), filterdata, table, filterfield, course_dep, academic_year);
                }
            }

            new Updates().updateCourse(colarray , table);

            horizon.getChildren().addAll(save, delete, searchBox);
            vbox.getChildren().addAll(back, filterfield, table, hbox, horizon);
            root.getChildren().add(vbox);

        }else if(tablename.equals("demostrator_courses")){
            ObservableList<Demonstrator_Courses> data = FXCollections.observableArrayList();

            System.out.println("\ndemostrator_courses table\n");
            stat = con.createStatement();
            rs = stat.executeQuery("SELECT * FROM `demostrator_courses`");

            while (rs.next()) {
                data.add(new Demonstrator_Courses(rs.getString(1),rs.getString(2)));
                System.out.println(rs.getString(1)+"\t"+rs.getString(2));
            }

            table.setItems(data);
            for(int i=0 ; i<colarray.length ; i++){
                table.getColumns().add(colarray[i]);
            }

            TextField demonstrator_name = new TextField();
            demonstrator_name.setPromptText("demonstrator_name");
            demonstrator_name.setPrefSize(160, 30);

            TextField demonstrator_course = new TextField();
            demonstrator_course.setPromptText("demonstrator_course");
            demonstrator_course.setPrefSize(150, 30);

            searchBox.getChildren().addAll(demonstrator_name , demonstrator_course);

            database = data ;
            FilteredList<Demonstrator_Courses> filterdata = new FilteredList<>(data , p -> true);

            new Searching().searchInDemonstratorCourses(filterdata, table, filterfield, demonstrator_name, demonstrator_course);

            horizon.getChildren().addAll(save,delete,searchBox);
            vbox.getChildren().addAll(back, filterfield, table, hbox, horizon);
            root.getChildren().add(vbox);

        }else if(tablename.equals("roles")){
            ObservableList<Roles> data = FXCollections.observableArrayList();

            stat = con.createStatement();
            rs = stat.executeQuery("SELECT * FROM `roles");

            while (rs.next()) {
                data.add(new Roles(rs.getInt(1),rs.getString(2)));
            }

            table.setItems(data);
            for(int i=0 ; i<colarray.length ; i++){
                table.getColumns().add(colarray[i]);
            }

            database = data ;
            FilteredList<Roles> filterdata = new FilteredList<>(data , p -> true);
            for(int i=0 ; i<inputs.size() ; i++){
                if(inputs.get(i)instanceof TextField){
                   new Searching().searchInRoles((TextField) inputs.get(i) , filterdata , table , filterfield);
                }
            }
            vbox.getChildren().addAll(back, filterfield, table, hbox);
            root.getChildren().add(vbox);

        }

        root.setStyle("-fx-background-image: url('View/background.jpg'); "
            + "-fx-background-position: center center; "
            + "-fx-background-size: cover;"
            + "-fx-background-repeat: stretch;"
            + "-fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");

        scene = new Scene(root,700,600);
        scene.getStylesheets().add("/View/style.css");

        return scene ;

    }
}