package ar.edu.unlam.tallerweb1.domain.cuidado;

import ar.edu.unlam.tallerweb1.domain.mascotas.Mascota;

import java.util.List;

public interface ServicioCuidado {
    List<Cuidado> ObtenerTodosLosRefugios();

    Cuidado ObtenerDetalleRefugio(long id);
}
