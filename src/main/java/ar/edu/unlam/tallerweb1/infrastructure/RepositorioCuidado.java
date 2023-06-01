package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cuidado.Cuidado;
import ar.edu.unlam.tallerweb1.domain.cuidado.Tipocuidado;

import java.util.List;

public interface RepositorioCuidado {

    List<Cuidado> TodosLosRefugios();

    Cuidado BuscarDetalleRefugio(long id);

    List<Cuidado> obtenerTodosLosCuidadores();

    void GuardarTipoCuidado(Tipocuidado tc);

    void Guardar(Cuidado c);

    Cuidado getDetalle(long id);
}
