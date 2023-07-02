<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <jsp:include page="head.jsp">
    <jsp:param name="head" value="head" />
  </jsp:include>
</head>

<body>

<!-- NAV -->
<jsp:include page="nav.jsp">
  <jsp:param name="nav" value="nav" />
</jsp:include>
<!-- NAV -->
<div class="container">
  <!-- CONTENT -->
  <div class="content" style="width: 100%">
    <c:if test="${not empty mascota}">
      <h1 style="text-align: center;">${mascota.nombre}</h1>
      <div class="card" style="width: 100%;">
        <div class="h-100 d-flex">
          <img src="../img/${mascota.imagen}" class="card-img-top col-lg-6 p-0" alt="Mascota....">

          <!-- Mostrar el mapa con la ubicación de la mascota -->
          <div id="map" class="col-lg-6"></div>
        </div>

        <div class="card-body">
          <p class="card-text" id="mascotaNombre">Raza: ${mascota.tipoRaza.nombre}</p>
          <p class="card-text">Estado: ${mascota.estado.nombre}</p>
          <c:if test="${not empty mascota.fechaAdopcion}">
            <p class="card-text">Fecha de Adopcion: ${mascota.fechaAdopcion}</p>
          </c:if>
          <p class="card-text">${mascota.descripcion}</p>

          <!--Valores para dibujar el mapa -->
          <input type="hidden" value="${mascota.latitud}" id="latitud">
          <input type="hidden" value="${mascota.longitud}" id="longitud">
          <input type="hidden" value="${mascota.nombre}" id="nombrePin">
        </div>
      </div>
    </c:if>
    <c:if test="${empty mascota}">
      <h3><span>No existe mascota</span></h3>
      <br>
    </c:if>

    <section id="comentarios">
      <div class="container my-5 py-5">
        <h1>Comentarios</h1>
        <div class="row d-flex justify-content-center">
          <div class="col-md-12 col-lg-10 col-xl-8">
            <div class="card">
              <c:if test="${empty mascota.comentarios}">
                <h3><span>No hay comentarios</span></h3>
                <br>
              </c:if>
              <c:forEach  var="comentario" items="${mascota.comentarios}">
                <div class="card-body">
                  <div class="d-flex flex-start align-items-center">
                    <c:choose>
                      <c:when test="${not empty comentario.usuario.imagen}">
                        <img class="rounded-circle shadow-1-strong me-3" src="../img/${comentario.usuario.imagen}" alt="avatar" width="60"
                             height="60" />
                      </c:when>
                      <c:otherwise>
                        <img class="rounded-circle shadow-1-strong me-3" src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(19).webp" alt="avatar" width="60"
                             height="60" />
                      </c:otherwise>
                    </c:choose>
                    <div>
                      <h6 class="fw-bold text-primary mb-1">${comentario.usuario.nombre}</h6>
                      <p class="text-muted small mb-0">
                        publicado ${comentario.fecha}
                      </p>
                    </div>

                  </div>
                  <p class="mt-3 mb-4 pb-2">
                      ${comentario.mensaje}
                  </p>

                  <div class="small d-flex justify-content-start">
                    <a href="#!" onclick="agregarSubcomentario(${comentario.id},${sessionScope.IDUSUARIO},${mascota.id})" class="d-flex align-items-center me-3">
                      <i class="far fa-comment-dots me-2"></i>
                      <p class="mb-0">comentar</p>
                    </a>
                    <c:if test="${sessionScope.ROL eq 'Administrador'}">
                      <a href="#!" onclick="borrarComentario(${comentario.id},${sessionScope.IDUSUARIO},${comentario.mascota.id},'mascotas')" class="d-flex align-items-center me-3">
                        <i class="fa fa-light fa-trash me-2"></i>
                        <p class="mb-0">eliminar</p>
                      </a>
                    </c:if>
                  </div>
                </div>
              </c:forEach>
              <c:choose>
                <c:when test="${empty sessionScope.IDUSUARIO}">
                  <div class="container">
                    <div class="row justify-content-center">
                      <div class="col-auto m-3">
                        <h5 class="d-inline">Para comentar debe tener una cuenta</h5>
                        <a class="btn btn-primary btn-sm d-inline" href="/login">Ingresar</a>
                      </div>
                    </div>
                  </div>
                </c:when>
                <c:otherwise>
                  <form method="post" action="/comentario/mascota">
                    <div class="card-footer py-3 border-0" style="background-color: #f8f9fa;">
                      <div class="d-flex flex-start w-100">
                        <img class="rounded-circle shadow-1-strong me-3"
                             src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(19).webp" alt="avatar" width="40"
                             height="40" />
                        <div class="form-outline w-100">
                          <textarea class="form-control" id="mensaje" name="mensaje" value = "mensaje" rows="4" style="background: #fff;"></textarea>
                          <input type="hidden" name="idUsuario" value="${sessionScope.IDUSUARIO}">
                          <input type="hidden" name="idMascota" value="${mascota.id}">
                          <label class="form-label" for="mensaje">Mensaje</label>
                          <div class="float-end mt-2 pt-1">
                            <input type=submit class="btn btn-primary btn-sm">
                          </div>
                        </div>
                      </div>
                    </div>
                  </form>
                </c:otherwise>
              </c:choose>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- CONTENT -->

    <!-- FOOTER -->
    <jsp:include page="foot.jsp">
      <jsp:param name="foot" value="foot" />
    </jsp:include>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4"></script>
    <script src="../js/detalle-mapa.js"></script>
    <link href="../css/comentarios.css" rel="stylesheet">
    <script src="../js/comentarios.js"></script>
    <!-- FOOTER -->
  </div>
</div>
</body>
</html>