package Server.controllers;

import Client.model.note;
import Server.model.*;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import static lecturemanagement.DataTransferProtocol.DbName;

public class ShowTables extends VBox {

    private TabPane tabPane;
    private StorageManager st;

    Connection con = null;
    Statement stat = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;

    public ShowTables(VBox layout) {
        tabPane = new TabPane();
        con = new JDBCDriver().getConnection();
        st = new StorageManager(DbName);    
        ArrayList<String> tables = st.getTablesNames();       
        for (int i = 0; i < tables.size(); i++) {
            tabPane.getTabs().add(showUp(layout, tables.get(i)));
        }
        getChildren().add(tabPane);
    }

    public Tab showUp(VBox layout, String tableName) {
        TableView table = new TableView();
        TableColumn[] tableColumns;
        Tab tab = new Tab();
        tab.setText(tableName + " table");
        try {
            stat = con.createStatement();
            rs = stat.executeQuery("SELECT * FROM `" + tableName + "`");
            rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            System.out.println("Cols: " + cols);
            String columnNames[] = new String[cols];
            tableColumns = new TableColumn[cols];
            for (int i = 0, x = 1; i < cols; i++, x++) {
                ResultSetMetaData rsm = rs.getMetaData();
                columnNames[i] = rsm.getColumnName(x);
                System.out.print(columnNames[i] + "\t");
                tableColumns[i] = new TableColumn(rsm.getColumnName(x));
                tableColumns[i].setPrefWidth(150);
                tableColumns[i].setCellValueFactory(new PropertyValueFactory(columnNames[i]));
            }

            if (tableName.equalsIgnoreCase("doctor")) {
                ObservableList<doctor> data = FXCollections.observableArrayList();

                while (rs.next()) {
                    data.add(new doctor(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }

                table.setItems(data);
                for (TableColumn tableColumn : tableColumns) {
                    table.getColumns().add(tableColumn);
                }
            } else if (tableName.equalsIgnoreCase("question")) {
                ObservableList<question> data = FXCollections.observableArrayList();


                while (rs.next()) {
                    data.add(new question(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
                }

                table.setItems(data);
                for (TableColumn tableColumn : tableColumns) {
                    table.getColumns().add(tableColumn);
                }
            } else if (tableName.equalsIgnoreCase("quiz")) {
                ObservableList<quiz> data = FXCollections.observableArrayList();

                while (rs.next()) {
                    data.add(new quiz(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                table.setItems(data);
                for (TableColumn tableColumn : tableColumns) {
                    table.getColumns().add(tableColumn);
                }
            } else if (tableName.equalsIgnoreCase("note")) {
                ObservableList<note> data = FXCollections.observableArrayList();

                while (rs.next()) {
                    data.add(new note(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }

                table.setItems(data);
                for (TableColumn tableColumn : tableColumns) {
                    table.getColumns().add(tableColumn);
                }
            } else if (tableName.equalsIgnoreCase("quizquestion")) {
                ObservableList<quizquestion> data = FXCollections.observableArrayList();

                while (rs.next()) {
                    data.add(new quizquestion(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
                }

                table.setItems(data);
                for (TableColumn tableColumn : tableColumns) {
                    table.getColumns().add(tableColumn);
                }
            } else if (tableName.equalsIgnoreCase("slides")) {
                ObservableList<slides> data = FXCollections.observableArrayList();

                while (rs.next()) {
                    data.add(new slides(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
                }

                table.setItems(data);
                for (TableColumn tableColumn : tableColumns) {
                    table.getColumns().add(tableColumn);
                }
            } else if (tableName.equalsIgnoreCase("student")) {
                ObservableList<student> data = FXCollections.observableArrayList();

                while (rs.next()) {
                    data.add(new student(rs.getInt(1), rs.getInt(2)));
                }

                table.setItems(data);
                for (TableColumn tableColumn : tableColumns) {
                    table.getColumns().add(tableColumn);
                }
            } else if (tableName.equalsIgnoreCase("user")) {
                ObservableList<user> data = FXCollections.observableArrayList();

                while (rs.next()) {
                    data.add(new user(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                }
                table.setItems(data);
                for (TableColumn tableColumn : tableColumns) {
                    table.getColumns().add(tableColumn);
                }
            }
            tab.setContent(table);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return tab;
    }
}
