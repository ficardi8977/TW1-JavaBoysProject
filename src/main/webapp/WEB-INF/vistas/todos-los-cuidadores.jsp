<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="head" value="head" />
    </jsp:include>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="nav" value="nav" />
</jsp:include>
<div class = "container">
    <h1>Nuestros Cuidadores</h1>
</div>

<c:if test="${not empty cuidadores}">
    <c:forEach  var="mascota" items="${cuidadores}">
        <div class="card" style="width: 18rem;">
            <img src="../img/${cuidado.imagen}" class="card-img-top" alt="Cuidado....">
            <div class="card-body">
                <h5 class="card-title">${cuidado.nombre}</h5>
                <p class="card-text">Email: ${cuidado.email}</p>
                <p class="card-text">Direccion: ${cuidado.direccion}</p>
                <p class="card-text">Telefono: ${cuidado.telefono}</p>
                <a href="#" class="btn btn-primary">Detalles</a>
            </div>
        </div>
    </c:forEach>
</c:if>

<c:if test="${empty cuidadores}">
    <h3><span>No hay cuidadores</span></h3>
    <br>
</c:if>

<jsp:include page="foot.jsp">
    <jsp:param name="foot" value="foot" />
</jsp:include>
</body>
</html>