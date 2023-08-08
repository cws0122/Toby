package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker {
    String url = "jdbc:mariadb://localhost:3306/Toby";
    String user = "root";
    String password = "1234";
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
