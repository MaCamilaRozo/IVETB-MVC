<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Productos</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
        }

        .contenedor {
            background: white;
            padding: 20px;
            border-radius: 10px;
        }

        .boton-nuevo {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 15px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .boton-nuevo:hover {
            background-color: #218838;
        }

        .boton-eliminados {
            display: inline-block;
            margin-bottom: 20px;
            margin-left: 10px;
            padding: 10px 15px;
            background-color: #6c757d;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .boton-eliminados:hover {
            background-color: #5a6268;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        table th,
        table td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }

        table th {
            background-color: #343a40;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .btn-editar {
            padding: 6px 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .btn-editar:hover {
            background-color: #0069d9;
        }

        .btn-eliminar {
            padding: 6px 10px;
            background-color: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .btn-eliminar:hover {
            background-color: #c82333;
        }

        .btn-restaurar {
            padding: 6px 10px;
            background-color: #17a2b8;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .btn-restaurar:hover {
            background-color: #138496;
        }

    </style>

</head>

<body>

<div class="contenedor">

    <h1>
        <c:choose>
            <c:when test="${mostrandoEliminados}">
                Productos Eliminados
            </c:when>
            <c:otherwise>
                Lista de Productos
            </c:otherwise>
        </c:choose>
    </h1>

    <c:choose>
        <c:when test="${mostrandoEliminados}">

            <a class="boton-eliminados"
               href="ProductoServlet">

                Ver productos activos

            </a>

        </c:when>
        <c:otherwise>

            <a class="boton-nuevo"
               href="ProductoServlet?accion=nuevo">

                + Nuevo Producto

            </a>

            <a class="boton-eliminados"
               href="ProductoServlet?accion=listarEliminados">

                Ver eliminados

            </a>

        </c:otherwise>
    </c:choose>

    <table>

        <thead>

        <tr>

            <th>ID</th>
            <th>ID Unidad</th>
            <th>Nombre</th>
            <th>Tipo Producto</th>
            <th>Descripción</th>
            <th>Acciones</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach var="p" items="${productos}">

            <tr>

                <td>${p.idProducto}</td>

                <td>${p.idUnidad}</td>

                <td>${p.nombre}</td>

                <td>${p.tipoProducto}</td>

                <td>${p.descripcion}</td>

                <td>

                    <c:choose>
                        <c:when test="${mostrandoEliminados}">

                            <a class="btn-restaurar"
                               href="ProductoServlet?accion=restaurar&id=${p.idProducto}"
                               onclick="return confirm('¿Deseas restaurar este producto?')">

                                Restaurar

                            </a>

                        </c:when>
                        <c:otherwise>

                            <a class="btn-editar"
                               href="ProductoServlet?accion=editar&id=${p.idProducto}">

                                Editar

                            </a>

                            <a class="btn-eliminar"
                               href="ProductoServlet?accion=eliminar&id=${p.idProducto}"
                               onclick="return confirm('¿Deseas eliminar este producto?')">

                                Eliminar

                            </a>

                        </c:otherwise>
                    </c:choose>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

</body>

</html>
