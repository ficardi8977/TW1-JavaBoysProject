<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <jsp:include page="head.jsp">
    <jsp:param name="head" value="head" />
  </jsp:include>
</head>

<body>

<div class="container">
  <!-- NAV -->
  <jsp:include page="nav.jsp">
    <jsp:param name="nav" value="nav" />
  </jsp:include>
  <!-- NAV -->

  <!-- CONTENT -->
  <div class="content">
    <c:if test="${not empty mascota}">
    <h1 style="text-align: center;">${mascota.nombre}</h1>
    <div class="card" style="width: 95%;">
      <img src="../img/${mascota.imagen}" class="card-img-top" alt="Mascota....">
      <div class="card-body">
        <p class="card-text" id="mascotaNombre">Raza: ${mascota.tipoRaza.nombre}</p>
        <p class="card-text">Estado: ${mascota.estado.nombre}</p>
        <c:if test="${not empty mascota.fechaAdopcion}">
          <p class="card-text">Fecha de Adopcion: ${mascota.fechaAdopcion}</p>
        </c:if>
        <p class="card-text">${mascota.descripcion}</p>
        <input type="hidden" value="${mascota.latitud}" id="mascotaLatitud">
        <input type="hidden" value="${mascota.longitud}" id="mascotaLongitud">
      </div>
    </div>
    </c:if>
    <c:if test="${empty mascota}">
      <h3><span>No existe mascota</span></h3>
      <br>
    </c:if>

    <!-- Mostrar el mapa con la ubicación de la mascota -->
    <div id="map" style="height: 400px;"></div>
  <!-- CONTENT -->

  <!-- FOOTER -->
  <jsp:include page="foot.jsp">
    <jsp:param name="foot" value="foot" />
  </jsp:include>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4"></script>
  <script src="../js/detalle-mascota.js"></script>
  <!-- FOOTER -->
</div>

</body>

</html>