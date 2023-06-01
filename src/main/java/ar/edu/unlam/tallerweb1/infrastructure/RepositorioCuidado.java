package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.Tipocuidado;

import java.util.List;

public interface RepositorioCuidado {

    void Guardar(Cuidado cuidado);

    List<Cuidado> TodosLosRefugios();

    Cuidado BuscarDetalleRefugio(long id);

    List<Cuidado> obtenerTodosLosCuidadores();

    void GuardarTipoCuidado(Tipocuidado tc);

    Cuidado getDetalle(long id);
}
