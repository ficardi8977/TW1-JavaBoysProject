document.addEventListener('DOMContentLoaded', function() {
    // Obtener los datos de latitud y longitud de la mascota
  var latitud;
  var longitud;
  var nombre;
  const mascotaLatitudElement = document.getElementById("mascotaLatitud");
  const mascotaLongitudElement = document.getElementById("mascotaLongitud");
  const mascotaNombreElement = document.getElementById("mascotaNombrePin");

    latitud = mascotaLatitudElement.value;
    longitud = mascotaLongitudElement.value;
    nombre = mascotaNombreElement.value;

    // Crear una instancia de LatLng para la ubicación de la mascota
    var mascotaLatLng = new google.maps.LatLng(latitud, longitud);

    // Configurar opciones del mapa
    var mapOptions = {
        center: mascotaLatLng,
        zoom: 15
    };

    // Crear el mapa
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);

    // Crear el marcador de la mascota
    var marker = new google.maps.Marker({
        position: mascotaLatLng,
        map: map,
        title: nombre  // Título del marcador
    });
});
