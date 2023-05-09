package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;

public interface RepositorioTipoMascota {
    void Guardar(TipoMascota tipoMascota);
}
