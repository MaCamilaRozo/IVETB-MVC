<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Formulario Producto</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 40px;
        }

        .contenedor {
            background: white;
            padding: 30px;
            border-radius: 10px;
            width: 500px;
            margin: auto;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        label {
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #0069d9;
        }

        .volver {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }

        .volver:hover {
            text-decoration: underline;
        }

    </style>

</head>

<body>

<div class="contenedor">

    <h1>

        ${producto != null ? 'Editar Producto' : 'Nuevo Producto'}

    </h1>

    <form action="ProductoServlet" method="post">

        <!-- Acción -->
        <input type="hidden"
               name="accion"
               value="${producto != null ? 'actualizar' : 'insertar'}">

        <!-- ID oculto SOLO cuando se edita -->
        <c:if test="${producto != null}">

            <input type="hidden"
                   name="id_producto"
                   value="${producto.idProducto}">

        </c:if>

        <!-- ID UNIDAD -->
        <label>ID Unidad</label>

        <input type="number"
               name="id_unidad"
               required
               value="${producto.idUnidad}">

        <!-- NOMBRE -->
        <label>Nombre</label>

        <input type="text"
               name="nombre"
               required
               value="${producto.nombre}">

        <!-- TIPO PRODUCTO -->
        <label>Tipo Producto</label>

        <input type="text"
               name="tipo_producto"
               required
               value="${producto.tipoProducto}">

        <!-- DESCRIPCIÓN -->
        <label>Descripción</label>

        <input type="text"
               name="descripcion"
               required
               value="${producto.descripcion}">

        <!-- BOTÓN -->
        <button type="submit">

            ${producto != null ? 'Actualizar Producto' : 'Guardar Producto'}

        </button>

    </form>

    <a class="volver"
       href="ProductoServlet">

        ← Volver a la lista

    </a>

</div>

</body>

</html>