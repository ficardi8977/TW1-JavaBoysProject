$(document).ready(function() {
    $.ajax({
        url: '/estadoMascota',
        type: 'GET',
        success: function(data) {
            var estadoSubmenu = $('#estadoSubmenu');

            // Agregar la opción "Todas las mascotas" como la primera opción
            var todasMascotasOpcion = $('<a></a>').addClass('dropdown-item').attr('href', '/mascotas').text('Todas las mascotas');
            estadoSubmenu.append(todasMascotasOpcion);

            $.each(data, function(index, estado) {
                switch (estado.nombre) {
                    case 'Perdido':
                        var opcion = $('<a></a>').addClass('dropdown-item').text("Perdidos");
                        opcion.attr('onclick', 'submenuSeleccionado(' + estado.id + ')');
                        estadoSubmenu.append(opcion);
                        break;
                    case 'EnAdopcion':
                        var opcion = $('<a></a>').addClass('dropdown-item').text("Adopción");
                        opcion.attr('onclick', 'submenuSeleccionado(' + estado.id + ')');
                        estadoSubmenu.append(opcion);
                        break;
                    default:
                        // No hacer nada para otros nombres de estado
                        break;
                }
            });
        }
    });
});
