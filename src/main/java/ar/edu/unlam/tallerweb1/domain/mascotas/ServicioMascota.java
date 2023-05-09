package ar.edu.unlam.tallerweb1.domain.mascotas;

import java.util.List;

public interface ServicioMascota {
    List<Mascota> ObtenerMascotasPorTipo(long idTipoMascota);
}
