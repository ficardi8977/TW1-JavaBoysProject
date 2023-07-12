<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <jsp:include page="head.jsp">
    <jsp:param name="head" value="head" />
  </jsp:include>
  <link rel="stylesheet" href="../css/mis-mascotas.css">
</head>
<body>

<jsp:include page="nav.jsp">
  <jsp:param name="nav" value="nav" />
</jsp:include>

<div id="contenido-home">
  <div class="mis-mascotas-encabezado">
    <h1 style="text-align: center; margin:10px;">Mis Mascotas</h1>

    <h4 style="text-align: center; margin:10px;">
      <a href="/registrar-mascota" class="btn btn-lg btn-success">Agregar Mascota</a>
    </h4>
  </div>

  <div class="d-flex justify-content-center">
    <c:if test="${not empty error}">
      <h5 class="text-success m-0">${error}</h5>
      <br>
    </c:if>
  </div>

  <c:if test="${not empty mascotas}">
    <div class="cards-container" style="display:block">
      <c:forEach  var="mascota" items="${mascotas}">
        <div class="card mis-mascotas" style="width: 95%; margin: 10px auto 10px auto;">
          <div class="card-body-img">
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
          <div class="card-body vacunacion">
            <div class="d-flex align-items-center justify-content-center vacunas">
              <h2 style="text-align: center; margin-right:10px;margin-bottom: 6px;">Vacunacion</h2>
              <button class="btn-success agregarBtn" style="display: flex; height: 40px; width: 40px; border-radius: 50%; justify-content: center; align-items: center;" title="Agregar vacuna">
                <div class="d-flex">
                  <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><style>svg{fill:#ffffff}</style><path d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32V224H48c-17.7 0-32 14.3-32 32s14.3 32 32 32H192V432c0 17.7 14.3 32 32 32s32-14.3 32-32V288H400c17.7 0 32-14.3 32-32s-14.3-32-32-32H256V80z"/></svg>
                </div>
              </button>
            </div>
            <div class="formVacuna" style="display:none">
              <form action="/registrar-vacuna" method="post" class="nuevaVacunaForm d-flex m-3">
                <input type="text" class="form-control form-control-lg" name="nuevaVacuna" placeholder="Nombre de la vacuna" required>
                <input type="hidden" name="idUsuario" value="${mascota.idUsuario}">
                <input type="hidden" name="idMascota" value="${mascota.id}">
                <button type="submit" class="btn-success" style="display: flex; justify-content: center; align-items: center;">
                  <div class="d-flex">
                    <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><style>svg{fill:#ffffff}</style><path d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32V224H48c-17.7 0-32 14.3-32 32s14.3 32 32 32H192V432c0 17.7 14.3 32 32 32s32-14.3 32-32V288H400c17.7 0 32-14.3 32-32s-14.3-32-32-32H256V80z"/></svg>
                  </div>
                </button>
              </form>
            </div>
            <c:if test="${not empty mascota.vacunas}">
              <c:forEach  var="vacuna" items="${mascota.vacunas}">
                <div class="d-flex align-items-center">
                  <p class="card-text my-2"> - ${vacuna.nombre}</p>
                  <form action="/eliminar-vacuna" method="post">
                    <input type="hidden" name="idUsuario" value="${mascota.idUsuario}">
                    <input type="hidden" name="idVacuna" value="${vacuna.id}">
                    <input type="hidden" name="idMascota" value="${mascota.id}">
                    <button type="submit" style="padding:10px;color:red;background-color: transparent" title="Eliminar vacuna"><b>-</b></button>
                  </form>
                </div>

              </c:forEach>
            </c:if>
            <c:if test="${empty mascota.vacunas}">
              <p class="mt-4"><span>No se encontraron vacunas</span></p>
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
<script src="../js/mascotas.js"></script>
</body>
</html>
