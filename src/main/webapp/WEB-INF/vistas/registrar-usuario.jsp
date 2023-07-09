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
<body style="background-color: #E6B9A0;">

  <jsp:include page="nav.jsp">
    <jsp:param name="nav" value="nav" />
  </jsp:include>

  <div class="container p-4 m-4 w-80 rounded-4 mx-auto" style="background-color: white;">

    <form action="/registrar-usuario" method="post" enctype="multipart/form-data">

      <div class="d-flex align-items-center mb-3 pb-1">
        <img class="img-fluid img-thumbnail border-0 rounded mx-auto d-block" style="width: 20%; height: auto;" src="img/AMIPETS2.PNG" alt="AmiPets">
      </div>

      <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Registrar Usuario</h5>

      <div class="h6">

        <div class="row">
          <div class="col form-outline mb-4">
            <label class="form-label" for="nombre">Nombre</label>
            <input type="text" name="nombre" id="nombre" class="form-control form-control-lg" required/>
          </div>

          <div class="col form-outline mb-4">
            <label class="form-label" for="apellido">Apellido</label>
            <input type="text" name="apellido" id="apellido" class="form-control form-control-lg" required/>
          </div>
        </div>

        <div class="row">
          <div class="col form-outline mb-4">
            <label class="form-label" for="email">Email</label>
            <input type="email" name="email" id="email" class="form-control form-control-lg" required/>
          </div>

          <div class="col form-outline mb-4">
            <label class="form-label" for="password">Contraseña</label>
            <input type="password" name="password" id="password" class="form-control form-control-lg" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$" title="Formato de contraseña: al menos una mayúscula, al menos una minúscula, al menos un digito y que sea de entre 4 y 8 caracteres de largo" required/>
          </div>
        </div>

      </div>

      <div class="h6">
        <label for="img">Imagen</label>
        <input type="file" id="img" name="img" class="form-control form-control-lg">
      </div>

      <div class="form-outline mb-4 h5 text-center" style="margin:10px!important;display: flex; align-items: center">
        <label for="address" class="m-0 mr-2">Ubicación</label>
        <input type="text" class="form-control form-control-lg" id="address"><br>

        <input type="hidden" id="latitud" name="latitud" value="-34.6157959">
        <input type="hidden" id="longitud" name="longitud" value="-58.5158707">
      </div>

      <div id="map" style="width: 100%; height: 400px;"></div>

      <div class="form-outline my-3 h6">
        <label class="form-label" for="telefono">Telefono</label>
        <input type="tel" name="telefono" id="telefono" class="form-control form-control-lg" pattern="^\d{7,11}$" maxlength="11" title="Ingrese un número de teléfono de 7 u 11 digitos" placeholder="1112345678" required/>
      </div>

      <div>
        <c:if test="${not empty error}">
          <h5 class="text-error"><span>${error}</span></h5>
          <br>
        </c:if>
      </div>

      <div class="pt-1 mb-4">
        <button type="submit" class="btn btn-dark btn-lg btn-block">Registrarme</button>
      </div>

      <p class="mb-2 pb-lg-2" style="color: #393f81;">Ya tenes una cuenta?
        <a href="/login"style="color: #393f81;">Ingresa aqui</a>
      </p>

    </form>
  </div>

  <jsp:include page="foot.jsp">
    <jsp:param name="foot" value="foot" />
  </jsp:include>

  <script src="../js/mapa-registro.js"></script>
</body>
</html>

<!-- <script src="../js/mapa-registro.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4&callback=initMap"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4&libraries=places&callback=initAutocomplete"></script>
-->


