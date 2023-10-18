package ru.sberbank.edu;

import java.sql.*;

public class DatabaseResource extends OutputData implements DataResource {
    private final Connection connection;
    private final Statement statement;
    private final ResultSet resultSet;

    public DatabaseResource(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM my_table");
    }

    @Override
    public String readData() throws SQLException {
        if (resultSet.next()) {
            return resultSet.getString("data");
        }
        return null;
    }

    @Override
    public void close() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }


}
