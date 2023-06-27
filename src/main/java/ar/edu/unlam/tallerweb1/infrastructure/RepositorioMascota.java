package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;

import java.util.List;

public interface RepositorioMascota {

    public List<Mascota> BuscarMascotasPorTipo(Long idTipoMascota);

    public Mascota BuscarDetalle(long id);

    void Guardar(Mascota mascota);

    List<Mascota> TodasLasMascotas();

    //void modificar(Mascota mascota);
    void guardar(Mascota mascota);

    List<Mascota> buscarMascotasPorIdUsuario(Long idUsuario);

    List<Mascota> ObtenerMascotasFiltradas(DatosMascotasFiltradas request);

    public void guardarVacuna(Vacunacion vacuna);

}
