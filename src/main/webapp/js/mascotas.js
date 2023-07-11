var submenuSeleccionadoGlobal = 0;

var latitud = sessionStorage.getItem('ubicacion.latitud');
var longitud = sessionStorage.getItem('ubicacion.longitud');
var radio = sessionStorage.getItem('radio');

var url =
    "/mascotas/cercanas?latitud=" +
    latitud +
    "&longitud=" +
    longitud+
    "&radio=" +
    radio;
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
            enviarSolicitudAlControlador(url, latitud, longitud, radio);

        },

        error: function (error) {
            console.log(error);
        }
    });
}

function actualizarRazas() {
    var tipoMascota = document.getElementById("tipo").value;
    var razasMascota = document.getElementById("raza");

    // Eliminar las opciones de razas actuales
    razasMascota.innerHTML = "";

    // Agregar las nuevas opciones de razas según el tipo de mascota seleccionado
    if (tipoMascota === "1") {
        var razasPerro = [
            { name: "Selecciona la raza de tu perro", value: "0" },
            { name: "Labrador", value: "Labrador" },
            { name: "Chihuahua", value: "Chihuahua" },
            { name: "Mestizo", value: "Mestizo" }
        ]; // Ejemplo de razas de perro
        for (var i = 0; i < razasPerro.length; i++) {
            var option = document.createElement("option");
            option.text = razasPerro[i].name;
            option.value = razasPerro[i].value;
            razasMascota.add(option);
        }
    } else if (tipoMascota === "2") {
        var razasGato = [
            { name: "Selecciona la raza de tu gato", value: "0" },
            { name: "Persa", value: "Persa" },
            { name: "Siames", value: "Siames" },
            { name: "Sphynx", value: "Sphynx" }
        ]; // Ejemplo de razas de gato
        for (var j = 0; j < razasGato.length; j++) {
            var option = document.createElement("option");
            option.text = razasGato[j].name;
            option.value = razasGato[j].value;
            razasMascota.add(option);
        }
    }
    // Agrega más condiciones para otros tipos de mascotas si es necesario
}

// Obtén una lista de todos los botones y formularios
const botonesAgregar = document.querySelectorAll('.agregarBtn');
const formularios = document.querySelectorAll('.formVacuna');

// Agrega eventos de clic a todos los botones "Agregar vacuna"
botonesAgregar.forEach((boton, index) => {
    boton.addEventListener('click', function() {
        // Verifica el estilo actual del formulario asociado al botón clicado
        const estiloActual = formularios[index].style.display;
        if (estiloActual === 'none') {
            // Si está oculto, cambia a 'display: block' para mostrarlo
            formularios[index].style.display = 'block';
        } else {
            // Si está visible, cambia a 'display: none' para ocultarlo
            formularios[index].style.display = 'none';
        }
    });
});

mostrarTiposMascotas();