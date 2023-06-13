<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <jsp:include page="head.jsp">
    <jsp:param name="head" value="head" />
  </jsp:include>
  <link rel="stylesheet" href="../css/mascotas.css">
</head>
<body>

<jsp:include page="nav.jsp">
  <jsp:param name="nav" value="nav" />
</jsp:include>

<div id="contenido-home">
  <h1 style="text-align: center">Mis Mascotas</h1>
  <c:if test="${not empty mascotas}">
    <div class="cards-container" style="display:block">
      <c:forEach  var="mascota" items="${mascotas}">
        <div class="card mis-mascotas" style="width: 95%;">
          <div class="card-body">
            <img src="../img/${mascota.imagen}" class="card-img-top" alt="Mascota....">
          </div>
          <div class="card-body">
            <h2 style="text-align: center">${mascota.nombre}</h2>
            <p class="card-text">Raza: ${mascota.tipoRaza.nombre}</p>
            <p class="card-text">Estado: ${mascota.estado.nombre}</p>
            <c:if test="${not empty mascota.fechaAdopcion}">
              <p class="card-text">Fecha de Adopcion: ${mascota.fechaAdopcion}</p>
            </c:if>
            <p class="card-text">${mascota.descripcion}</p>
          </div>
          <div class="card-body">
            <h2 style="text-align: center">Vacunacion</h2>
            <p class="card-text">${mascota.vacunas}</p>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:if>
</div>

<jsp:include page="foot.jsp">
  <jsp:param name="foot" value="foot" />
</jsp:include>

</body>
</html>
