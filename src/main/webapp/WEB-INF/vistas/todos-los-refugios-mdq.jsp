<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <jsp:include page="head.jsp">
    <jsp:param name="head" value="head" />
  </jsp:include>
  <link rel="stylesheet" href="../css/lista-todos.css">
</head>
<body>
<jsp:include page="nav.jsp">
  <jsp:param name="nav" value="nav" />
</jsp:include>

<div class="banners-pagina-refugios">
  <div class="titulos-todos">
    <div>
      <h1>Refugios</h1>
    </div>
    <div>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/home">Home</a></li>
          <li class="breadcrumb-item active" aria-current="page">Refugios</li>
        </ol>
      </nav>
    </div>
  </div>
</div>

<div id="contenido-home">
  <c:if test="${not empty cuidados}">
    <div class="cards-container">
      <c:forEach  var="cuidado" items="${cuidados}">
        <div class="card">
          <img src="../img/${cuidado.imagen}" class="card-img" alt="Refugio....">
          <div class="card-body">
            <h5 class="card-title">${cuidado.nombre}</h5>
            <h6>Email:</h6>
            <p class="card-text">${cuidado.email}</p>
            <h6>Direccion:</h6>
            <p class="card-text">${cuidado.direccion}</p>
            <h6>Telefono:</h6>
            <p class="card-text">${cuidado.telefono}</p>
            <div style="padding:10px">
              <a href="refugio/${cuidado.id}" class="dog-paw-button btn-detalle">Detalles</a>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:if>
</div>

<c:if test="${empty cuidados}">
  <h3><span>No hay Refugios</span></h3>
  <br>
</c:if>

<jsp:include page="foot.jsp">
  <jsp:param name="foot" value="foot" />
</jsp:include>
</body>
</html>
