<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="head.jsp">
    <jsp:param name="head" value="head" />
  </jsp:include>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
  <link rel="stylesheet" href="../css/mis-mascotas.css">
</head>
<body>

<jsp:include page="nav.jsp">
  <jsp:param name="nav" value="nav" />
</jsp:include>

<div class="container pt-4 pb-2 px-4 m-4 w-80 rounded-4 mx-auto" style="background-color: white">
  <form action="/alta-mascota" method="post" enctype="multipart/form-data" >
    <div class="d-flex align-items-center pb-1 logo-registrar">
      <img class="img-fluid img-thumbnail border-0 rounded mx-auto" style="width: 20%; height: auto;" src="img/AMIPETS2.PNG" alt="AmiPets">
    </div>

    <h5 class="fw-normal mb-3 pb-3 registrar-titulo">Agregar Nueva Mascota</h5>
    <div class="h6 contenedor-info">
      <div>
        <div class="form-outline mb-4">
          <label class="form-label" for="nombre">Nombre</label>
          <input type="text" name="nombre" id="nombre" class="form-control form-control-lg" placeholder="Agregar nombre..." required/>
        </div>

        <div class="form-outline mb-4">
          <label class="form-label" for="descripcion" style="display:block">Descripcion</label>
          <textarea name="descripcion" id="descripcion" style="resize: vertical; max-height: 150px;" cols="30" rows="10" placeholder="Agregar descripcion..." class="form-control form-control-lg"></textarea>
        </div>
      </div>

      <div>
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
          <select name="tipo" id="tipo" onchange="actualizarRazas()" class="form-control form-control-lg">
            <option value="def" selected>Seleccionar tipo de mascota</option>
            <option value="1">Perro</option>
            <option value="2">Gato</option>
          </select>
        </div>

        <div class="form-outline mb-4">
          <div class="d-flex">
            <label class="form-label" for="raza" style="display:block">Raza</label>
            <span style="opacity: 65%; margin-left: 5px">(Selecciona el tipo de mascota primero)</span>
          </div>
          <select name="raza" id="raza" class="form-control form-control-lg"></select>
        </div>
      </div>
    </div>

    <div class="h6 contenedor-img">
      <label for="img" class="mb-2">Imagen</label>
      <div class="div-img align-items-center mb-2">
        <img src="../img/huellita.jpg" id="profile-pic" style="height: 100px;">
        <input type="file" id="img" name="img" accept="image/jpeg, image/png, image/jpg" class="form-control form-control-lg">
      </div>
    </div>

    <input type="hidden" id="telefono" name="telefono" value="${sessionScope.TELEFONO}">
    <input type="hidden" id="nombreUsuario" name="nombreUsuario" value="${sessionScope.NOMBRE}">

    <div class="form-outline mb-2 h5 text-center div-ubicacion">
      <label for="address">Ubicación</label>
      <input type="text" class="form-control form-control-lg" id="address" placeholder="Introduce una ubicación o haz clic en el mapa">

      <input type="hidden" id="latitud" name="latitud" value="-34.6157959">
      <input type="hidden" id="longitud" name="longitud" value="-58.5158707">
    </div>

    <div id="map" style="width: 100%; height: 400px;"></div>

    <input type="hidden" value="${sessionScope.IDUSUARIO}" id="idUsuario" name="idUsuario">

    <div>
      <c:if test="${not empty error}">
        <h5 class="text-error mt-3"><span>${error}</span></h5>
        <br>
      </c:if>
    </div>

    <div class="my-3">
      <button type="submit" class="btn btn-success btn-lg btn-block">Agregar Mascota</button>
    </div>
  </form>
</div>



<jsp:include page="foot.jsp">
  <jsp:param name="foot" value="foot" />
</jsp:include>

<link rel="stylesheet" href="../css/mascotas.css">
<script src="../js/mapa-registro.js"></script>
<script src="../js/mascotas.js"></script>
<script src="../js/registro-imagen.js"></script>
</body>
</html>
