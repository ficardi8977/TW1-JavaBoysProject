package ar.edu.unlam.tallerweb1.domain.home;

import ar.edu.unlam.tallerweb1.delivery.DTOHome;
import ar.edu.unlam.tallerweb1.delivery.DatosMascotasFiltradas;
import ar.edu.unlam.tallerweb1.domain.mascotas.EstadoMascotasEnum;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioCuidado;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioHomeImpl implements ServicioHome{
    private RepositorioMascota repositorioMascota;
    private RepositorioCuidado repositorioCuidado;

    @Autowired
    public ServicioHomeImpl(RepositorioMascota repositorioMascota, RepositorioCuidado repositorioCuidado) {
        this.repositorioMascota = repositorioMascota;
        this.repositorioCuidado = repositorioCuidado;
    }

    @Override
    public DTOHome ListCarruseles() {
        try {
            var respuestaCarruseles = new DTOHome();
            respuestaCarruseles.setCuidadores(this.repositorioCuidado.obtenerTodosLosCuidadores());
            respuestaCarruseles.setRefugios(this.repositorioCuidado.TodosLosRefugios());
            var requestMascotas = new DatosMascotasFiltradas();
            requestMascotas.setIdEstado(EstadoMascotasEnum.Perdido.ordinal());
            respuestaCarruseles.setPerdidos(this.repositorioMascota.ObtenerMascotasFiltradas(requestMascotas));
            return respuestaCarruseles;
        } catch (Exception e) {
            System.err.println("Error al obtener los datos de carruseles: " + e.getMessage());
            throw e;
        }
    }
}
