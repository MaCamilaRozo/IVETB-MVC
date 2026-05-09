package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import model.Producto;
import model.ProductoDAO;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {

    private final ProductoDAO productoDAO = new ProductoDAO();

    // ==================================================
    // GET
    // ==================================================
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;

            case "editar":
                mostrarFormularioEditar(request, response);
                break;

            case "eliminar":
                eliminarProducto(request, response);
                break;

            case "listarEliminados":
                listarProductosEliminados(request, response);
                break;

            case "restaurar":
                restaurarProducto(request, response);
                break;

            default:
                listarProductos(request, response);
                break;
        }
    }

    // ==================================================
    // POST
    // ==================================================
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {

            case "insertar":
                insertarProducto(request, response);
                break;

            case "actualizar":
                actualizarProducto(request, response);
                break;
        }
    }

    // ==================================================
    // READ - LISTAR
    // ==================================================
    private void listarProductos(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Producto> productos = productoDAO.listar();

        request.setAttribute("productos", productos);
        request.setAttribute("mostrandoEliminados", false);

        request.getRequestDispatcher("productos.jsp")
                .forward(request, response);
    }

    // ==================================================
    // READ - LISTAR ELIMINADOS
    // ==================================================
    private void listarProductosEliminados(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Producto> productos = productoDAO.listarEliminados();

        request.setAttribute("productos", productos);
        request.setAttribute("mostrandoEliminados", true);

        request.getRequestDispatcher("productos.jsp")
                .forward(request, response);
    }

    // ==================================================
    // CREATE - MOSTRAR FORMULARIO NUEVO
    // ==================================================
    private void mostrarFormularioNuevo(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("formularioProducto.jsp")
                .forward(request, response);
    }

    // ==================================================
    // CREATE - INSERTAR
    // ==================================================
    private void insertarProducto(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        Producto producto = new Producto();

        producto.setIdUnidad(
                Integer.parseInt(request.getParameter("id_unidad")));

        producto.setNombre(
                request.getParameter("nombre"));

        producto.setTipoProducto(
                request.getParameter("tipo_producto"));

        producto.setDescripcion(
                request.getParameter("descripcion"));

        productoDAO.insertar(producto);

        response.sendRedirect("ProductoServlet");
    }

    // ==================================================
    // UPDATE - MOSTRAR FORMULARIO EDITAR
    // ==================================================
    private void mostrarFormularioEditar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id"));

        Producto producto = productoDAO.obtenerPorId(id);

        request.setAttribute("producto", producto);

        request.getRequestDispatcher("formularioProducto.jsp")
                .forward(request, response);
    }

    // ==================================================
    // UPDATE - ACTUALIZAR
    // ==================================================
    private void actualizarProducto(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        Producto producto = new Producto();

        producto.setIdProducto(
                Integer.parseInt(
                        request.getParameter("id_producto")));

        producto.setIdUnidad(
                Integer.parseInt(
                        request.getParameter("id_unidad")));

        producto.setNombre(
                request.getParameter("nombre"));

        producto.setTipoProducto(
                request.getParameter("tipo_producto"));

        producto.setDescripcion(
                request.getParameter("descripcion"));

        productoDAO.actualizar(producto);

        response.sendRedirect("ProductoServlet");
    }

    // ==================================================
    // DELETE - ELIMINAR
    // ==================================================
    private void eliminarProducto(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(
                request.getParameter("id"));

        productoDAO.eliminar(id);

        response.sendRedirect("ProductoServlet");
    }

    // ==================================================
    // RESTORE - RESTAURAR
    // ==================================================
    private void restaurarProducto(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(
                request.getParameter("id"));

        productoDAO.restaurar(id);

        response.sendRedirect("ProductoServlet?accion=listarEliminados");
    }
}
