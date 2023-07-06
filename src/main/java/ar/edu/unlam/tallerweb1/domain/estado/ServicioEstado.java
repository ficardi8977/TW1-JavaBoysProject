package ar.edu.unlam.tallerweb1.domain.estado;

import ar.edu.unlam.tallerweb1.domain.mascotas.EstadoMascotasEnum;
import ar.edu.unlam.tallerweb1.domain.tipoMascota.TipoMascota;

import java.util.List;

public interface ServicioEstado {
    List<Estado> listar();
    Long getIdEstadoPorNombre(EstadoMascotasEnum nombre);
}
