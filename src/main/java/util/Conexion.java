package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = getenvOrDefault(
            "DB_URL",
            "jdbc:mysql://localhost:3306/ivetb_proyecto?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
    private static final String USER = getenvOrDefault("DB_USER", "root");
    private static final String PASSWORD = getenvOrDefault("DB_PASSWORD", "");

    private Conexion() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static String getenvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? defaultValue : value;
    }

}
