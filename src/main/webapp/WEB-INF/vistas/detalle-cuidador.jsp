<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!-- CONTENT -->

    <!-- FOOTER -->
    <jsp:include page="foot.jsp">
        <jsp:param name="foot" value="foot"/>
    </jsp:include>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4"></script>
    <script src="../js/detalle-mapa.js"></script>
    <!-- FOOTER -->
</div>

</body>
</html>