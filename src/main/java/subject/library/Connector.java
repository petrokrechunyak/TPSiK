package subject.library;

import java.sql.*;

public class Connector {
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/TPSIK",
                "ama_user", "123");
    }

    public static int executeUpdate(String query) throws SQLException {
        Statement statement = getConnection().createStatement();
        int result = statement.executeUpdate(query);
        getConnection().close();
        return result;
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        getConnection().close();
        return resultSet;
    }
}
