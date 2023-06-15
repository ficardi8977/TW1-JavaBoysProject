package ar.edu.unlam.tallerweb1.domain.estado;

import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioEstadoImpl implements ServicioEstado{

    private RepositorioEstado repositorioEstado;
    @Autowired
    public ServicioEstadoImpl(RepositorioEstado repositorioEstado) {
        this.repositorioEstado = repositorioEstado;
    }

    public List<Estado> listar()
    {
        return this.repositorioEstado.list();
    }
}
