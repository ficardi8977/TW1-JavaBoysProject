package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.delivery.MascotasCercanasResponse;

import java.util.List;

public interface ServicioMascota {

    List<Mascota> ObtenerMascotasPorTipo(long idTipoMascota);
    List<Mascota> ObtenerTodasLasMascotas();
    Mascota ObtenerDetalle(long id);
    List<Mascota> obtenerMascotaPorIdUsuario(Long idUsuario);

    List<Mascota> ObtenerMascotasFiltradas(DatosMascotasFiltradas request);

    MascotasCercanasResponse obtenerMascotasCercanas(DatosMascotasFiltradas request);
}
