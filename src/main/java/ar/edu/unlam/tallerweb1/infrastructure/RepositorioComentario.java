package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosComentario;
import ar.edu.unlam.tallerweb1.domain.comentarios.Comentario;

public interface RepositorioComentario {

    int guardar(Comentario request);
}
