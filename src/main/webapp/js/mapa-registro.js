// Variable global para almacenar el mapa y el marcador
let map;
let marker;

// Función para cargar la API de Google Maps
function loadGoogleMaps() {
    const script = document.createElement('script');
    script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyBIdTKseyia8vgxVs7Mmdz34MI4zUIkLY4&libraries=places&callback=initMapCallback';
    script.async = true;
    script.defer = true;
    document.head.appendChild(script);
}

// Inicializar el mapa con la ubicación del usuario
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -34.6157959, lng: -58.5158707},
        zoom: 8
    });

    marker = new google.maps.Marker({
        position: {lat: -34.6157959, lng: -58.5158707},
        map: map
    });

    // Agregar el evento de clic al mapa para agregar el marcador
    map.addListener('click', function(event) {
        if (marker) {
            marker.setMap(null);
        }
        marker = new google.maps.Marker({
            position: event.latLng,
            map: map
        });

        // Actualizar las coordenadas al hacer clic en el mapa
        updateCoordinates(event.latLng);
    });
}

// Inicializar el mapa una vez que la API de Google Maps esté cargada
function initMapCallback() {
    initMap();
    setupAutocomplete();
}

// Configurar el campo de autocompletar de direcciones
function setupAutocomplete() {
    const addressInput = document.getElementById('address');
    const autocomplete = new google.maps.places.Autocomplete(addressInput);

    // Actualizar el mapa y las coordenadas cuando se seleccione una dirección
    autocomplete.addListener('place_changed', function() {
        const place = autocomplete.getPlace();

        if (!place.geometry) {
            // Dirección no encontrada
            return;
        }

        // Centrar el mapa en la ubicación seleccionada
        map.setCenter(place.geometry.location);

        // Eliminar el marcador anterior y crear uno nuevo en la ubicación seleccionada
        if (marker) {
            marker.setMap(null);
        }
        marker = new google.maps.Marker({
            position: place.geometry.location,
            map: map
        });

        // Actualizar las coordenadas
        updateCoordinates(place.geometry.location);
    });
}

// Actualizar los valores de latitud y longitud en los input ocultos
function updateCoordinates(latLng) {
    document.getElementById('latitud').value = latLng.lat();
    document.getElementById('longitud').value = latLng.lng();
}

// Cargar la API de Google Maps al cargar la página
window.onload = loadGoogleMaps;


