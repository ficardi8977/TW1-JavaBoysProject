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

<div class="content m-3">
    <c:if test="${not empty refugio}">
    <h1 style="text-align: center;">${refugio.nombre}</h1>

    <div class="card mt-5">
        <div class="container">
         <div class="row">
             <div class="col-lg-6 col-md-6 col-sm-12 ">
                 <img src="../img/${refugio.imagen}" class="card-img-top p-0" alt="refugio....">
             </div>
                 <div id="map" class="col-lg-6 col-md-6 col-sm-12 d-flex"></div>
         </div>
        </div>
            <div class="card-body">
          <h5 class="card-title">${refugio.nombre}</h5>
          <p class="card-text">Email: ${refugio.email}</p>
          <p class="card-text">Direccion: ${refugio.direccion}</p>
          <p class="card-text">Telefono: ${refugio.telefono}</p>
          <p class="card-text">CBU: ${refugio.cbu}</p>
          <input type="hidden" value="${refugio.latitud}" id="refugioLatitud">
          <input type="hidden" value="${refugio.longitud}" id="refugioLongitud">
            <input type="hidden" value="${refugio.nombre}" id="nombreRefugio">
        </div>
      </div>
    </c:if>
</div>

<c:if test="${empty refugio}">
  <h3><span>No hay Refugio</span></h3>
  <br>
</c:if>

<jsp:include page="foot.jsp">
  <jsp:param name="foot" value="foot" />
</jsp:include>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4"></script>
<script src="../js/detalle-refugio.js"></script>
</body>
</html>