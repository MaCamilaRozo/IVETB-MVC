package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Producto;
import model.ProductoDAO;

public class ProductoServlet extends HttpServlet {

    private final ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Get data from DAO (DB)
            List<Producto> productos = productoDAO.listar();

            // 2. DEBUG (check in terminal)
            System.out.println("Productos desde servlet: " + productos.size());

            // 3. Send data to JSP
            request.setAttribute("productos", productos);

            // 4. Forward to view
            RequestDispatcher dispatcher = request.getRequestDispatcher("/productos.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();

            request.setAttribute("error", "Error al obtener productos: " + e.getMessage());
            request.getRequestDispatcher("/productos.jsp").forward(request, response);
        }
    }
}