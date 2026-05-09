package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import model.Producto;
import model.ProductoDAO;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private final ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("🔥 Servlet ejecutado");

        // 1. Obtener datos desde el DAO
        List<Producto> productos = productoDAO.listar();

        // 2. Debug
        System.out.println(
                "➡️ Productos desde servlet: " + productos.size());

        // 3. Enviar datos al JSP
        request.setAttribute("productos", productos);

        // 4. Redireccionar a la vista
        RequestDispatcher dispatcher = request.getRequestDispatcher("/productos.jsp");

        dispatcher.forward(request, response);
    }
}