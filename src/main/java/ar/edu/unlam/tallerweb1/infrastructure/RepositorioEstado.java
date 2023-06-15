package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.estado.Estado;
import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface RepositorioEstado {
    void guardar(Estado estado);

    List<Estado> list();
}
