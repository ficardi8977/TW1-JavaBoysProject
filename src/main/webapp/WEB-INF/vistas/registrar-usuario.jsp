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
<section class="vh-100" style="background-color: #E6B9A0;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col col-xl-10">
        <div class="card" style="border-radius: 1rem;">
          <div class="row g-0">
            <div class="col-md-6 col-lg-5 d-none d-md-block">
              <img src="img/BannerLogin2.png"
                   alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
            </div>
            <div class="col-md-6 col-lg-7 d-flex align-items-center">
              <div class="card-body p-4 p-lg-5 text-black">

                <form action="/registrar-usuario" method="post">

                  <div class="d-flex align-items-center mb-3 pb-1">
                    <img class="img-fluid img-thumbnail border-0 rounded mx-auto d-block" style="width: 50%; height: auto;" src="img/AMIPETS2.PNG" alt="AmiPets">
                  </div>

                  <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Registrar Usuario</h5>

                  <div class="form-outline mb-4">
                    <input type="text" name="nombre" id="nombre" class="form-control form-control-lg" />
                    <label class="form-label" for="nombre">Nombre</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="text" name="apellido" id="apellido" class="form-control form-control-lg" />
                    <label class="form-label" for="apellido">Apellido</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="email" name="email" id="email" class="form-control form-control-lg" />
                    <label class="form-label" for="email">Email</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="password" name="password" id="password" class="form-control form-control-lg" />
                    <label class="form-label" for="password">Contraseña</label>
                  </div>

                  <div class="form-outline mb-4">
                    <label>Ubicacion</label>
                    <input type="hidden" id="latitud" name="latitud">
                    <input type="hidden" id="longitud" name="longitud">
                  </div>

                  <div id="map" style="width: 100%; height: 400px;"></div>

                  <div class="form-outline mb-4">
                    <input type="number" name="telefono" id="telefono" class="form-control form-control-lg" />
                    <label class="form-label" for="telefono">Telefono</label>
                  </div>

                  <div>
                    <c:if test="${not empty error}">
                      <h5 class="text-error"><span>${error}</span></h5>
                      <br>
                    </c:if>
                  </div>
                  <div class="pt-1 mb-4">
                    <input class="btn btn-dark btn-lg btn-block" type="submit">
                  </div>
                  <p class="mb-5 pb-lg-2" style="color: #393f81;">Ya tenes una cuenta?
                    <a href="/login"style="color: #393f81;">Ingresa aqui</a>
                  </p>
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

