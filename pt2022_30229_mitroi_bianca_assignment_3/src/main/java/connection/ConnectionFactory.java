package connection;

import java.sql.*;

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/tema3";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch(ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch(SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void close(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch(SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch(SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
