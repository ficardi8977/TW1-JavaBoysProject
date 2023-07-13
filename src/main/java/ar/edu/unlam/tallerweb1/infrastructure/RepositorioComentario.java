package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.Comentario;

import java.util.List;

public interface RepositorioComentario {

    int guardar(Comentario request);

    Comentario obtener(long id);

    void eliminar(Comentario comentario);

    List<Comentario> obtenerPorIdCuidado(long idCuidado);

    List<Comentario> obtenerSubcomentarios(long id);
}
