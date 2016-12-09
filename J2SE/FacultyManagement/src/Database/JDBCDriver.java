package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDriver {

    private Connection con;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/facultydb", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return con;
    }
}
