package ar.edu.unlam.tallerweb1.domain.tipoMascota;

import ar.edu.unlam.tallerweb1.domain.mascotas.ServicioMascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioTipoMascota;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioTipoMascotaImpl implements ServicioTipoMascota {

    private RepositorioTipoMascota repositorioTipoMascota;
    public ServicioTipoMascotaImpl(RepositorioTipoMascota repositorioTipoMascota) {
        this.repositorioTipoMascota = repositorioTipoMascota;
    }

    public List<TipoMascota> Listar()
    {
        return this.repositorioTipoMascota.List();
    }
}
