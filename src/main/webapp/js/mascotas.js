var latitud = sessionStorage.getItem('ubicacion.latitud');
var longitud = sessionStorage.getItem('ubicacion.longitud');
var radio = sessionStorage.getItem('radio');
const labelPerdidoMascota = "Perdidos";
const labelAdopcionMascota = "Adopción";
const estadoPerdidoMascota = 'Perdido';
const estadoAdopcionMascota = 'EnAdopcion';
const estadoOpcion = 'estado';
const tipoOpcion = 'tipo';
var tipoSeleccion = "";
var url =
    "/mascotas/cercanas?latitud=" +
    latitud +
    "&longitud=" +
    longitud+
    "&radio=" +
    radio;

async function obtenerDatosSelect(url) {
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Error al obtener los datos del select.');
        }
        const datos = await response.json();
        return datos;
    } catch (error) {
        console.error(error);
        return [];
    }
}

function crearSelect(containerId, labelNombre, selectId, defaultOptionText, datos, tipoSeleccion) {
    const container = document.getElementById(containerId);
    container.innerHTML = `Cargando ${labelNombre}...`;

    try {
        container.innerHTML = '';
        // Crear div de columna para el título
        const titleColumnDiv = document.createElement('div');
        titleColumnDiv.classList.add('col-auto'); // Establecer el ancho automático de la columna
        container.appendChild(titleColumnDiv);
        // Crear label con estilos de Bootstrap dentro de la columna
        const label = document.createElement('label');
        label.textContent = labelNombre;
        label.classList.add('form-label', 'mb-1', 'fw-bold'); // Agregar clases de Bootstrap
        titleColumnDiv.appendChild(label);

        // Crear div de columna para el select
        const selectColumnDiv = document.createElement('div');
        selectColumnDiv.classList.add('col'); // Establecer el ancho de la columna al 100%
        container.appendChild(selectColumnDiv);
        // Crear select con estilos de Bootstrap dentro de la columna
        const selectElement = document.createElement('select');
        selectElement.id = selectId;
        selectElement.value = selectId;
        selectElement.classList.add('form-select', 'w-100'); // Agregar clase de Bootstrap para establecer el ancho al 100%
        selectElement.setAttribute('aria-label', 'Default select example');

        const optionDefault = document.createElement('option');
        optionDefault.value = 0;
        optionDefault.selected = true;
        optionDefault.textContent = defaultOptionText;
        selectElement.appendChild(optionDefault);

        datos.forEach(dato => {
            // Agregar condición para mostrar solo "Perdidos" y "Adopción" en el select de estados
            if (tipoSeleccion === 'estado' && (dato.nombre === estadoPerdidoMascota || dato.nombre === estadoAdopcionMascota)) {
                if (dato.nombre === estadoPerdidoMascota) {
                    const optionElement = document.createElement('option');
                    optionElement.value = dato.id;
                    optionElement.textContent = labelPerdidoMascota;
                    selectElement.appendChild(optionElement);
                } else {
                    const optionElement = document.createElement('option');
                    optionElement.value = dato.id;
                    optionElement.textContent = labelAdopcionMascota;
                    selectElement.appendChild(optionElement);
                }
            } else if (tipoSeleccion === 'tipo') { // Mostrar todas las opciones en el select de tipo
                const optionElement = document.createElement('option');
                optionElement.value = dato.id;
                optionElement.textContent = dato.nombre;
                selectElement.appendChild(optionElement);
            }
        });
        selectColumnDiv.appendChild(selectElement);
    } catch (error) {
        container.innerHTML = `Error al cargar ${labelNombre}.`;
    }
}


async function mostrarTiposMascotas() {
    const url = '/tipoMascota';
    const datos = await obtenerDatosSelect(url);
    tipoSeleccion = tipoOpcion;
    crearSelect('tiposMascotasContainer', 'Tipo de Mascota',
        'tipoMascotaSelector', 'Todos', datos, tipoSeleccion);
}

async function mostrarEstadosMascotas() {
    const url = '/estadoMascota';
    const datos = await obtenerDatosSelect(url);
    tipoSeleccion = estadoOpcion;
    crearSelect('estadosMascotasContainer', 'Estado de Mascota',
        'estadoMascotaSelector', 'Todos', datos, tipoSeleccion);
}

$(document).on('change', '#tipoMascotaSelector, #estadoMascotaSelector', function() {
    const tipoMascotaSeleccionado = $('#tipoMascotaSelector').val();
    const estadoMascotaSeleccionado = $('#estadoMascotaSelector').val();

    cargarHomeConFiltros(tipoMascotaSeleccionado, estadoMascotaSeleccionado);
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

function mostrarFormularioVacuna() {
    var vacunasDiv = document.getElementById('vacunas');
    if (vacunasDiv.style.display === 'block') {
        vacunasDiv.style.display = 'none';
    } else {
        vacunasDiv.style.display = 'block';
    }
}

function agregarVacuna() {
    var nombreVacunaInput = document.getElementById('nombre-vacuna');
    var nombreVacuna = nombreVacunaInput.value;

    if (nombreVacuna !== '') {
        var listaVacunas = document.getElementById('listaVacunas');
        var nuevaVacuna = document.createElement('li');
        nuevaVacuna.textContent = "- " + nombreVacuna;
        nuevaVacuna.setAttribute("value", nombreVacuna);
        nuevaVacuna.setAttribute("name", "vacuna");
        listaVacunas.appendChild(nuevaVacuna);

        nombreVacunaInput.value = '';
    }
}

mostrarTiposMascotas();
mostrarEstadosMascotas();