package ar.edu.unlam.tallerweb1.domain.comentarios;

import ar.edu.unlam.tallerweb1.delivery.DTOComentario;
import ar.edu.unlam.tallerweb1.delivery.DatosComentario;

import java.util.List;

public interface ServicioComentario {

    int guardar(DatosComentario request);

    int guardarMascotas(DatosComentario request);

    void eliminar(long id, long idUsuario);

    /*List<DTOComentario> obtenerPorIdCuidado(long id);*/

    List<DTOComentario> obtenerSubcomentarios(long id);
}
