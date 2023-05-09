package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface RepositorioMascota {

    public List<Mascota> BuscarMascotasPorTipo(Long idTipoMascota);
    void Guardar(Mascota mascota);

    //void modificar(Mascota mascota);
}
