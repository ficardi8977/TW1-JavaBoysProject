<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="head.jsp">
        <jsp:param name="head" value="head"/>
    </jsp:include>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
            <div class="card d-flex">
                <div class="d-flex">
                    <div class="card-body">
                        <h5 class="card-title">${cuidado.nombre}</h5>
                        <p class="card-text">Email: ${cuidado.email}</p>
                        <p class="card-text mb-2">Direccion: ${cuidado.direccion}</p>
                        <div class="d-flex align-items-center mb-2">
                            <p class="card-text m-0">Telefono: ${cuidado.telefono}</p>
                            <button class="compartir ml-3" style="width: 40px;height: 40px">
                                <a href="http://wa.me/${cuidado.telefono}" class="d-flex" style="text-decoration: none" target="_blank" title="Contactar por WhatsApp">
                                    <i class="fa fa-whatsapp" style="font-size:20px;text-align: center;color:black;"></i>
                                </a>
                            </button>
                        </div>
                        <p class="card-text">Latitud: ${cuidado.latitud}</p>
                        <p class="card-text">Longitud: ${cuidado.longitud}</p>
                        <p class="card-text">CBU: ${cuidado.cbu}</p>

                        <!-- valores para dibujar el mapa -->
                        <input type="hidden" value="${cuidado.nombre}" id="nombrePin">
                        <input type="hidden" value="${cuidado.latitud}" id="latitud">
                        <input type="hidden" value="${cuidado.longitud}" id="longitud">
                    </div>
                    <div class="share-button d-flex">
                        <div class="copy-link m-2">
                            <ul style="list-style:none;border-radius: 10px;overflow: hidden" class="p-0">
                                <li class="lista-compartir copiar">
                                    <span class="material-symbols-outlined icono-copiar-enlace mr-1 text-center">link</span>
                                    <span class="copiar-enlace">Copiar enlace</span>
                                </li>
                                <li class="lista-compartir">
                                    <div>
                                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M459.37 151.716c.325 4.548.325 9.097.325 13.645 0 138.72-105.583 298.558-298.558 298.558-59.452 0-114.68-17.219-161.137-47.106 8.447.974 16.568 1.299 25.34 1.299 49.055 0 94.213-16.568 130.274-44.832-46.132-.975-84.792-31.188-98.112-72.772 6.498.974 12.995 1.624 19.818 1.624 9.421 0 18.843-1.3 27.614-3.573-48.081-9.747-84.143-51.98-84.143-102.985v-1.299c13.969 7.797 30.214 12.67 47.431 13.319-28.264-18.843-46.781-51.005-46.781-87.391 0-19.492 5.197-37.36 14.294-52.954 51.655 63.675 129.3 105.258 216.365 109.807-1.624-7.797-2.599-15.918-2.599-24.04 0-57.828 46.782-104.934 104.934-104.934 30.213 0 57.502 12.67 76.67 33.137 23.715-4.548 46.456-13.32 66.599-25.34-7.798 24.366-24.366 44.833-46.132 57.827 21.117-2.273 41.584-8.122 60.426-16.243-14.292 20.791-32.161 39.308-52.628 54.253z"/></svg>
                                    </div>
                                    <a class="enlace-compartir-tw m-0" href='https://twitter.com/intent/tweet?text=¡Echa un vistazo al cuidador "${cuidado.nombre}"!: ' data-action="share/tweet" target="_blank">
                                        <span class="copiar-enlace">Twitter</span>
                                    </a>
                                </li>
                                <li class="lista-compartir">
                                    <i class="fa fa-whatsapp" style="font-size:20px;text-align: center;color:black;"></i>
                                    <a class="enlace-compartir-wpp m-0" href='https://web.whatsapp.com/send?text=¡Echa un vistazo al cuidador "${cuidado.nombre}"!: ' target="_blank">
                                        <span class="copiar-enlace">WhatsApp</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="card-body btn-share p-0 m-2">
                            <button class="compartir" title="Compartir"><span class="material-symbols-outlined" style="color:white">share</span></button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${empty cuidado}">
            <h3><span>No hay cuidador</span></h3>
            <br>
        </c:if>
    </div>
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
                                <div class="small d-flex justify-content-start">
                                <a href="#!" onclick="agregarSubcomentario(${comentario.id},${sessionScope.IDUSUARIO},${cuidado.id})" class="d-flex align-items-center me-3">
                                    <i class="far fa-comment-dots me-2"></i>
                                    <p class="mb-0">comentar</p>
                                </a>
                                <c:if test="${sessionScope.ROL eq 'Administrador'}">
                                    <a href="#!" onclick="borrarComentario(${comentario.id},${sessionScope.IDUSUARIO},${comentario.cuidado.id},'cuidadores')" class="d-flex align-items-center me-3">
                                        <i class="fa fa-light fa-trash me-2"></i>
                                        <p class="mb-0">eliminar</p>
                                    </a>
                                </c:if>
                                </div>
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
    <link href="../css/comentarios.css" rel="stylesheet" >
    <script src="../js/comentarios.js"></script>
    <link rel="stylesheet" href="../css/redes-sociales.css">
    <script src="../js/redes-sociales.js"></script>
    <!-- FOOTER -->
</div>
</body>
</html>