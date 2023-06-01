package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;

public interface RepositorioEstado {
    void Guardar(Estado estado);
}
