package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    private static final PostgresConnection instance = new PostgresConnection();
    private Connection connection;

    private PostgresConnection() {
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/dropify",
                            "postgres",
                            "sony");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

    }

    public static Connection getInstance() {
        return instance.connection;
    }
}