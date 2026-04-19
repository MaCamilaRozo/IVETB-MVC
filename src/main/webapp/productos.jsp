<%
    Object obj = request.getAttribute("productos");

    if (obj == null) {
        out.println("<p style='color:red;'>DEBUG: productos es NULL</p>");
    } else {
        out.println("<p style='color:green;'>DEBUG: productos NO es NULL</p>");
    }
%>
<%@ page import="java.util.*, model.Producto" %>

<h1>Lista de Productos</h1>

<%
    List<Producto> lista = (List<Producto>) request.getAttribute("productos");

    if (lista != null && !lista.isEmpty()) {
        for (Producto p : lista) {
%>
            <p>
                <%= p.getIdProducto() %> -
                <%= p.getNombre() %> -
                <%= p.getTipoProducto() %>
            </p>
<%
        }
    } else {
%>
        <p>No hay productos</p>
<%
    }
%>