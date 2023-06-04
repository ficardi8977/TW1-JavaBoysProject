var submenuSeleccionadoGlobal = 0;

function actualizarSubmenuSeleccionado(opcion) {
    submenuSeleccionadoGlobal = opcion;
}

async function obtenerTiposMascotas() {
    try {
        const response = await fetch('/tipoMascota');
        if (!response.ok) {
            throw new Error('Error al obtener los tipos de mascotas.');
        }
        const tiposMascotas = await response.json();
        return tiposMascotas;
    } catch (error) {
        console.error(error);
        return [];
    }
}

async function mostrarTiposMascotas() {
    const tiposMascotasContainer = document.getElementById('tiposMascotasContainer');
    tiposMascotasContainer.innerHTML = 'Cargando tipos de mascotas...';

    try {
        const tiposMascotas = await obtenerTiposMascotas();
        tiposMascotasContainer.innerHTML = '';
        // creando label
        const label = document.createElement('label');
        label.textContent = "Tipo de Mascota";
        tiposMascotasContainer.appendChild(label);
        // creando select
        const selectElement = document.createElement('select');
        selectElement.id = "tipoMascotaSelector"
        selectElement.value = "tipoMascotaSelector"
        selectElement.classList.add('form-select');
        selectElement.setAttribute('aria-label', 'Default select example');

        const optionDefault = document.createElement('option');
        optionDefault.value = 0;
        optionDefault.selected = true;
        optionDefault.textContent = "Todos";
        selectElement.appendChild(optionDefault);

        tiposMascotas.forEach(tipo => {
            const optionElement = document.createElement('option');
            optionElement.value = tipo.id;
            optionElement.textContent = tipo.nombre;
            selectElement.appendChild(optionElement);
        });
        tiposMascotasContainer.appendChild(selectElement);
    } catch (error) {
        tiposMascotasContainer.innerHTML = 'Error al cargar los tipos de mascotas.';
    }
}

$(document).on('change', '#tipoMascotaSelector', function() {
    const tipoMascotaSeleccionada = $(this).val();

    cargarHomeConFiltros(tipoMascotaSeleccionada, submenuSeleccionadoGlobal);
});

$(document).on('click', '.dropdown-item', function() {
    const submenuSeleccionado = parseInt($(this).attr('onclick').match(/\d+/)[0]);
    actualizarSubmenuSeleccionado(submenuSeleccionado);

    // Ejecutar los filtros al hacer clic en el submenu
    const tipoMascotaSeleccionada = $('#tipoMascotaSelector').val();

    cargarHomeConFiltros(tipoMascotaSeleccionada, submenuSeleccionadoGlobal);
});
function cargarHomeConFiltros(idTipoMascota, idEstado) {
    var parametros = {
        idTipoMascota: idTipoMascota,
        idEstado: idEstado,
    };
    $.ajax({
        url: "/mascotas/filtradas",
        type: "GET",
        data: parametros,
        success: function (mascotas) {
            $("#contenido-home").empty(); // borramos contenido de cards
            var contenidoHtml ='<div class="cards-container">'
            $.each(mascotas, function(index, mascota) {
                var cardHtml = `<div class="card">
                    <img src="img/${mascota.imagen}" class="card-img-top" alt="Mascota...">
                    <div class="card-body">
                      <h5 class="card-title">${mascota.nombre}</h5>
                      <form action="mascota/detalle" method="GET">
                        <input name="id" value="${mascota.id}" id="${mascota.id}" type="hidden" class="form-control"/>
                        <button class="dog-paw-button">Detalle</button>
                      </form>
                    </div>
                  </div>`;
                contenidoHtml += cardHtml;
            });
            $("#contenido-home").html(contenidoHtml + ' </div>');
        },
        error: function (error) {
            console.log(error);
        }
    });
}

mostrarTiposMascotas();