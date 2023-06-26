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
  <div style="display:flex;justify-content: space-between;align-items: center;margin: 10px auto 10px auto;width: 90%;">
        <h1 style="text-align: center; margin:0px;">Mis Mascotas</h1>

        <h4 style="margin:0px;">
          <a href="/registrar-mascota" class="btn btn-lg btn-success">Agregar Mascota</a>
        </h4>
  </div>
  
  <c:if test="${not empty mascotas}">
    <div class="cards-container" style="display:block">
      <c:forEach  var="mascota" items="${mascotas}">
        <div class="card mis-mascotas" style="width: 95%; margin: 10px auto 10px auto;">
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
          </div>
          <div class="card-body">
            <h2 style="text-align: center">Vacunacion</h2>
            <c:if test="${not empty mascota.vacunas}">
              <c:forEach  var="vacuna" items="${mascota.vacunas}">
                <p class="card-text"> - ${vacuna.nombre}</p>
              </c:forEach>
            </c:if>
            <c:if test="${empty mascota.vacunas}">
              <p><span>No se encontraron vacunas</span></p>
              <br>
            </c:if>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:if>
</div>

<c:if test="${empty mascotas}">
  <h3><span>No hay Mascotas</span></h3>
  <br>
</c:if>

<jsp:include page="foot.jsp">
  <jsp:param name="foot" value="foot" />
</jsp:include>

</body>
</html>
