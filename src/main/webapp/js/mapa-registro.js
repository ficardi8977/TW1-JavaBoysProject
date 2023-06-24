function initMap() {
    var latitudInput = document.getElementById('latitud');
    var longitudInput = document.getElementById('longitud');
    var latitud;
    var longitud;
    var marker;

    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -34.6577234, lng: -58.3877528},  // Coordenadas iniciales del mapa
        zoom: 8  // Nivel de zoom inicial
    });

    google.maps.event.addListener(map, 'click', function(event) {
        latitud = event.latLng.lat();
        longitud = event.latLng.lng();


        // Elimina el marcador anterior (si existe)
        if (marker) {
            marker.setMap(null);
        }

        // Crea un nuevo marcador en la ubicación seleccionada
        marker = new google.maps.Marker({
            position: event.latLng,
            map: map
        });

        // Actualiza los valores de latitud y longitud en los campos del formulario
        latitudInput.value = latitud;
        longitudInput.value = longitud;
    });

}