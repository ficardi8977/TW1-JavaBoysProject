package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotas;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;

import java.util.List;

public interface ServicioMascota {

    List<Mascota> ObtenerMascotasPorTipo(long idTipoMascota);
    List<Mascota> ObtenerTodasLasMascotas();
    Mascota ObtenerDetalle(long id);
    List<Mascota> obtenerMascotaPorIdUsuario(Long idUsuario);

    List<Mascota> ObtenerMascotasFiltradas(DatosMascotasFiltradas request);

    List<Mascota> obtenerMascotasCercanas(DatosMascotasFiltradas request);

    List<Mascota> obtenerMascotasPorEstados(String[] estados);


    Boolean registrarMascota(DatosMascotas datosMascotas);

    Boolean validarDatos(DatosMascotas datosMascotas);
}
