package ar.edu.unlam.tallerweb1.domain.comentarios;

import ar.edu.unlam.tallerweb1.delivery.DatosComentario;

public interface ServicioComentario {

    int guardar(DatosComentario request);

    int guardarMascotas(DatosComentario request);

    boolean eliminar(long id, long idUsuario);
}
