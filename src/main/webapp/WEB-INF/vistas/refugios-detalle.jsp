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
  <h1>Refugio detalle</h1>
</div>

<c:if test="${not empty refugio}">
<div class="container-fluid d-flex">
  <div class="row">
    <div class="col-4">
      <div class="card d-inline-block m-3"  style="width: 400px">
        <img src="../img/${refugio.imagen}" class="card-img-top" alt="Refugio....">
        <div class="card-body">
          <h5 class="card-title">${refugio.nombre}</h5>
          <p class="card-text">Email: ${refugio.email}</p>
          <p class="card-text">Direccion: ${refugio.direccion}</p>
          <p class="card-text">Telefono: ${refugio.telefono}</p>
            <p class="card-text">Latitud: ${refugio.latitud}</p>
            <p class="card-text">Longitud: ${refugio.longitud}</p>
        </div>
      </div>
      </div>
    </div>
</div>
</c:if>

<c:if test="${empty refugio}">
  <h3><span>No hay Refugio</span></h3>
  <br>
</c:if>

<jsp:include page="foot.jsp">
  <jsp:param name="foot" value="foot" />
</jsp:include>

</body>
</html>