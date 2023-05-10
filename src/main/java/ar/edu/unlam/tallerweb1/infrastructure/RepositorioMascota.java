package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;

import java.util.List;

public interface RepositorioMascota {

    void guardar(Mascota mascota);

    List<Mascota> buscarMascotasPorIdUsuario(Long idUsuario);

}
