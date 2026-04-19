package model;

import util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String SELECT_ALL = """
            SELECT id_producto, id_unidad, nombre, tipo_producto, descripcion
            FROM productos
            ORDER BY id_producto DESC
            """;
    private static final String SELECT_BY_ID = """
            SELECT id_producto, id_unidad, nombre, tipo_producto, descripcion
            FROM productos
            WHERE id_producto = ?
            """;
    private static final String INSERT = """
            INSERT INTO productos (id_unidad, nombre, tipo_producto, descripcion)
            VALUES (?, ?, ?, ?)
            """;
    private static final String UPDATE = """
            UPDATE productos
            SET id_unidad = ?, nombre = ?, tipo_producto = ?, descripcion = ?
            WHERE id_producto = ?
            """;
    private static final String DELETE = "DELETE FROM productos WHERE id_producto = ?";

    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        System.out.println("Conectado a la BD...");

        try (Connection connection = Conexion.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT DATABASE()");
                ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                System.out.println("Database actual: " + rs.getString(1));
            }
        }

        try (Connection connection = Conexion.getConnection();
                PreparedStatement st = connection.prepareStatement("SELECT COUNT(*) FROM productos");
                ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                System.out.println("Cantidad en DB (Java): " + rs.getInt(1));
            }
        }

        try (Connection connection = Conexion.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(resultSet.getInt("id_producto"));
                producto.setIdUnidad(resultSet.getInt("id_unidad"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setTipoProducto(resultSet.getString("tipo_producto"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                productos.add(producto);
            }
        }

        return productos;
    }

    public Producto obtenerPorId(int id) throws SQLException {
        Producto producto = null;

        try (Connection connection = Conexion.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    producto = new Producto();
                    producto.setIdProducto(resultSet.getInt("id_producto"));
                    producto.setIdUnidad(resultSet.getInt("id_unidad"));
                    producto.setNombre(resultSet.getString("nombre"));
                    producto.setTipoProducto(resultSet.getString("tipo_producto"));
                    producto.setDescripcion(resultSet.getString("descripcion"));
                }
            }
        }

        return producto;
    }

    public void insertar(Producto producto) throws SQLException {
        try (Connection connection = Conexion.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setInt(1, producto.getIdUnidad());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getTipoProducto());
            statement.setString(4, producto.getDescripcion());
            statement.executeUpdate();
        }
    }

    public void actualizar(Producto producto) throws SQLException {
        try (Connection connection = Conexion.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setInt(1, producto.getIdUnidad());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getTipoProducto());
            statement.setString(4, producto.getDescripcion());
            statement.setInt(5, producto.getIdProducto());
            statement.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        try (Connection connection = Conexion.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();

        try {
            List<Producto> lista = dao.listar();

            System.out.println("Cantidad de productos: " + lista.size());

            for (Producto p : lista) {
                System.out.println(
                        p.getIdProducto() + " - " +
                                p.getNombre() + " - " +
                                p.getTipoProducto());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
