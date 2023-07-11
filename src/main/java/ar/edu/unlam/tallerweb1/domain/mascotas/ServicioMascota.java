package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotas;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ServicioMascota {

    List<Mascota> ObtenerMascotasPorTipo(long idTipoMascota);
    List<Mascota> ObtenerTodasLasMascotas();
    Mascota ObtenerDetalle(long id);
    List<Mascota> obtenerMascotaPorIdUsuario(Long idUsuario);

    List<Mascota> ObtenerMascotasFiltradas(DatosMascotasFiltradas request);

    List<Mascota> obtenerMascotasCercanas(DatosMascotasFiltradas request);

    List<Mascota> obtenerMascotasPorEstados(String[] estados);

    String registrarImagen(MultipartFile img) throws IOException;

    Boolean registrarMascota(DatosMascotas datosMascotas);

    Boolean validarDatos(DatosMascotas datosMascotas);

    void registrarVacuna(String nuevaVacuna, Long idMascota);

    void eliminarVacuna(Long idVacuna);
}
