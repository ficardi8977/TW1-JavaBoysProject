var mapa;
const perdido = "Perdido";
const enAdopcion = "EnAdopcion";
var aceptoPermisos = "false";
    aceptoPermisos = localStorage.getItem("aceptoPermisos") != null;
var canceloPermisos = "false";
    canceloPermisos = localStorage.getItem("canceloPermisos") != null;

function solicitarUbicacion() {
    if (!aceptoPermisos){
        if(confirm("¡Encuentra mascotas cercanas! Permítenos acceder a tu ubicación.")){
            obtenerUbicacion();
            aceptoPermisos = "true";
            localStorage.setItem("aceptoPermisos", "true");
        }else{
            aceptoPermisos = "true";
            localStorage.setItem("aceptoPermisos", "true");
            localStorage.setItem("canceloPermisos", "true");
            canceloPermisos = "true";
        }

    }else{
        if (!canceloPermisos) {
            obtenerUbicacion();
        }
    }
}

function obtenerUbicacion() {
    if (navigator.geolocation) {
        var options = {
            enableHighAccuracy: true,
        };

        navigator.geolocation.getCurrentPosition(
            function (position) {
                var latitude = position.coords.latitude;
                var longitude = position.coords.longitude;
                var radio = 4.5; // Ejemplo de radio en kilómetros

                var url =
                    "/mascotas/cercanas?latitud=" +
                    latitude +
                    "&longitud=" +
                    longitude +
                    "&radio=" +
                    radio;

                // Enviar la solicitud al controlador utilizando AJAX
                enviarSolicitudAlControlador(url, latitude, longitude, radio);

            },
            function (error) {
                // Error al obtener la ubicación
            }
        );
    } else {
        // Geolocalización no soportada por el navegador
    }
}

function enviarSolicitudAlControlador(url, latitud, longitud, radio) {
    $.ajax({
        type: "GET",
        url: url,
        success: function(response) {
            var mascotasCercanas = response;

            // Crear el mapa y mostrar la ubicación
            inicializarMapa(latitud, longitud, radio);

            agregarMarcadoresSegunEstado(mascotasCercanas, perdido);

            agregarMarcadoresSegunEstado(mascotasCercanas, enAdopcion);

            // Resto del código...

            // Reactivar el botón de permitir ubicación

        },
        error: function(xhr, status, error) {
            // Manejar el error de la solicitud AJAX

            // Reactivar el botón de permitir ubicación
            btnPermitirUbicacion.disabled = false;
        }
    });
}

function inicializarMapa(latitud, longitud, radio) {
    mapa = new google.maps.Map(document.getElementById("mapa"), {
        center: { lat: latitud, lng: longitud },
        zoom: 13
    });

    // Mostrar la ubicación en el mapa
    mostrarUbicacionEnMapa(latitud, longitud, radio);
    agregarReferenciasEnMapa();
}

function agregarMarcadoresMascotasPerdidas(mascotasPerdidas) {
    var iconoPerdidas = {
        path: google.maps.SymbolPath.CIRCLE,
        fillColor: "red", // Color para mascotas perdidas
        fillOpacity: 0.8,
        strokeWeight: 2,
        scale: 10
    };

    mascotasPerdidas.forEach(function(mascota) {
        var latitudMascota = parseFloat(mascota.latitud);
        var longitudMascota = parseFloat(mascota.longitud);
        var nombre = mascota.nombre;
        var marker = new google.maps.Marker({
            position: { lat: latitudMascota, lng: longitudMascota },
            map: mapa,
            title: nombre,
            icon: Object.assign({}, iconoPerdidas) // Clonar el objeto de ícono para evitar la referencia compartida
        });

        // Agregar evento click al marcador
        google.maps.event.addListener(marker, "click", function() {
            var idMascota = mascota.id; // Obtener el ID de la mascota asociada al marcador

            // Redirigir al controlador de detalle de mascota con el ID
            window.location.href = "/mascota/detalle?id=" + idMascota;
        });
    });
}

function agregarMarcadoresMascotasAdopcion(mascotasAdopcion) {
    var iconoAdopcion = {
        path: google.maps.SymbolPath.CIRCLE,
        fillColor: "green", // Color para mascotas en adopción
        fillOpacity: 0.8,
        strokeWeight: 2,
        scale: 10
    };

    mascotasAdopcion.forEach(function(mascota) {
        var latitudMascota = parseFloat(mascota.latitud);
        var longitudMascota = parseFloat(mascota.longitud);
        var nombre = mascota.nombre;

        var marker = new google.maps.Marker({
            position: { lat: latitudMascota, lng: longitudMascota },
            map: mapa,
            title: nombre,
            icon: Object.assign({}, iconoAdopcion) // Clonar el objeto de ícono para evitar la referencia compartida
        });

        // Agregar evento click al marcador
        google.maps.event.addListener(marker, "click", function() {
            var idMascota = mascota.id; // Obtener el ID de la mascota asociada al marcador

            // Redirigir al controlador de detalle de mascota con el ID
            window.location.href = "/mascota/detalle?id=" + idMascota;
        });
    });
}

function agregarMarcadoresSegunEstado(mascotas, estado) {
    const mascotasFiltradas = mascotas.filter(mascota => mascota.estado.nombre === estado);

    switch (estado) {
        case perdido:
            agregarMarcadoresMascotasPerdidas(mascotasFiltradas);
            break;
        case enAdopcion:
            agregarMarcadoresMascotasAdopcion(mascotasFiltradas);
            break;
        default:
            // Acción predeterminada si el estado no coincide con ningún caso
            break;
    }
}

function mostrarUbicacionEnMapa(latitud, longitud, radio) {
    // Agregar un marcador en la ubicación
    var marcador = new google.maps.Marker({
        position: { lat: latitud, lng: longitud },
        map: mapa
    });

    // Agregar un círculo con el radio
    var circulo = new google.maps.Circle({
        center: { lat: latitud, lng: longitud },
        radius: radio * 1000, // Convertir el radio a metros
        strokeColor: "#0000FF",
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: "#0000FF",
        fillOpacity: 0.2,
        map: mapa
    });
}

function agregarReferenciasEnMapa() {
    var perdidoColor = "red";
    var adopcionColor = "green";

    var referenciaHTML = '<div class="referencia-item"><span class="referencia-color" style="background-color: ' + perdidoColor + ';"></span> Perdido</div>';
    referenciaHTML += '<div class="referencia-item"><span class="referencia-color" style="background-color: ' + adopcionColor + ';"></span> En adopción</div>';

    var referenciaContainer = document.createElement("div");
    referenciaContainer.innerHTML = referenciaHTML;

    referenciaContainer.classList.add("referencia-container");
    mapa.controls[google.maps.ControlPosition.LEFT_TOP].push(referenciaContainer);
}

$(document).ready(function() {
    solicitarUbicacion();
});
