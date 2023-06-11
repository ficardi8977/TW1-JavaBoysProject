<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
      <h1>Mis Vacunas</h1>
    </div>
    <div id="contenido-home">
      <c:if test="${not empty vacunas}">
        <div class="cards-container">
          <c:forEach  var="vacuna" items="${vacunas}">
            <div class="card" style="width: 95%;">
              <p class="card-text">Nombre: ${vacuna.nombre}</p>
              <p class="card-text">Fecha: ${vacuna.fecha}</p>
            </div>
          </c:forEach>
        </div>
      </c:if>
    </div>

    <c:if test="${empty vacunas}">
      <h3><span>No se encontraron vacunas</span></h3>
      <br>
    </c:if>

    <jsp:include page="foot.jsp">
      <jsp:param name="foot" value="foot" />
    </jsp:include>
    <script src="../js/mascotas.js"></script>
    <link href="../css/mascotas.css" rel="stylesheet" >
  </body>
</html>
