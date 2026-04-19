package util;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection con = Conexion.getConnection()) {
            System.out.println("Conexión exitosa 🚀");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}