<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <jsp:include page="head.jsp">
    <jsp:param name="head" value="head"/>
  </jsp:include>
  <link rel="stylesheet" href="../css/lista-todos.css">
</head>
<body>

<jsp:include page="nav.jsp">
  <jsp:param name="nav" value="nav"/>
</jsp:include>

<div class="banners-pagina-mascotas">
  <div class="titulos-todos">
    <div>
      <h1>Mascotas</h1>
    </div>
    <div>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/home">Home</a></li>
          <li class="breadcrumb-item active" aria-current="page">Mascotas</li>
        </ol>
      </nav>
    </div>
  </div>
</div>

<div class="container-fluid">
  <div class="filtros">
    <div class="col-md-3">
      <div id="tiposMascotasContainer" class="mb-3"></div>
    </div>
    <div class="col-md-3">
      <div id="estadosMascotasContainer" class="mb-3"></div>
    </div>
  </div>
  <div id="contenido-home">
    <c:if test="${not empty mascotas}">
      <div class="cards-container cards-container">
        <c:forEach var="mascota" items="${mascotas}">

          <div class="card">
            <img src="img/${mascota.imagen}" class="card-img" alt="Mascota....">
            <div class="card-body">
              <h5 class="card-title">${mascota.nombre}</h5>
              <form:form action="mascota/detalle" method="GET">
                <input name="id" value=${mascota.id} id=${mascota.id} type="hidden" class="form-control"/>
                <button class="dog-paw-button">Detalle</button>
              </form:form>
            </div>
          </div>
        </c:forEach>

      </div>

    </c:if>

    <c:if test="${empty mascotas}">
      <h3><span>No hay Mascotas</span></h3>
      <br>
    </c:if>

  </div>
  <div id="mapa"></div>
  <jsp:include page="foot.jsp">
    <jsp:param name="foot" value="foot"/>
  </jsp:include>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4"></script>
  <script src="../js/mascotas.js"></script>
  <script src="../js/mascotas-cercanas.js"></script>

</div>
</body>
</html>
