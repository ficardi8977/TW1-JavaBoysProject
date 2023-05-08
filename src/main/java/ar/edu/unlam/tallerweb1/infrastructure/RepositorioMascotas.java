package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioMascotas {
    List<Mascota> mascotasPorEstado(Integer idEstado);
}
