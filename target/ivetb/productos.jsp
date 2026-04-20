<%
    Object obj = request.getAttribute("productos");
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