document.addEventListener('DOMContentLoaded', function() {
  var latitud;
  var longitud;
  var nombre;
  const refugioLatitud = document.getElementById("refugioLatitud");
  const refugioLongitud = document.getElementById("refugioLongitud");
  const nombreRefugio = document.getElementById("nombreRefugio");

    latitud = refugioLatitud.value;
    longitud = refugioLongitud.value;
    nombre = nombreRefugio.value;

    // Crear una instancia de LatLng para la ubicación de la mascota
    var refugioLatLng = new google.maps.LatLng(latitud, longitud);

    // Configurar opciones del mapa
    var mapOptions = {
        center: refugioLatLng,
        zoom: 15
    };

    // Crear el mapa
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);

    // Crear el marcador
    var marker = new google.maps.Marker({
        position: refugioLatLng,
        map: map,
        title: nombre
    });

    var radius = 1000;

    //  círculo alrededor del marcador
    var circleOptions = {
        strokeColor: '#4a4747',
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: '#212020',
        fillOpacity: 0.35,
        map: map,
        center: refugioLatLng,
        radius: radius
    };

    var circle = new google.maps.Circle(circleOptions);
});
