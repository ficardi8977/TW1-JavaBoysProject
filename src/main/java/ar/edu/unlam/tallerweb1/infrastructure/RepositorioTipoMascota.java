package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;

import java.util.List;

public interface RepositorioTipoMascota {
    void Guardar(TipoMascota tipoMascota);

    List<TipoMascota> List();
}
