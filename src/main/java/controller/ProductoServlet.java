package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ProductoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("mensaje", "Hola desde el Servlet");

        RequestDispatcher dispatcher = request.getRequestDispatcher("productos.jsp");
        dispatcher.forward(request, response);
    }
}