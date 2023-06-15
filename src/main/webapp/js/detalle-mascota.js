document.addEventListener('DOMContentLoaded', function() {
    // Obtener los datos de latitud y longitud de la mascota
  var latitud;
  var longitud;
  const mascotaLatitud = document.getElementById("mascotaLatitud");
  const mascotaLongitud = document.getElementById("mascotaLongitud");

    latitud = mascotaLatitud.value;
    longitud = mascotaLongitud.value;

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
        title: '${mascota.nombre}' // Título del marcador
    });
});
