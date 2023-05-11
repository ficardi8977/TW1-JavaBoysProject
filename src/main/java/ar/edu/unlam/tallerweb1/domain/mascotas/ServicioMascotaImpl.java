package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioMascotaImpl implements ServicioMascota {
    private RepositorioMascota repositorioMascota;

    @Autowired
    public ServicioMascotaImpl(RepositorioMascota repositorioMascota){
        this.repositorioMascota = repositorioMascota;
    }

    @Override
    public List<Mascota> ObtenerMascotasPorTipo(long idTipoMascota) {
        return repositorioMascota.BuscarMascotasPorTipo(idTipoMascota);
    }
    @Override
    public Mascota ObtenerDetalle(long id) {
        return repositorioMascota.BuscarDetalle(id);
    }
    public List<Mascota> obtenerMascotaPorIdUsuario(Long idUsuario) {
        return this.repositorioMascota.buscarMascotasPorIdUsuario(idUsuario);
    }
}
