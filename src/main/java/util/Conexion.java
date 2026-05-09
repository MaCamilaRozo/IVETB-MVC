package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = getenvOrDefault(
            "DB_URL",
            "jdbc:mysql://127.0.0.1:3306/ivetb_proyecto?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");

    private static final String USER = getenvOrDefault("DB_USER", "root");

    private static final String PASSWORD = getenvOrDefault("DB_PASSWORD", "1234");

    private Conexion() {
    }

    public static Connection getConnection() throws Exception {

        // Registrar el driver JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Intentando conectar a MySQL...");

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

        System.out.println("✅ Conexión exitosa a ivetb_proyecto");

        return con;
    }

    private static String getenvOrDefault(
            String key,
            String defaultValue) {

        String value = System.getenv(key);

        return value == null || value.isBlank()
                ? defaultValue
                : value;
    }
}