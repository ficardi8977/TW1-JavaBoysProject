const diccionarioUrl = {};

diccionarioUrl["mascotas"] = "/mascota/detalle?id=";
diccionarioUrl["cuidadores"] = "/cuidador/detalle?id=";
diccionarioUrl["refugios"] = "/refugio/";

/*const idCuidado = 11;
const ROL = "Administrador";
const NOMBRE = "FER";
const IDUSUARIO = "FER";
*/
function agregarSubcomentario(idComentario, idUsuario, idCuidado) {
    var contenedorSubcomentarios = document.getElementById('contenedorSubcomentarios' + idComentario);
    contenedorSubcomentarios.appendChild(cargarFormularioComentario(idComentario, idUsuario, idCuidado));
}

function cargarFormularioComentario(idComentario, idUsuario, idCuidado){

    var formulario = document.createElement("form");
    formulario.setAttribute("method", "post");
    formulario.setAttribute("action", "/comentario/cuidador");

    var divCardFooter = document.createElement("div");
    divCardFooter.setAttribute("class", "card-footer py-3 border-0");
    divCardFooter.setAttribute("style", "background-color: #f8f9fa;");

    var divFlex = document.createElement("div");
    divFlex.setAttribute("class", "d-flex flex-start w-100");

// imagen de usuario
    var imagenAvatar = document.createElement("img");
    imagenAvatar.setAttribute("class", "rounded-circle shadow-1-strong me-3");
    imagenAvatar.setAttribute("src", "https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(19).webp");
    imagenAvatar.setAttribute("alt", "avatar");
    imagenAvatar.setAttribute("width", "40");
    imagenAvatar.setAttribute("height", "40");

    var divFormOutline = document.createElement("div");
    divFormOutline.setAttribute("class", "form-outline w-100");

// mensaje que envia en el comentario + parametros que va a enviar en el POST
    divFormOutline.innerHTML =
`<textarea class="form-control" id="mensaje" name="mensaje" value="mensaje" rows="4" style="background: #fff;"></textarea>
    <input type="hidden" name="idUsuario" value="${idUsuario}">
    <input type="hidden" name="idCuidado" value="${idCuidado}">
    <input type="hidden" name="idComentarioPadre" value="${idComentario}">
    <label class="form-label" for="mensaje">Mensaje</label>
    <div class="float-end mt-2 pt-1">
        <input type="submit" class="btn btn-primary btn-sm">
    </div>`;

    divFlex.appendChild(imagenAvatar);
    divFlex.appendChild(divFormOutline);
    divCardFooter.appendChild(divFlex);
    formulario.appendChild(divCardFooter);
    return formulario;
}
function borrarComentario(idComentario, idUsuario, idFuncionalidad, funcionalidad)
{
    let urlDeFuncionalidad = diccionarioUrl[funcionalidad];

    fetch(`/comentario/${idComentario}?idUsuario=${idUsuario}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                window.location.replace(urlDeFuncionalidad+idFuncionalidad);
            } else{
                alert("error" + response);
            }
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });
}

function obtenerComentarios(idCuidado) {
    fetch(`/comentarios/cuidado/${idCuidado}`)
        .then(response => response.json())
        .then(data => {
            cargarComentario(data);
        })
        .catch(error => {
            console.error('Error al cargar los subcomentarios:', error);
        });
}
function obtenerSubcomentarios(idComentario, sessionUsuarioId, sessionRol) {
    fetch(`/subcomentarios/comentario/${idComentario}`)
        .then(response => response.json())
        .then(data => {
            cargarSubComentarios(data,sessionUsuarioId, sessionRol, idComentario);
        })
        .catch(error => {
            console.error('Error al cargar los subcomentarios:', error);
        });
}

function cargarSubComentarios(data,sessionUsuarioId, sessionRol,idComentarioPadre){
    let html = '';

    data.forEach(comentario => {
        html += `
        <div class="card-body">
            <div class="d-flex flex-start align-items-center">
                ${comentario.imagenUsuario ? `
                    <img class="rounded-circle shadow-1-strong me-3" src="../img/${comentario.imagenUsuario}" alt="avatar" width="60" height="60" />
                ` : `
                    <img class="rounded-circle shadow-1-strong me-3" src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(19).webp" alt="avatar" width="60" height="60" />
                `}
                <div>
                    <h6 class="fw-bold text-primary mb-1">${comentario.nombreUsuario}</h6>
                    <p class="text-muted small mb-0">
                        publicado ${comentario.fecha}
                    </p>
                </div>
            </div>
            <label class="puntaje">Puntaje indicado : &#9733;${comentario.clasificacion}</label>
            <p class="mt-3 mb-4 pb-2">
                ${comentario.mensaje}
            </p>
            <div class="small d-flex justify-content-start">
                ${sessionRol === 'Administrador' ? `
                    <a href="#!" onclick="borrarComentario(${comentario.id}, ${sessionUsuarioId}, ${comentario.idCuidado}, 'cuidadores')" class="d-flex align-items-center me-3">
                        <i class="fa fa-light fa-trash me-2"></i>
                        <p class="mb-0">eliminar</p>
                    </a>
                ` : ''}
            </div>
        </div>
    `;
        var contenedorSubcomentarios = document.getElementById('contenedorSubcomentarios' + idComentarioPadre);
        contenedorSubcomentarios.innerHTML = html;
    });

}