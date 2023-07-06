<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="head" value="head"/>
    </jsp:include>
</head>
<body>
    <!-- NAV -->
    <jsp:include page="nav.jsp">
        <jsp:param name="nav" value="nav"/>
    </jsp:include>
    <!-- NAV -->
    <div class="container">
    <!-- CONTENT -->
    <div class="Content" style="width: 100%">
        <c:if test="${not empty cuidado}">
        <h1>Detalles del Cuidador</h1>

            <div class="card" style="width: 100%;">
                <div class="h-100 d-flex">
                    <img src="../img/${cuidado.imagen}" class="card-img-top col-lg-6 p-0" alt="Cuidador...">

                    <!-- Mostrar el mapa con la ubicación de la mascota -->
                    <div id="map" class="col-lg-6"></div>
                </div>
            </div>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${cuidado.nombre}</h5>
                        <p class="card-text">Email: ${cuidado.email}</p>
                        <p class="card-text">Direccion: ${cuidado.direccion}</p>
                        <p class="card-text">Telefono: ${cuidado.telefono}</p>
                        <p class="card-text">Latitud: ${cuidado.latitud}</p>
                        <p class="card-text">Longitud: ${cuidado.longitud}</p>
                        <p class="card-text">CBU: ${cuidado.cbu}</p>

                        <!-- valores para dibujar el mapa -->
                        <input type="hidden" value="${cuidado.nombre}" id="nombrePin">
                        <input type="hidden" value="${cuidado.latitud}" id="latitud">
                        <input type="hidden" value="${cuidado.longitud}" id="longitud">
                    </div>
                </div>
        </c:if>

        <c:if test="${empty cuidado}">
            <h3><span>No hay cuidador</span></h3>
            <br>
        </c:if>
    </div>

        <!-- mercado pago-->
        <div id="wallet_container"></div>


        <section id="comentarios">
            <div class="container my-5 py-5">
                <h1>Comentarios</h1>
                <div class="row d-flex justify-content-center">
                    <div class="col-md-12 col-lg-10 col-xl-8">
                        <div class="card">
                            <c:if test="${empty cuidado.comentarios}">
                                <h3><span>No hay comentarios</span></h3>
                                <br>
                            </c:if>
                            <c:forEach  var="comentario" items="${cuidado.comentarios}">
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
                                <label class="puntaje">Puntaje indicado :  &#9733;${comentario.clasificacion} </label>
                                <p class="mt-3 mb-4 pb-2">
                                    ${comentario.mensaje}
                                </p>

                                <a href="#!" onclick="agregarSubcomentario(${comentario.id},${sessionScope.IDUSUARIO},${cuidado.id})" class="d-flex align-items-center me-3">
                                    <i class="far fa-comment-dots me-2"></i>
                                    <p class="mb-0">comentar</p>
                                </a>
                                <div id="contenedorSubcomentarios${comentario.id}"></div>
                            </div>
                            </c:forEach>
                            <c:choose>
                            <c:when test="${empty sessionScope.IDUSUARIO}">
                                <div class="container">
                                    <div class="row justify-content-center">
                                        <div class="col-auto">
                                            <h5 class="d-inline mr-2">Para comentar debe tener una cuenta</h5>
                                            <a class="btn btn-primary btn-sm d-inline" href="/login">Ingresar</a>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                            <form method="post" action="/comentario/cuidador">
                            <div class="card-footer py-3 border-0" style="background-color: #f8f9fa;">
                                <div class="d-flex flex-start w-100">
                                    <img class="rounded-circle shadow-1-strong me-3"
                                         src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(19).webp" alt="avatar" width="40"
                                         height="40" />
                                    <div class="form-outline w-100">
                                        <div>
                                            <p id="calificacion" class="clasificacion">
                                                <input id="radio1" type="radio" name="clasificacion" value="5">
                                                <label for="radio1">&#9733;</label>

                                                <input id="radio2" type="radio" name="clasificacion" value="4">
                                                <label for="radio2">&#9733;</label>

                                                <input id="radio3" type="radio" name="clasificacion" value="3">
                                                <label for="radio3">&#9733;</label>

                                                <input id="radio4" type="radio" name="clasificacion" value="2">
                                                <label for="radio4">&#9733;</label>

                                                <label for="radio5">&#9733;</label>
                                                <input id="radio5" type="radio" name="clasificacion" value="1">
                                            </p>
                                            <label class="form-label" for="calificacion">Calificación</label>

                                        </div>
                                        <textarea class="form-control" id="mensaje" name="mensaje" value = "mensaje" rows="4" style="background: #fff;"></textarea>
                                        <input type="hidden" name="idUsuario" value="${sessionScope.IDUSUARIO}">
                                        <input type="hidden" name="idCuidado" value="${cuidado.id}">
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
        <jsp:param name="foot" value="foot"/>
    </jsp:include>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4"></script>
         <script src="../js/detalle-mapa.js"></script>
        <script src="../js/mercado-pago.js"></script>
        // SDK MercadoPago.js
        <script src="https://sdk.mercadopago.com/js/v2"></script>
        <link href="../css/comentarios.css" rel="stylesheet" >
        <script src="../js/comentarios.js"></script>
         <!-- FOOTER -->
</div>

</body>
</html>