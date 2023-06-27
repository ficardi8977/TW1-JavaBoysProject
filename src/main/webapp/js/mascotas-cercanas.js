var ubicacionModal = document.getElementById("ubicacionModal");
var mapa;
var btnPermitirUbicacion = document.getElementById("btnPermitirUbicacion");

function solicitarUbicacion() {
    $(ubicacionModal).modal("dispose"); // Reinicializar el modal
    $(ubicacionModal).modal("show");
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

                $(ubicacionModal).modal("hide");
                $("body").removeClass("modal-open");
                $(".modal-backdrop").remove();
                ubicacionModal.style.display = "none";
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
            var mascotasPerdidas = response.mascotasPerdidas;
            var mascotasAdopcion = response.mascotasAdopcion;

            // Crear el mapa y mostrar la ubicación
            inicializarMapa(latitud, longitud, radio);

            // Agregar los marcadores de mascotas perdidas en el mapa con un color de pin rojo
            agregarMarcadoresMascotasPerdidas(mascotasAdopcion);

            // Agregar los marcadores de mascotas en adopción en el mapa con un color de pin verde
            agregarMarcadoresMascotasAdopcion(mascotasPerdidas);

            // Resto del código...

            // Reactivar el botón de permitir ubicación
            btnPermitirUbicacion.disabled = false;
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
    });
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

// Resto del código...


