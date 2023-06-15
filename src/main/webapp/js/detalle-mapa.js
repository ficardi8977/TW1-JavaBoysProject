document.addEventListener('DOMContentLoaded', function() {
    // Obtener los datos de latitud y longitud de la mascota
  var latitud;
  var longitud;
  var nombre;
  const latitudElement = document.getElementById("latitud");
  const longitudElement = document.getElementById("longitud");
  const nombreElement = document.getElementById("nombrePin");

    latitud = latitudElement.value;
    longitud = longitudElement.value;
    nombre = nombreElement.value;

    // Crear una instancia de LatLng para la ubicación de la mascota
    var mascotaLatLng = new google.maps.LatLng(latitud, longitud);

    // Configurar opciones del mapa
    var mapOptions = {
        center: mascotaLatLng,
        zoom: 12.7
    };

    // Crear el mapa
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);

    // Crear el marcador de la mascota
    var marker = new google.maps.Marker({
        position: mascotaLatLng,
        map: map,
        title: nombre  // Título del marcador
    });

    var radius = 3000; // Radio en metros

    // Crear el círculo alrededor del marcador
    var circleOptions = {
        strokeColor: '#4a4747',
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: '#212020',
        fillOpacity: 0.35,
        map: map,
        center: mascotaLatLng,
        radius: radius
    };

    var circle = new google.maps.Circle(circleOptions);
});
