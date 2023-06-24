package ar.edu.unlam.tallerweb1.domain.mascotas;

import ar.edu.unlam.tallerweb1.delivery.DatosMascotas;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.vacunas.Vacunacion;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascotaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Mascota> ObtenerTodasLasMascotas() {
        return repositorioMascota.TodasLasMascotas();
    }


    @Override
    public Mascota ObtenerDetalle(long id) {
        return repositorioMascota.BuscarDetalle(id);
    }
    public List<Mascota> obtenerMascotaPorIdUsuario(Long idUsuario) {
        return this.repositorioMascota.buscarMascotasPorIdUsuario(idUsuario);
    }

    @Override
    public List<Mascota> ObtenerMascotasFiltradas(DatosMascotasFiltradas request) {
        return this.repositorioMascota.ObtenerMascotasFiltradas(request);
    }

    @Override
    public Boolean registrarMascota(DatosMascotas datosMascotas) {
        return this.repositorioMascota.registrarMascota(datosMascotas);
    }

}
