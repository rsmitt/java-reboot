package ru.sberbank.edu.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DbEmbedded implements AutoCloseable {
    private static final String URL_MEM = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    public static final String PASSWD = "";

    private static Connection connection;

    public static void initDb() throws SQLException {
        String createCarTableSql = "CREATE TABLE car ( " +
                "id VARCHAR(30), " +
                "model VARCHAR(30)" +
                ")";
        Connection conn = getConnection();
        try (Statement statement = conn.createStatement()) {
            int count = statement.executeUpdate(createCarTableSql);
            System.out.println(count);
        }
    }

    public static Connection getConnection() throws SQLException {
        connection = connection == null ? DriverManager.getConnection(URL_MEM, USER, PASSWD) : connection;
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection == null) {
            return;
        } else {
            connection.close();
        }
    }
}
