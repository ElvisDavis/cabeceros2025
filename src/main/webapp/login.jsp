<%--
  Created by IntelliJ IDEA.
  User: ADMIN-ITQ
  Date: 10/11/2025
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Usuario</title>
</head>
<body>
<h1>INICIO DE SESION</h1>
<div>
    <form action="/cabeceras/login" method="post">
        <div>
            <label for="user">Ingrese el usuario</label>
            <input type="text" id="user" name="user">
        </div>
        <div>
            <label for="password">Ingrese el password</label>
            <input type="password" id="password" name="password">
        </div>

        <div>
            <input type="submit" value="INICIAR SESION">
        </div>

    </form>
</div>

</body>
</html>
