<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <jsp:include page="head.jsp">
    <jsp:param name="head" value="head" />
  </jsp:include>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <div class="d-flex">
            <div class="card-body">
                <h5 class="card-title">${refugio.nombre}</h5>
                <p class="card-text">Email: ${refugio.email}</p>
                <p class="card-text mb-2">Direccion: ${refugio.direccion}</p>
                <div class="d-flex align-items-center mb-2">
                    <p class="card-text m-0">Telefono: ${refugio.telefono}</p>
                    <button class="compartir ml-3" style="width: 40px;height: 40px">
                        <a href="http://wa.me/${refugio.telefono}" class="d-flex" style="text-decoration: none" target="_blank" title="Contactar por WhatsApp">
                            <i class="fa fa-whatsapp" style="font-size:20px;text-align: center;color:black;"></i>
                        </a>
                    </button>
                </div>
                <p class="card-text">CBU: ${refugio.cbu}</p>
                <input type="hidden" value="${refugio.latitud}" id="refugioLatitud">
                <input type="hidden" value="${refugio.longitud}" id="refugioLongitud">
                <input type="hidden" value="${refugio.nombre}" id="nombreRefugio">
            </div>
            <div class="share-button d-flex">
                <div class="copy-link m-2">
                    <ul style="list-style:none;border-radius: 10px;overflow: hidden" class="p-0">
                        <li class="lista-compartir copiar">
                            <span class="material-symbols-outlined icono-copiar-enlace mr-1 text-center">link</span>
                            <span class="copiar-enlace">Copiar enlace</span>
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
</div>

<c:if test="${empty refugio}">
  <h3><span>No hay Refugio</span></h3>
  <br>
</c:if>

<section id="comentarios">
    <div class="container my-5 py-5">
        <h1>Comentarios</h1>
        <div class="row d-flex justify-content-center">
            <div class="col-md-12 col-lg-10 col-xl-8">
                <div class="card">
                    <c:if test="${empty refugio.comentarios}">
                        <h3><span>No hay comentarios</span></h3>
                        <br>
                    </c:if>
                    <c:forEach  var="comentario" items="${refugio.comentarios}">
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
                            <!--  <div class="small d-flex justify-content-start">
                                <a href="#!" class="d-flex align-items-center me-3">
                                    <i class="far fa-thumbs-up me-2"></i>
                                    <p class="mb-0">Like</p>
                                </a>
                                <a href="#!" class="d-flex align-items-center me-3">
                                    <i class="far fa-comment-dots me-2"></i>
                                    <p class="mb-0">Comment</p>
                                </a>
                                <a href="#!" class="d-flex align-items-center me-3">
                                    <i class="fas fa-share me-2"></i>
                                    <p class="mb-0">Share</p>
                                </a>
                            </div>-->
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
                            <form method="post" action="/comentario/refugio">
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
                                            <input type="hidden" name="idCuidado" value="${refugio.id}">
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

<jsp:include page="foot.jsp">
  <jsp:param name="foot" value="foot" />
</jsp:include>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4"></script>
<script src="../js/detalle-refugio.js"></script>
<link href="../css/comentarios.css" rel="stylesheet" >
<script src="../js/redes-sociales.js"></script>
<link rel="stylesheet" href="../css/redes-sociales.css">
</body>
</html>