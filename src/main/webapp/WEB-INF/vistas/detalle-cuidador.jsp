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
  <h1>Detalles del Cuidador</h1>
</div>

<c:if test="${not empty cuidado}">
  <div class = "container">
    <div class="row">
      <div class="card" style="width: 80%;">
        <img src="../img/${cuidado.imagen}" class="card-img-top" alt="Cuidador....">
        <div class="card-body">
          <h5 class="card-title">${cuidado.nombre}</h5>
          <p class="card-text">Email: ${cuidado.email}</p>
          <p class="card-text">Direccion: ${cuidado.direccion}</p>
          <p class="card-text">Telefono: ${cuidado.telefono}</p>
          <p class="card-text">Latitud: ${cuidado.latitud}</p>
          <p class="card-text">Longitud: ${cuidado.longitud}</p>
          <p class="card-text">CBU: ${cuidado.cbu}</p>
        </div>
      </div>
    </div>
  </div>
  </div>
</c:if>

<c:if test="${empty cuidado}">
  <h3><span>No hay cuidador</span></h3>
  <br>
</c:if>

<jsp:include page="foot.jsp">
  <jsp:param name="foot" value="foot" />
</jsp:include>

</body>
</html>