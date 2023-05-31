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
  <h1>Bienvenidos a  todos los Refugios</h1>
</div>


<c:if test="${not empty cuidados}">
<div class="container-fluid d-flex">
  <c:forEach  var="cuidado" items="${cuidados}">
  <div class="row">
    <div class="col-4">
      <div class="card d-inline-block m-3"  style="width: 400px">
        <img src="../img/${cuidado.imagen}" class="card-img-top" alt="Refugio....">
        <div class="card-body">
          <h5 class="card-title">${cuidado.nombre}</h5>
          <p class="card-text">Email: ${cuidado.email}</p>
          <p class="card-text">Direccion: ${cuidado.direccion}</p>
          <p class="card-text">Telefono: ${cuidado.telefono}</p>
          <a href="refugio/${cuidado.id}" class="btn btn-primary">detalles</a>
        </div>
      </div>
      </div>
    </div>
  </c:forEach>
</div>
</c:if>

<c:if test="${empty cuidados}">
  <h3><span>No hay Refugios</span></h3>
  <br>
</c:if>

<jsp:include page="foot.jsp">
  <jsp:param name="foot" value="foot" />
</jsp:include>

</body>
</html>