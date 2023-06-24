<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp">
            <jsp:param name="head" value="head" />
        </jsp:include>

    </head>
    <body>

        <jsp:include page="nav.jsp">
            <jsp:param name="nav" value="nav" />
        </jsp:include>

        <section class="vh-100" style="background-color: #E6B9A0;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-xl-10">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="row g-0">
                                <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                    <div class="card-body p-4 p-lg-5 text-black">
                                        <form action="alta-mascota" method="post" >


                                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Agregar nueva mascota</h5>

                                            <div class="form-outline mb-4">
                                                <label class="form-label" for="nombre">Nombre</label>
                                                <input type="text" name="nombre" id="nombre" class="form-control form-control-lg" />
                                            </div>

                                            <div class="form-outline mb-4">
                                                <label class="form-label" for="descripcion" style="display:block">Descripcion</label>
                                                <textarea name="descripcion" id="descripcion" style="resize: vertical; max-height: 150px;" cols="30" rows="10" placeholder="Agregar descripcion..." class="form-control form-control-lg"></textarea>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <label>Ubicacion</label>
                                                <input type="hidden" id="latitud" name="latitud">
                                                <input type="hidden" id="longitud" name="longitud">
                                            </div>

                                            <div id="map" style="width: 100%; height: 400px;"></div>

                                            <div class="form-outline mb-4">
                                                <label class="form-label" for="imagen" style="display:block">Imagen</label>
                                                <input type="file" name="imagen" id="imagen" >
                                            </div>

                                            <div class="form-outline mb-4">
                                                <label class="form-label" for="estado" style="display:block">Estado</label>
                                                <select name="estado" id="estado" class="form-control form-control-lg">
                                                    <option value="2">En adopcion</option>
                                                    <option value="3" selected>En posesion</option>
                                                    <option value="5">Perdido</option>
                                                </select>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <label class="form-label" for="tipo" style="display:block">Tipo</label>
                                                <select name="tipo" id="tipo" class="form-control form-control-lg">
                                                    <option value="1">Perro</option>
                                                    <option value="2">Gato</option>
                                                </select>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <label class="form-label" for="raza" style="display:block">Raza</label>
                                                <input type="text" name="raza" id="raza" class="form-control form-control-lg">

                                            </div>

                                            <input type="hidden" value="${sessionScope.IDUSUARIO}" id="idUsuario" name="idUsuario">

                                            <div>
                                                <c:if test="${not empty error}">
                                                    <h5 class="text-error"><span>${error}</span></h5>
                                                    <br>
                                                </c:if>
                                            </div>

                                            <div class="pt-1 mb-4">
                                                <input class="btn btn-dark btn-lg btn-block" type="submit">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="foot.jsp">
            <jsp:param name="foot" value="foot" />
        </jsp:include>

    </body>
</html>

<script src="../js/mapa-registro.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4&callback=initMap"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4"></script>



